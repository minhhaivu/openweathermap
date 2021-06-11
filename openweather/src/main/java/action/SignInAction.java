package action;

import org.openqa.selenium.WebElement;
import pages.HomePage;
import pages.SignInPage;
import pages.UserHomePage;

import java.util.List;

public class SignInAction extends ActionBase {

    private final HomePage homePage;
    private final SignInPage signInPage;
    private final UserHomePage userHomePage;

    public SignInAction() {

        super();

        homePage = getPageInstance(HomePage.class);
        signInPage = getPageInstance(SignInPage.class);
        userHomePage = getPageInstance(UserHomePage.class);
    }

    public void signIn(String userEmail, String password) {
        homePage.open().selectMenu("Sign in");
        signInPage.enterSignInInfo(userEmail, password).submitSignIn();
    }

    public List<WebElement> getSuccessMsg() {

        return userHomePage.getSuccessSignInMsg();
    }

    public List<WebElement> getInvalidMsg() {

        return signInPage.getInvalidSignMsg();
    }

    @Override
    public void tearDown() {
        userHomePage.close();
    }
}
