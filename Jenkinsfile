pipeline {
   agent any

   stages {

      stage('Maven installation') {
         steps {
             sh 'mvn clean test -Dbrowser=chrome -Dremote=true -DseleniumGridURL=http://hub:4444/wd/hub'
        }
      }
   }
}