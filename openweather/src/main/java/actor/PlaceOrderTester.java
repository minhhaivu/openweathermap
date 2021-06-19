package actor;

import action.PlaceOrderAction;

public class PlaceOrderTester {

    private static PlaceOrderTester instance;

    public final PlaceOrderAction placeOrderAction = new PlaceOrderAction();

    public static PlaceOrderTester getInstance () {
        if(instance==null) {
            instance = new PlaceOrderTester();
        }
        return instance;
    }


}
