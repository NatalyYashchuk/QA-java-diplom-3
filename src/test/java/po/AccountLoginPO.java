package po;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.page;

public class AccountLoginPO {
    // локатор Button "Войти" в Личный Кабинет 
    @FindBy(how = How.XPATH, using = "//form[contains(@class, 'Auth_form')]//button[contains(text(), 'Войти')]")
    private SelenideElement personalAreaInButton;

    // локатор Email 
    @FindBy(how = How.XPATH, using = "//label[contains(text(), 'Email')]/..//input")
    private SelenideElement emailInput;

    // локатор Password
    @FindBy(how = How.XPATH, using = "//label[contains(text(), 'Пароль')]/..//input")
    private SelenideElement passwordInput;

    // локатор Зарегистрироваться новому пользователю
    @FindBy(how = How.XPATH, using = "//a[contains(@href, 'register')]")
    private SelenideElement  registerNewUserLink;

    // локатор Восстановить пароль
    @FindBy(how = How.XPATH, using = "//a[contains(@href, 'forgot-password')]")
    private SelenideElement  passwordRestore;

    @Step("User Login make from Account login page")
    public  void loginPersonalAccount(String email, String password) {
        emailInput.shouldBe(Condition.visible, (Duration.ofSeconds(5000))).scrollTo().click();
        emailInput.clear();
        emailInput.sendKeys(email);

        passwordInput.shouldBe(Condition.visible, (Duration.ofSeconds(5000))).scrollTo().click();
        passwordInput.clear();
        passwordInput.sendKeys(password);

        personalAreaInButton.scrollTo().click();
        BurgerConstructorPO burgerConstructorPO = page(BurgerConstructorPO.class);
        burgerConstructorPO.headerPageWait();
    }

    @Step("'Register' link click from Account login page")
    public void registerNewUserLinkClick() {
        registerNewUserLink.shouldBe(Condition.visible, (Duration.ofSeconds(5000))).scrollTo().click();
    }

    @Step("'Restore password' link from Account login page")
    public void passwordRestoreLinkClick() {
        passwordRestore.shouldBe(Condition.visible, (Duration.ofSeconds(5000))).scrollTo().click();
    }

    public void loginBtnWait(){
        personalAreaInButton.shouldBe(Condition.enabled,(Duration.ofSeconds(10000)));
    }

    @Step("Check whether login is possible.It is If button is visible.")
    public Boolean isLoginButton(){
        Boolean isVisible = false;
        if(personalAreaInButton.shouldBe(Condition.visible, (Duration.ofSeconds(5000))).isDisplayed()) {
            isVisible = true;
        }
        return isVisible;
    }


}
