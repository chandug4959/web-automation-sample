package tests;

import org.testng.asserts.SoftAssert;
import org.testng.annotations.Test;
import pages.ExcelDataProvider;
import pages.homePage;
import pages.loginPage;

public class navalDockyardTestSuite extends BaseTest {


    @Test(dataProvider = "excelData", priority = 1, description = "Verify Naval Dockyard: Login and Logout Successfully",
            dataProviderClass = ExcelDataProvider.class)
    public void loginAndLogoutSuccessfully(String methodName, String personalNumber, String password) throws InterruptedException {
        loginPage loginPage = new loginPage(driver);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(loginPage.verifyNavalDockyard(), "Verify Naval Dockyard is displayed on the login page.");
        loginPage.enterPersonalNumber(personalNumber);
        loginPage.enterPassword(password);
        loginPage.clickOnLogin();
        homePage homePage = new homePage(driver);
        softAssert.assertTrue(homePage.verifyDashboardInHomePage(), "Verify the Dashboard is displayed on the home page.");
        homePage.clickOnLogout();
        softAssert.assertTrue(loginPage.verifyNavalDockyard(), "Verify Naval Dockyard is displayed on the login page");
        softAssert.assertAll();
    }

    @Test(dataProvider = "excelData", priority = 2, description = "Verify Naval Dockyard password is required.",
            dataProviderClass = ExcelDataProvider.class)
    public void verifyPasswordRequired(String methodName, String personalNumber, String password) throws InterruptedException {
        loginPage loginPage = new loginPage(driver);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(loginPage.verifyNavalDockyard(), "Verify Naval Dockyard is displayed on the login page.");
        loginPage.enterPersonalNumber(personalNumber);
        loginPage.clickOnLogin();
        softAssert.assertTrue(loginPage.verifyPasswordRequired(), "Verify that a password is required");
        softAssert.assertAll();
    }

    //ToDo: Issue with password—any password successfully logs in.
    @Test(dataProvider = "excelData", priority = 3, description = "Verify that any password allows successful login to Naval Dockyard",
            dataProviderClass = ExcelDataProvider.class)
    public void loginAndAnyPassword(String methodName, String personalNumber, String password) throws InterruptedException {
        loginPage loginPage = new loginPage(driver);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(loginPage.verifyNavalDockyard(), "Verify Naval Dockyard is displayed on the login page.");
        loginPage.enterPersonalNumber(personalNumber);
        loginPage.enterPassword(password);//Todo bug
        loginPage.clickOnLogin();
        softAssert.assertTrue(loginPage.verifyNavalDockyard(), "Verify Naval Dockyard is displayed on the login page.");
        extentTest.fail("Test failed due to Assert issue");
        homePage homePage = new homePage(driver);
        homePage.clickOnLogout();
        softAssert.assertAll();
    }

    @Test(dataProvider = "excelData", priority = 4, description = "Verify that the Naval Dockyard login requires a password, role, and division. ",
            dataProviderClass = ExcelDataProvider.class)
    public void verifyRequiredInLogin(String methodName, String personalNumber, String password) throws InterruptedException {
        loginPage loginPage = new loginPage(driver);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(loginPage.verifyNavalDockyard(), "Verify Naval Dockyard is displayed on the login page.");
        loginPage.enterPersonalNumber(personalNumber);
        loginPage.clickOnLogin();
        softAssert.assertTrue(loginPage.verifyPasswordRequired(), "Verify that a password is required.");
        softAssert.assertTrue(loginPage.verifyRoleRequired(), "Verify that a role is required.");
        softAssert.assertTrue(loginPage.verifyDivisionRequired(), "Verify that a division is required.");
        softAssert.assertAll();
    }
}
