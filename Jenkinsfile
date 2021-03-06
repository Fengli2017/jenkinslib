#!groovy

@Library('jenkinslib') _

def tools = new org.devops.tools()

String workspace = "/opt/jenkins/workspace"

hello()

//Pipeline
pipeline {
    agent { node {  label "master"   //指定运行节点的标签或者名称
                    customWorkspace "${workspace}"   //指定运行工作目录（可选）
            }
    }

    options {
        timestamps()  //日志会有时间
        skipDefaultCheckout()  //删除隐式checkout scm语句
        disableConcurrentBuilds() //禁止并行
        timeout(time: 1, unit: 'HOURS')  //流水线超时设置1h
    }

    stages {
        //下载代码
        stage("GetCode"){ //阶段名称
            steps{  //步骤
                timeout(time:5, unit:"MINUTES"){   //步骤超时时间
                    script{ //填写运行代码
                        println('Getting Code!')
			tools.PrintMes("Getting Code!", 'green')
                    }
                }
            }
        }

        
	//构建
	stage("Build"){
		steps{
			timeout(time:20, unit:"MINUTES"){
				script{
					println('Packaging Application!')
					tools.PrintMes("Packaging Application!", 'green')
				}
			}
		}
	}

        
	//代码扫描
	stage("CodeScan"){
		steps{
			timeout(time:30, unit:"MINUTES"){
				script{
					print("Scanning Code!")
					tools.PrintMes("Scanning Code!", 'green')
				}
			}
		}
	}
    }

    //构建后操作
    post {
        always {
            script{
                println("always")
            }
        }

        success {
            script{
                currentBuild.description = "\n Build Success!" 
            }
        }

        failure {
            script{
                currentBuild.description = "\n Build Failed!" 
            }
        }

        aborted {
            script{
                currentBuild.description = "\n Build Canceled!" 
            }
        }
    }
}
