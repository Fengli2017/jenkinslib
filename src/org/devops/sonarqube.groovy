package org.devops

//scan
def SonarScan(projectName,projectDesc,projectPath){
    
    withSonarQubeEnv("sonar"){
        
        def sonarServer = "http://localhost:9000"
        def sonarDate = bat returnStdout: true, script: """echo %DATE:~0,4%%DATE:~5,2%%DATE:~8,2%%TIME:~0,2%%TIME:~3,2%%TIME:~6,2%"""
        sonarDate = sonarDate[-16..-3]
        println("${sonarDate}")

        bat """
            //C:/DevOps/Sonar-Scanner/bin/sonar-scanner -Dsonar.host.url=${sonarServer} -Dsonar.projectKey=${projectName} -Dsonar.projectName=${projectName} -Dsonar.projectVersion=${sonarDate} -Dsonar.login=admin -Dsonar.password=123456 -Dsonar.ws.timeout=30 -Dsonar.projectDescription=${projectDesc} -Dsonar.links.homepage=http://www.baidu.com -Dsonar.sources=${projectPath} -Dsonar.sourceEncoding=UTF-8 -Dsonar.java.binaries=target/classes -Dsonar.java.test.binaries=target/test-classes -Dsonar.java.surefire.report=target/surefire-reports
            sonar-scanner -Dsonar.projectKey=${projectName} -Dsonar.projectName=${projectName} -Dsonar.projectVersion=${sonarDate} -Dsonar.ws.timeout=30 -Dsonar.projectDescription=${projectDesc} -Dsonar.links.homepage=http://www.baidu.com -Dsonar.sources=${projectPath} -Dsonar.sourceEncoding=UTF-8 -Dsonar.java.binaries=target/classes -Dsonar.java.test.binaries=target/test-classes -Dsonar.java.surefire.report=target/surefire-reports
        """
    }
}
