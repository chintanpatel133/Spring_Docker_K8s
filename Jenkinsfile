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
    }
}
