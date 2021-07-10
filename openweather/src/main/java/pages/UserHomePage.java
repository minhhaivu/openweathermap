package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class UserHomePage extends AbstractPage {

    @FindBy(xpath = "//*[@class='panel-body']")
    private WebElement successSignInMsg;

    public UserHomePage(WebDriver driver) {
        this.pageDriver = driver;
    }

    public String getSuccessMsg() {
        return successSignInMsg.getText();
    }
}
