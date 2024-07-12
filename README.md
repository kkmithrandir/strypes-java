*** PROJECT SCOPE ***

This project is given as an assignment to
provide automated tests for Strypes Ltd's website.
The tests have been ran on and executed on the
latest version of Chrome and Firefox

*** TOOLS AND LIBRARIES ***

Selenium
Cucumber
Java Development Kit (17 coretto)
Maven (Latest version)
WebDriverManager (Latest version)
The complete list of dependacies used can be see in the 'pom.xml' file.
TestNG (Latest version)

*** RUNNING & REPORTING ***

===HOW TO CONFIGURE TEST RUNNER===
Click on "Edit configuration...*
Click on the "+" button located in the left-most side of the "Run/Debug Configurations" window
Choose "JUnit" as configuration type
In the "Name" textbox input "TestRunner"
Next to "Class" dropdown input the following "runner.TestRunner"
Click apply and then OK 
Now we are all set to run the test scenarios =)

===HOW TO USE THE CONFIGURATION FILE===
Go to configuration folder
Open configuration.properties
BrowserType=Firefox or Chrome - depending on your preferences
BaseUrl="https://strypes.eu/" - can be easily set if there is a domain change

===HOW TO RUN TESTS/FEATURES===
Simply add "@testing" tag before the feature/test you would like to run and press play using the "TestRunner" we previously configured


