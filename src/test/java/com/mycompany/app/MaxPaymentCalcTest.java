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
        $(By.name("marital-status-married")).shouldBe(Condition.checked);

        $(By.name("monthly-income")).shouldHave(Condition.value("900"));
        $(By.xpath("//*[@id=\"max-payment\"]/div[1]/div[3]/div[2]")).shouldHave(Condition.text("280.32"));

    }

    //I figured out that I can just use the correct url instead of clicking on the tab, but I left it in because it was part of the learning process
    @Test
    public void ChangingFieldsGivesCorrectPayment() {
        open("https://www.lhv.ee/et/liising#max-payment");

        if ($(By.id("acceptPirukas")).isDisplayed()){
            $(By.id("acceptPirukas")).click();
        }

        $(By.xpath("//*[@id=\"max-payment\"]/div[1]/div[3]/div[2]")).shouldHave(Condition.text("280.32"));


        $(By.name("dependent-persons")).selectOptionByValue("3");
        $(By.xpath("//*[@id=\"max-payment\"]/div[1]/div[3]/div[2]")).shouldHave(Condition.text("197.39"));

        $(By.name("monthly-income")).setValue("1000");
        $(By.xpath("//*[@id=\"max-payment\"]/div[1]/div[3]/div[2]")).shouldHave(Condition.text("260.62"));

        $(By.name("monthly-income")).shouldHave(Condition.value("1000"));
        $(By.name("dependent-persons")).shouldHave(Condition.value("3"));

    }
}
