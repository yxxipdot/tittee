package Pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class RegistrationPage {
    private final SelenideElement firstName = $(byXpath("//*[@id=\"__layout\"]/div/div[2]/section[1]/div/div/div[2]/div/div/form/div/div[1]/div[1]/div/div[2]/div[3]/input"));
    private final SelenideElement lastName  = $(byXpath("//*[@id=\"__layout\"]/div/div[2]/section[1]/div/div/div[2]/div/div/form/div/div[1]/div[2]/div/div[2]/div[3]/input")); //????
    private final SelenideElement email     = $(byXpath("//*[@id=\"__layout\"]/div/div[2]/section[1]/div/div/div[2]/div/div/form/div/div[1]/div[3]/div/div[2]/div[3]/input"));
    private final SelenideElement password = $(byXpath("//*[@id=\"__layout\"]/div/div[2]/section[1]/div/div/div[2]/div/div/form/div/div[1]/div[4]/div/div[2]/div[3]/input"));
    private final SelenideElement confirm = $(byXpath("//*[@id=\"__layout\"]/div/div[2]/section[1]/div/div/div[2]/div/div/form/div/div[1]/div[5]/div/div[2]/div[3]/input"));

    // Контейнер ошибок (общий или по полям). Найдите реальный.
    private SelenideElement getFieldError(String fieldId) {
        return $(By.xpath(fieldId));
    }

    private final SelenideElement generalErrorSummary = $(".alert-error");

    @Step("Открыть форму регистрации")
    public RegistrationPage openForm() {
        open("/"); // Использует baseUrl

        // Кнопка регистрации (лучше искать по части класса или тексту)
        $(".header-desktop__action.action-button--primary").shouldBe(visible).click();

        // Выбор роли "Athlete" и переход дальше
        $(".check-option__box").shouldBe(visible).click();
        $(".action-button--large.action-button--block").shouldBe(visible).click();

        return this;
    }

    @Step("Заполнить поля: {fn} {ln}, email={em}")
    public RegistrationPage fill(String fn, String ln, String em, String pw, String pw2) {
        firstName.shouldBe(visible, Duration.ofSeconds(10)).shouldBe(editable);

        if (fn != null) {
            firstName.sendKeys(Keys.CONTROL + "a");
            firstName.sendKeys(Keys.BACK_SPACE);
            firstName.sendKeys(fn);
        }
        if (ln != null) {
            lastName.sendKeys(Keys.CONTROL + "a");
            lastName.sendKeys(Keys.BACK_SPACE);
            lastName.sendKeys(ln);
        }
        if (em != null) {
            email.sendKeys(Keys.CONTROL + "a");
            email.sendKeys(Keys.BACK_SPACE);
            email.sendKeys(em);
        }
        if (pw != null)
        {
            password.sendKeys(Keys.CONTROL + "a");
            password.sendKeys(Keys.BACK_SPACE);
            password.sendKeys(pw);
        }
        if (pw2 != null) {
            confirm.sendKeys(Keys.CONTROL + "a");
            confirm.sendKeys(Keys.BACK_SPACE);
            confirm.sendKeys(pw2);
        }
        return this;
    }

    @Step("Нажать кнопку регистрации")
    public RegistrationPage submit() {
        sleep(10000);
        //submitBtn = .shouldBe(visible);
        $(byText("Sign Up")).scrollTo().shouldBe(visible, Duration.ofSeconds(10)).click();
        return this;
    }

    @Step("Проверить общее сообщение об ошибке")
    public RegistrationPage checkGeneralError(String expectedText) {
        generalErrorSummary.shouldBe(visible).shouldHave(text(expectedText));
        return this;
    }

    @Step("Проверить текст ошибки для поля {fieldId}")
    public RegistrationPage checkFieldError(String fieldId, String expectedText) {
        SelenideElement se = getFieldError(fieldId);
        se.shouldBe(visible, Duration.ofSeconds(10));
                se.shouldHave(text(expectedText));
        return this;
    }
}
