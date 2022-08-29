package org.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.concurrent.TimeUnit;


public class ChromeExample {

    public static void main(String[] args) throws IOException {
        System.setProperty("webdriver.chrome.driver", "chromedriver");
        WebDriver driver = new Driver().getDriver();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.get("https://chercher.tech/practice/explicit-wait");

        //print log

        FileWriter fileWriter = new FileWriter("Log.txt");
        PrintWriter printWriter = new PrintWriter(fileWriter);
        ConsoleLog consoleLog = new ConsoleLog();
        LogEntries logEntries = consoleLog.getLogEntries(driver);

        for (LogEntry entry : logEntries) {
            String logOut = new Date(entry.getTimestamp()) + "  " + entry.getLevel() + " ---> " + entry.getMessage();
            printWriter.println(logOut);
            System.out.println(logOut);
        }
        printWriter.close();

    }


}
