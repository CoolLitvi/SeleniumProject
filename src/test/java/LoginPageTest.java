import io.qameta.allure.Attachment;
import io.qameta.allure.Description;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import pages.LogInPage;
import pages.SignUpPage;
import webDriver.WebDriverFactory;

public class LoginPageTest {

    private WebDriver driver;
    private LogInPage logInPage;

    @BeforeMethod
    @Parameters({"browser"})
    public void setUp(String browser) {
        driver = WebDriverFactory.createDriver(browser);
        driver.get("https://www.work.ua/jobseeker/login/");
        logInPage = new LogInPage(driver);
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
    @Description("Verify LogIn with empty credentials")
    public void loginWithEmptyCredsTest() {
        LogInPage newLoginPage = logInPage.loginWithInvalidCreds("","");
        String emailError = newLoginPage.getEmailErrorText();
        String passwordError = newLoginPage.getPasswordErrorText();
        SoftAssert sa = new SoftAssert();
        sa.assertEquals(emailError, "Будь ласка, введіть вашу ел. пошту." );
        sa.assertEquals(passwordError, "Будь ласка, введіть ваш пароль.");
        sa.assertAll();
    }

    @Test(dataProvider = "myDataProvider")
    @Description("Verify LogIn with invalid email")
    public void loginWithInvalidEmailTest(String userEmail, String password) {
        LogInPage newLoginPage = logInPage.loginWithInvalidCreds(userEmail, password);
        String emailError = newLoginPage.getEmailErrorText();
        Assert.assertEquals(emailError, "Будь ласка, виправте помилку в ел. пошті.");
    }

    @DataProvider(name = "myDataProvider")
    public Object[][] getSomeTestData() {
        return new Object[][] {
                {"plainaddress", "12345"},
                {"#@%^%#$@#$@#.com", "12345"},
                {"@example.com", "12345"}
        };
    }

    @Test
    @Description("Verify SignUp page heading text")
    public void signUpHeadingTest() {
        SignUpPage signUpPage = logInPage.clickSignUpLink();
        String heading = signUpPage.getHeadingText();
        Assert.assertEquals(heading, "Зареєструйтесь");
    }


}
