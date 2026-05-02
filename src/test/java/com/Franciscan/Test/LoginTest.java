package com.Franciscan.Test;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.Franciscan.Base.BaseClass;
import com.Franciscan.Pages.LoginPage;
import com.Franciscan.Pages.DashboardPage;

public class LoginTest extends BaseClass {

    LoginPage login;
    DashboardPage dashboard;

    @BeforeMethod
    public void start() {
        setup();
        login = new LoginPage(driver);
        dashboard = new DashboardPage(driver);

        login.login("SF001", "Francis@1988");
    }

    @Test
    public void verifyDashboard() {
        // ✅ Verify profile icon
        Assert.assertTrue(dashboard.isProfileIconVisible(), "Profile icon not visible");

        // ✅ Verify profile name
        String name = dashboard.getProfileName();
        Assert.assertTrue(name.contains("MOHIT"), "Profile name mismatch");

        // ✅ Verify URL
        String url = driver.getCurrentUrl();
        Assert.assertTrue(url.contains("Dashboard"), "Not navigated to Dashboard");
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