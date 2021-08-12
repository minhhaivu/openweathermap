package objects.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import objects.search.LocationType;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

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
    @Builder.Default
    private String unit = Unit.IMPERIAL.getName();
    @Builder.Default
    private Map<String, Boolean> fileFormat = defaultFormat();
    @Builder.Default
    private String downLoadOption = DownLoadOption.ALL_LOCATION.getOption();

    public CustomWeatherProduct historyBulkParse(OrderDetail orderDetail) {
        return parse(historyBulkWeatherPara(), orderDetail);
    }

    public CustomWeatherProduct historyForecastBulkParse(OrderDetail orderDetail) {
        return parse(historyBulkForecastWeatherPara(), orderDetail);
    }

    private CustomWeatherProduct parse(Map<String, Boolean> weatherPara, OrderDetail orderDetail) {
        String[] fromTo = orderDetail.periodTime.split(" - ");
        SimpleDateFormat oldFormat = new SimpleDateFormat("dd-MM-yyyy");
        SimpleDateFormat newFormat = new SimpleDateFormat("dd-MMM-yyyy");
        Date from = newFormat.parse(newFormat.format
                (oldFormat.parse(fromTo[0], new ParsePosition(0))), new ParsePosition(0));
        Date to = newFormat.parse(newFormat.format
                (oldFormat.parse(fromTo[1], new ParsePosition(0))), new ParsePosition(0));

        String[] weather = orderDetail.weatherPara.split(", ");
        if (weather.length == weatherPara.size()) {
            weatherPara = null;
        } else {
            for (String para : weather) {
                weatherPara.remove(para, true);
            }
            for (String para : weatherPara.keySet()) {
                weatherPara.merge(para, true, (oldValue, newValue) -> false);
            }
        }

        Map<String, Boolean> fileFormatOpt = new HashMap<>();
        String[] format = orderDetail.fileFormats.split(", ");
        for (String f : format) {
            fileFormatOpt.put(f, true);
        }
        return CustomWeatherProduct.builder()
                .fromDate(from)
                .toDate(to)
                .weatherPara(weatherPara)
                .unit(orderDetail.units)
                .fileFormat(fileFormatOpt)
                .downLoadOption(orderDetail.downLoadOption)
                .build();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        CustomWeatherProduct product = (CustomWeatherProduct) obj;
        return getFromDate().equals(product.getFromDate()) &&
                getToDate().equals(product.getToDate()) &&
                Objects.equals(getWeatherPara(), product.getWeatherPara()) &&
                Objects.equals(getUnit(), product.getUnit()) &&
                Objects.equals(getFileFormat(), product.getFileFormat()) &&
                Objects.equals(getDownLoadOption(), product.getDownLoadOption());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getLocation(), getFromDate(), getToDate(),
                getWeatherPara(), getUnit(), getFileFormat(), getDownLoadOption());
    }

    private Map<String, Boolean> historyBulkWeatherPara() {
        Map<String, Boolean> weatherParameter = new HashMap<>();
        weatherParameter.put(WeatherPara.TEMPERATURE.getPara(), true);
        weatherParameter.put(WeatherPara.MIN_TEMP.getPara(), true);
        weatherParameter.put(WeatherPara.MAX_TEMP.getPara(), true);
        weatherParameter.put(WeatherPara.FEEL_LIKE.getPara(), true);
        weatherParameter.put(WeatherPara.WIND.getPara(), true);
        weatherParameter.put(WeatherPara.PRESSURE.getPara(), true);
        weatherParameter.put(WeatherPara.HUMIDITY.getPara(), true);
        weatherParameter.put(WeatherPara.CLOUDS.getPara(), true);
        weatherParameter.put(WeatherPara.WEATHER_CONDITIONS.getPara(), true);
        weatherParameter.put(WeatherPara.RAIN.getPara(), true);
        weatherParameter.put(WeatherPara.SNOW.getPara(), true);
        return weatherParameter;
    }

    private Map<String, Boolean> historyBulkForecastWeatherPara() {
        Map<String, Boolean> weatherParameter = new HashMap<>();
        weatherParameter.put(WeatherPara.PRESSURE.getPara(), true);
        weatherParameter.put(WeatherPara.HUMIDITY.getPara(), true);
        weatherParameter.put(WeatherPara.WIND.getPara(), true);
        weatherParameter.put(WeatherPara.CLOUDS.getPara(), true);
        weatherParameter.put(WeatherPara.DEW_POINT.getPara(), true);
        weatherParameter.put(WeatherPara.PRECIPITATION.getPara(), true);
        return weatherParameter;
    }

    private static Map<String, Boolean> defaultFormat() {
        Map<String, Boolean> option = new HashMap<>();
        option.put("CSV", true);
        return option;
    }
}

