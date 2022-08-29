package org.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;

public class ConsoleLog {

    private LogEntries logEntries;
    private LogEntry entry;

    public LogEntries getLogEntries(WebDriver driver) {
        LogEntries logEntries = driver.manage().logs().get(LogType.BROWSER);
        return logEntries;
    }

}
