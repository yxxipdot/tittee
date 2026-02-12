import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.testng.annotations.*;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.Allure;
import java.io.ByteArrayInputStream;

public abstract class BaseTest {
    @BeforeClass
    public void globalSetup() {
        Configuration.baseUrl = "https://log.finalsurge.com";
        Configuration.timeout = 8000;
        Configuration.browser = System.getProperty("browser", "chrome");
        Configuration.headless = Boolean.parseBoolean(System.getProperty("headless", "false"));
        Configuration.screenshots = true;
        Configuration.savePageSource = true;
    }

    @BeforeMethod
    public void addAllureListener() {
        SelenideLogger.addListener("AllureSelenide",
                new AllureSelenide().screenshots(true).savePageSource(true));
    }

    @AfterMethod(alwaysRun = true)
    public void removeAllureListener() {
        SelenideLogger.removeListener("AllureSelenide");
    }
}


