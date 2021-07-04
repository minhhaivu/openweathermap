package test.signin;

import actor.Tester;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.util.List;

public class ValidSignInTest {
    @Test
    public void testInValidSignIn() {
        String userEmail = "minhhai.vu83@gmail.com";
        String password = "N0rm@l22";
        Tester tester = Tester.getInstance();
        tester.signInAction.signIn(userEmail, password);
        List<WebElement> successMsg = tester.signInAction.getSuccessMsg();
        SignInValidator.checkSuccessSignInMessageDisplayed(successMsg);
        tester.signInAction.tearDown();

    }
}
