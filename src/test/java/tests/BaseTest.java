package tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class BaseTest {
    public static WebDriver driver;
    public static String baseUrl;
    public static String browser;
    public static ExtentReports extentReports;
    public static ExtentTest extentTest;

    public static final Logger logger = Logger.getLogger(BaseTest.class.getName());

    @BeforeMethod
    public void beforeTest(ITestResult result)
    {
        extentTest = extentReports.createTest(result.getMethod().getMethodName());
        extentTest.info("Test Started");
    }
    @BeforeClass
    public void setUp() throws InterruptedException, IOException {
        // Load properties
        String filePath = "src/test/resources/config.properties";
        try (InputStream inputStream = new FileInputStream(filePath)) {
            Properties properties = new Properties();
            properties.load(inputStream);
            logger.info("Properties File values are retrieved");
            baseUrl = properties.getProperty("AppUrl");
            browser = properties.getProperty("browser");
        }

        // Create the extent-reports directory if it does not exist
        File reportDir = new File("extent-reports");
        if (!reportDir.exists()) {
            reportDir.mkdir();
        }

        // Generate a timestamp for the report file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String reportPath = "extent-reports/extentReport_" + timeStamp + ".html";

        // Initialize Extent Reports with ExtentSparkReporter
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath);
        extentReports = new ExtentReports();
        extentReports.attachReporter(sparkReporter);

        // Initialize WebDriver
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        logger.info("Successfully navigated to website.");
        Thread.sleep(3000);
        driver.get(baseUrl);
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
          //  extentTest.info("Test completed");
            driver.quit();
        }
        if (extentReports != null) {
            //extentTest.info("Test completed");
            extentReports.flush();
        }
    }

    static {
        try {
            LogManager.getLogManager().readConfiguration(new FileInputStream("src/test/resources/log.properties"));
        } catch (IOException e) {
            System.err.println("Could not load logging configuration file");
            e.printStackTrace();
        }
    }
    @AfterMethod
    public void afterMethod() {
        extentTest.info("Test completed");
        extentReports.flush();
    }

//    @AfterMethod
//    public void afterMethod() {
//        extentTest.info("Test completed");
//        if (driver != null) {
//            driver.quit();
//        }
//        if (extentReports != null) {
//            extentReports.flush();
//        }
//    }
    public String takeScreenshot(String testName) {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String screenshotPath = "extent-reports/screenshots/" + testName + "_" + timeStamp + ".png";
        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File destFile = new File(screenshotPath);
        try {
            FileUtils.copyFile(srcFile, destFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return screenshotPath;
    }
}
