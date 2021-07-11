package objects.product;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class CustomWeatherOrderDetail {
    private String periodTime;
    private String noOfLocations;
    private String weatherPara;
    private String fileFormat;
    private String unit;
    private String downLoadOption;

}
