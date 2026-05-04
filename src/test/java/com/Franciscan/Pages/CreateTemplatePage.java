package com.Franciscan.Pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import dev.failsafe.internal.util.Durations;

public class CreateTemplatePage {

    WebDriver driver;
    WebDriverWait wait;

    public CreateTemplatePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    // 🔹 Locator
    @FindBy(xpath = "//a[normalize-space()='Create template']")
    private WebElement createTemplateLink;

    @FindBy(xpath = "//label[text()='ID Card Type']/following::select[1]")
    private WebElement idCardTypeDropdown;

    @FindBy(xpath = "//div[@id='BtmBox']")
    private WebElement bottomBox;
    
    @FindBy(xpath = "//a[contains(text(),'Landscape')]")
    private WebElement landscapeBtn;
    
    @FindBy(xpath = "//a[contains(text(),'Portrait')]")
    private WebElement portraitBtn;
    
    @FindBy(xpath = "//div[text()='ID Card Dimension']/following::label[1]")
    private WebElement dimensionLabel;
    
 // 🔹 Save button (main page)
    @FindBy(xpath = "//a[normalize-space()='Save']")
    private WebElement saveBtn;

    // 🔹 Popup - Template name input
    @FindBy(xpath = "//input[@formcontrolname='formatName']")
    private WebElement templateNameInput;

    // 🔹 Meta tag input
    @FindBy(xpath = "//input[@placeholder='New Meta...']")
    private WebElement metaTagInput;

    // 🔹 Cancel button
    @FindBy(xpath = "//span[normalize-space()='Cancel']")
    private WebElement cancelBtn;

    // 🔹 Save Template button
    @FindBy(xpath = "//span[normalize-space()='Save Template']")
    private WebElement saveTemplateBtn;

    // 🔹 Blank template validation
    @FindBy(xpath = "//div[text()=' Cannot save blank template ']")
    private WebElement blankTemplateMsg;

    // 🔹 Duplicate meta tag validation
    @FindBy(xpath = "//div[contains(text(),'already selected')]")
    private WebElement duplicateMetaMsg;
    
   // All suggestions
     @FindBy(xpath = "//mat-option//span[@class='mdc-list-item__primary-text']")
    private List<WebElement> metaTagOptions;
     
  // Search box
     @FindBy(xpath = "//input[@placeholder='Search...']")
     private WebElement searchBox;

     // All draggable fields
     @FindBy(xpath = "//div[@class='item' and @draggable='true']")
     private List<WebElement> allFields;

     // Canvas (drop area)
     @FindBy(xpath = "//canvas[@id='frontCanvas']")
     private WebElement canvas;
   
    
    // 🔹 Action
     public void clickCreateTemplate() {
    	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    	    wait.until(ExpectedConditions.elementToBeClickable(createTemplateLink));
    	    createTemplateLink.click();
    	}
     
