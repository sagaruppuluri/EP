# Demo Application 

This demo application is designed to showcase the spring dependency injection features and configuration.

## How to Run in IntelliJ IDEA

1. Open demo1 project in IntelliJ IDEA.
    a. Open IntelliJ IDEA and select "Open" from the welcome screen or "File > Open" from the menu.
    b. Navigate to the demo1 directory and open the `pom.xml` file as project.
    c. If jdk is not found then setup the jdk in the project structure settings. Go to "File > Project Structure > Project" and set the Project SDK to a valid JDK installation, use jdk 17 or later. Then click "Apply" and "OK" to save the settings. IntelliJ will automatically import the project and download the necessary dependencies specified in the `pom.xml` file. You should see the project structure in the Project Explorer on the left side of the IDE.
2. Once the project is loaded, locate the `DemoApplicationTests` class in the `src/test/java/com/example/demo` directory.
3. Run the `DemoApplicationTests` and you should see the below output, 

```text
Saving to MySQL: TestUser
Sending welcome email to: TestUser
```

### Problems Running Tests

In case of any issues, ensure that the project dependencies are correctly imported and the JDK is properly configured in IntelliJ IDEA.

Open maven tool window (View > Tool Windows > Maven) and click on the "Reload All Maven Projects" button to refresh the dependencies. Click the run `Execute maven goal` from the `Maven tool window` and use the following command to run the tests from maven directly,

```
    mvn clean test 
```
