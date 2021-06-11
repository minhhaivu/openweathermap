package test.signin;

import actor.SignInTester;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.util.List;

public class ValidSignInTest {
    @Test
    public void testInValidSignIn() {
        String userEmail = "minhhai.vu83@gmail.com";
        String password = "N0rm@l22";
        SignInTester tester = SignInTester.getInstance();
        tester.signInAction.signIn(userEmail, password);
        List<WebElement> successMsg = tester.signInAction.getSuccessMsg();
        Validator.checkSuccessSignInMessageDisplayed(successMsg);
        tester.signInAction.tearDown();

    }
}
