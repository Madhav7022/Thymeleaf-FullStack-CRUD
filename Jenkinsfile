pipeline {
    agent any

    environment {
        REGISTRY = "your-dockerhub-username"
        IMAGE = "spring-boot-app"
        CONTAINER_NAME = "springboot-container"
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main',
                    url: 'https://github.com/Madhav7022/Thymeleaf-FullStack-CRUD.git',
                    credentialsId: 'github-creds'
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('Docker Build') {
            steps {
                sh "docker build -t $REGISTRY/$IMAGE:${BUILD_NUMBER} ."
            }
        }

        stage('Push to Registry') {
            steps {
                withCredentials([usernamePassword(credentialsId: 'dockerhub-creds',
                                                 usernameVariable: 'USER',
                                                 passwordVariable: 'PASS')]) {
                    sh "echo $PASS | docker login -u $USER --password-stdin"
                    sh "docker push $REGISTRY/$IMAGE:${BUILD_NUMBER}"
                }
            }
        }

        stage('Deploy') {
            steps {
                sh """
                    docker pull $REGISTRY/$IMAGE:${BUILD_NUMBER}
                    docker stop $CONTAINER_NAME || true
                    docker rm $CONTAINER_NAME || true
                    docker run -d --name $CONTAINER_NAME -p 8080:8080 $REGISTRY/$IMAGE:${BUILD_NUMBER}
                """
            }
        }
    }
}
