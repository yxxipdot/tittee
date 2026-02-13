import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import Pages.RegistrationPage;

public class RegistrationTest extends BaseTest{
    RegistrationPage rp = new RegistrationPage();

    @Test
    @DisplayName("Negative tests: the fields must be empty.")
    void EmptyFieldsTest(){
        rp.openForm()
                .submit()
                .checkFieldError("FirstName", "First name is required")
                .checkFieldError("Password", "Password is required");
    }

    @Test
    void InvalidEmailTest(){
        rp.openForm().submit()
                .fill("Vanya", "vanko", "bbeebe@bebeeb", "password12221112!", "password12221112!")
                .submit().checkFieldError("Email", "Email is not valid");
    }
}
