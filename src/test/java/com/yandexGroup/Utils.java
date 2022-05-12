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

//    public static WebDriver myWebDriver = new FirefoxDriver();

//    public static WebDriver getMyDriver(String driverName){
//        WebDriver myWebDriver = null;
//
//        if(driverName.equals("FireFox")){
//            myWebDriver = new FirefoxDriver();
//        }
//        return  myWebDriver;
//
//    }

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


    public static void moveTo(WebDriver driver, SelenideElement target, SelenideElement moveTo){
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("function createEvent(typeOfEvent) {\n" + "var event =document.createEvent(\"CustomEvent\");\n"
                + "event.initCustomEvent(typeOfEvent,true, true, null);\n" + "event.dataTransfer = {\n" + "data: {},\n"
                + "setData: function (key, value) {\n" + "this.data[key] = value;\n" + "},\n"
                + "getData: function (key) {\n" + "return this.data[key];\n" + "}\n" + "};\n" + "return event;\n"
                + "}\n" + "\n" + "function dispatchEvent(element, event,transferData) {\n"
                + "if (transferData !== undefined) {\n" + "event.dataTransfer = transferData;\n" + "}\n"
                + "if (element.dispatchEvent) {\n" + "element.dispatchEvent(event);\n"
                + "} else if (element.fireEvent) {\n" + "element.fireEvent(\"on\" + event.type, event);\n" + "}\n"
                + "}\n" + "\n" + "function simulateHTML5DragAndDrop(element, destination) {\n"
                + "var dragStartEvent =createEvent('dragstart');\n" + "dispatchEvent(element, dragStartEvent);\n"
                + "var dropEvent = createEvent('drop');\n"
                + "dispatchEvent(destination, dropEvent,dragStartEvent.dataTransfer);\n"
                + "var dragEndEvent = createEvent('dragend');\n"
                + "dispatchEvent(element, dragEndEvent,dropEvent.dataTransfer);\n" + "}\n" + "\n"
                + "var source = arguments[0];\n" + "var destination = arguments[1];\n"
                + "simulateHTML5DragAndDrop(source,destination);", target, moveTo);

    }


}
