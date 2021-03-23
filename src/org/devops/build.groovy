package org.devops

//build type

def Build(buildType,buildShell){
    def buildTools = ["mvn":"M2","ant":"ANT","gradle":"GRADLE","npm":"NPM"]
    println("Selected Build Type is ${buildType}")
    buildHome- = tool buildTools[buildType]
  
    batch """${buildHome}/bin/${buildType} &{buildShell}"""
}
