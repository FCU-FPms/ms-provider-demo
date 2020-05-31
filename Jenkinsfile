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
        stage('add jdbc to workspace') {
            steps {
                sh 'echo "pwd = ${pwd}"'
                sh "cp ../../franky-mysql/jdbc.properties ./src/main/resources/jdbc.properties"
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
                sh 'docker rm -f franky-ms-test-docker-$BRANCH_NAME | true'
            }
        }
        stage('use image run container') {
            steps {
                sh 'docker run --name franky-ms-test-docker-$BRANCH_NAME -d --memory 256MB --net=host franky-ms-test-docker'
            }
        }
    }

    //post {
    //    always {
    //        cleanWs()
    //   }
    //}
}
