pipeline {
    agent any
    
    stages {
        stage('Clone the Project') {
            steps {
                checkout scm
                echo "clone the project successfully."
            }
        }
	stage('Build the Project') {
            steps {
		sh 'mvn clean package -Dstyle.color=never'
                echo "build the project successfully."
            }
        }
        stage('Build Docker Image') {
            steps {
		sh 'docker build -t chintan671/mywebapp .'
                echo "build the docker image successfully."
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
