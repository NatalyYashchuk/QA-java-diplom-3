package test;

import com.yandexGroup.Utils;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import po.*;

import java.util.ArrayList;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.page;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class SwitchIngredientsTabTest {
    private ArrayList<String> userData;
    private String url;

    int fieldsSet;
    int signsQuantity;
    String email;
    String password;
    String name;
    String passwordIncorrect;

    @Before
    public void setUp() {
        url = "https://stellarburgers.nomoreparties.site";
        fieldsSet = 1;
        signsQuantity = 10;

        userData = Utils.getUserData(fieldsSet, signsQuantity);

        email = userData.get(0);
        password = userData.get(1);
        name = userData.get(2);

//        Utils.logoutIfAuthorized(url);

        RegistrationNewUserPO registrationNewUserPO = open("https://stellarburgers.nomoreparties.site/register", RegistrationNewUserPO.class);
        registrationNewUserPO.registerNewUser(name, email, password,true);
        System.out.println("Name = " + name + "  email = " + email + " password = " + password);
        AccountLoginPO accountLoginPO = page(AccountLoginPO.class);
       accountLoginPO.loginPersonalAccount(email,password);

    }



    @Test
    @DisplayName("Tab_Buns has been switched to successfully")
    public void testSwitchToTabBunsSuccessfully() {
//        AppHeaderLinkPO appHeaderLinkPO = open(url,AppHeaderLinkPO.class);
        AppHeaderLinkPO appHeaderLinkPO = page(AppHeaderLinkPO.class);
        appHeaderLinkPO.openBurgerConstructor();
        BurgerConstructorPO burgerConstructorPO = page(BurgerConstructorPO.class);
        burgerConstructorPO.fillingsClick();
        burgerConstructorPO.bunsClick();

        WebDriver webDriver = getWebDriver();
        burgerConstructorPO.moveBunToConstructor(webDriver);

        Assert.assertTrue("Tab Buns hasn't been switched to successfully because of" +
                " content is not available to BurgerConstructor. " +
                "Required top bun is not in a Burger Constructor ", burgerConstructorPO.ingredientsInConstructorIs("Флюоресцентная булка R2-D3 (верх)"));
    }


    @Test
    @DisplayName("Following to Sauces has been done successfully")
    public void testSwitchToTabSaucesSuccessfully() {
        AppHeaderLinkPO appHeaderLinkPO = page(AppHeaderLinkPO.class);
        appHeaderLinkPO.openBurgerConstructor();

        BurgerConstructorPO burgerConstructorPO = page(BurgerConstructorPO.class);
        burgerConstructorPO.sauceClick();

        WebDriver webDriver = getWebDriver();
        burgerConstructorPO.moveSauceToConstructor(webDriver);

        Assert.assertTrue("Tab Sauces haven't been switched to successfully because of" +
                " content is not available to BurgerConstructor. " +
                "Required sauce is not in a Burger Constructor ", burgerConstructorPO.ingredientsInConstructorIs("Соус Spicy-X"));

    }

    @Test
    @DisplayName("Following to Fillings has been done successfully")
    public void testFollowingToFillingsSuccessfully() {
        AppHeaderLinkPO appHeaderLinkPO = page(AppHeaderLinkPO.class);
        appHeaderLinkPO.openBurgerConstructor();

        BurgerConstructorPO burgerConstructorPO = page(BurgerConstructorPO.class);
        burgerConstructorPO.fillingsClick();

        WebDriver webDriver = getWebDriver();
        burgerConstructorPO.moveFillingToConstructor(webDriver);

        Assert.assertTrue("Tab Fillings haven't been switched to successfully because of" +
                " content is not available to BurgerConstructor. " +
                "Required sauce is not in a Burger Constructor ",
                burgerConstructorPO.ingredientsInConstructorIs("Мясо бессмертных моллюсков Protostomia"));
    }

@After
    public void logout(){
    Utils.logoutIfAuthorized(url);
}

}