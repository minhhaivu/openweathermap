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
        homePage.open();
        signInPage.enterSignInInfo(userEmail, password).submitSignIn();
    }

    public String getSuccessMsg() {
        return userHomePage.getSuccessMsg();
    }

    public List<WebElement> getInvalidMsg() {

        return signInPage.getInvalidSignMsg();
    }

    @Override
    public void close() {
        userHomePage.close();
    }
}
