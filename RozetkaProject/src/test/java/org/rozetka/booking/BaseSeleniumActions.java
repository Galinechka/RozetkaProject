package org.rozetka.booking;

import static org.junit.Assert.fail;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.jbehave.core.junit.JUnitStories;

import org.jbehave.core.annotations.AfterStories;
import org.jbehave.core.annotations.AfterStory;
import org.jbehave.core.annotations.BeforeStories;
import org.jbehave.core.annotations.BeforeStory;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BaseSeleniumActions {
	
	 public static WebDriver driver;
	  private StringBuffer verificationErrors = new StringBuffer();
	  
	public BaseSeleniumActions() throws Exception {
		
	}	
	
	public static WebDriver getWebDriver()
    {
        return driver;
    }
	
	public void open(String url) {
		driver.get(url);
	}

}
