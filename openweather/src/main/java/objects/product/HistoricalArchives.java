package objects.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class HistoricalArchives implements Product {
    private String name;
    private String state;
    private String year;
//
//    public HistoricalArchives(String name) {
//        this.name = name;
//    }
}
