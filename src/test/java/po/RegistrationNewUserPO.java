package po;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;

public class RegistrationNewUserPO {

    // локатор Войти для уже зарегистрированного пользователя по линку 'Войти'
    @FindBy(how = How.XPATH, using = "//a[contains(@href, 'login')]")
    private SelenideElement loginRegisteredUser;


    // локатор Имя
    @FindBy(how = How.XPATH, using = "//form[contains(@class, 'Auth_form')]//label[contains(text(), 'Имя')]/..//input")
    private SelenideElement nameInput;


    // локатор Email 
    @FindBy(how = How.XPATH, using = "//form[contains(@class, 'Auth_form')]//label[contains(text(), 'Email')]/..//input")
    private SelenideElement emailInput;

    // локатор Password
    @FindBy(how = How.XPATH, using = "//form[contains(@class, 'Auth_form')]//input[contains(@type, 'password')]/..//input")
    private SelenideElement passwordInput;

    // локатор Зарегистрировать нового пользователя
    @FindBy(how = How.XPATH, using = "//button[contains(text(), 'Зарегистрироваться')]")
    private SelenideElement registerUserBtn;


    @Step("Register new user from Registration page.")
    public  void registerNewUser(String name, String email, String password, Boolean register) {
        nameInput.shouldBe(Condition.visible, (Duration.ofSeconds(20000))).scrollTo().click();
        nameInput.clear();
        nameInput.setValue(name);

        emailInput.scrollTo().click();
        emailInput.clear();
        emailInput.setValue(email);

        passwordInput.scrollTo().click();
        passwordInput.clear();
        passwordInput.setValue(password);

        if(register.equals(true)) {
            registerUserBtn.scrollTo().click();
        }

    }

    @Step("Login by link 'Login' from Registration page.")
    public void loginRegisteredUserClick() {
        loginRegisteredUser.shouldBe(Condition.visible, (Duration.ofSeconds(5000))).scrollTo().click();
    }


    @Step("Get an error message if password has an incorrect format during a Registration.")
    public String errorMessageGet(){
        SelenideElement error = $(By.xpath("//p[contains(@class, 'input__error')]"));
        return  error.getText();
    }
}
