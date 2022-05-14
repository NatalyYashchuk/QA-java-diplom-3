package test;

import com.codeborne.selenide.WebDriverRunner;
import com.yandexGroup.Utils;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.firefox.FirefoxDriver;
import po.*;

import java.util.ArrayList;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.page;

public class UserLoginTest {
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

        RegistrationNewUserPO registrationNewUserPO = open("https://stellarburgers.nomoreparties.site/register", RegistrationNewUserPO.class);
        registrationNewUserPO.registerNewUser(name, email, password,true);

    }

    @Test
    @DisplayName("Login from MainPage successfully")
    public void testLoginFromMainPageSuccessfully() {
        MainPagePO mainPagePO = open(url, MainPagePO.class);
        mainPagePO.loginToAccountBtnClick();

        AccountLoginPO accountLoginPO3 = page(AccountLoginPO.class);
        accountLoginPO3.loginPersonalAccount(email,password);

        AppHeaderLinkPO appHeaderLinkPO4 = page(AppHeaderLinkPO.class);

        appHeaderLinkPO4.openPersonalAccount();
        PersonalAccountSettingsPO personalAccountSettingsPO = page(PersonalAccountSettingsPO.class);
        String nameToCheck = personalAccountSettingsPO.getPersonalAccountName();

        Assert.assertEquals("Login was not done properly, because name isn't as in Personal_Account settings",
                name, nameToCheck);
    }

    @Test
    @DisplayName("Login from PersonalAccount Page successfully")
    public void testLoginFromPersonalAreaPageSuccessfully() {
        MainPagePO mainPagePO = open(url, MainPagePO.class);

        AppHeaderLinkPO appHeaderLinkPO = page(AppHeaderLinkPO.class);
        appHeaderLinkPO.openPersonalAccount();

        AccountLoginPO accountLoginPO = page(AccountLoginPO.class);
        accountLoginPO.loginPersonalAccount(email,password);

        AppHeaderLinkPO appHeaderLinkPO2 = page(AppHeaderLinkPO.class);
        appHeaderLinkPO2.openPersonalAccount();
        PersonalAccountSettingsPO personalAccountSettingsPO = page(PersonalAccountSettingsPO.class);
        String nameToCheck = personalAccountSettingsPO.getPersonalAccountName();
        Assert.assertEquals("Login was not done properly, because name in Personal_Account settings isn't as required ",
                name, nameToCheck);
    }


    @Test
    @DisplayName("Login from Registration Page successfully")
    public void testLoginFromRegistrationPageSuccessfully() {
        MainPagePO mainPagePO = open(url, MainPagePO.class);

        AppHeaderLinkPO appHeaderLinkPO = page(AppHeaderLinkPO.class);
        appHeaderLinkPO.openPersonalAccount();

        AccountLoginPO accountLoginPO = page(AccountLoginPO.class);
        accountLoginPO.registerNewUserLinkClick();
        RegistrationNewUserPO registrationNewUserPO = page(RegistrationNewUserPO.class);
        registrationNewUserPO.loginRegisteredUserClick();

        AccountLoginPO accountLoginPO2 = page(AccountLoginPO.class);
        accountLoginPO2.loginPersonalAccount(email,password);

        AppHeaderLinkPO appHeaderLinkPO2 = page(AppHeaderLinkPO.class);
        appHeaderLinkPO2.openPersonalAccount();
        PersonalAccountSettingsPO personalAccountSettingsPO = page(PersonalAccountSettingsPO.class);
        String nameToCheck = personalAccountSettingsPO.getPersonalAccountName();

        Assert.assertEquals("Login was not done properly, because name in Personal_Account settings isn't as require",
                name, nameToCheck);
    }

    @Test
    @DisplayName("Login from PasswordRestore Page successfully")
    public void testLoginFromPasswordRestoreSuccessfully() {
        MainPagePO mainPagePO = open(url, MainPagePO.class);

        AppHeaderLinkPO appHeaderLinkPO = page(AppHeaderLinkPO.class);
        appHeaderLinkPO.openPersonalAccount();
        AccountLoginPO accountLoginPO = page(AccountLoginPO.class);
        accountLoginPO.passwordRestoreLinkClick();

        PasswordRestorePO passwordRestorePO = page(PasswordRestorePO.class);
        passwordRestorePO.loginFromPasswordRestoreClick();

        AccountLoginPO accountLoginPO2 = page(AccountLoginPO.class);
        accountLoginPO2.loginPersonalAccount(email,password);

        AppHeaderLinkPO appHeaderLinkPO2 = page(AppHeaderLinkPO.class);
        appHeaderLinkPO2.openPersonalAccount();
        PersonalAccountSettingsPO personalAccountSettingsPO = page(PersonalAccountSettingsPO.class);
        String nameToCheck = personalAccountSettingsPO.getPersonalAccountName();

        Assert.assertEquals("Login was not done properly, because name in Personal_Account settings isn't as requires",
                name, nameToCheck);
    }

@After
public void logout(){
    Utils.logoutIfAuthorized(url);
}
}

