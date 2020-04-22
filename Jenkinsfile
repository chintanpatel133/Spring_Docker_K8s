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
	    post{
                success{
                    slackSend channel: '#jenkins-pipeline-demo',
                            color: 'good',
                            message: "Build is *${currentBuild.currentResult}:* *Job*: ${env.JOB_NAME} *Build Number*: ${env.BUILD_NUMBER}\n More info at: ${env.BUILD_URL}",
                            tokenCredentialId: 'Jenkins-Slack'
                }
                failure{
                    slackSend channel: '#jenkins-pipeline-demo',
                            color: 'danger',
                            message: "Build is *${currentBuild.currentResult}:* *Job*: ${env.JOB_NAME} *Build Number*: ${env.BUILD_NUMBER}\n More info at: ${env.BUILD_URL}",
                            tokenCredentialId: 'Jenkins-Slack'
                }
            }
        }
        stage('Build Docker Image') {
            steps {
		sh 'docker build -t chintan671/mywebapp .'
	        slackSend channel: '#jenkins-pipeline-demo', 
			  color: 'good',
			  message: "Docker image created successfully.",
                          tokenCredentialId: 'Jenkins-Slack'
            }
        }
        stage('Push Docker Image to DockerHub') {
            steps {
		withCredentials([string(credentialsId: 'DockerHubPwd', variable: 'Password_DockeHub')]) {
            		sh "docker login -u chintan671 -p ${Password_DockeHub}"
 			sh 'docker push chintan671/mywebapp'
                	slackSend channel: '#jenkins-pipeline-demo', 
			  color: 'good',
			  message: "Docker image pushed to DockerHub successfully.",
                          tokenCredentialId: 'Jenkins-Slack'
        	}        	
            }
        }
	stage('Deploy To Kubernetes Cluster') {
	    steps {
		sh 'kubectl apply -f deployment.yml'
		sh 'kubectl apply -f service.yml'
		slackSend channel: '#jenkins-pipeline-demo', 
			  color: 'good',
			  message: "Deployment completed successfully....",
                          tokenCredentialId: 'Jenkins-Slack'
	    }	    
	}	
    }
}
