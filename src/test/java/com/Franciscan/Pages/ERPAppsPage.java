package com.Franciscan.Pages;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ERPAppsPage {

	WebDriver driver;
	WebDriverWait wait;

	public ERPAppsPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, Duration.ofSeconds(500));
	}

	// 🔹 Locators

	@FindBy(xpath = "//img[@src='/assets/Images/ERPApp.svg']")
	private WebElement erpAppsIcon;

	@FindBy(xpath = "//div[@class='offcanvas-body']//input[@placeholder='Search...']")
	private WebElement searchBox;

	// improved XPath (remove extra space issue)
	@FindBy(xpath = "//div[contains(@class,'LinkSrvc')]//a[normalize-space()='Go']")
	private WebElement goButton;

	@FindBy(xpath = "//mat-card-title[normalize-space()='Welcome to ID Card Application']")
	private WebElement dashboardText;

	// 🔹 Actions

	public void clickERPAppsIcon() {
		wait.until(ExpectedConditions.elementToBeClickable(erpAppsIcon)).click();
	}

	public void searchICard() {
		wait.until(ExpectedConditions.visibilityOf(searchBox)).sendKeys("i card");
	}

	public void clickGoAndSwitchWindow() {

		String parentWindow = driver.getWindowHandle();

		wait.until(ExpectedConditions.elementToBeClickable(goButton)).click();

		// switch to new window
		Set<String> allWindows = driver.getWindowHandles();

		for (String window : allWindows) {
			if (!window.equals(parentWindow)) {
				driver.switchTo().window(window);
			}
		}
	}

	public boolean isDashboardDisplayed() {
		return wait.until(ExpectedConditions.visibilityOf(dashboardText)).isDisplayed();
	}
	public String getDashboardText() {
		return wait.until(ExpectedConditions.visibilityOf(dashboardText)).getText();
	}
	// 🔥 Reusable Navigation Method
	public void navigateToICardPage() {

		clickERPAppsIcon();
		searchICard();
		clickGoAndSwitchWindow();
	}
}