package objects.search;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Import implements LocationType {
    private final String filePath;

    @Override
    public Object getInfo() {
        return filePath;
    }
}
