package tests;

import helpers.BaseRequest;
import helpers.EntityRequestFactory;
import helpers.Property;
import io.qameta.allure.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pojo.AdditionRequest;
import pojo.EntityRequest;
import pojo.EntityResponse;

import java.util.ArrayList;

import static io.restassured.RestAssured.given;

public class UpdateEntityTest {
    EntityRequest entityRequest;
    String entityID;

    @BeforeMethod
    @Step("Создание нового Entity для тестирования")
    public void createEntity() {
        entityRequest = EntityRequestFactory.createDefaultEntityRequest(20);
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
    @Description("Тест проверяет обновление Entity по id")
    @Severity(SeverityLevel.CRITICAL)
    @Feature("API")
    public void testEntityUpdate() {
        entityRequest.title+="_updated";
        entityRequest.verified = !entityRequest.verified;
        entityRequest.addition = new AdditionRequest(
                entityRequest.addition.additional_info+"_updated",
                entityRequest.addition.additional_number*100
        );
        (entityRequest.important_numbers = new ArrayList<>(entityRequest.important_numbers)).add(1000);

         given()
                .spec(BaseRequest.initRequestSpecification())
                .body(entityRequest)
                .when()
                .patch(Property.getProperty("properties.api.patch_path")+entityID)
                .then();

        EntityResponse updatedEntityRequest = given()
                .spec(BaseRequest.initRequestSpecification())
                .when()
                .get(Property.getProperty("properties.api.get_path")+entityID)
                .then()
                .extract().as(EntityResponse.class);

        BaseRequest.compareEntityRequestAndResponseWithoutID(entityRequest, updatedEntityRequest);

    }
    @AfterMethod
    @Step("Удаление созданного Entity")
    public void deleteEntityAfterTest() {
        BaseRequest.deleteEntityByID(entityID);
    }
}
