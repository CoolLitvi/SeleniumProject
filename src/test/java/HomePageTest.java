import io.qameta.allure.Attachment;
import io.qameta.allure.Description;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.AdvancedSearchPage;
import pages.FoundJobsPage;
import pages.HomePage;
import pages.LogInPage;
import webDriver.WebDriverFactory;

public class HomePageTest {

    private WebDriver driver;
    private HomePage homePage;

    @BeforeMethod
    @Parameters({"browser"})
    public void setUp(String browser) {
        driver = WebDriverFactory.createDriver(browser);
        driver.get("https://www.work.ua/");
        homePage = new HomePage(driver);
    }

    @Attachment(value = "Page screenshot", type = "image/png")
    public byte[] takeScreenshot(WebDriver driver){
        TakesScreenshot takesScreenshot = (TakesScreenshot)driver;
        return takesScreenshot.getScreenshotAs(OutputType.BYTES);
    }
    @AfterMethod
    public void screenShot(ITestResult result){
        if (!result.isSuccess()) {
            takeScreenshot(driver);
        }
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    @Description("Verify LogIn page heading text")
    public void signInHeadingTest() {
        LogInPage logInPage = homePage.clickSignInButton();
        String heading = logInPage.getHeadingText();
        Assert.assertEquals(heading, "Вхід");
    }

    @Test
    @Description("Verify Advanced Search page URL")
    public void advancedSearchUrlTest() {
        AdvancedSearchPage advancedSearchPage = homePage.clickAdvancedSearchLink();
        String currentUrl = advancedSearchPage.getCurrentUrl();
        Assert.assertEquals(currentUrl, "https://www.work.ua/jobs-kyiv/?advs=1");
    }

    @Test
    @Description("Verify job search functionality")
    public void findJobsTest() {
        FoundJobsPage foundJobsPage = homePage.findJobs("Automation QA", "Львів");
        String heading = foundJobsPage.getHeading();
        Assert.assertEquals(heading, "Automation qa у Львові за 30 днів");
    }

    @Test
    @Description("Verify job search negative scenario")
    public void jobsNotFoundTest() {
        FoundJobsPage foundJobsPage = homePage.findJobs("!@#$%^", "Вся Україна");
        String jobsNotFoundHeading = foundJobsPage.getJobsNotFoundHeading();
        Assert.assertEquals(jobsNotFoundHeading, "За вашим запитом «!@#$%^» вакансій поки немає.");
    }

}
