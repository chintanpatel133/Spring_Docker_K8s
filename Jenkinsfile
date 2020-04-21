pipeline {
    agent any
    
    stages {
        stage('Clone the Project') {
            steps {
                checkout scm
            }
        }
	stage('Build the Project') {
            steps {
		sh 'mvn clean package -Dstyle.color=never'
            }
        }
        stage('Build Docker Image') {
            steps {
		sh 'docker build -t chintan671/mywebapp .'
            }
        }
        stage('Push Docker Image to DockerHub') {
            steps {
		withCredentials([string(credentialsId: 'DockerHubPwd', variable: 'Password_DockeHub')]) {
            		sh "docker login -u chintan671 -p ${Password_DockeHub}"
 			sh 'docker push chintan671/mywebapp'
                	echo "push the docker image successfully to Docker Hub."
        	}        	
            }
        }
    }
}
