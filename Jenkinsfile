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
            stage('Find Latest Report') {
                        steps {
                            script {
                                def latestFile = bat(script: "for /f \"delims=\" %i in ('dir /b /a-d /o-d ${env.REPORTS_DIR}\\Test-Report-*.html') do @echo %i", returnStdout: true).trim()
                                def latestFilePath = "${env.REPORTS_DIR}\\${latestFile}"
                                echo "Latest file: ${latestFilePath}"

                                if (latestFile) {
                                    def reportName = latestFile.tokenize('\\').last()
                                    def artifactPath = latestFile.replaceFirst(/^[A-Z]:\\/, '') // Adjust if needed
                                    currentBuild.description = """<a href="${env.BUILD_URL}artifact/${artifactPath}">Latest Report: ${reportName}</a>"""
                                }
                            }
                        }
                    }
                }
}
