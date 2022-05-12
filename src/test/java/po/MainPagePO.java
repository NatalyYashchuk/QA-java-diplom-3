package po;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;

public class MainPagePO {

    @Step("Open Main Page to login")
    public void loginToAccountBtnClick(){
        SelenideElement buttonLogin = $(By.xpath("//section[contains(@class, 'BurgerConstructor_basket')]//button[contains(text(), 'Войти в аккаунт')]"));
        buttonLogin.shouldBe(Condition.visible, (Duration.ofSeconds(5000))).click();
    }


}
