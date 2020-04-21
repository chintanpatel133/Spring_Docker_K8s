pipeline {
    agent any
    
    stages {
        stage('Clone the Project') {
            steps {
		slackSend channel: '#jenkins-pipeline-demo', 
			  color: 'good',
			  message: "New code is available on github. Now started to clone the latest code.",
                          tokenCredentialId: 'Jenkins-Slack'
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
