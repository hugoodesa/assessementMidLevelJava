pipeline{
    agent any
    tools { 
      maven 'MAVEN_DEFAULT' 
      jdk 'JDK_DEFAULT' 
    }
    stages{
        stage('Build BackEnd'){
            steps {
               sh "mvn clean package -DskipTests=true"
            }
        }
        stage("Unit Tests") {
            step {
               sh "mvn test"
            }
        }
    }
    
}
