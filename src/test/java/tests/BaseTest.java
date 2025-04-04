package tests;

import com.fasterxml.jackson.databind.ObjectMapper;
import helpers.BaseRequest;
import helpers.Property;
import org.testng.asserts.SoftAssert;
import pojo.Entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class BaseTest {
    protected List<Entity> entityRequests = new ArrayList<>();
    protected List<String> entityIDs = new ArrayList<>();

    protected String createEntity(Entity request) {
        return given()
                .spec(BaseRequest.initRequestSpecification())
                .body(request)
                .when()
                .post(Property.getProperty("properties.api.post_path"))
                .then()
                .statusCode(200)
                .extract().asString();
    }

    protected Entity createEntityWithID(Entity request) {
        String id = given()
                .spec(BaseRequest.initRequestSpecification())
                .body(request)
                .when()
                .post(Property.getProperty("properties.api.post_path"))
                .then()
                .statusCode(200)
                .extract().asString();
        Entity entity = given()
                .spec(BaseRequest.initRequestSpecification())
                .when()
                .get(Property.getProperty("properties.api.get_path")+id)
                .then()
                .statusCode(200)
                .extract().as(Entity.class);
        return entity;
    }

    protected void deleteEntity(String entityId) {
        given()
                .spec(BaseRequest.initRequestSpecification())
                .when()
                .delete(Property.getProperty("properties.api.delete_path") + entityId)
                .then()
                .statusCode(204);
    }

    protected void deleteAllCreatedEntities() {
        for (String id : entityIDs) {
            deleteEntity(id);
        }
        entityIDs.clear();
        entityRequests.clear();
    }

    protected static void compareEntityRequestAndResponseWithoutID(Entity request, Entity response) {
        ObjectMapper objectMapper = new ObjectMapper();
        SoftAssert softAssert = new SoftAssert();

        Map<String, Object> requestMap = objectMapper.convertValue(request, Map.class);
        Map<String, Object> responseMap = objectMapper.convertValue(response, Map.class);

        responseMap.remove("id");
        if (responseMap.containsKey("addition") && responseMap.get("addition") instanceof Map) {
            ((Map<String, Object>) responseMap.get("addition")).remove("id");
        }

        softAssert.assertEquals(responseMap, requestMap, "Objects do not match (ignoring ID)");
        softAssert.assertAll();
    }
}
