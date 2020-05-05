pipeline {
    agent any
    stages {
        stage('Build') {
            agent {
                docker {
                    image 'maven:3-alpine'
                    args '-v /root/.m2:/root/.m2' 
                }
            }
            steps {
                sh 'mvn -B -DskipTests clean package'
            }
        }
        stage('build image') {
            steps {
                sh 'docker build --no-cache -t="franky-ms-test-docker" .'
            }
        }
        stage('remove old container if exist') {
            steps {
                sh 'docker rm -f franky-ms-test-docker | true'
            }
        }
        stage('use image run container') {
            steps {
                sh 'docker run --name franky-ms-test-docker -d -P franky-ms-test-docker'
            }
        }
    }
}
