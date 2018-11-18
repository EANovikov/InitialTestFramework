# InitialTestFramework
This is complex framework, which should include test data handling, reporting and other features

How to run tests?
mvn clean test -Dsuite=smokeTests.xml -Dbrowser=Chrome
mvn clean test -Dsuite=smokeTests.xml -Dbrowser=IE
mvn clean test -Dsuite=smokeTests.xml -Dbrowser=Chrome -Durl=https://www.google.com

If you would like to generate report, use command:
mvn allure:serve
