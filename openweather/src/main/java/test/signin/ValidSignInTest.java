package test.signin;

import actor.Tester;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ValidSignInTest {
    private Tester tester;

    @BeforeClass
    public void initVariables() {
        tester = Tester.getInstance();
    }

    @Test
    public void testInValidSignIn() {
        String userEmail = "minhhai.vu83@gmail.com";
        String password = "N0rm@l22";

        tester.signInAction.signIn(userEmail, password);
        SignInValidator.signInSucessfully();

        tester.signInAction.close();
    }

    @AfterClass
    public void closeBrowser() {
        tester.signInAction.close();
    }
}
