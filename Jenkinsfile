pipeline {
    agent any

    tools {
        jdk 'OracleJDK11'  // Use your predefined JDK tool
    }

    environment {
        ANDROID_HOME = '/home/vagrant/Android/Sdk'   // Ensure this is the correct path to the SDK
        PATH = "${ANDROID_HOME}/tools:${ANDROID_HOME}/platform-tools:${env.PATH}"  // Add SDK tools to PATH
    }

    stages {
        stage('Set Permissions') {
            steps {
                // Grant execute permission to the Gradle wrapper
                sh 'chmod +x ./gradlew'
            }
        }

        stage('Build') {
            steps {
                // Run Gradle build
                sh './gradlew assembleRelease'
            }
        }
    }

    post {
        success {
            echo 'Build completed successfully!'
        }
        failure {
            echo 'Build failed. Please check the logs for errors.'
        }
    }
}
