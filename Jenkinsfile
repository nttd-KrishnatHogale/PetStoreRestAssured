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
                    def latestFilePath

                    dir(reportsDir) {
                        latestFile = bat(
                            script: '''
                            for /f "delims=" %%a in ('dir /b /a-d /o-d /t:c') do set "latest =%%a" & goto :done
                            :done
                            echo %latest%
                            ''',returnStdout: true).trim()
                            // Create HTML file with clickable link
                            def htmlContent = """<html>
                            <head><title>Latest Report</title></head>
                            <body>
                            <p><a href="file:///${latestFile}">Latest result_${env.BUILD_NUMBER}</a></p>
                            </body>
                            </html>"""
                            // Write HTML content to file
                             writeFile file: "latest_result_${env.BUILD_NUMBER}.html", text: htmlContent

                            // Archive the HTML file
                        archiveArtifacts artifacts: "latest_result_${env.BUILD_NUMBER}.html", allowEmptyArchive: true


                        // Print the contents of the directory for debugging
                        bat "dir /b"
                    }

                    if (latestFile) {
                        // Archive the latest file
                        archiveArtifacts artifacts: "${latestFile}", allowEmptyArchive: true

                        // Output the clickable link
//                         def baseUrl = "${env.JENKINS_URL}/job/${env.JOB_NAME}/${env.BUILD_NUMBER}/artifact/${reportsDir}/${latestFile}"
                        def baseUrl = "${latest}"

                        echo "The latest generated file can be found at: ${baseUrl}"
//                         echo "${baseUrl}/${latestFile}"
                    } else {
                        echo "No files found in the reports directory."
                    }
                } catch (Exception e) {
                    echo "An error occurred: ${e.getMessage()}"
                    currentBuild.result = 'FAILURE'
                }
            }
        }
    }
}
