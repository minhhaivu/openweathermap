package objects.product;

import lombok.Getter;

@Getter
public enum FileFormat {
    CSV("CSV"),
    JSON("JSON");

    private final String format;

    FileFormat(String format) {
        this.format = format;
    }
}
