package objects.product;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Setter;

@Setter
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class OrderDetail {

    private String periodTime;
    private String noOfLocations;
    private String weatherPara;
    private String fileFormat;
    private String unit;
    private String downLoadOption;

}
