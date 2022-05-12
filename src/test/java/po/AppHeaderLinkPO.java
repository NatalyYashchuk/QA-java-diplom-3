package po;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;

public class AppHeaderLinkPO {

    @Step("Open user personal account")
    public void openPersonalAccount() {
    SelenideElement personalArea = $(By.xpath("//p[contains(text(), 'Личный Кабинет')]"));
    personalArea.shouldBe(Condition.visible, (Duration.ofSeconds(10000))).scrollTo().click();
    }

    @Step("Open user order list")
    public void openOrdersList() {
        SelenideElement ordersListBtnOpen = $(By.xpath("//p[contains(text(), 'Лента Заказов')]"));
        ordersListBtnOpen.shouldBe(Condition.visible, (Duration.ofSeconds(10000))).click();
    }

    @Step("Open burger constructor")
    public void openBurgerConstructor() {
        SelenideElement ordersListBtnOpen = $(By.xpath("//p[contains(text(), 'Конструктор')]"));
        ordersListBtnOpen.shouldBe(Condition.visible, (Duration.ofSeconds(10000))).click();
    }

}
