package objects.product;

import element.DateInput;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import objects.search.LocationType;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CustomWeatherProduct implements Product {
    private String name;
    private LocationType location;
    private Date fromDate;
    private Date toDate;
    private Map<String, Boolean> weatherPara;
    private String unit;
    private Map<String, Boolean> fileFormat;
    private String downLoadOption;

    private String datePeriod;
    private int numberOfLocation;
    private String customWeatherPara;
    private String fileFormatInString;

    public void setCustomWeatherPara() {
        Map<String, Boolean> customPara = new HashMap<>();
        if (name.equals("History Bulk")) {
            customPara.put("Temperature", true);
            customPara.put("Min temperature", true);
            customPara.put("Max temperature", true);
            customPara.put("Feels like", true);
            customPara.put("Wind", true);
            customPara.put("Pressure", true);
            customPara.put("Humidity", true);
            customPara.put("Clouds", true);
            customPara.put("Weather conditions", true);
            customPara.put("Rain", true);
            customPara.put("Snow", true);
        } else if (name.equals("History Forecast Bulk")) {
            customPara.put("Pressure", true);
            customPara.put("Humidity", true);
            customPara.put("Wind", true);
            customPara.put("Clouds", true);
            customPara.put("Dew point", true);
            customPara.put("Precipitation", true);
        }
        if (weatherPara != null) {
            weatherPara.forEach((para, value) ->
                    customPara.remove(para, false)
            );
        }
        customWeatherPara = String.join(" ,", customPara.keySet());
    }

    public void setDatePeriod() {
        datePeriod = DateInput.dateToString("dd-MM-y", fromDate)
                + " - " + DateInput.dateToString("dd-MM-y", toDate);
    }

    public String getFileFormatInString() {
        if (fileFormat == null) {
            fileFormat = new HashMap<>();
            fileFormat.put("CSV", true);
        } else {
            fileFormat.forEach((para, value) ->
                    fileFormat.remove(para, false)
            );
        }
        return String.join(" ,", fileFormat.keySet());
    }
}

