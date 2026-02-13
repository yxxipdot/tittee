package Pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class RegistrationPage {
    private final SelenideElement firstName = $(byName("firstName"));
    private final SelenideElement lastName  = $(byName("lastName")); //????
    private final SelenideElement email     = $(byName("email"));
    private final SelenideElement password  = $("#Password");
    private final SelenideElement confirm   = $("#RepeatPassword");
    private final SelenideElement timezoneList   = $("#BTZ"); // Таймзона часто обязательна
    private SelenideElement submitBtn; // или кнопка внутри формы

    // Контейнер ошибок (общий или по полям). Найдите реальный.
    private SelenideElement getFieldError(String fieldId) {
        return $(By.xpath("//span[@data-valmsg-for='" + fieldId + "']"));
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
        sleep(5000);
        if (fn != null) firstName.setValue(fn);
        if (ln != null) lastName.setValue(ln);
        if (em != null) email.setValue(em);
        if (pw != null) password.setValue(pw);
        if (pw2 != null) confirm.setValue(pw2);
        return this;
    }

    @Step("Нажать кнопку регистрации")
    public RegistrationPage submit() {
        sleep(5000);
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
        getFieldError(fieldId).shouldBe(visible).shouldHave(text(expectedText));
        return this;
    }
}
