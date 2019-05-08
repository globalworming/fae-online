package com.headissue.fate.e2e;

import net.thucydides.core.webdriver.DriverSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.logging.Level;

public class MyChromeDriver implements DriverSource {

  @Override
  public WebDriver newDriver() {
    DesiredCapabilities caps = DesiredCapabilities.chrome();
    LoggingPreferences logPrefs = new LoggingPreferences();
    logPrefs.enable(LogType.BROWSER, Level.ALL);
    caps.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);
    return new ChromeDriver(caps);
  }

  @Override
  public boolean takesScreenshots() {
    return true;
  }
}
