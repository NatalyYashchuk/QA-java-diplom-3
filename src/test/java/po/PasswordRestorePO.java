package po;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.time.Duration;

public class PasswordRestorePO {

    // локатор Email 
    @FindBy(how = How.XPATH, using = "//label[contains(text(), 'Email')]/..//input")
    private SelenideElement emailInput;

    // локатор Восстановить кнопки
    @FindBy(how = How.XPATH, using = "//button[contains(text(), 'Восстановить')]")
    private SelenideElement restoreBtn;


    // локатор Войти для уже зарегистрированного пользователя
    @FindBy(how = How.XPATH, using = "//a[contains(@href, 'login')]")
    private SelenideElement loginRegisteredUser;

    @Step("Login  by 'Login' link from  the password restore page.")
    public void loginFromPasswordRestoreClick(){
        loginRegisteredUser.shouldBe(Condition.visible, Duration.ofSeconds(5000)).scrollTo().click();
    }


}
