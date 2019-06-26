package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

    private WebDriver driver;

    public HomePage(WebDriver driver) {
        PageFactory.initElements(driver,this);
        this.driver = driver;
    }

   @FindBy(xpath = "//a[@class=\"btn btn-default hidden-xs\"]")
   private WebElement signInButton;

   @FindBy(xpath = "//input[@id=\"search\"]")
   private WebElement searchField;

   @FindBy(xpath = "//div[@class=\"input-holder input-search-city\"]/input[1]")
   private WebElement citySelectionField;

   @FindBy(xpath = "//button[@id=\"sm-but\"]")
   private WebElement findJobsButton;

   @FindBy(xpath = "//a[@class=\"text-opacity hovered\"]")
   private WebElement advancedSearchLink;

   @Step("Click on SignIn button")
   public LogInPage clickSignInButton() {
       signInButton.click();
       return new LogInPage(driver);
   }

   @Step("Type value into search field")
   public HomePage typeIntoSearchField(String jobSearchInput) {
       searchField.sendKeys(jobSearchInput);
       return this;
   }

   @Step("Clear city selection field")
   public HomePage clearCitySelectionField() {
       citySelectionField.clear();
       return this;
   }

   @Step("Type value into city selection field")
   public HomePage typeIntoCitySelectionField(String citySearchInput) {
       citySelectionField.sendKeys(citySearchInput);
       return this;
   }

   @Step("Click on FindJobs button")
   public FoundJobsPage clickFindJobsButton() {
       findJobsButton.click();
       return new FoundJobsPage(driver);
   }

   @Step("Click on AdvanceSearch link")
   public AdvancedSearchPage clickAdvancedSearchLink() {
       advancedSearchLink.click();
       return new AdvancedSearchPage(driver);
   }

   @Step("Find jobs")
   public FoundJobsPage findJobs(String jobSearchInput, String citySearchInput) {
       typeIntoSearchField(jobSearchInput);
       clearCitySelectionField();
       typeIntoCitySelectionField(citySearchInput);
       clickFindJobsButton();
       return new FoundJobsPage(driver);
   }

}
