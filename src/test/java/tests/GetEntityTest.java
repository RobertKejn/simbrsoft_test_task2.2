package tests;

import helpers.BaseRequest;
import helpers.EntityFactory;
import helpers.Property;
import io.qameta.allure.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pojo.Entity;

import java.util.List;

import static io.restassured.RestAssured.given;

public class GetEntityTest extends BaseTest {

    @BeforeMethod
    @Step("Создание новых Entity для тестирования")
    public void setUp() {
        for (int i = 1; i < 4; i++) {
            Entity request = EntityFactory.generateEntity();
            String id = createEntity(request);
            entityRequests.add(request);
            entityIDs.add(id);
        }
    }

    @Test
    @Description("Тест проверяет получение Entity по id")
    @Severity(SeverityLevel.CRITICAL)
    @Feature("API")
    public void testEntityGet() {
        for (int i = 0; i < entityRequests.size(); i++) {
            String id = entityIDs.get(i);
            Entity entityResponse = given()
                    .spec(BaseRequest.initRequestSpecification())
                    .when()
                    .get(Property.getProperty("properties.api.get_path") + id)
                    .then()
                    .statusCode(200)
                    .extract().as(Entity.class);
            compareEntityRequestAndResponseWithoutID(entityRequests.get(i), entityResponse);
        }
    }

    @Test
    @Description("Тест проверяет полчение всех Entity")
    @Severity(SeverityLevel.CRITICAL)
    @Feature("API")
    public void testEntityGetAll() {
        List<Entity> entityResponses = given()
                .spec(BaseRequest.initRequestSpecification())
                .when()
                .get(Property.getProperty("properties.api.getall_path"))
                .then()
                .statusCode(200)
                .extract()
                .response()
                .jsonPath()
                .getList("entity", Entity.class);

        for(int i = 0; i < entityRequests.size(); i++){
            compareEntityRequestAndResponseWithoutID(entityRequests.get(i), entityResponses.get(i));
        }
    }

    @AfterMethod
    public void tearDown() {
        deleteAllCreatedEntities();
    }
}
