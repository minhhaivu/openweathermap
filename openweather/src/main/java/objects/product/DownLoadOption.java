package objects.product;

import lombok.Getter;

@Getter
public enum DownLoadOption {
    ALL_LOCATION ("All locations in a single file"),
    PER_LOCATION (" One file per location ");

    DownLoadOption(String option) {
        this.option = option;
    }
    private final String option;
}
