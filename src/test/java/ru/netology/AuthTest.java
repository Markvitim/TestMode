package ru.netology;

import org.junit.jupiter.api.Test;


public class AuthTest {

    @Test
    void shouldGenerate() {
        RegistrationInfo registrationInfo = DataGenerator.generate();
        System.out.println(registrationInfo);
    }

    @Test
    void shouldReturnDemoAccounts() {
        DataGenerator.setUpAll(DataGenerator.generate());
    }

}


