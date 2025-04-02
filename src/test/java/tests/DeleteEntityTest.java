package tests;

import helpers.BaseRequest;
import helpers.EntityRequestFactory;
import helpers.Property;
import io.qameta.allure.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pojo.EntityRequest;

import static io.restassured.RestAssured.given;

public class DeleteEntityTest {
    EntityRequest entityRequest;
    String entityID;

    @BeforeMethod
    @Step("Создание нового Entity для тестирования")
    public void createEntity() {
        entityRequest = EntityRequestFactory.createDefaultEntityRequest(10);
        entityID = given()
                .spec(BaseRequest.initRequestSpecification())
                .body(entityRequest)
                .when()
                .post(Property.getProperty("properties.api.post_path"))
                .then()
                .statusCode(200)
                .extract().asString();
    }

    @Test
    @Description("Тест проверяет удаление Entity по id")
    @Severity(SeverityLevel.CRITICAL)
    @Feature("API")
    public void testEntityDelete() {
        given()
                .spec(BaseRequest.initRequestSpecification())
                .when()
                .delete(Property.getProperty("properties.api.delete_path") + entityID)
                .then()
                .statusCode(204);
    }
}
