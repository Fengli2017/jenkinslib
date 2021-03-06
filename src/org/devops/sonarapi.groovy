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
    result = response["branches"][0]["status"]["qualityGateStatus"]
    //println(response)
    
    return result
}

//搜索sonarqube的项目，判断项目是否存在
def SearchProject(projectName){
    apiUrl = "projects/search?projects=${projectName}"
    response = HttpReq("GET",apiUrl,'')
    
    response = readJSON text: """${response.content}"""
    result = response["paging"]["total"]
    
    if(result.toString() == "0"){
        return "false"
    } else {
        return "ture"
    }
}

//在sonarqube里创建sonar项目
def CreateProject(projectName){
    apiUrl = "projects/create?name=${projectName}&project=${projectName}"
    response = HttpReq("POST",apiUrl,'')
    println(response)
}
    
 //配置sonarqube项目质量规则
def ConfigQualityProfiles(projectName,lang,qualityProfileName){
    apiUrl = "qualityprofiles/add_project?language=${lang}&project=${projectName}&qualityProfile=${qualityProfileName}"
    response = HttpReq("POST",apiUrl,'')
    println(response)
}

//获取sonarqube质量域ID
def GetQualityGateId(gateName){
    apiUrl = "qualitygates/show?name=${gateName}"
    response = HttpReq("GET",apiUrl,'')
    
    response = readJSON text: """${response.content}"""
    result = response["id"]
    
    return result
}

 //配置sonarqube项目质量域
def ConfigQualityGates(projectName,gateName){
    gateId = GetQualityGateId(gateName)
    apiUrl = "qualitygates/select?gateId=${gateId}&projectKey=${projectName}"
    response = HttpReq("POST",apiUrl,'')
    println(response)
}
