package helpers;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class BaseRequest {
    public static RequestSpecification initRequestSpecification(){
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        RequestSpecBuilder builder = new RequestSpecBuilder();
        builder.setContentType(ContentType.JSON);
        builder.setBaseUri(Property.getProperty("properties.api.baseurl"));
        builder.setAccept(ContentType.JSON);
        return builder.build();
    }

}
