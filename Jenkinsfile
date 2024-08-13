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


                    // Navigate to the reports directory
                    bat "cd /d ${reportsDir}"

                    // Find the latest report
                    def latestReportFile = bat(script: '''
                        @echo off
                        for /f "delims=" %%i in ('dir /b /a-d /o-d "Test-Report-*.html"') do set "latest=%%~fi" & goto :done
                        :done
                        echo %latest%
                        ''', returnStdout: true).trim()
                         // Check if we have found a report
                                        if (!latestReportFile) {
                                            error "No report files found."
                                        }

                    // Define the build number variable if not already set
                    def buildNumber = env.BUILD_NUMBER ?: 'unknown'

                    // Generate the HTML file with the link to the latest report
                    def resultFile = "latest_result_${buildNumber}.html"
                    bat """
                        @echo off
                        echo ^<a href="file:///${latestReportFile.replaceAll('/', '\\\\')}"^>Latest result_${buildNumber}^</a^> > ${resultFile}
                        start ${resultFile}
                        """

                    // Optionally, you could archive the result file
                    // archiveArtifacts artifacts: resultFile
                }
            }
        }
}
