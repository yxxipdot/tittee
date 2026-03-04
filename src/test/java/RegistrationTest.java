import io.qameta.allure.Feature;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import Pages.RegistrationPage;


@Feature("Sign Up Functionality")
public class RegistrationTest extends BaseTest{
    RegistrationPage rp = new RegistrationPage();

    @Test
    @Tag("Critical")
    @DisplayName("Negative tests: the fields must be empty.")
    void EmptyFieldsTest(){
        rp.openForm()
                .submit()
                .checkFieldError("//*[@id=\"__layout\"]/div/div[2]/section[1]/div/div/div[2]/div/div/form/div/div[1]/div[1]/div/div[3]/div", "First name is required")
                .checkFieldError("//*[@id=\"__layout\"]/div/div[2]/section[1]/div/div/div[2]/div/div/form/div/div[1]/div[4]/div/div[3]/div", "Password is required");
    }

    @Test
    @Tag("Neutral")
    @DisplayName("Negative tests: Email is not valid.")
    void InvalidEmailTest(){
        rp.openForm().submit()
                .fill("Vanya", "vanko", "bbeebebebeeb", "password12221112!", "password12221112!")
                .submit().checkFieldError("//*[@id=\"__layout\"]/div/div[2]/section[1]/div/div/div[2]/div/div/form/div/div[1]/div[3]/div/div[3]/div", "Email is not valid");
    }

    @Test
    @Tag("Neutral")
    @DisplayName("Negative tests: password too short.")
    void PasswordTooShortTest(){
        rp.openForm().submit()
                .fill("Vanya", "Vanko", "bbeebe@bebeeb.com", "123!", "123!")
                .submit().checkFieldError("//*[@id=\"__layout\"]/div/div[2]/section[1]/div/div/div[2]/div/div/form/div/div[1]/div[4]/div/div[3]/div","Must be 7-15 characters long, must include: upper-case letter, lower-case letter, number.");
    }

    @Test
    @Tag("Critical")
    @DisplayName("Negative tests: passwords are not the same.")
    void DifferentPasswordsTest(){
        rp.openForm().submit()
                .fill("Maaaa", "BAaaa", "bbeebe@bebeeb.com", "hH1!uiop", "hH1!uip")
                .submit().checkFieldError("//*[@id=\"__layout\"]/div/div[2]/section[1]/div/div/div[2]/div/div/form/div/div[1]/div[5]/div/div[3]/div","Repeated password does not match original one.");
    }

    @Test
    @Tag("Neutral")
    @DisplayName("Negative tests: password is too long.")
    void PasswordTooLongTest(){
        rp.openForm().submit()
                .fill("Maaaa", "BAaaa", "bbeebe@bebeeb.com", "11111111111111111111111111111111111", "11111111111111111111111111111111111")
                .submit().checkFieldError("//*[@id=\"__layout\"]/div/div[2]/section[1]/div/div/div[2]/div/div/form/div/div[1]/div[4]/div/div[3]/div","Must be 7-15 characters long, must include: upper-case letter, lower-case letter, number.");
    }

    @Test
    @Tag("Neutral")
    @DisplayName("Negative tests: only one password field filled.")
    void RepeatPasswordFieldEmptyTest(){
        rp.openForm().submit()
                .fill("Maaaa", "BAaaa", "bbeebe@bebeeb.com", "11111111111111111111111111111111111", "")
                .submit().checkFieldError("//*[@id=\"__layout\"]/div/div[2]/section[1]/div/div/div[2]/div/div/form/div/div[1]/div[5]/div/div[3]/div","Repeat Password is required");
    }
}
