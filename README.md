# Selenium 4.0 step by step with Java

![image](https://user-images.githubusercontent.com/89974862/149460510-99d4cd94-ab02-4d71-a43d-495d31e1da4f.png)


**Youtube Webinar Video**

https://www.youtube.com/watch?v=lfeYoIlTwtM

***

**Requirements:**

Selenium IDE Chrome Extension -> https://chrome.google.com/webstore/detail/selenium-ide/mooikfkahbdckldjjndioackbalphokd

NodeJS Installation is required for selenium-side-runner -> https://www.selenium.dev/selenium-ide/docs/en/introduction/command-line-runner

Java JDK8 Installation -> https://www.oracle.com/tr/java/technologies/javase-downloads.html

Jetbrains IntelliJ IDEA CE Installation -> https://www.jetbrains.com/idea/

Allure Reporting Installation -> https://docs.qameta.io/allure/#_installing_a_commandline

***

**Driver Installations**
```
npm install -g chromedriver
npm install -g edgedriver
npm install -g geckodriver
npm install -g iedriver
```
![image](https://user-images.githubusercontent.com/89974862/149463217-0f9e1b34-df64-4b39-851d-4d1c587fb41b.png)


***

**Selenium IDE Usage**

![image](https://user-images.githubusercontent.com/89974862/149463032-c19c8455-8a63-479b-87fe-895cc48bc3d5.png)


```
selenium-side-runner gloriajeans.side
selenium-side-runner gloriajeans.side -c "browserName=firefox"
selenium-side-runner gloriajeans.side -c "browserName=chrome goog:chromeOptions.args=[disable-infobars, headless]"
```

***

**Selenium Grid Usage**

![image](https://user-images.githubusercontent.com/89974862/149462933-158c8b17-ee4d-4255-b83d-54b1a18e9458.png)


**Standalone mode:**

```
java -jar selenium-server-4.1.1.jar standalone
```

**Hub-Node mode:**

```
java -jar selenium-server-4.1.1.jar hub
java -jar selenium-server-4.1.1.jar node --detect-drivers true --max-sessions 20 --override-max-sessions true
```

**Container mode:**

```
docker run -d -p 4444:4444 -p 7900:7900 --shm-size="2g" selenium/standalone-firefox:4.1.1-20211217
```
![image](https://user-images.githubusercontent.com/89974862/149462814-a6da9818-6696-4dd6-9c18-2962f2976697.png)


***

**Allure Reporting Usage**

```
mvn clean test -Dsurefire.suiteXmlFiles=src/test/java/suites/SingleClassesSuite.xml
allure serve allure-results
```

![image](https://user-images.githubusercontent.com/89974862/149462507-4637f4e8-a236-4a3b-9b8b-f1d0149bf3d4.png)


***

**Resources**

https://www.selenium.dev/selenium-ide/

https://www.selenium.dev/selenium-ide/docs/en/introduction/getting-started

https://www.selenium.dev/documentation/webdriver/

https://www.selenium.dev/selenium/docs/api/java/deprecated-list.html

https://mvnrepository.com/artifact/org.seleniumhq.selenium/selenium-java

https://www.selenium.dev/documentation/grid/

https://github.com/SeleniumHQ/docker-selenium

https://docs.qameta.io/allure/#_testng


**Repository owner**

https://www.linkedin.com/in/ozgurkayaist/

***

"Software Testing Bootcamp" is a community of people interested in software testing. We record the weekly webinars that we organize and publish them on Youtube. For more information -> https://testingbootcamp.com/
