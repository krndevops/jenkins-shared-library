def call() {

    node('workstation') {
        if (env.BRANCH_NAME == 'main') {
            echo 'Nothing to Do'

        }
        else if (env.BRANCH_NAME =~ '.*') {
            common.unitTests()
            common.integrationTests()
            common.codeQuality()
        }
        else if (env.TAG_NAME =~ '.*') {
            common.sast()
            common.sca()
            common.secretDetection()
            common.artifactProduce()
        }

    }

}