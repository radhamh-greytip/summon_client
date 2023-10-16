podTemplate(label: 'summon-client-pipeline-01', containers: [
        containerTemplate(name: 'jnlp', image: 'greytip/jenkins-slave:0.9.0', args: '${computer.jnlpmac} ${computer.name}'),
],
volumes:[
    hostPathVolume(mountPath: '/var/run/docker.sock', hostPath: '/var/run/docker.sock'),
    persistentVolumeClaim(claimName: 'gradle-local-repo', mountPath: '/root/.gradle'),
    persistentVolumeClaim(claimName: 'jenkins-tools', mountPath: '/home/jenkins/tools'),
]) {
        properties([
            buildDiscarder(logRotator(artifactDaysToKeepStr: '', artifactNumToKeepStr: '', daysToKeepStr: '', numToKeepStr: '10')),
            parameters([
                string(defaultValue: '', description: 'Custom version number for deployment, if left empty default version will be computed by merging branch name and build number', name: 'version', trim: true),
                string(defaultValue: 'http://nexus.greytip.com/repository/greytip/', description: 'Nexus repository URL', name: 'nexusURL', trim: true),
                credentials(credentialType: 'com.cloudbees.plugins.credentials.impl.UsernamePasswordCredentialsImpl', defaultValue: 'nexus-greytip', description: 'Credentials to access nexus server', name: 'greytipNexusCredentials', required: true)
            ])
        ])

    node('summon-client-pipeline-01') {
        stage('Checkout project') {
            checkout scm
        }

        def GRADLE_HOME = tool name: 'gradle-4.5', type: 'gradle'
        def CMD_GRADLE = GRADLE_HOME + '/bin/gradle '
        println 'Gradle CMD: ' + CMD_GRADLE
        def version

        stage('Build') {
            withCredentials([[$class: 'UsernamePasswordMultiBinding', credentialsId: params.greytipNexusCredentials, usernameVariable: 'NEXUS_USER', passwordVariable: 'NEXUS_PASSWORD']]){
                version = params.version
                if( !version ){
                    version = sh(
                        script: CMD_GRADLE + '-PnexusUrl=NULL -PnexusUserName=NULL -PnexusPassword=NULL printVersion -q',
                        returnStdout: true
                    ).trim();
                }
                println "Building version: " + version

                def gradleProperties = '-PreleaseVersion=' + version + ' -PnexusUrl=' + params.nexusURL + ' -PnexusUserName=' + env.NEXUS_USER + ' -PnexusPassword=' + env.NEXUS_PASSWORD
                sh CMD_GRADLE + gradleProperties + ' publishMavenPublicationToNexusRepository'
            }
        }

		stage('Tag') {
			sshagent(credentials: [scm.userRemoteConfigs[0].credentialsId]){
				sh 'git tag t-' + version
				sh 'git push --tags'
			}
        }
    }
}