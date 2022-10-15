package com.mycompany.app;

import com.codeborne.selenide.Condition;
import org.junit.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;


public class MaxPaymentCalcTest {
    @Test
    public void DefaultValuesAreCorrect() {
        open("https://www.lhv.ee/et/liising#monthly-payment");

        if ($(By.id("acceptPirukas")).isDisplayed()){
            $(By.id("acceptPirukas")).click();
        }
        
        $(By.linkText("Maksimaalne kuumakse")).click();

        $(By.name("dependent-persons")).shouldHave(Condition.value("1"));

        $(By.name("monthly-income")).shouldHave(Condition.value("900"));
        $(By.xpath("//*[@id=\"max-payment\"]/div[1]/div[3]/div[2]")).shouldHave(Condition.text("280.32"));

    }
    @Test
    public void CanChangePrice() {
        open("https://www.lhv.ee/et/liising#monthly-payment");

        $(By.id("acceptPirukas")).click();
        $(By.name("price")).setValue("35 000");
        $(By.name("price")).shouldHave(Condition.value("35 000"));

    }

//    @Test
//    public void CanChangePercent() {
//        open("https://www.cooppank.ee/autoliising");
//
//        $(By.name("esmaneprots")).setValue("30").pressEnter();
//        $(By.name("esmaneprots")).shouldHave(Condition.value("30"));
//    }
}
