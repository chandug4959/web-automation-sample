package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import tests.BaseTest;

public class homePage extends BaseTest {

    /**
     * label Dashboard
     */
    @FindBy(xpath = "//h4[text()='Dashboard']")
    private WebElement labelDashboard;

    /**
     * Button Circle User Round
     */
    @FindBy(xpath = "//a[@class='dropdown-toggle nav-link' and @aria-expanded='false']//lucide-icon[@name='circle-user-round']")
    private WebElement ButtonCircleUserRound;

    /**
     * Button LogOut
     */
    @FindBy(xpath = "//button[text()='Logout']")
    private WebElement ButtonLogout;


    public homePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /**
     * Verify the Dashboard is displayed on the home page
     */
    public boolean verifyDashboardInHomePage() {
        commonMethod.waitForElement(labelDashboard);
        extentTest.info("Verify the Dashboard is displayed on the home page");
        return commonMethod.isWebElementDisplayed(labelDashboard);
    }

    /**
     * Click on logout successfully
     */
    public void clickOnLogout() throws InterruptedException {
        Thread.sleep(3000);
        commonMethod.waitForElement(ButtonCircleUserRound);
        commonMethod.clickOnElement(ButtonCircleUserRound);
        Thread.sleep(2000);
        commonMethod.clickOnElement(ButtonLogout);
        extentTest.info("Click on 'Log Out' successfully.");
    }

}
