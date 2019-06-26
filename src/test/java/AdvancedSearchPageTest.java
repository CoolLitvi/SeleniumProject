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
import webDriver.WebDriverFactory;

public class AdvancedSearchPageTest {

    private WebDriver driver;
    private AdvancedSearchPage advancedSearchPage;

    @BeforeMethod
    @Parameters({"browser"})
    public void setUp(String browser) {
        driver = WebDriverFactory.createDriver(browser);
        driver.get("https://www.work.ua/jobs-lviv/?advs=1");
        advancedSearchPage = new AdvancedSearchPage(driver);
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
    @Description("Verify job search by category")
    public void advancedSearchByCategory() {
        AdvancedSearchPage newAdvancedSearchPage = advancedSearchPage.selectCategoryCheckbox();
        String heading = newAdvancedSearchPage.getHeadingText();
        Assert.assertEquals(heading, "Сфера обслуговування");
    }

    @Test
    @Description("Verify job search by employment type")
    public void advancedSearchByEmploymentType() {
        AdvancedSearchPage newAdvancedSearchPage = advancedSearchPage.selectEmploymentTypeCheckbox();
        String heading = newAdvancedSearchPage.getHeadingText();
        Assert.assertEquals(heading, "Повна зайнятість");
    }

    @Test
    @Description("Verify job search by other option")
    public void advancedSearchByOtherOption() {
        AdvancedSearchPage newAdvancedSearchPage = advancedSearchPage.selectOtherCheckbox();
        String heading = newAdvancedSearchPage.getHeadingText();
        Assert.assertEquals(heading, "Без досвіду");
    }

    @Test
    @Description("Verify job search by salary From")
    public void advancedSearchBySalaryFrom() {
        AdvancedSearchPage newAdvancedSearchPage = advancedSearchPage.selectSalaryFromOption();
        String heading = newAdvancedSearchPage.getHeadingText();
        Assert.assertEquals(heading, "Від 3 000 грн");
    }

    @Test
    @Description("Verify job search by salary To")
    public void advancedSearchBySalaryTo() {
        AdvancedSearchPage newAdvancedSearchPage = advancedSearchPage.selectSalaryToOption();
        String heading = newAdvancedSearchPage.getHeadingText();
        Assert.assertEquals(heading, "До 5 000 грн");
    }

}
