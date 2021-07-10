package objects.search;

import lombok.AllArgsConstructor;
import objects.LocationType;

@AllArgsConstructor
public class Import implements LocationType {
    private String filePath;

    @Override
    public Object getInfo() {
        return filePath;
    }
}
