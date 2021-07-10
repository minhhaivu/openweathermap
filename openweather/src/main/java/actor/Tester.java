package actor;

import action.PlaceOrderAction;
import action.SearchAction;
import action.SignInAction;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Tester {
    private static Tester instance;
    public final PlaceOrderAction placeOrderAction = new PlaceOrderAction();
    public final SignInAction signInAction = new SignInAction();
    public final SearchAction searchAction = new SearchAction();

    public static Tester getInstance() {
        if (instance == null) {
            instance = new Tester();
        }
        return instance;
    }
}
