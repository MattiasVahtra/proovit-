package com.mycompany.app;

import com.codeborne.selenide.Condition;
import org.junit.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;

//Couldn't quite figure out how to select checkboxes and radio buttons yet. It seems isSelected() doesn't work like I thought.
public class MonthlyPaymentCalcTest {
    @Test
    public void DefaultValuesAreCorrect() {
        open("https://www.lhv.ee/et/liising#monthly-payment");

        $(By.name("price")).shouldHave(Condition.value("15 000"));
        $(By.id("account_type-1")).isSelected();
        $(By.id("vat_included")).isSelected();
        $(By.name("initial_percentage")).shouldHave(Condition.value("10"));
        $(By.name("initial")).shouldHave(Condition.value("1500"));
        $(By.name("years")).shouldHave(Condition.value("72"));
        $(By.name("interest_rate")).shouldHave(Condition.value("4"));
        $(By.name("reminder_percentage")).shouldHave(Condition.value("10"));
        $(By.name("reminder")).shouldHave(Condition.value("1500"));

    }
    @Test
    public void CanChangePrice() {
        open("https://www.lhv.ee/et/liising#monthly-payment");

        if ($(By.id("acceptPirukas")).isDisplayed()){
            $(By.id("acceptPirukas")).click();
        }

        $(By.name("price")).shouldHave(Condition.value("15 000"));
        $(By.name("price")).setValue("35 000");
        $(By.name("price")).shouldHave(Condition.value("35 000"));

    }


    @Test
    public void ChangingAllFieldsGivesCorrectPayment() {
        open("https://www.lhv.ee/et/liising#monthly-payment");

        if ($(By.id("acceptPirukas")).isDisplayed()){
            $(By.id("acceptPirukas")).click();
        }

        $(By.xpath("//*[@id='monthly-payment']/div[1]/div[3]/div[2]")).shouldHave(Condition.text("192.74"));

        $(By.name("price")).setValue("35 000");
        $(By.xpath("//*[@id='monthly-payment']/div[1]/div[3]/div[2]")).shouldHave(Condition.text("449.73"));

        $(By.name("initial_percentage")).setValue("20");
        $(By.xpath("//*[@id='monthly-payment']/div[1]/div[3]/div[2]")).shouldHave(Condition.text("394.97"));

        $(By.name("initial")).setValue("14 000");
        $(By.xpath("//*[@id='monthly-payment']/div[1]/div[3]/div[2]")).shouldHave(Condition.text("285.45"));

        $(By.name("years")).selectOptionByValue("60");
        $(By.xpath("//*[@id='monthly-payment']/div[1]/div[3]/div[2]")).shouldHave(Condition.text("333.95"));

        $(By.name("interest_rate")).setValue("5");
        $(By.xpath("//*[@id='monthly-payment']/div[1]/div[3]/div[2]")).shouldHave(Condition.text("344.82"));

        $(By.name("reminder_percentage")).setValue("15");
        $(By.xpath("//*[@id='monthly-payment']/div[1]/div[3]/div[2]")).shouldHave(Condition.text("319.09"));

        $(By.name("reminder")).setValue("1050");
        $(By.xpath("//*[@id='monthly-payment']/div[1]/div[3]/div[2]")).shouldHave(Condition.text("380.85"));


        $(By.name("price")).shouldHave(Condition.value("35 000"));
        $(By.name("initial_percentage")).shouldHave(Condition.value("40"));
        $(By.name("initial")).shouldHave(Condition.value("14 000"));
        $(By.name("years")).shouldHave(Condition.value("60"));
        $(By.name("interest_rate")).shouldHave(Condition.value("5"));
        $(By.name("reminder_percentage")).shouldHave(Condition.value("3"));
        $(By.name("reminder")).shouldHave(Condition.value("1050"));

    }
}
