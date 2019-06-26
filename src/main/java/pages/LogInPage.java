package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LogInPage {

    WebDriver driver;

    public LogInPage(WebDriver driver) {
        PageFactory.initElements(driver,this);
        this.driver = driver;
    }

    @FindBy(xpath = "//input[@id=\"email\"]")
    private WebElement emailField;

    @FindBy(xpath = "//input[@id=\"password\"]")
    private WebElement passwordField;

    @FindBy(xpath = "//button[@type=\"submit\"]")
    private WebElement signInButton;

    @FindBy(xpath = "//h1[@id=\"login\"]")
    private WebElement heading;

    @FindBy(xpath = "//p[@class=\"text-center add-top-md\"]/a")
    private WebElement signUpLink;

    @FindBy(xpath = "//p[@id=\"email-error\"]")
    private WebElement emailError;

    @FindBy(xpath = "//p[@id=\"password-error\"]")
    private WebElement passwordError;

    @Step("Type value into Email field")
    public LogInPage typeEmail(String userEmail) {
        emailField.sendKeys(userEmail);
        return this;
    }

    @Step("Type value into Password field")
    public LogInPage typePassword(String password) {
        passwordField.sendKeys(password);
        return this;
    }

    @Step("Login with invalid credentials")
    public LogInPage loginWithInvalidCreds(String userEmail, String password) {
        this.typeEmail(userEmail);
        this.typePassword(password);
        this.clickSignInButton();
        return new LogInPage(driver);
    }

    @Step("Get LogIn page heading text")
    public String getHeadingText() {
        return heading.getText();
    }

    @Step("Get Email field error text")
    public String getEmailErrorText() {
        return emailError.getText();
    }

    @Step("Get Password Field error text")
    public String getPasswordErrorText() {
        return passwordError.getText();
    }

    @Step("Click on SignUp link")
    public SignUpPage clickSignUpLink() {
        signUpLink.click();
        return new SignUpPage(driver);
    }

    @Step("Click on SignIn button")
    public LogInPage clickSignInButton() {
        signInButton.click();
        return new LogInPage(driver);
    }




}
