import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.chrome.ChromeOptions;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.Allure;
import java.io.ByteArrayInputStream;

import static com.codeborne.selenide.Selenide.closeWebDriver;

public abstract class BaseTest {

        static{
            Configuration.baseUrl = "https://www.finalsurge.com";
            Configuration.browser = System.getProperty("browser", "chrome");
            Configuration.timeout = 5000; // 10 секунд вполне достаточно
            Configuration.pageLoadStrategy = "eager";
            Configuration.screenshots = true;
            Configuration.savePageSource = true;

            ChromeOptions options = new ChromeOptions();
            options.addArguments("--remote-allow-origins=*", "--disable-gpu", "--no-sandbox");
            Configuration.browserCapabilities = options;

            SelenideLogger.addListener("AllureSelenide", new AllureSelenide()
                    .screenshots(true)
                    .savePageSource(true)
            );
        }

    /*@BeforeEach
    public void addAllureListener() {
        SelenideLogger.addListener("AllureSelenide",
                new AllureSelenide().screenshots(true).savePageSource(true));
    }

    @AfterEach
    public void removeAllureListener() {
        SelenideLogger.removeListener("AllureSelenide");
    }*/

    @AfterEach
    void tearDown() {
        // Закрываем браузер после каждого теста для чистоты окружения
        closeWebDriver();
    }
}


