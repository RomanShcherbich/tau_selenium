pipeline {
    agent any

    tools {
        maven "M3"
    }
    triggers {
        cron('0 8 * * *')
    }

    parameters {
        booleanParam(defaultValue: true, description: '', name: 'headless')
        string(defaultValue: 'chrome', description: '', name: 'browser', trim: false)
    }

    stages {
        stage('Testing') {
            steps {
                git branch: '$BRANCH', changelog: false, poll: false, url: 'https://github.com/RomanShcherbich/CircleCiDemo'
                sh "mvn test -Dmaven.test.failure.ignore=true -DSauceLabUser=%SauceLabUser% -DSauceLabLockedUser=%SauceLabLockedUser% -DSauceLabPass=%SauceLabPass% "
            }

            post {
                success {
                    junit '**/target/surefire-reports/TEST-*.xml'
                }
            }
        }

        stage('Allure-report') {
            steps {
                script {
                    allure ([
                        includeProperties: false,
                        jdk: '',
                        properties: [],
                        reportBuildPolicy: 'ALWAYS',
                        results: [[path: 'target/allure-results']]
                        ])
                }
            }
        }
    }
}
