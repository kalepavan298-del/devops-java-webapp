pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean compile'
            }
        }

        stage('Test') {
            steps {
                sh 'mvn test'
            }
        }

        stage('Package') {
            steps {
                sh 'mvn package -DskipTests'
            }
        }

        stage('Archive') {
            steps {
                archiveArtifacts artifacts: 'target/*.war', fingerprint: true
            }
        }
         stage('Deploy and Verify') {
            steps {
                script {
                    try {
                        sh 'sudo /usr/local/bin/deploy-devops-app.sh "$WORKSPACE/target/devops-java-webapp.war"'

                        retry(5) {
                            sh 'sleep 5'
                            sh 'curl -fsS http://localhost:8081/devops-java-webapp/health'
                        }

                    } catch (err) {
                        echo 'Deployment verification failed. Starting rollback...'
                        sh 'sudo /usr/local/bin/rollback-devops-app.sh'
                        throw err
                    }
                }
            }
        }
    }
}
