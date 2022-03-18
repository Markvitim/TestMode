package ru.netology;

import com.github.javafaker.Faker;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import lombok.experimental.UtilityClass;

import java.util.Locale;

import static io.restassured.RestAssured.given;

@UtilityClass

public class DataGenerator {

    public static RequestSpecification requestSpec = new RequestSpecBuilder()
            .setBaseUri("http://localhost")
            .setPort(9999)
            .setAccept(ContentType.JSON)
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();

    public static void setUpAll(RegistrationInfo info) {

        given()
                .spec(requestSpec)
                .body(info)
                .when()
                .post("/api/system/users")
                .then()
                .statusCode(200);
    }

    public static RegistrationInfo generateActive() {
        Faker faker = new Faker(new Locale("ru"));
        return new RegistrationInfo(
                faker.name().username(),
                faker.internet().password(),
                faker.letterify("active", true)
        );
    }

    public static RegistrationInfo generateBlocked() {
        Faker faker = new Faker(new Locale("ru"));
        return new RegistrationInfo(
                faker.name().username(),
                faker.internet().password(),
                faker.letterify("blocked", false)
        );
    }
}



