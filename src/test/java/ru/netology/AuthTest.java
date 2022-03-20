package ru.netology;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;


public class AuthTest {

    @BeforeEach
    void shouldOpen() {
        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999");
    }

    @Test
    void shouldUserActive() {
        var info = DataGenerator.Registration.generate("active");
        DataGenerator.setUpAll(info);
        $("[data-test-id=\"login\"] input").setValue(info.getLogin());
        $("[data-test-id=\"password\"] input").setValue(info.getPassword());
        $(".button").click();
        $(".heading ").shouldHave(Condition.text("Личный кабинет"));
    }

    @Test
    void shouldUserBlocked() {
        var info = DataGenerator.Registration.generate("blocked");
        DataGenerator.setUpAll(info);
        $("[data-test-id=\"login\"] input").setValue(info.getLogin());
        $("[data-test-id=\"password\"] input").setValue(info.getPassword());
        $(".button").click();
        $(".notification__content").shouldHave(Condition.text("пользователь заблокирован"));
    }

    @Test
    void shouldInvalidLogin() {
        var info = DataGenerator.Registration.generate("active");
        DataGenerator.setUpAll(info);
        $("[data-test-id=\"login\"] input")
                .setValue(DataGenerator.Registration.generate("active").getLogin());
        $("[data-test-id=\"password\"] input").setValue(info.getPassword());
        $(".button").click();
        $(".notification__content").shouldHave(Condition.text("Неверно указан логин или пароль"));
    }

    @Test
    void shouldInvalidPassword() {
        var info = DataGenerator.Registration.generate("active");
        DataGenerator.setUpAll(info);
        $("[data-test-id=\"login\"] input").setValue(info.getLogin());
        $("[data-test-id=\"password\"] input")
                .setValue(DataGenerator.Registration.generate("active").getPassword());
        $(".button").click();
        $(".notification__content").shouldHave(Condition.text("Неверно указан логин или пароль"));
    }
}


