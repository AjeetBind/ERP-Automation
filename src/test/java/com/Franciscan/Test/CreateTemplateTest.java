package com.Franciscan.Test;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.Franciscan.Base.BaseClass;
import com.Franciscan.Pages.LoginPage;
import com.Franciscan.Pages.CreateTemplatePage;
import com.Franciscan.Pages.ERPAppsPage;

public class CreateTemplateTest extends BaseClass {

	LoginPage login;
    ERPAppsPage erp;
    CreateTemplatePage templatePage;

    @BeforeMethod
    public void start() {
    	setup();
        login = new LoginPage(driver);
        erp = new ERPAppsPage(driver);
        templatePage = new CreateTemplatePage(driver);

        
        // 🔹 Login first
        login.login("SF001", "Francis@1988");
        erp.navigateToICardPage();
    }

    @Test
    public void tc_001_verifyCreateTemplateNavigation() { 

        templatePage.clickCreateTemplate();
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("ICard"), 
                "Not navigated to Create Template page");
        System.out.println(" Successfully navigated to Create Template page");
    }
    
    @Test
    public void tc_002_verifyIdCardTypeSelection() {

      
        templatePage.clickCreateTemplate();
        templatePage.selectIdCardType("Single side");
        String selected = templatePage.getSelectedIdCardType();
        Assert.assertEquals(selected, "Single side");

        System.out.println("Selected ID Card Type: " + selected);

        templatePage.selectIdCardType("Double side");
        selected = templatePage.getSelectedIdCardType();
        Assert.assertEquals(selected, "Double side");
        System.out.println("Selected ID Card Type: " + selected);

      
        Assert.assertTrue(templatePage.isBottomBoxDisplayed(),
                "Bottom section not displayed for Double side");
        System.out.println(" Bottom section displayed for Double side");
    }
    @Test
    public void tc_003_verifyOrientationChange_LandscapeToPortrait() {
    	 templatePage.clickCreateTemplate();
    	 templatePage.clickPortrait();
    	 templatePage.verifyPortraitDimension(); 
    	 templatePage.clickLandscape();
    	 templatePage.verifyLandscapeDimension();

    }
    
    @Test
    public void tc_004_verifyUserIsAbleToSaveBankTemplate() {
    	 templatePage.clickCreateTemplate();
         templatePage.clickSaveButton(); 
         templatePage.enterTemplateNameSlowly("Test Template");
         templatePage.enterMetaTagAndSelect("Black");
         templatePage.enterMetaTagAndSelect("Red");
         templatePage.enterMetaTagAndSelect("Blue");    
         templatePage.clickSaveTemplate();
         templatePage.verifyBlankTemplateMessage();
         templatePage.clickCancel();
         
      

        System.out.println("Test case executed successfully");
    }
    
    @Test
    public void tc_verify_multipleFieldsDragAndDrop() {
    	templatePage.clickCreateTemplate();
    	
    	templatePage.searchField("Photo");

    	WebElement photoField = templatePage.getFieldIfAvailable("Photo");

    	templatePage.dragAndDropUsingJS(photoField);
    }
    
    @AfterMethod
    public void close() {
        try {
            login.logout();
        } catch (Exception e) {
            System.out.println("Logout skipped");
        }
        tearDown();
    }
}