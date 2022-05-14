package com.yandexGroup;

import com.codeborne.selenide.SelenideElement;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import po.AppHeaderLinkPO;
import po.PersonalAccountSettingsPO;
import po.RegistrationNewUserPO;

import java.util.ArrayList;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.page;

public class Utils {

    public static ArrayList<String> getUserData(Integer quantity, Integer signs){
        ArrayList<String> userDataArray = new ArrayList<>();

        for(int i = 0; i< quantity; i++) {
            String userEmail = RandomStringUtils.randomAlphabetic(signs)+"@yandex.ru";
            String userPassword = RandomStringUtils.randomAlphabetic(signs);
            String userName = RandomStringUtils.randomAlphabetic(signs);

            userDataArray.add(userEmail);
            userDataArray.add(userPassword);
            userDataArray.add(userName);
        }
        return userDataArray;
    }
    public static void registerNewUser(String name, String email, String password){
        RegistrationNewUserPO registrationNewUserPO = open("https://stellarburgers.nomoreparties.site/register" , RegistrationNewUserPO.class);
        registrationNewUserPO.registerNewUser(name, email, password,true);
    }

    public static void logoutIfAuthorized(String url){
        AppHeaderLinkPO appHeaderLinkPO = open(url, AppHeaderLinkPO.class);
        appHeaderLinkPO.openPersonalAccount();
        PersonalAccountSettingsPO personalAccountSettingsPO = page(PersonalAccountSettingsPO.class);
        if (personalAccountSettingsPO.logoutIs().equals(true)) {
            personalAccountSettingsPO.logoutPersonalAccount();
            System.out.println("Logout has done");
        } else {
            System.out.println("Logout was not required");
            appHeaderLinkPO.openBurgerConstructor();
        }
    }

}
