def call() {

    node('workstation') {

        common.codeCheckout()

        if (env.BRANCH_NAME == 'main') {
            common.codeQuality()

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