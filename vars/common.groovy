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
//        withCredentials([usernamePassword(credentialsId: 'SONARQUBE', passwordVariable: 'SONAR_PASS', usernameVariable: 'SONAR_USER')]) {
//            sh 'sonar-scanner -Dsonar.host.url=http://sonarqube-int.kdevops.online:9000 -Dsonar.login=${SONAR_USER} -Dsonar.password=${SONAR_PASS} -Dsonar.projectKey="${service_name}" -Dsonar.qualitygate.wait=true'
//        }
        echo 'OK'
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
    stage('Secret Detection'){
        sh 'trufflehog git file://. --no-update'
    }
}

def artifactProduce() {
    stage('Artifact Produce'){
        sh 'aws ecr get-login-password --region us-east-1 | docker login --username AWS --password-stdin 367241114876.dkr.ecr.us-east-1.amazonaws.com'
        sh 'docker build -t 367241114876.dkr.ecr.us-east-1.amazonaws.com/default/${service_name}:${TAG_NAME} .'
        sh 'docker push 367241114876.dkr.ecr.us-east-1.amazonaws.com/default/${service_name}:${TAG_NAME} .'

    }
}


def codeCheckout() {
    stage('CodeCheckout') {

        sh "find ."
        sh "find . | sed -e '1d' |xargs rm -rf"
        if (env.TAG_NAME ==~ ".*") {
            env.branch_name = "refs/tags/${env.TAG_NAME}"
        } else {
            env.branch_name = "${env.BRANCH_NAME}"
        }
        checkout scmGit(
                branches: [[name: "${branch_name}"]],
                userRemoteConfigs: [[url: "https://github.com/krndevops/${service_name}"]]
        )
    }
}
