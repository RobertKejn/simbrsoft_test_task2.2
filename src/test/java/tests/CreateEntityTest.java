package tests;

import helpers.BaseRequest;
import helpers.EntityFactory;
import helpers.Property;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import pojo.Entity;

import static io.restassured.RestAssured.given;


public class CreateEntityTest extends BaseTest{
    @Test
    @Description("Тест проверяет создание нового Entity")
    @Severity(SeverityLevel.CRITICAL)
    @Feature("API")
    public void testEntityCreation() {
        Entity entity = EntityFactory.generateEntity();
        String id = createEntity(entity);
        entityRequests.add(entity);
        entityIDs.add(id);

        Entity entityResponse = given()
                .spec(BaseRequest.initRequestSpecification())
                .when()
                .get(Property.getProperty("properties.api.get_path") + id)
                .then()
                .statusCode(200)
                .extract().as(Entity.class);

        compareEntityRequestAndResponseWithoutID(entity, entityResponse);
    }

    @AfterMethod
    public void tearDown() {
        deleteAllCreatedEntities();
    }
}
