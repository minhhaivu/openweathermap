package objects.product;

import lombok.Getter;

@Getter
public enum WeatherPara {
    TEMPERATURE("Temperature"),
    MIN_TEMP("Min temperature"),
    MAX_TEMP("Max temperature"),
    FEEL_LIKE("Feels like"),
    WIND("Wind"),
    PRESSURE("Pressure"),
    HUMIDITY("Humidity"),
    CLOUDS("Clouds"),
    WEATHER_CONDITIONS("Weather conditions"),
    RAIN("Rain"),
    SNOW("Snow"),
    DEW_POINT("Dew point"),
    PRECIPITATION("Precipitation");

    WeatherPara(String para) {
        this.para = para;
    }

    private final String para;
}
