package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;


public class AdvancedSearchPage {

    private WebDriver driver;

    public AdvancedSearchPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

   @FindBy(xpath = "//div[@id=\"category_selection\"]//input[@value=\"20\"]")
   private WebElement categoryCheckbox;

   @FindBy(xpath = "//div[@id=\"employment_selection\"]//input[@value=\"74\"]")
   private WebElement employmentTypeCheckbox;

   @FindBy(xpath = "//div[@id=\"experience_selection\"]//input[@value=\"1\"]")
   private WebElement otherCheckbox;

   @FindBy(xpath = "//select[@id=\"salaryfrom_selection\"]")
   private WebElement salaryFromDropdown;

   @FindBy(xpath = "//select[@id=\"salaryto_selection\"]")
   private WebElement salaryToDropdown;

   @FindBy(xpath = "//span[@class=\"label label-filter\"]")
   private WebElement heading;

   @Step("Select Category checkbox")
   public AdvancedSearchPage selectCategoryCheckbox() {
       if(!categoryCheckbox.isSelected()) {
           categoryCheckbox.click();
       }
       return new AdvancedSearchPage(driver);
   }

   @Step("Select Employment Type checkbox")
   public AdvancedSearchPage selectEmploymentTypeCheckbox() {
       if (!employmentTypeCheckbox.isSelected()) {
           employmentTypeCheckbox.click();
       }
       return new AdvancedSearchPage(driver);
   }

   @Step("Select Other checkbox")
   public AdvancedSearchPage selectOtherCheckbox() {
       if(!otherCheckbox.isSelected()){
           otherCheckbox.click();
       }
       return new AdvancedSearchPage(driver);
   }

   @Step("Select value from SalaryFrom dropdown")
   public AdvancedSearchPage selectSalaryFromOption() {
       Select select = new Select(salaryFromDropdown);
       select.selectByIndex(1);
       return new AdvancedSearchPage(driver);
   }

    @Step("Select value from SalaryTo dropdown")
    public AdvancedSearchPage selectSalaryToOption() {
        Select select = new Select(salaryToDropdown);
        select.selectByIndex(2);
        return new AdvancedSearchPage(driver);
    }

    @Step("Get Advanced Search page heading text")
    public String getHeadingText() {
        return heading.getText();
    }

    @Step("Get Advanced Search page URL")
    public String getCurrentUrl() {
       return driver.getCurrentUrl();
    }
}
