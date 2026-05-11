def call() {

    node('workstation') {

        common.codeCheckout()

        // TAG BUILD
        if (env.TAG_NAME) {

            common.sast()
            common.sca()
            common.secretDetection()
            common.artifactProduce()
        }

        // MAIN BRANCH
        else if (env.BRANCH_NAME == 'main') {

            common.codeQuality()
        }

        // OTHER BRANCHES
        else {

            common.unitTests()
            common.integrationTests()
            common.codeQuality()
        }
    }
}