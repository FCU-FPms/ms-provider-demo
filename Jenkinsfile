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
                sh 'docker build -t="franky-ms-test-docker" .'
            }
        }
        stage('use image run container') {
            steps {
                sh 'docker run -d -p 50602:8080 franky-ms-test-docker'
            }
        }
    }
}