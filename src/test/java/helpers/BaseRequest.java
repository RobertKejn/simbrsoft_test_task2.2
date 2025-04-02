package helpers;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.testng.asserts.SoftAssert;
import pojo.EntityRequest;
import pojo.EntityResponse;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class BaseRequest {
    public static RequestSpecification initRequestSpecification(){
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        RequestSpecBuilder builder = new RequestSpecBuilder();
        builder.setContentType(ContentType.JSON);
        builder.setBaseUri(Property.getProperty("properties.api.baseurl"));
        builder.setAccept(ContentType.JSON);
        return builder.build();
    }

    public static void deleteEntityByID(String id){
        given()
                .when()
                .delete(Property.getProperty("properties.api.delete_path") + id)
                .then()
                .statusCode(204);
    }

    public static void compareEntityRequestAndResponseWithoutID(EntityRequest request, EntityResponse response) {
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
