package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FoundJobsPage {

    WebDriver driver;

    public FoundJobsPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(xpath = "//h1[@class=\"text-basic text-muted\"]")
    private WebElement heading;

    @FindBy(xpath = "//div[@class='disconnect-alert hidden-md hidden-lg']/following-sibling::p/b")
    private WebElement jobsNotFoundHeading;

    @Step("Get Found Jobs page heading text")
    public String getHeading() {
        return heading.getText();
    }

    @Step("Get JobsNotFound heading text")
    public String getJobsNotFoundHeading() {
        return jobsNotFoundHeading.getText();
    }
}
