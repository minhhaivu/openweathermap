package objects.product;

import lombok.Getter;
import lombok.Setter;
import objects.search.LocationType;

import java.util.Date;
import java.util.List;

@Setter
@Getter
public class CustomWeatherProduct extends Product {
    private LocationType location;
    private Date fromDate;
    private Date toDate;
    private List<String> unselectedWeatherPara;
    private String unit;
    private List<String> fileFormat;
    private String downLoadOption;

    public CustomWeatherProduct(String name) {
        super(name);
    }

    @SuppressWarnings("java:S107")
    public CustomWeatherProduct(String name, LocationType location, Date from, Date to,
                                List<String> unselectedWeatherPara, String unit,
                                List<String> fileFormat, String downLoadOption) {
        super(name);
        this.location = location;
        this.fromDate = from;
        this.toDate = to;
        this.unselectedWeatherPara = unselectedWeatherPara;
        this.unit = unit;
        this.fileFormat = fileFormat;
        this.downLoadOption = downLoadOption;
    }
}

