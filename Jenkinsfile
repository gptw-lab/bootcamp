pipeline {
   agent any

   stages {

      stage('Maven installation') {
         steps {
             sh 'mvn clean test'
        }
      }
   }
}