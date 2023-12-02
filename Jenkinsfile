pipeline {
    agent any
    
    environment {
        SSH_USER = 'ubuntu'
        EC2_HOST = 'ec2-18-205-106-216.compute-1.amazonaws.com'
        
        // JAVA_HOME = '/usr/lib/jvm/java-17-openjdk-amd64' // Update with the actual path to JDK 17
    }

    tools {
        // Use the tool installation names defined in Jenkins
        maven 'maven-3.9.4'
        nodejs 'node-18.16.1'
    }

    stages {
   


           stage('Test car-management-service') {
            steps {
                script {
                    echo 'Testing car-management-service'
                    dir('car-management-service') {
                        sh 'mvn test'
                    }
                }
            }
        }

        stage('Test auth-service') {
            steps {
                script {
                    echo 'Testing auth-service'
                    dir('auth-service') {
                        sh 'mvn test'
                    }
                }
            }
        }

        stage('Build car-management-service') {
            steps {
                script {
                    echo 'Building car-management-service'
                    dir('car-management-service') {
                        sh 'mvn clean package spring-boot:repackage'
                    }
                }
            }
        }

        stage('Build auth-service') {
            steps {
                script {
                    echo 'Building auth-service'
                    dir('auth-service') {
                        sh 'mvn clean package spring-boot:repackage'
                    }
                }
            }
        }
        
        stage('Build Angular App') {
            steps {
                script {
                    echo 'Building the Angular application'
                    dir('smartTaxi') {
                        sh 'npm install'
                        sh 'npm install -g @angular/cli'
                        sh 'ng build'
                    }
                }
            }
        }

        stage('Build and Push Docker Images') {
            steps {
                script {
                    echo 'Building and pushing Docker images'
                    withCredentials([usernamePassword(credentialsId: 'docker', passwordVariable: 'DOCKER_PASSWORD', usernameVariable: 'DOCKER_USERNAME')]) {
                        sh 'docker build -t yosra28/carmanagement-service:latest ./car-management-service/'
                        sh 'docker login -u $DOCKER_USERNAME -p $DOCKER_PASSWORD'
                        sh 'docker push yosra28/carmanagement-service:latest'

                        sh 'docker build -t yosra28/auth-service:latest ./auth-service/'
                        sh 'docker push yosra28/auth-service:latest'
                        
                        sh 'docker build -t yosra28/angular-app:latest ./smartTaxi/'
                        sh 'docker push yosra28/angular-app:latest'
                    }
                }
            }
        }


        stage('Connect to EC2') {
            steps {
                sshagent(['ec2-server-key']) {
                    sh "ssh -o StrictHostKeyChecking=no ${SSH_USER}@${EC2_HOST}"
                }
            }
        }


        stage('Pull Docker Images on EC2') {
            steps {
                sshagent(['ec2-server-key']) {
                    script {
                         // Stop and remove existing containers
                        sh "ssh ${SSH_USER}@${EC2_HOST} docker stop carmanagement-service || true"
                        sh "ssh ${SSH_USER}@${EC2_HOST} docker rm carmanagement-service || true"

                        sh "ssh ${SSH_USER}@${EC2_HOST} docker stop auth-service || true"
                        sh "ssh ${SSH_USER}@${EC2_HOST} docker rm auth-service || true"

                        sh "ssh ${SSH_USER}@${EC2_HOST} docker stop angular-app || true"
                        sh "ssh ${SSH_USER}@${EC2_HOST} docker rm angular-app || true"
                        
                        sh "ssh ${SSH_USER}@${EC2_HOST} docker pull yosra28/carmanagement-service:latest"
                        sh "ssh ${SSH_USER}@${EC2_HOST} docker pull yosra28/auth-service:latest"
                        sh "ssh ${SSH_USER}@${EC2_HOST} docker pull yosra28/angular-app:latest"
                    }
                }
            }
        }

        stage('Deploy Docker Containers on EC2') {
            steps {
                sshagent(['ec2-server-key']) {
                    script {
                        // Create Docker network if it doesn't exist
                        sh "ssh ${SSH_USER}@${EC2_HOST} docker network create my-network >/dev/null 2>&1 || true"

                        // Deploy new containers
                        sh "ssh ${SSH_USER}@${EC2_HOST} docker run --name carmanagement-service --network my-network -d -p 8082:8082 yosra28/carmanagement-service:latest"
                        sh "ssh ${SSH_USER}@${EC2_HOST} docker run --name auth-service --network my-network -d -p 8081:8081 yosra28/auth-service:latest"
                        sh "ssh ${SSH_USER}@${EC2_HOST} docker run --name angular-app --network my-network  -d -p 4200:4200 yosra28/angular-app:latest"
                        
                    }
                }
            }
        }


    }
    
}

