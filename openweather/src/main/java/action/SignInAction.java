package action;

import element.Menu;
import org.openqa.selenium.WebElement;
import pages.HomePage;
import pages.SignInPage;
import pages.UserHomePage;

import java.util.List;

public class SignInAction extends ActionBase {

    private final HomePage homePage;
    private final SignInPage signInPage;
    private final UserHomePage userHomePage;
    private final Menu homeMenu;

    public SignInAction() {

        super();

        homePage = getPageInstance(HomePage.class);
        signInPage = getPageInstance(SignInPage.class);
        userHomePage = getPageInstance(UserHomePage.class);
        homeMenu = new Menu();
        homeMenu.getInstance();

    }

    public void signIn(String userEmail, String password) {
        homePage.open();
        homeMenu.select("Sign in");
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
