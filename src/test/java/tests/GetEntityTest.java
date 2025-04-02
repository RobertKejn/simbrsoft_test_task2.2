package tests;

import helpers.BaseRequest;
import helpers.EntityRequestFactory;
import helpers.Property;
import io.qameta.allure.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pojo.EntityRequest;
import pojo.EntityResponse;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class GetEntityTest {
    List<EntityRequest> entityRequests;
    List<String> entityIDs;

    @BeforeMethod
    @Step("Создание новых Entity для тестирования")
    public void createEntities() {
        entityRequests = new ArrayList<>();
        entityIDs = new ArrayList<>();
        for (int i = 1; i < 4; i++) {
            EntityRequest entity = EntityRequestFactory.createDefaultEntityRequest(i);
            String entityId = given()
                    .spec(BaseRequest.initRequestSpecification())
                    .body(entity)
                    .when()
                    .post(Property.getProperty("properties.api.post_path"))
                    .then()
                    .statusCode(200)
                    .extract().asString();

            entityRequests.add(entity);
            entityIDs.add(entityId);
        }
    }

    @Test
    @Description("Тест проверяет получение Entity по id")
    @Severity(SeverityLevel.CRITICAL)
    @Feature("API")
    public void testEntityGet() {
        for (int i = 0; i < entityRequests.size(); i++) {
            String id = entityIDs.get(i);
            EntityResponse entityResponse = given()
                    .spec(BaseRequest.initRequestSpecification())
                    .when()
                    .get(Property.getProperty("properties.api.get_path") + id)
                    .then()
                    .extract().as(EntityResponse.class);
            BaseRequest.compareEntityRequestAndResponseWithoutID(entityRequests.get(i), entityResponse);
        }
    }

    @Test
    @Description("Тест проверяет полчение всех Entity")
    @Severity(SeverityLevel.CRITICAL)
    @Feature("API")
    public void testEntityGetAll() {
        List<EntityResponse> entityResponses = given()
                .spec(BaseRequest.initRequestSpecification())
                .when()
                .get(Property.getProperty("properties.api.getall_path"))
                .then()
                .statusCode(200)
                .extract()
                .response()
                .jsonPath()
                .getList("entity", EntityResponse.class);

        for(int i = 0; i < entityRequests.size(); i++){
            BaseRequest.compareEntityRequestAndResponseWithoutID(entityRequests.get(i), entityResponses.get(i));
        }
    }

    @AfterMethod
    @Step("Удаление созданных Entity")
    public void deleteEntitiesAfterTest() {
        for (String id : entityIDs) {
            BaseRequest.deleteEntityByID(id);
        }
    }
}
