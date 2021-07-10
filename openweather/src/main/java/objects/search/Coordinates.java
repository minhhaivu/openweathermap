package objects.search;

import objects.LocationType;

import java.util.HashMap;
import java.util.Map;

public class Coordinates implements LocationType {
    private String longitude;
    private String latitude;

    @Override
    public Object getInfo() {
        Map<String, String> map = new HashMap<>();
        map.put("LONG", longitude);
        map.put("LAT", latitude);

        return map;
    }
}
