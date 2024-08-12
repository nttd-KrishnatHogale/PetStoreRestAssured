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
            dir('PetStoreRestAssuredProject/reports'){
            bat 'cd'
            }
            }
        }
    }

    post {
            always {
                script {
                    // Find the latest report (Windows PowerShell command)
                    def latestReport = bat(script: 'powershell -command "Get-ChildItem reports\\*.html | Sort-Object LastWriteTime -Descending | Select-Object -First 1 | ForEach-Object { $_.FullName }"', returnStdout: true).trim()

                    // Create a link to the latest report
                    if (latestReport) {
                        def reportName = latestReport.tokenize('\\').last()
                        def artifactPath = latestReport.replaceFirst(/^[A-Z]:\\/, '') // Remove drive letter
                        currentBuild.description = """<a href="${env.BUILD_URL}artifact/${artifactPath}">Latest Report: ${reportName}</a>"""
                    }
                }
            }
        }
}