 // 🔥 Dynamic Method (as you asked)
    public void selectIdCardType(String type) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(idCardTypeDropdown));

        Select select = new Select(idCardTypeDropdown);
        select.selectByVisibleText(type);

        System.out.println("Selected: " + type);
    }

    // Get selected value (for verification)
    public String getSelectedIdCardType() {
        Select select = new Select(idCardTypeDropdown);
        return select.getFirstSelectedOption().getText();
    }

    // Verify bottom section (only for Double side)
    public boolean isBottomBoxDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOf(bottomBox)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    
    public void clickLandscape() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(landscapeBtn));

        landscapeBtn.click();

        System.out.println("Clicked on Landscape");
    }
    
    public void verifyLandscapeDimension() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(ExpectedConditions.visibilityOf(dimensionLabel));

        String dimensionText = dimensionLabel.getText();
        System.out.println("Dimension: " + dimensionText);

        Assert.assertTrue(
            dimensionText.contains("Width: 723px") &&
            dimensionText.contains("Height: 467px"),
            "Landscape dimension is incorrect"
        );

        System.out.println("Landscape dimension verified");
    }
    
    public void clickPortrait() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(portraitBtn));

        portraitBtn.click();

        System.out.println("Clicked on Portrait");
    }
    
    public void verifyPortraitDimension() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(dimensionLabel));

        String dimensionText = dimensionLabel.getText();
        System.out.println("Dimension: " + dimensionText);

        Assert.assertTrue(
            dimensionText.contains("Width: 456px") &&
            dimensionText.contains("Height: 707px"),
            "Portrait dimension is incorrect"
        );

        System.out.println("Portrait dimension verified");
    }
    public void clickSaveButton() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
            .until(ExpectedConditions.elementToBeClickable(saveBtn))
            .click();

        System.out.println("✅ Clicked Save button");
    }
    
    public void enterTemplateNameSlowly(String name) {
         templateNameInput.clear();
         Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofMillis(200));

        for (char ch : name.toCharArray()) {

            templateNameInput.sendKeys(String.valueOf(ch));

            wait.until(d -> true);  // creates delay via polling
        }

        System.out.println("✅ Entered template name slowly: " + name);
    }
    public void enterMetaTag(String tag) {

        metaTagInput.sendKeys(tag);
        metaTagInput.sendKeys(Keys.ENTER);

        System.out.println("✅ Meta tag entered: " + tag);
    }
    public void clickSaveTemplate() {
        saveTemplateBtn.click();
        System.out.println("✅ Clicked Save Template");
    }
    public void verifyBlankTemplateMessage() {

        new WebDriverWait(driver, Duration.ofSeconds(10))
            .until(ExpectedConditions.visibilityOf(blankTemplateMsg));

        Assert.assertTrue(blankTemplateMsg.isDisplayed(),
            "❌ Blank template validation not displayed");

        System.out.println("✅ Blank template validation verified");
    }
    
    public void verifyDuplicateMetaTagMessage() {

        new WebDriverWait(driver, Duration.ofSeconds(10))
            .until(ExpectedConditions.visibilityOf(duplicateMetaMsg));

        String msg = duplicateMetaMsg.getText();

        Assert.assertTrue(msg.contains("already selected"),
            "❌ Duplicate meta tag validation not shown");

        System.out.println("✅ Duplicate meta tag validation verified");
    }
    
    public void clickCancel() {
        cancelBtn.click();
        System.out.println("✅ Clicked Cancel button");
    }
    public void enterMetaTagAndSelect(String tagName) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Step 1: Type meta tag
        metaTagInput.clear();
        metaTagInput.sendKeys(tagName);

        // Step 2: Wait for suggestions to appear
        wait.until(ExpectedConditions.visibilityOfAllElements(metaTagOptions));

        // Step 3: Click matching suggestion
        for (WebElement option : metaTagOptions) {
            String text = option.getText().trim();

            if (text.equalsIgnoreCase(tagName)) {
                option.click();
                System.out.println("✅ Selected meta tag: " + text);
                return;
            }
        }

        throw new RuntimeException("❌ Meta tag not found in suggestions: " + tagName);
    }
    
    public void searchField(String fieldName) {

        searchBox.clear();
        searchBox.sendKeys(fieldName);

        System.out.println("✅ Searched field: " + fieldName);
    }
    public WebElement getFieldIfAvailable(String fieldName) {

        for (WebElement field : allFields) {

            String text = field.getText().trim();

            if (text.equalsIgnoreCase(fieldName)) {
                System.out.println("✅ Field found: " + fieldName);
                return field;
            }
        }

        System.out.println("❌ Field NOT found: " + fieldName);
        return null;
    }
    
    public void dragAndDropUsingJS(WebElement source) {

        String script = "function createEvent(type) {" +
                "return new DragEvent(type, { bubbles: true }); }" +
                "arguments[0].dispatchEvent(createEvent('dragstart'));" +
                "arguments[1].dispatchEvent(createEvent('drop'));";

        ((JavascriptExecutor) driver).executeScript(script, source, canvas);
    }
    
    
    public void searchAndDragField(String fieldName) {

        searchField(fieldName);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfAllElements(allFields));

        WebElement field = getFieldIfAvailable(fieldName);

        if (field != null) {
            dragAndDropUsingJS(field);
        } else {
            throw new RuntimeException("❌ Field not available: " + fieldName +
                " → Please check field settings");
        }
    }
}