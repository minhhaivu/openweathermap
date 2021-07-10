package objects.search;

import objects.LocationType;

public class Location implements LocationType {
    private String location;

    @Override
    public Object getInfo() {
        return location;
    }
}
