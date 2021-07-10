package objects.search;

public class Location implements LocationType {
    private String location;

    public Location(String location) {
        this.location=location;
    }
    @Override
    public Object getInfo() {
        return location;
    }
}
