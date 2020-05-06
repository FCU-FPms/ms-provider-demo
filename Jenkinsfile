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
                sh "mvn -B -Dmaven.test.failure.ignore=true clean package"
            }
        }
        stage('build image') {
            steps {
                sh 'echo see WORKSPACE'
                sh 'echo ${WORKSPACE}'
                sh 'docker build --no-cache -t="franky-ms-test-docker" .'
            }
        }
        stage('remove old container if exist') {
            steps {
                sh 'echo ${WORKSPACE}'
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
