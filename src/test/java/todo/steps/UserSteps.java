package todo.steps;

import com.github.javafaker.Faker;
import io.restassured.response.Response;
import todo.apis.UserApis;
import todo.models.userModel;

public class UserSteps {
    public static userModel generatedUser() {
        Faker faker = new Faker();
        String FirstName = faker.name().firstName();
        String LastName = faker.name().lastName();
        String email = faker.internet().emailAddress();
        String password = "12345678";
        return new userModel(email, password, FirstName, LastName);
    }

    public static userModel registeredUser() {
        userModel user = generatedUser();
        UserApis.UserRegister(user);
        return user;
    }
    public static String getUserToken(){
        userModel user=generatedUser();
        Response response=UserApis.UserRegister(user);
        return response.body().path("access_token");
    }
}
