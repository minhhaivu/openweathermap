package test.signin;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Validator {

    public static void checkSuccessSignInMessageDisplayed(List<WebElement> signInMsg) {
        Assert.assertFalse(signInMsg.isEmpty(), "Sign In is unsuccessfully!");
    }

    public static void checkInvalidSignInMessageDisplayed(List<WebElement> signInMsg) {
        Assert.assertFalse(signInMsg.isEmpty(),"Invalid Email or password message is not displayed");
    }
}
