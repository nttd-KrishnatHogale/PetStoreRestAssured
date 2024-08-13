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
                    stage('Find Latest Report') {
                        steps {
                            script {
                                dir('PetStoreRestAssuredProject/reports') {
                                    def latestFile = bat(
                                        script: '''
                                        for /f "delims=" %%a in ('dir /b /a-d /o-d /t:c') do @echo %%a & goto :done
                                        :done
                                        ''', returnStdout: true).trim()
                                    echo "The latest generated file is: ${latestFile}"
                                }
                            }
                        }
                    }
                }
            }
        }

//                     // Find the latest report file
//                     def latestFile = bat(script: """
//                         for /f "delims=" %%i in ('dir /b /a-d /o-d ${env.REPORTS_DIR}\\Test-Report-*.html') do @echo %%i
//                     """, returnStdout: true).trim()

//                     if (latestFile) {
//                         def latestFilePath = "${env.REPORTS_DIR}\\${latestFile}"
//                         echo "Latest file: ${latestFilePath}"
//
//                         def reportName = latestFile.tokenize('\\').last()
//                         def artifactPath = latestFile.replaceFirst("/^[A-Z]:\\/", '') // Adjust if needed
//                         currentBuild.description = """<a href="${env.BUILD_URL}artifact/${artifactPath}">Latest Report: ${reportName}</a>"""
//                     } else {
//                         echo "No report files found."
//                     }
