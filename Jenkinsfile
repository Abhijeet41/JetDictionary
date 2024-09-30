pipeline {
    agent any

    /*environment {
        JAVA_HOME = tool name: 'JDK11'  // Ensure JDK 11 is installed in Jenkins tools
        PATH = "${JAVA_HOME}/bin:${env.PATH}"
    }*/
    tools {
      jdk "OracleJDK11"
    }
    stages {
        stage('Build') {
            steps {
                // Compile the Kotlin project (assuming it's using Gradle)
                script {
                    if (fileExists('gradlew')) {
                        sh './gradlew clean build'  // Use Gradle wrapper if present
                    } else if (fileExists('build.gradle')) {
                        sh 'gradle clean build'  // Use system Gradle
                    } else if (fileExists('pom.xml')) {
                        sh 'mvn clean compile'  // Use Maven if it's a Maven project
                    } else {
                        error "No recognized build tool found!"
                    }
                }
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
