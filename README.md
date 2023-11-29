# Sprint_4 : Selenium  Project
This is a Project with tests for web application https://qa-scooter.praktikum-services.ru/

## Tools Used
1. IntelliJ IDEA community Edition
2. Maven 3.9.0
3. JDK 11
4. Selenium 4.8.1
5. JUnit 4.13.2
6. WebDriverManager

##Tests
To run the application tests just type the following command:
`mvn clean test`
You can run tests both in Firefox and in Chrome browser. For running tests in Chrome use the following command:
`mvn verify -Dbrowser=chrome`
For running tests in Firefox use the following command:
`mvn verify -Dbrowser=firefox`
