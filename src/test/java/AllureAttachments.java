import com.codeborne.selenide.Selenide;
import io.qameta.allure.Allure;

import java.io.ByteArrayInputStream;

public final class AllureAttachments {
    private AllureAttachments(){}

    public static void attachConsoleLog(String name, String text) {
        Allure.addAttachment(name, new ByteArrayInputStream(text.getBytes()));
    }

    public static void attachCurrentUrl() {
        Allure.addAttachment("Current URL",
                new ByteArrayInputStream(Selenide.webdriver().driver().url().getBytes()));
    }
}
