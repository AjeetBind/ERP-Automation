package com.Franciscan.Pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DashboardPage {

    WebDriver driver;
    WebDriverWait wait;

    public DashboardPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    // 🔹 Locators

    @FindBy(xpath = "//a[contains(@class,'dropdown-toggle')]")
    private WebElement profileIcon;

    @FindBy(xpath = "//h6[contains(text(),'MOHIT')]")
    private WebElement profileName;

    // 🔹 Actions

    public boolean isProfileIconVisible() {
        return wait.until(ExpectedConditions.visibilityOf(profileIcon)).isDisplayed();
    }

    public String getProfileName() {
        return wait.until(ExpectedConditions.visibilityOf(profileName)).getText();
    }
}