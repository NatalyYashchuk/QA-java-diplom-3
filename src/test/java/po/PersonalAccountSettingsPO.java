package po;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;
import static com.codeborne.selenide.Selenide.sleep;

public class PersonalAccountSettingsPO {

    @Step("Get Account Name from the Personal Account Settings page.")
    public String getPersonalAccountName(){
        SelenideElement nameEdit = $(By.xpath("//label[contains(text(), 'Имя')]/..//input"));
        return nameEdit.getAttribute("value");
    }

    @Step("Logout from the Personal Account.")
    public void logoutPersonalAccount(){
        SelenideElement logoutBtn = $(By.xpath("//button[contains(text(), 'Выход')]"));
        logoutBtn.shouldBe(Condition.visible, (Duration.ofSeconds(10000))).scrollTo().click();
        AccountLoginPO accountLoginPO = page(AccountLoginPO.class);
        accountLoginPO.loginBtnWait();
    }


    public Boolean logoutIs(){
        Boolean logout = false;
        SelenideElement logoutBtn = $(By.xpath("//button[contains(text(), 'Выход')]"));

        if(logoutBtn.shouldBe(Condition.visible, (Duration.ofSeconds(20000))).isDisplayed()) {
            logout = true;
        }
        return logout;
    }
}
