pipeline {
    agent any

    environment {
        GITHUB_REPO = 'https://github.com/nttd-KrishnatHogale/PetStoreRestAssured.git'
        REPORTS_DIR = 'PetStoreRestAssuredProject/reports'
    }

    stages {
        stage('Checkout') {
            steps {
                git url: "${env.GITHUB_REPO}", branch: 'main'
            }
        }

        stage('Build') {
            steps {
                dir('PetStoreRestAssuredProject') {
                    bat 'mvn clean test'
                }
            }
        }
    }

    post {
            always {
                script {
                    // Define the path to the reports directory
                    def reportsDir = 'C:\\Users\\Anuj\\.jenkins\\workspace\\jenknisfilecheck\\PetStoreRestAssuredProject\\reports'
                    def buildNumber = env.BUILD_NUMBER ?: 'unknown'
                    // Navigate to the reports directory
                    bat "cd /d ${reportsDir} && dir"
                    bat """@echo off
                    cd PetStoreRestAssuredProject\\reports
                    for /f "delims=" %%i in ('dir /b /a-d /o-d "Test-Report-*.html"') do (
                             set "latest=%%~fi"
                             goto :done
                         )
                         :done
                         echo ^<a href="file://%latest%"^>Latest result_${buildNumber}^</a^> >> latest_result_%BUILD_NUMBER%.html
                         start latest_result_%BUILD_NUMBER%.html"""
                    }
                }
            }
    }
