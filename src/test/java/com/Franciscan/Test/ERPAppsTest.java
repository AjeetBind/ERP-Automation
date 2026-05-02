package com.Franciscan.Test;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.Franciscan.Base.BaseClass;
import com.Franciscan.Pages.LoginPage;
import com.Franciscan.Pages.ERPAppsPage;

public class ERPAppsTest extends BaseClass {

    LoginPage login;
    ERPAppsPage erp;

    @BeforeMethod
    public void start() {
        setup();
        login = new LoginPage(driver);
        erp = new ERPAppsPage(driver);

        // Login first
        login.login("SF001", "Francis@1988");
    }


    @Test
    public void verifyICardNavigation() {
        erp.clickERPAppsIcon();
        erp.searchICard();
        erp.clickGoAndSwitchWindow();
        String text = erp.getDashboardText();
        Assert.assertTrue(text.contains("ID Card Application"));
        // ✅ Print actual text
        System.out.println("Dashboard Message: " + text);
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