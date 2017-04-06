pipeline {
    agent any
    stages {
       stage('Checkout') {
            steps {
                git url: 'https://github.com/openaffect/openaffect-server.git'
            }
           
       }
       stage('Build') {
           steps {
                dir (path: "./docker-images/oa-java-server/") {
                    sh './build-docker-image.sh'
                }
           }
       }
       stage('Redeploy') {
           steps {
                dir (path: "./docker-topologies/runtime/") {
                    echo "current directory is: ${pwd()}"
                    sh 'docker-compose down --volumes'
                    sh 'docker-compose up -d'
                }
           }
       }
       stage('API tests') {
           steps {
               dir (path: "./docker-images/oa-server-specs/") {
                   sh './build-docker-image.sh'
                   sh './run-docker-image.sh'
               }
           }
       }   
    }
}