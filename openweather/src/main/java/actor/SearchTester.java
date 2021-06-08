package actor;

import action.SearchAction;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SearchTester {

    private static SearchTester instance;

    public final SearchAction searchAction = new SearchAction();

    public static SearchTester getInstance() {
        if (instance == null) {
            instance = new SearchTester();
        }

        return instance;
    }
}
