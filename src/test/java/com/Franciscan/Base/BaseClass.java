package com.Franciscan.Base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BaseClass {

    public WebDriver driver;   // ❗ remove static (important)

    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://app.franciscanecare.com/Login/Identifier?SchCode=DEMOIN");
    }

    public void tearDown() {
        driver.quit();
    }
}