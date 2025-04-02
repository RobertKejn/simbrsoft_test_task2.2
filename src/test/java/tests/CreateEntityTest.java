package tests;

import helpers.BaseRequest;
import helpers.EntityRequestFactory;
import helpers.Property;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import pojo.EntityRequest;
import pojo.EntityResponse;

import static io.restassured.RestAssured.given;


public class CreateEntityTest {
    private String enityId;

    @Test
    @Description("Тест проверяет создание нового Entity")
    @Severity(SeverityLevel.CRITICAL)
    @Feature("API")
    public void testEntityCreation() {
        EntityRequest entityRequest = EntityRequestFactory.createDefaultEntityRequest(1);

        enityId = given()
                .spec(BaseRequest.initRequestSpecification())
                .body(entityRequest)
                .when()
                .post(Property.getProperty("properties.api.post_path"))
                .then()
                .statusCode(200)
                .extract().asString();

        EntityResponse entityResponse = given()
                .spec(BaseRequest.initRequestSpecification())
                .when()
                .get(Property.getProperty("properties.api.get_path") + enityId)
                .then()
                .statusCode(200)
                .extract().as(EntityResponse.class);

        BaseRequest.compareEntityRequestAndResponseWithoutID(entityRequest, entityResponse);

    }

    @AfterMethod
    public void deleteEntityAfterTest() {
        BaseRequest.deleteEntityByID(enityId);
    }
}
