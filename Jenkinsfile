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
                // Change directory to where the reports are located
                def reportsDir = "${env.REPORTS_DIR}"
                def reports = findFiles(glob: "${reportsDir}/Test-Report-*.html")

                // Define a date-time formatter to parse the date-time from the filenames
                def formatter = java.time.format.DateTimeFormatter.ofPattern('yyyy.MM.dd.HH.mm.ss')

                def latestFile = null
                def latestDateTime = null

                // Iterate over the files to find the latest one
                reports.each { file ->
                    def fileName = file.name
                    def dateTimeString = fileName.replaceAll('Test-Report-', '').replaceAll('.html', '')
                    def fileDateTime = java.time.LocalDateTime.parse(dateTimeString, formatter)

                    if (latestDateTime == null || fileDateTime.isAfter(latestDateTime)) {
                        latestDateTime = fileDateTime
                        latestFile = file
                    }
                }

                if (latestFile != null) {
                    echo "The latest generated file is: ${latestFile.path}"
                    // Archive the latest file
                    archiveArtifacts artifacts: latestFile.path
                } else {
                    echo "No report files found."
                }
            }
        }
    }
}
