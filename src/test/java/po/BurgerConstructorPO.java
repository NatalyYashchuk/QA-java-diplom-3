package po;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.yandexGroup.Utils;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.actions;

public class BurgerConstructorPO {

    //локатор Заголовок страницы
    @FindBy(how = How.XPATH, using = "//h1[text()='Соберите бургер']")
    private SelenideElement headerPage;

    // локатор Button "Булки"
    @FindBy(how = How.XPATH, using = "//section[contains(@class, 'BurgerIngredients_ingredients')]//div[contains(@class, 'tab_tab')]//span[text()='Булки']")
    private SelenideElement bunsButton;

    // локатор Раздел выбран
    @FindBy(how = How.XPATH, using = "//div[contains(@class, 'tab_tab__1SPyG tab_tab_type_current__2BEPc pt-4')]")
    private SelenideElement sectionSelected;


    // локатор Button "Соусы"
    @FindBy(how = How.XPATH, using = "//section[contains(@class, 'BurgerIngredients_ingredients')]//div[contains(@class, 'tab_tab')]//span[text()='Соусы']")
    private SelenideElement sauceButton;

    // локатор  "Соусы" выбранные
    @FindBy(how = How.XPATH, using = "//span[contains(text(), 'Соусы')]/../..//div[contains(@class, 'tab_tab_type_current__2BEPc')]")
    private SelenideElement sauceSelected;


    // локатор Button "Начинки"
    @FindBy(how = How.XPATH, using = "//section[contains(@class, 'BurgerIngredients_ingredients')]//div[contains(@class, 'tab_tab')]//span[text()='Начинки']")
    private SelenideElement fillingsButton;

    // локатор  "Начинки" выбранные
    @FindBy(how = How.XPATH, using = "//span[contains(text(), 'Начинки')]/../..//div[contains(@class, 'tab_tab_type_current__2BEPc')]")
    private SelenideElement fillingsSelected;

    // локатор ингредиент вкладка Булки "Флюоресцентная булка"
    @FindBy(how = How.XPATH, using = "//p[text()='Флюоресцентная булка R2-D3']/..//img")
    private SelenideElement bun1;


    // локатор ингредиент вкладка Соусы "Соус Spicy-X"
        @FindBy(how = How.XPATH, using = "//p[text()='Соус Spicy-X']/..//img")
    private SelenideElement sauce1;

    // локатор ингредиент вкладка Соусы "Соус с шипами Антарианского плоскоходца"
    @FindBy(how = How.XPATH, using = "//p[text()='Соус с шипами Антарианского плоскоходца']/..//img")
    private SelenideElement sauce2;

    // локатор ингредиент вкладка Начинки "Мясо бессмертных моллюсков Protostomia"
    @FindBy(how = How.XPATH, using = "//p[text()='Мясо бессмертных моллюсков Protostomia']/..//img")
    private SelenideElement filling1;

    // локатор ингредиент вкладка Начинки "Филе Люминесцентного тетраодонтимформа"
    @FindBy(how = How.XPATH, using = "//p[text()='Филе Люминесцентного тетраодонтимформа']/..//img")
    private SelenideElement filling2;

    // локатор  "Перетяните булочку сюда (верх)" выбранные
        @FindBy(how = How.XPATH, using = "//span[contains(text(), 'Перетяните булочку сюда (верх)')]")
        private SelenideElement burgerConstructor;

    // локатор  "Бургер Кoнструктор. Булочка верх " выбранные
    @FindBy(how = How.XPATH, using = "(//section[contains(@class, 'BurgerConstructor_basket')]" +
            "//span[contains(@class, 'constructor-element__row')]/span)[1]")
    private SelenideElement bunTopInConstructor;

    // локатор  "Бургер Кoнструктор. Соус " выбранные
    @FindBy(how = How.XPATH, using = "(//section[contains(@class, 'BurgerConstructor_basket')]" +
            "//span[contains(@class, 'constructor-element__row')]/span)[2]")
    private SelenideElement sauceInConstructor;

    // локатор  "Бургер Кoнструктор. Ингреиент " выбранные
    @FindBy(how = How.XPATH, using = "//section[contains(@class, 'BurgerConstructor_basket')]" +
            "//span[contains(@class, 'constructor-element__row')]/span")
    private SelenideElement ingredient;

