package test.signin;

import actor.Tester;
import org.openqa.selenium.WebElement;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

public class InvalidSignInTest {

    @Test(dataProvider = "invalidAccount")
    public void testInvalidSignIn(String userEmail, String password) {
        Tester tester = Tester.getInstance();
        tester.signInAction.signIn(userEmail, password);
        List<WebElement> signInMsg = tester.signInAction.getInvalidMsg();
        SignInValidator.checkInvalidSignInMessageDisplayed(signInMsg);

    }

    @DataProvider
    private static Object[][] invalidAccount() {
        return new Object[][]{
                {"", ""},
                {"", "password"},
                {"userName@gmail.com", ""},
                {"ilymtics@gmail.com", "bydkh"}
        };
    }
}
