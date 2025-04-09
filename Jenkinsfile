pipeline {
    agent any
    environment {
        DOCKER_IMAGE_BASE = 'pranav1706/my-spring-app' // Update Docker image name
        K8S_NAMESPACE = 'default' // Kubernetes namespace
    }

    stages {
        stage('Checkout') {
            steps {
                git credentialsId: 'Github-cred', branch: 'main', url: 'https://github.com/Pranavmanish/springboot.git' // Update repo URL
            }
        }

        stage('Build & Package Application') {
            steps {
                script {
                    // Ensure Maven is installed on the Jenkins agent
                    sh 'mvn clean package -DskipTests'  
                }
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    def imageTag = "${DOCKER_IMAGE_BASE}:${env.BUILD_NUMBER}"
                    def latestTag = "${DOCKER_IMAGE_BASE}:latest"
                    
                    // Build the Docker image using the packaged JAR
                    sh "sudo docker build --no-cache -t ${imageTag} ."
                    sh "sudo docker tag ${imageTag} ${latestTag}"
                    
                    // Save the image tag for the next stages
                    env.DOCKER_IMAGE = imageTag
                }
            }
        }

        stage('Push Docker Image to DockerHub') {
            steps {
                script {
                    withCredentials([usernamePassword(credentialsId: 'Dockerhub-cred', usernameVariable: 'DOCKER_USERNAME', passwordVariable: 'DOCKER_PASSWORD')]) {
                        sh "sudo docker logout"
                        sh "sudo echo ${DOCKER_PASSWORD} | docker login -u ${DOCKER_USERNAME} --password-stdin"

                        // Push both versioned and latest images
                        sh "sudo docker push ${env.DOCKER_IMAGE}"
                        sh "sudo docker push ${DOCKER_IMAGE_BASE}:latest"
                    }
                }
            }
        }

        stage('Deploy to Kubernetes') {
            steps {
                script {
                    withCredentials([file(credentialsId: 'kubeconfig', variable: 'KUBECONFIG')]) {
                        sh '''
                            sudo kubectl --kubeconfig=$KUBECONFIG apply -f deployment.yaml --namespace=$K8S_NAMESPACE
                            sudo kubectl --kubeconfig=$KUBECONFIG apply -f service.yaml --namespace=$K8S_NAMESPACE
                        '''
                    }
                }
            }
        }

        stage('Update Deployment with New Docker Image') {
            steps {
                script {
                    withCredentials([file(credentialsId: 'kubeconfig', variable: 'KUBECONFIG')]) {
                        // Ensure deployment name matches the one in deployment.yaml
                        sh """
                            sudo kubectl --kubeconfig=$KUBECONFIG set image deployment/new-springboot-app new-springboot-app-container=${env.DOCKER_IMAGE} --namespace=$K8S_NAMESPACE
                            sudo kubectl --kubeconfig=$KUBECONFIG rollout status deployment/new-springboot-app --namespace=$K8S_NAMESPACE
                        """
                    }
                }
            }
        }
    }

    post {
        success {
            echo 'Deployment Successful!'
        }
        failure {
            echo 'Deployment Failed'
        }
    }
}
