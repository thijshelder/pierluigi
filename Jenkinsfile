Jenkinsfile (Declarative Pipeline)
pipeline {
    agent { docker 'maven3.3.3' }
    stages {
        stage('build') {
            steps {
                bat 'mvn --version'
            }
        }
    }
}