package objects.product;

import lombok.Getter;

@Getter
public enum Unit {
    METRIC("Metric (Celsius, hPa, meter/sec, mm/h)"),
    IMPERIAL("Imperial (Fahrenheit, hPa, miles/hour, mm/h)"),
    STANDARD("Standard (Kelvin, hPa, meter/sec, mm/h)");

    private final String name;

    Unit(String unit) {
        this.name = unit;
    }
}
