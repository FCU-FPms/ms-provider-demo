pipeline {
    agent any
    tools { 
        maven 'Maven 3.6.3' 
        jdk 'jdk8' 
    }
    stages {
        stage ('Initialize') {
            steps {
                sh 'echo "PATH = ${PATH}"'
                sh 'echo "M2_HOME = ${M2_HOME}"'
            }
        }
        stage('Build') {
            steps {
                sh "mvn -B -DskipTests=true clean package"
            }
        }
        stage('build image and remove old container') {
            steps {
                sh 'docker build --no-cache -t="franky-ms-test-docker" .'
                sh 'docker rm -f franky-ms-test-docker | true'
            }
        }
        stage('use image run container') {
            steps {
                sh 'docker run --name franky-ms-test-docker -d --memory 256MB -p 50609:50609 franky-ms-test-docker'
            }
        }
    }
    post { 
        always { 
            cleanWs()
        }
    }
}
