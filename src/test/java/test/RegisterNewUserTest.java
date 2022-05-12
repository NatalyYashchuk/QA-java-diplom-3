package test;

import com.yandexGroup.Utils;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import po.AppHeaderLinkPO;
import po.MainPagePO;
import po.AccountLoginPO;
import po.RegistrationNewUserPO;

import java.util.ArrayList;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.page;

public class RegisterNewUserTest {
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
        passwordIncorrect = "123";
        name = userData.get(2);

    }



    @Test
    @DisplayName("Register from Login Page successfully")
    public void testUserRegisterFromLoginPageSuccessfully() {
        MainPagePO mainPagePO = open(url, MainPagePO.class);
        mainPagePO.loginToAccountBtnClick();

        AccountLoginPO accountLoginPO = page(AccountLoginPO.class);
        accountLoginPO.registerNewUserLinkClick();

        RegistrationNewUserPO registrationNewUserPO = page(RegistrationNewUserPO.class);
        registrationNewUserPO.registerNewUser(name, email, password,true);

        AccountLoginPO accountLoginPO2 = page(AccountLoginPO.class);
        Assert.assertTrue("Register from Login Page failed",accountLoginPO2.isLoginButton());
    }

    @Test
    @DisplayName("User account registration failed because of 4 less then 6 password signs")
    public void testUserRegisterFromLoginPageFailed() {
        MainPagePO mainPagePO = open(url, MainPagePO.class);
        mainPagePO.loginToAccountBtnClick();

        AccountLoginPO accountLoginPO = page(AccountLoginPO.class);
        accountLoginPO.registerNewUserLinkClick();

        RegistrationNewUserPO registrationNewUserPO = page(RegistrationNewUserPO.class);
        password = passwordIncorrect;
        registrationNewUserPO.registerNewUser(name, email, password,true);
        RegistrationNewUserPO registrationNewUserPO2 = page(RegistrationNewUserPO.class);

        Assert.assertEquals("Error is absent","Некорректный пароль", registrationNewUserPO2.errorMessageGet());
    }

}
