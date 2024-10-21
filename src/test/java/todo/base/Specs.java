package todo.base;

import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class Specs {
    public static RequestSpecification getRequestSpec(){
       return given().baseUri("https://todo.qacart.com/api/")
                .contentType(ContentType.JSON)
                .log().all();
    }
}
