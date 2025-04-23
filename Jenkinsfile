pipeline {
    agent any

    environment {
        DOCKER_IMAGE = 'santoshlimbale76/employee-service'
    }

    tools {
        maven 'Maven 3'   // Make sure this matches your Jenkins config
        jdk 'JDK 17'
    }

    stages {
        stage('Checkout') {
            steps {
                git 'https://github.com/your-username/your-repo.git' // Replace with your repo
            }
        }

        stage('Build Application') {
            steps {
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('Docker Build') {
            steps {
                sh 'docker build -t $DOCKER_IMAGE .'
            }
        }

        stage('Docker Login & Push') {
            steps {
                withCredentials([usernamePassword(credentialsId: 'dockerhub-creds', usernameVariable: 'DOCKER_USER', passwordVariable: 'DOCKER_PASS')]) {
                    sh '''
                        echo "$DOCKER_PASS" | docker login -u "$DOCKER_USER" --password-stdin
                        docker push $DOCKER_IMAGE
                    '''
                }
            }
        }

        stage('Cleanup') {
            steps {
                sh 'docker rmi $DOCKER_IMAGE || true'
            }
        }
    }

    post {
        success {
            echo '✅ Build & Push successful!'
        }
        failure {
            echo '❌ Build failed. Please check the logs.'
        }
    }
}
