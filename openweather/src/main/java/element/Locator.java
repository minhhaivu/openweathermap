package element;

import org.openqa.selenium.By;

public class Locator {
    public static By buttonContainText(String text) {
        return xpathTagContainText("button", text);
    }

    public static By tdContainText(String text) {
        return xpathTagContainText("td", text);
    }

    public static By xpathContain(String prefix, String text, String suffix) {
        return By.xpath(prefix + text + suffix);
    }

    public static By xpathTagContainText(String tag, String text) {
        return By.xpath("//" + tag + "[contains(text(),'" + text + "')]");
    }

}
