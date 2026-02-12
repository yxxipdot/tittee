package Pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static jdk.internal.misc.ThreadFlock.open;

public class RegistrationPage {
    private final SelenideElement firstName = $("#firstName");      // пример
    private final SelenideElement lastName  = $("#lastName");       // пример
    private final SelenideElement email     = $("[type=email]");    // пример
    private final SelenideElement password  = $("#password");       // пример
    private final SelenideElement confirm   = $("#confirmPassword");// пример
    private final SelenideElement terms     = $("#agree");          // пример чекбокса
    private final SelenideElement submitBtn = $(byText("Create Account")); // пример

    // Контейнер ошибок (общий или по полям). Найдите реальный.
    private SelenideElement errorBox() { return $(".error, .validation, #errors"); }

    @Step("Открыть форму регистрации")
    public RegistrationPage openForm() {
        open("/"); // главная / логин
        // Нажмите ссылку/кнопку "Register/Create Account" → перейти к форме.
        $(byText("Create Account")).shouldBe(visible).click(); // адаптировать локатор
        return this;
    }

    @Step("Заполнить поля: {fn} {ln}, email={em}")
    public RegistrationPage fill(String fn, String ln, String em, String pw, String pw2, boolean acceptTerms) {
        if (fn != null) firstName.setValue(fn);
        if (ln != null) lastName.setValue(ln);
        if (em != null) email.setValue(em);
        if (pw != null) password.setValue(pw);
        if (pw2 != null) confirm.setValue(pw2);
        if (acceptTerms) terms.scrollTo().setSelected(true);
        return this;
    }

    @Step("Отправить форму регистрации")
    public RegistrationPage submit() {
        submitBtn.shouldBe(enabled).click();
        return this;
    }

    @Step("Проверить наличие сообщения об ошибке, содержащее текст: {part}")
    public RegistrationPage shouldSeeErrorContaining(String part) {
        errorBox().shouldBe(visible).shouldHave(text(part));
        return this;
    }

    @Step("Проверить валидацию конкретного поля")
    public RegistrationPage fieldShouldHaveError(SelenideElement field, String cssErrorClass) {
        field.parent().shouldHave(cssClass(cssErrorClass)); // пример: 'has-error'
        return this;
    }
}
