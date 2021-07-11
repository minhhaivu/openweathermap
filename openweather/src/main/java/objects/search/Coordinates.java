package objects.search;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.HashMap;
import java.util.Map;

@Setter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
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
