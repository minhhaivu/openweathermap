package actor;

import action.SignInAction;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SignInTester {
    private static SignInTester instance;

    public final SignInAction signInAction = new SignInAction();

    public static SignInTester getInstance() {
        if (instance == null) {
            instance = new SignInTester();
        }

        return instance;
    }

}
