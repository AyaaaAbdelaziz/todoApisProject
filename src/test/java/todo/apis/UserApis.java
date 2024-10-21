package todo.apis;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import todo.base.Specs;
import todo.data.route;
import todo.models.userModel;

import static io.restassured.RestAssured.given;

public class UserApis {
    public static Response  UserRegister(userModel user){
        return given()
                .spec(Specs.getRequestSpec())
                .body(user)
                .when().post(route.Register_Route)
                .then().extract().response();
    }
    public static Response UserLogin (userModel user){
        return   given()
              .spec(Specs.getRequestSpec())
                .body(user)
                .when().post(route.Login_Route)
                .then().extract().response();
    }
}
