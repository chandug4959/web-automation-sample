package pages;
import java.time.Duration;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import tests.BaseTest;

public class commonMethod extends BaseTest {
    public commonMethod(WebDriver driver) {
        this.driver = driver;
    }

    /**
     *
     * @param clickableElement
     * @return
     */
    public static boolean clickOnElement(WebElement clickableElement) {
        boolean success = false;
        try{

            if (isWebElementDisplayed(clickableElement,true))
            {
                clickableElement.click();
                success = true;
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return success;


    }


    /**
     *
     * @param element
     * @param waitForElement
     * @return
     */
    public static boolean isWebElementDisplayed(WebElement element,
                                                boolean... waitForElement) {
        boolean blnElementDisplayed = false;
        if(element==null){
            return false;
        }
        try {
            try {
                if (waitForElement.length > 0) {
                    if (waitForElement[0] == true) {
                        waitForElement(element);
                    }
                }

            } catch (Exception e) {
                // Do nothing
            }
            blnElementDisplayed = element.isDisplayed();
        } catch (NoSuchElementException e) {
            blnElementDisplayed = false;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return blnElementDisplayed;
    }

    /**
     * wait For Element
     * @param element
     */
    public static void waitForElement(WebElement element) {
        try {

            new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.visibilityOf(element));
        } catch (Exception e) {
            //			e.printStackTrace();
        }
    }

    /**
     *
     * @param element
     * @return
     */
    public static String getWebElementText(WebElement element){
        if(isWebElementDisplayed(element)){
            return element.getText();
        }
        return "";
    }

    /**
     *
     * @param element The WebElement representing the input field
     * @param text The text to be entered into the input field
     * @return true if the text was successfully entered, false otherwise
     */
    public static boolean enterText(WebElement element, String text) {
        boolean success = false;
        try {
            if (isWebElementDisplayed(element, true)) {
                element.clear();
                element.sendKeys(text);
                success = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return success;
    }

}
