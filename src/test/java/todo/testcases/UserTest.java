package todo.testcases;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import todo.apis.UserApis;
import todo.data.Errors;
import todo.models.userModel;
import todo.steps.UserSteps;

public class UserTest {

    @Test
    public void shouldBeAbleToRegister() {
        userModel user = UserSteps.generatedUser();

        Response res = UserApis.UserRegister(user);
        userModel returenedUser = res.body().as(userModel.class);
        assertThat(res.statusCode(), equalTo(201));
        assertThat(returenedUser.getFirstName(), equalTo(user.getFirstName()));

    }

    @Test
    public void shouldnotBeableToRegister() {
        userModel user = UserSteps.registeredUser();
        Response res = UserApis.UserRegister(user);
        Error error = res.body().as(Error.class);
        assertThat(res.statusCode(), equalTo(400));
        assertThat(error.getMessage(), equalTo(Errors.Email_is_already_registered));
    }

    @Test
    public void shouldBeableToLogin() {
        userModel user = UserSteps.registeredUser();
        userModel  loginData= new userModel(user.getEmail(),user.getPassword());
        Response res = UserApis.UserLogin(loginData);
        userModel userResponse = res.body().as(userModel.class);
        assertThat(res.statusCode(), equalTo(200));
        assertThat(userResponse.getFirstName(), equalTo(user.getFirstName()));
        assertThat(userResponse.getAccessToken(), not(equalTo(null)));


    }

    @Test
    public void shouldnotBeableToLogin() {
        userModel user = UserSteps.registeredUser();
        userModel  loginData= new userModel(user.getEmail(),"wrongpass");
        Response res = UserApis.UserLogin(loginData);
        Error error = res.body().as(Error.class);
        assertThat(res.statusCode(), equalTo(401));
        assertThat(error.getMessage(), equalTo(Errors.In_correct_email_or_password));

    }
}
