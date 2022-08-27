package org.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;


public class ChromeExample {
    private String fileLog;

    public static void main(String[] args) throws IOException {

//Setting system properties of ChromeDriver
        System.setProperty("webdriver.chrome.driver", "chromedriver");


//Creating an object of ChromeDriver
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        WebDriver driver = new ChromeDriver(options);
        driver.manage().window();


//Deleting all the cookies
        driver.manage().deleteAllCookies();

//Specifiying pageLoadTimeout and Implicit wait
        driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

//launching the specified URL
        driver.get("https://chercher.tech/practice/explicit-wait");


        //log
        DesiredCapabilities caps = new DesiredCapabilities();
        LoggingPreferences logPrefs = new LoggingPreferences();
        logPrefs.enable(LogType.BROWSER, Level.ALL);
        caps.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);


        LogEntries logEntries = driver.manage().logs().get(LogType.BROWSER);
        FileWriter fileWriter = new FileWriter("Log.txt");
        PrintWriter printWriter = new PrintWriter(fileWriter);
        for (LogEntry entry : logEntries) {
            String logOut = new Date(entry.getTimestamp()) + "  " + entry.getLevel() + " ---> " + entry.getMessage();
            printWriter.println(logOut);
            System.out.println(logOut);

        }
        printWriter.close();
    }
}