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
                try {
                    def reportsDir = 'PetStoreRestAssuredProject/reports'
                    def latestFile

                    dir(reportsDir) {
                        // Print the reports directory for debugging
                        echo "Reports Directory: ${reportsDir}"

                        // Find the latest report file
                        latestFile = bat(
                            script: '''
                            for /f "delims=" %%a in ('dir /b /a-d /o-d /t:c') do set "latest=%%a" & goto :done
                            :done
                            echo %latest%
                            ''',
                            returnStdout: true
                        ).trim()

                        // Print the latest file for debugging
                        echo "Latest File: ${latest}"

                        if (latestFile) {
                            // Archive the latest report file
                            archiveArtifacts artifacts: "${reportsDir}/${latestFile}", allowEmptyArchive: true

                            // Create HTML file with a clickable link to the latest report
                            def htmlContent = """<html>
                            <head><title>Latest Report</title></head>
                            <body>
                            <p><a href="${env.BUILD_URL}artifact/${reportsDir}/${latestFile}">Latest result_${env.BUILD_NUMBER}</a></p>
                            </body>
                            </html>"""
                            writeFile file: "latest_result_${env.BUILD_NUMBER}.html", text: htmlContent

                            // Archive the HTML file
                            archiveArtifacts artifacts: "latest_result_${env.BUILD_NUMBER}.html", allowEmptyArchive: true

                            // Print the clickable link to the Jenkins console
                            echo "The latest generated file can be found at: ${env.BUILD_URL}artifact/${reportsDir}/${latestFile}"
                        } else {
                            echo "No files found in the reports directory."
                        }
                    }
                } catch (Exception e) {
                    echo "An error occurred: ${e.getMessage()}"
                    currentBuild.result = 'FAILURE'
                }
            }
        }
    }
}
