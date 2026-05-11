def unitTests() {
    stage('Unit Tests'){
        echo 'OK'
    }
}

def integrationTests() {
    stage('Integration Tests'){
        echo 'OK'
        sh 'env'
    }
}

def codeQuality() {
    stage('Code Quality') {
        withCredentials([usernamePassword(credentialsId: 'SONARQUBE', passwordVariable: 'SONAR_PASS', usernameVariable: 'SONAR_USER')]) {
            sh 'sonar-scanner -Dsonar.host.url=http://sonarqube-int.kdevops.online:9000 -Dsonar.login=${SONAR_USER} -Dsonar.password=${SONAR_PASS} -Dsonar.projectKey="${service_name}" -Dsonar.qualitygate.wait=true'
        }
    }
}

def sast() {
    stage('SAST'){
        echo 'OK'
    }
}

def sca() {
    stage('SCA'){
        echo 'OK'
    }
}

def secretDetection() {
    stage('SECRET Detection'){
        echo 'OK'
    }
}

def artifactProduce() {
    stage('Artifact Produce'){
        echo 'OK'
    }
}

