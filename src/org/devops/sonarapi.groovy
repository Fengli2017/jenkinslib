package org.devops

//封装HTTP
def HttpReq(reqType,reqUrl,reqBody){
    def sonarServer = "http://localhost:9000/api"
    result = httpRequest authentication: 'sonar-admin', 
        httpMode: reqType, 
        contentType: "APPLICATION_JSON",
        consoleLogResponseBody: true,
        ignoreSslErrors: true, 
        requestBody: reqBody,
        url: "${sonarServer}/${reqUrl}"
        //quiet: true
    return result
}

//获取Sonar质量阀状态
def GetProjectStatus(projectName){
    apiUrl = "project_branches/list?project=${projectName}"
    response = HttpReq("GET",apiUrl,'')
    
    response = readJSON text: """${response.content}"""
    result = response["branches"][0]["status"]["QualityGateStatus"]
    //println(response)
    
    return result
}
