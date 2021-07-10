package actor;

import action.CustomWeatherProductsAction;
import action.HistoricalDataArchivesAction;
import action.SearchAction;
import action.SignInAction;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Tester {
    private static Tester instance;

    public final SignInAction signInAction = new SignInAction();
    public final SearchAction searchAction = new SearchAction();
    public final CustomWeatherProductsAction customWeatherProductsAction
            = new CustomWeatherProductsAction();
    public final HistoricalDataArchivesAction historicalDataArchivesAction
            = new HistoricalDataArchivesAction();


    public static Tester getInstance() {
        if (instance == null) {
            instance = new Tester();
        }
        return instance;
    }
}
