package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import tests.BaseTest;

public class loginPage extends BaseTest {

    /**
     * label Naval Dockyard
     */
    @FindBy(xpath = " //h2[text()=' Naval Dockyard ']")
    private WebElement labelNavalDockyard;

    /**
     * Enter Personal Number
     */
    @FindBy(xpath = "//input[@placeholder='Personal number']")
    private WebElement EnterPersonalNumber;

    /**
     * Enter Password
     */
    @FindBy(xpath = "//input[@placeholder='Password'] ")
    private WebElement EnterPassword;

    /**
     * Button Login
     */
    @FindBy(xpath = "//span[text()='Login']")
    private WebElement buttonLogin;

    /**
     * label Password Required
     */
    @FindBy(xpath = "//small[text()='Password required']")
    private WebElement labelPasswordRequired;

    /**
     * label Role Required
     */
    @FindBy(xpath = "//small[text()='Role required']")
    private WebElement labelRoleRequired;

    /**
     * label Division Required
     */
    @FindBy(xpath = "//small[text()='Division required']")
    private WebElement labelDivisionRequired;

    public loginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    /**
     * Verify Naval Dockyard is displayed
     */
    public boolean verifyNavalDockyard() {
        commonMethod.waitForElement(labelNavalDockyard);
        extentTest.info("Welcome to the Naval Dockyard application. Please log in.");
        return commonMethod.isWebElementDisplayed(labelNavalDockyard);
    }

    /**
     * Enter Personal Number
     *
     * @param PersonalNumber
     */
    public void enterPersonalNumber(String PersonalNumber) throws InterruptedException {
        //Thread.sleep(3000);
        commonMethod.waitForElement(EnterPersonalNumber);
        commonMethod.clickOnElement(EnterPersonalNumber);
        commonMethod.enterText(EnterPersonalNumber, PersonalNumber);
        extentTest.info("Please enter your personal number");
    }

    /**
     * Enter Password
     *
     * @param password
     */
    public void enterPassword(String password) throws InterruptedException {
        Thread.sleep(3000);
        commonMethod.waitForElement(EnterPassword);
        commonMethod.clickOnElement(EnterPassword);
        commonMethod.enterText(EnterPassword, password);
        extentTest.info("Please enter your password");
    }

    /**
     * Click on Login
     */
    public void clickOnLogin() throws InterruptedException {
        Thread.sleep(3000);
        commonMethod.waitForElement(buttonLogin);
        commonMethod.clickOnElement(buttonLogin);
        extentTest.info("Click On Login successfully");
    }

    /**
     * Verify that the 'Password required' message is displayed.
     */
    public boolean verifyPasswordRequired() throws InterruptedException {
        Thread.sleep(3000);
        commonMethod.waitForElement(labelPasswordRequired);
        Thread.sleep(1000);
        extentTest.info("Verify that the 'Password required' message is displayed.");
        return commonMethod.isWebElementDisplayed(labelPasswordRequired);
    }

    /**
     * Verify that the 'Role required' message is displayed.
     */
    public boolean verifyRoleRequired() throws InterruptedException {
        Thread.sleep(3000);
        commonMethod.waitForElement(labelRoleRequired);
        Thread.sleep(1000);
        extentTest.info("Verify that the 'Role required' message is displayed.");
        return commonMethod.isWebElementDisplayed(labelRoleRequired);
    }

    /**
     * Verify that the 'Division required' message is displayed.
     */
    public boolean verifyDivisionRequired() throws InterruptedException {
        Thread.sleep(3000);
        commonMethod.waitForElement(labelDivisionRequired);
        Thread.sleep(1000);
        extentTest.info("Verify that the 'Division required' message is displayed.");
        return commonMethod.isWebElementDisplayed(labelDivisionRequired);
    }


}
