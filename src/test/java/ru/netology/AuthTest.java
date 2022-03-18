package ru.netology;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;


public class AuthTest {

    @Test
    void shouldGenerate() {
        RegistrationInfo info = DataGenerator.generateActive();
        System.out.println(info);
    }

    @Test
    void shouldReturnDemoActive() {
        DataGenerator.setUpAll(DataGenerator.generateActive());
    }

    @Test
    void shouldReturnDemoBlocked() {
        DataGenerator.setUpAll(DataGenerator.generateBlocked());
    }

    @BeforeEach
    void shouldOpen() {
        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999");
    }

    @Test
    void shouldUserActive() {
        RegistrationInfo info = DataGenerator.generateActive();
        DataGenerator.setUpAll(info);
        $("[data-test-id=\"login\"] input").setValue(info.getLogin());
        $("[data-test-id=\"password\"] input").setValue(info.getPassword());
        $(".button").click();
        $(".heading ").shouldHave(Condition.text("Личный кабинет"));
    }

    @Test
    void shouldUserBlocked() {
        RegistrationInfo info = DataGenerator.generateBlocked();
        DataGenerator.setUpAll(info);
        $("[data-test-id=\"login\"] input").setValue(info.getLogin());
        $("[data-test-id=\"password\"] input").setValue(info.getPassword());
        $(".button").click();
        $(".notification__content").shouldHave(Condition.text("пользователь заблокирован"));
    }

    @Test
    void shouldInvalidLogin() {
        RegistrationInfo info = DataGenerator.generateActive();
        DataGenerator.setUpAll(info);
        $("[data-test-id=\"login\"] input").setValue(DataGenerator.generateActive().getLogin());
        $("[data-test-id=\"password\"] input").setValue(info.getPassword());
        $(".button").click();
        $(".notification__content").shouldHave(Condition.text("Неверно указан логин или пароль"));
    }

    @Test
    void shouldInvalidPassword() {
        RegistrationInfo info = DataGenerator.generateActive();
        DataGenerator.setUpAll(info);
        $("[data-test-id=\"login\"] input").setValue(info.getLogin());
        $("[data-test-id=\"password\"] input").setValue(DataGenerator.generateActive().getPassword());
        $(".button").click();
        $(".notification__content").shouldHave(Condition.text("Неверно указан логин или пароль"));
    }
}


