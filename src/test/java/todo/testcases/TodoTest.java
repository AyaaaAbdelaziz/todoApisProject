package todo.testcases;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import todo.apis.TodoApis;
import todo.apis.UserApis;
import todo.data.Errors;
import todo.models.TodoModel;
import todo.steps.UserSteps;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.oauth2;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
public class TodoTest {

    String token= UserSteps.getUserToken();
    @Test
    public void ShouldBeAbleToAddTest(){

       TodoModel todoModel =new TodoModel(false,"Study List");
       Response res= TodoApis.AddTodo(todoModel,token);
        TodoModel todoModelResponse =res.body().as(TodoModel.class);
        assertThat(res.statusCode(),equalTo(201));
        assertThat(todoModelResponse.getItem(),equalTo(todoModel.getItem()));
    }
    @Test
    public void shouldnotBeAbleToAddTodoIfIsCompletedMissing(){
        TodoModel todoModel =new TodoModel("Study List");
        Response res= TodoApis.AddTodo(todoModel,token);

        assertThat(res.statusCode(),equalTo(400));

        Error error =res.body().as(Error.class);
        assertThat(error.getMessage(),equalTo(Errors.Is_completed_missing));

    }
    @Test
    public void ShouldBeAbleToGetTodoById(){
        String taskId="6716b3120f212c00148e526a";
      Response res= TodoApis.GetTodoByID(token,taskId);
        assertThat(res.statusCode(),equalTo(200));
        TodoModel todoModelResponse =res.body().as(TodoModel.class);
        assertThat(todoModelResponse.getIsCompleted(),equalTo(false));
    }
    @Test
    public void ShouldBeAbleToDeleteTodoById(){
        String taskId="6716b2950f212c00148e5251";
        Response res = TodoApis.DeleteTodoByID(token,taskId);
        assertThat(res.statusCode(),equalTo(200));
        TodoModel todoModelResponse =res.body().as(TodoModel.class);
        assertThat(todoModelResponse.getIsCompleted(),equalTo(false));

    }
}
