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
                script{
                    def mvnHome = tool name: "M2_HOME", type: "maven"
        	        def mvnCMD = "${mvnHome}/bin/mvn"
        	        sh "${mvnCMD} clean package"
                }
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
		withCredentials([string(credentialsId: 'DOCKER_HUB_CREDS', variable: 'DOCKER_HUB_CREDS')]) {
            		sh "docker login -u chintan671 -p ${DOCKER_HUB_CREDS}"
 			sh 'docker push chintan671/mywebapp'
                }
	    }  		    
	    post{
                success{
                    slackSend channel: '#jenkins-pipeline-demo',
                            color: 'good',
                            message: "Docker image pushed on Docker Hub successfully....",
                            tokenCredentialId: 'Jenkins-Slack'
                }
                failure{
                    slackSend channel: '#jenkins-pipeline-demo',
                            color: 'danger',
                            message: "Docker image failed to pushed on Docker Hub.",
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
