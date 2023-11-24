pipeline {
    agent any
    
    environment {
        SSH_USER = 'ec2-user'
        EC2_HOST = 'ec2-34-197-48-200.compute-1.amazonaws.com'
    }
    tools{
        maven 'maven-3.9.4'
        nodejs 'node-16.20.0'
    }

    stages {
           stage('Check Java Version') {
            steps {
                script {
                    sh 'java -version'
                }
            }
        }

                stage('Build carmanagement-service JAR') {
            steps {
                script {
                    echo 'Building the carmanagement-service Spring Boot application'
                    dir('car-management-service') {
                        sh 'mvn package'
                    }
                }
            }
        }

        stage('Build auth-service JAR') {
            steps {
                script {
                    echo 'Building the auth-service Spring Boot application'
                    dir('auth-service') {
                        sh 'mvn package'
                    }
                }
            }
        }

        stage('Build Angular App') {
            steps {
                script {
                    echo 'Building the Angular application'
                    dir('Angular') {
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
                    withCredentials([usernamePassword(credentialsId: 'docker-hub-repo', passwordVariable: 'DOCKER_PASSWORD', usernameVariable: 'DOCKER_USERNAME')]) {
                        sh 'docker build -t yosra28/carmanagement-service:latest ./carmanagement-service/'
                        sh 'docker login -u $DOCKER_USERNAME -p $DOCKER_PASSWORD'
                        sh 'docker push yosra28/carmanagement-service:latest'

                        sh 'docker build -t yosra28/auth-service:latest ./auth-service/'
                        sh 'docker push yosra28/auth-service:latest'
                        
                        sh 'docker build -t yosra28/angular-app:latest ./Angular/'
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
                    sh "ssh ${SSH_USER}@${EC2_HOST} docker pull yosra28/carmanagement-service:latest"
                    sh "ssh ${SSH_USER}@${EC2_HOST} docker pull yosra28/auth-service:latest"
                    sh "ssh ${SSH_USER}@${EC2_HOST} docker pull yosra28/angular-app:latest"
                }
            }
        }

        stage('Deploy Docker Containers on EC2') {
            steps {
                sshagent(['ec2-server-key']) {
                    sh "ssh ${SSH_USER}@${EC2_HOST} docker network create my-network >/dev/null 2>&1 || true"
                    
                    sh "ssh ${SSH_USER}@${EC2_HOST} docker run --name carmanagement-service --network my-network -d -p 8082:8082 yosra28/carmanagement-service:latest"
                    sh "ssh ${SSH_USER}@${EC2_HOST} docker run --name auth-service --network my-network -d -p 8081:8081 yosra28/auth-service:latest"
                    sh "ssh ${SSH_USER}@${EC2_HOST} docker run --name angular-app --network my-network -d -p 4200:4200 yosra28/angular-app:latest"
                }
            }
        }
    }

}
