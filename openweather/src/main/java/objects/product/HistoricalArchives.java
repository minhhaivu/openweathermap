package objects.product;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class HistoricalArchives extends Product {
    private String state;
    private String year;

    public HistoricalArchives(String name) {
        super(name);
    }
}
