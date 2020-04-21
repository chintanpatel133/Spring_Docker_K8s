pipeline {
    agent any
    
    stages {
        stage('Clone the Project') {
            steps {
		slackSend channel: '#jenkins-pipeline-demo', 
			  color: 'good',
			  message: "New code is available on github. Now started to clone the latest code.",
                          tokenCredentialId: 'slack-demo'
                checkout scm
            }
        }
	stage('Build the Project') {
            steps {
		sh 'mvn clean package -Dstyle.color=never'
            }
            post{
                success{
                    slackSend channel: '#jenkins-pipeline-demo',
                            color: 'good',
                            message: "Build is *${currentBuild.currentResult}:* *Job*: ${env.JOB_NAME} *Build Number*: ${env.BUILD_NUMBER}\n More info at: ${env.BUILD_URL}",
                            tokenCredentialId: 'slack-demo'
                }
                failure{
                    slackSend channel: '#jenkins-pipeline-demo',
                            color: 'danger',
                            message: "Build is *${currentBuild.currentResult}:* *Job*: ${env.JOB_NAME} *Build Number*: ${env.BUILD_NUMBER}\n More info at: ${env.BUILD_URL}",
                            tokenCredentialId: 'slack-demo'
                }
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
