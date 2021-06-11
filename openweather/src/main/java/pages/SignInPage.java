package pages;

import lombok.Getter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

@Getter
public class SignInPage extends AbstractPage {
    @FindBy(id = "user_email")
    private WebElement userEmailTxt;

    @FindBy(id = "user_password")
    private WebElement passwordTxt;

    @FindBy(xpath = "//div[@class='sign-form']/form[@id='new_user']/input[@name='commit']")
    private WebElement submitBtn;

    @FindBy(xpath = "//div[contains(text(),'Invalid Email or password.')]")
    private List<WebElement> invalidSignMsg;

    public SignInPage enterSignInInfo(String userEmail, String password) {
        userEmailTxt.sendKeys(userEmail);
        passwordTxt.sendKeys(password);

        return this;
    }

    public void submitSignIn() {
        submitBtn.click();
    }
}
