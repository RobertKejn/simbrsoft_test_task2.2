package tests;

import helpers.BaseRequest;
import helpers.EntityFactory;
import helpers.Property;
import io.qameta.allure.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pojo.*;

import java.util.ArrayList;

import static io.restassured.RestAssured.given;

public class UpdateEntityTest extends BaseTest {
    @BeforeMethod
    @Step("Создание нового Entity для тестирования")
    public void setUp() {
        Entity entity = EntityFactory.generateEntity();
        String id = createEntity(entity);
        entityRequests.add(entity);
        entityIDs.add(id);
    }

    @Test
    @Description("Тест проверяет обновление Entity по id")
    @Severity(SeverityLevel.CRITICAL)
    @Feature("API")
    public void testEntityUpdate() {
        Entity entityRequest = entityRequests.get(0);
        String entityID = entityIDs.get(0);

        entityRequest.title += "_updated";
        entityRequest.verified = !entityRequest.verified;
        entityRequest.addition = new Addition(
                entityRequest.addition.additional_info + "_updated",
                entityRequest.addition.additional_number * 100
        );
        (entityRequest.important_numbers = new ArrayList<>(entityRequest.important_numbers)).add(1000);

        given()
                .spec(BaseRequest.initRequestSpecification())
                .body(entityRequest)
                .when()
                .patch(Property.getProperty("properties.api.patch_path") + entityID)
                .then()
                .statusCode(204);

        Entity updatedEntityRequest = given()
                .spec(BaseRequest.initRequestSpecification())
                .when()
                .get(Property.getProperty("properties.api.get_path") + entityID)
                .then()
                .statusCode(200)
                .extract().as(Entity.class);

        compareEntityRequestAndResponseWithoutID(entityRequest, updatedEntityRequest);
    }

    @AfterMethod
    @Step("Удаление созданного Entity")
    public void tearDown() {
        deleteAllCreatedEntities();
    }
}
