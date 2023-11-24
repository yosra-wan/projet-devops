pipeline {
    agent any
 environment {
        SSH_USER = 'ec2-user'
        EC2_HOST = 'ec2-34-197-48-200.compute-1.amazonaws.com'
    }
    tools{
        maven 'maven-3.9.4'
        nodejs 'nodeJs'
    }

    stages {

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

        stage('build jar') {
            steps {
                script{
                    echo 'Building the Spring boot application'
                    dir('SpringBoot') {
                        sh 'mvn package'
                    }
                }
            }
        }

     stage('build image') {
            steps {
                script{
                    echo 'building the docker image'
                    withCredentials([usernamePassword(credentialsId:'docker-hub-repo', passwordVariable:'PASS',usernameVariable:'USER')]){
                        sh 'docker build -t  yosra28/springboot ./SpringBoot/'
                        sh "echo $PASS | docker login -u $USER --password-stdin "
                        sh 'docker push yosra28/springboot'

                        sh 'docker build -t  yosra28/angular ./Angular/'
                        sh 'docker push yosra28/angular'
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
        stage('Pull Docker image') {
            steps {
                sshagent(['ec2-server-key']) {
                    sh "ssh ${SSH_USER}@${EC2_HOST} docker container rm -f springboot"
                    sh "ssh ${SSH_USER}@${EC2_HOST} docker container rm -f angular"
                    sh "ssh ${SSH_USER}@${EC2_HOST} docker rmi \$(docker images -a -q) >/dev/null 2>&1 || true"
                    sh "ssh ${SSH_USER}@${EC2_HOST} docker pull  yosra28/springboot:latest"
                    sh "ssh ${SSH_USER}@${EC2_HOST} docker pull  yosra28/angular:latest"
                }
            }
        }            


        stage('Deploy') {
            steps {
                sshagent(['ec2-server-key']) {
                    sh "ssh ${SSH_USER}@${EC2_HOST} docker network create my-network >/dev/null 2>&1 || true"
                    sh "ssh ${SSH_USER}@${EC2_HOST} docker run --name springboot  --network my-network --hostname springboot -d  -p 9977:9977   yosra28/springboot:latest"
                    sh "ssh ${SSH_USER}@${EC2_HOST} docker run --name angular  --network my-network --hostname angular  -d -p 8080:8080   yosra28/angular:latest"
                }
            }
        }
    }

}
