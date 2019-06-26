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
import org.testng.asserts.SoftAssert;
import pages.SignUpPage;
import webDriver.WebDriverFactory;

public class SignUpPageTest {

    private WebDriver driver;
    private SignUpPage signUpPage;

    @BeforeMethod
    @Parameters({"browser"})
    public void setUp(String browser) {
        driver = WebDriverFactory.createDriver(browser);
        driver.get("https://www.work.ua/jobseeker/register/");
        signUpPage = new SignUpPage(driver);
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
    @Description("Verify SignUp with empty credentials")
    public void signUpWithEmptyCredsTest() {
        SignUpPage newSignUpPage = signUpPage.registerWithInvalidCreds("", "", "", "");
        String firstNameError = newSignUpPage.getFirstNameErrorText();
        String lastNameError = newSignUpPage.getLastNameErrorText();
        String emailError = newSignUpPage.getEmailErrorText();
        String passwordError = newSignUpPage.getPasswordErrorText();
        SoftAssert sa = new SoftAssert();
        sa.assertEquals(firstNameError, "Будь ласка, вкажіть ім’я.");
        sa.assertEquals(lastNameError, "Будь ласка, вкажіть прізвище.");
        sa.assertEquals(emailError, "Будь ласка, введіть вашу ел. пошту." );
        sa.assertEquals(passwordError, "Будь ласка, введіть ваш пароль.");
        sa.assertAll();
    }

    @Test
    @Description("Verify SignUp with invalid first name")
    public void signUpWithInvalidFirstName() {
        SignUpPage newSignUpPage = signUpPage.typeFirstName("!@#$%").clickSignUpButton();
        String error = newSignUpPage.getFirstNameErrorText();
        Assert.assertEquals(error, "Введено неприпустимі символи.");
    }

    @Test
    @Description("Verify SignUp with invalid last name")
    public void signUpWithInvalidLastName() {
        SignUpPage newSignUpPage = signUpPage.typeLastName("!@#$%").clickSignUpButton();
        String error = newSignUpPage.getLastNameErrorText();
        Assert.assertEquals(error, "Введено неприпустимі символи.");
    }

    @Test
    @Description("Verify SignUp with invalid email")
    public void signUpWithInvalidEmailTest() {
        SignUpPage newSignUpPage = signUpPage.typeEmail("qwerty").clickSignUpButton();
        String error = newSignUpPage.getEmailErrorText();
        Assert.assertEquals(error, "Будь ласка, виправте помилку в ел. пошті.");
    }

    @Test
    @Description("Verify SignUp with short password")
    public void signUpWithShortPass() {
        SignUpPage newSignUpPage = signUpPage.typePassword("123").clickSignUpButton();
        String error = newSignUpPage.getPasswordErrorText();
        Assert.assertEquals(error, "Будь ласка, введіть пароль зі щонайменше 4 символами.");
    }

}

