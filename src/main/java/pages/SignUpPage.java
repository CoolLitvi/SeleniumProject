package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class  SignUpPage {

    WebDriver driver;

    public SignUpPage(WebDriver driver) {
        PageFactory.initElements(driver,this);
        this.driver = driver;
    }

    @FindBy(xpath = "//h1[@id=\"register\"]")
    private WebElement heading;

    @FindBy(xpath = "//input[@name=\"btSub\"]")
    private WebElement signUpButton;

    @FindBy(xpath = "//input[@id=\"first_name\"]")
    private WebElement firstNameField;

    @FindBy(xpath = "//input[@id=\"last_name\"]")
    private WebElement lastNameField;

    @FindBy(xpath = "//input[@id=\"email\"]")
    private WebElement emailField;

    @FindBy(xpath = "//input[@id=\"password\"]")
    private WebElement passwordField;

    @FindBy(xpath = "//p[@id=\"first_name-error\"]")
    private WebElement firstNameError;

    @FindBy(xpath = "//p[@id=\"last_name-error\"]")
    private WebElement lastNameError;

    @FindBy(xpath = "//p[@id=\"email-error\"]")
    private WebElement emailError;

    @FindBy(xpath = "//p[@id=\"password-error\"]")
    private WebElement passwordError;

    @Step("Type value into First Name field")
    public SignUpPage typeFirstName(String firstName) {
        firstNameField.sendKeys(firstName);
        return this;
    }

    @Step("Type value into Last Name field")
    public SignUpPage typeLastName(String lastName) {
        lastNameField.sendKeys(lastName);
        return this;
    }

    @Step("Type value into Email field")
    public SignUpPage typeEmail(String userEmail) {
        emailField.sendKeys(userEmail);
        return this;
    }

    @Step("Type value into Password field")
    public SignUpPage typePassword(String password) {
        passwordField.sendKeys(password);
        return this;
    }

    @Step("Sign Up with invalid credentials")
    public SignUpPage registerWithInvalidCreds(String firstName, String lastName, String userEmail, String password) {
        this.typeFirstName(firstName);
        this.typeLastName(lastName);
        this.typeEmail(userEmail);
        this.typePassword(password);
        this.clickSignUpButton();
        return new SignUpPage(driver);
    }

    @Step("Get SignUp page heading text")
    public String getHeadingText() {
        return heading.getText();
    }

    @Step("Get First Name field error text")
    public String getFirstNameErrorText() {
        return firstNameError.getText();
    }

    @Step("Get Last Name field error text")
    public String getLastNameErrorText() {
        return lastNameError.getText();
    }

    @Step("Get Email field error text")
    public String getEmailErrorText() {
        return emailError.getText();
    }

    @Step("Get First Password field error text")
    public String getPasswordErrorText() {
        return passwordError.getText();
    }

    @Step("Click on SignUp button")
    public SignUpPage clickSignUpButton() {
        signUpButton.click();
        return new SignUpPage(driver);
    }


}
