package pages;

import lombok.Getter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

@Getter
public class UserHomePage extends AbstractPage {

    @FindBy(xpath = "//div[contains(text(),'Signed in successfully.')]")
    private List<WebElement> successSignInMsg;

}
