package test.signin;

import action.SignInAction;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SignInValidator {
    private static final SignInAction signInAction = new SignInAction();


    public static void signInSuccessfully() {
        Assert.assertTrue(StringUtils.isNotBlank(signInAction.getSuccessMsg()));
    }

    public static void checkInvalidSignInMessageDisplayed(List<WebElement> signInMsg) {
        Assert.assertFalse(signInMsg.isEmpty(), "Invalid Email or password message is not displayed");
    }
}
