package com.Franciscan.Pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {

    WebDriver driver;
    WebDriverWait wait;

    // Constructor
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    // 🔹 Locators

    @FindBy(xpath = "//input[@id='txtUsername']")
    private WebElement usernameField;

    @FindBy(xpath = "//input[@id='txtPassword']")
    private WebElement passwordField;

    @FindBy(xpath = "//button[text()='Login']")
    private WebElement loginBtn;
    
 // Logout locators
    @FindBy(xpath = "//a[@class='dropdown-toggle nav-link']//h6[text()='Ms. MOHIT SINGH PAWAR']")
    private WebElement profileBtn;

    @FindBy(xpath = "//a[@href='/Login/LogOff?SchCode=DEMOIN']")
    private WebElement logoutBtn;


  

    // 🔹 Actions

    public void enterUsername(String username) {
        wait.until(ExpectedConditions.visibilityOf(usernameField)).sendKeys(username);
    }

    public void enterPassword(String password) {
        wait.until(ExpectedConditions.visibilityOf(passwordField)).sendKeys(password);
    }

    public void clickLogin() {
        wait.until(ExpectedConditions.elementToBeClickable(loginBtn)).click();
    }
    // Logout action
    public void logout() {
        wait.until(ExpectedConditions.elementToBeClickable(profileBtn)).click();
        wait.until(ExpectedConditions.elementToBeClickable(logoutBtn)).click();
    }
    // 🔥 Complete Login Method
    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLogin();
    }
    
  
}