    // локатор  "Бургер Кoнструктор. Начинки " выбранные
    @FindBy(how = How.XPATH, using = "(//section[contains(@class, 'BurgerConstructor_basket')]" +
            "//span[contains(@class, 'constructor-element__row')]/span)[3]")
    private SelenideElement fillingsInConstructor;

    @Step ("Scroll to buns")
    public void scrollToBuns (){
        bun1.scrollIntoView(false);
        bun1.shouldBe(Condition.visible, Duration.ofSeconds(10000));
    }

    @Step("Section_Fillings content should be available - true.")
    public Boolean sectionBunsIsAvailable(){
        bun1.shouldBe(Condition.enabled, Duration.ofSeconds(10000));
        String sectionName = sectionSelected.getText();
        Boolean fillingIsAvailable = false;
        if(sectionName.equals("Булки")){
            fillingIsAvailable = true;
        }
        return  fillingIsAvailable;
    }



    @Step ("Scroll to fillings")
    public void scrollToFillings (){
        filling2.scrollIntoView(false);
        filling2.shouldBe(Condition.visible, Duration.ofSeconds(10000));
    }

    @Step("Section_Fillings content should be available - true.")
    public Boolean sectionFillingsIsAvailable(){
        filling2.shouldBe(Condition.enabled, Duration.ofSeconds(10000));
        String sectionName = sectionSelected.getText();
        Boolean fillingIsAvailable = false;
        if(sectionName.equals("Начинки")){
            fillingIsAvailable = true;
        }
        return  fillingIsAvailable;
    }



    @Step ("Scroll to Sauces")
    public void scrollToSauces (){
        sauce2.scrollIntoView(false);
        sauce2.shouldBe(Condition.visible, Duration.ofSeconds(10000));
    }

    @Step("Section_Sauces content should be available - true.")
    public Boolean sectionSaucesIsAvailable(){
        sauce2.shouldBe(Condition.enabled, Duration.ofSeconds(10000));
        String sectionName = sectionSelected.getText();
        Boolean SaucesIsAvailable = false;
        if(sectionName.equals("Соусы")){
            SaucesIsAvailable = true;
        }
        return  SaucesIsAvailable;
    }



    @Step("Check an ingerient in the burger_constructor_list")
    public Boolean ingredientsInConstructorIs(String ingredientRequiredName){
        Boolean ingredientInConstructor = false;
        ElementsCollection elementsList = $$(By.xpath("//section[contains(@class, 'BurgerConstructor_basket')]" +
                "//span[contains(@class, 'constructor-element__text')]"));

        int imax = elementsList.size();
        List<String> ingredientNames = new ArrayList<>();

        for(int i = 0; i < imax; i++){
            String name = elementsList.get(i).getText();
            ingredientNames.add(name);
            System.out.println(i + " "+name);
        }
        ingredientInConstructor = ingredientNames.contains(ingredientRequiredName);
          return ingredientInConstructor;
        }


    @Step("Get Bun Name in burger_constructor_list")
    public String getBurgerConstructorBuns(){
        return bunTopInConstructor.getText();
    }

    @Step("Get Sauce Name in burger_constructor_list")
    public String getBurgerConstructorSauce(){
        return sauceInConstructor.getText();
    }

    @Step("Get Fillins Name in burger_constructor_list")
    public String getBurgerConstructorFilling(){
        return fillingsInConstructor.getText();
    }


    public void headerPageWait(){
        headerPage.shouldBe(Condition.visible, Duration.ofSeconds(10000));
    }

    @Step("Click ingredients tab Buns")
    public void bunsClick(){
        bunsButton.shouldBe(Condition.enabled, (Duration.ofSeconds(10000)));
        actions().moveToElement(bunsButton).click().perform();
    }

    @Step("Click ingredients tab Sauces")
    public void sauceClick(){
        sauceButton.shouldBe(Condition.enabled, (Duration.ofSeconds(10000)));
        actions().moveToElement(sauceButton).click().perform();
    }

    @Step("Click ingredients tab Fillings")
    public void fillingsClick(){
        fillingsButton.shouldBe(Condition.enabled, (Duration.ofSeconds(10000)));
        actions().moveToElement(fillingsButton).click().perform();

    }


}
