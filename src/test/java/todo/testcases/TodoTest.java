package todo.testcases;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import todo.apis.TodoApis;
import todo.apis.UserApis;
import todo.data.Errors;
import todo.models.TodoModel;
import todo.steps.TodoSteps;
import todo.steps.UserSteps;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.oauth2;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
public class TodoTest {

    String token= UserSteps.getUserToken();

    @Test(dataProvider = "AddTodoValidData",dataProviderClass = TodoSteps.class)
    public void ShouldBeAbleToAddTest(String isCompleted,String item){
       TodoModel todoModel =new TodoModel(Boolean.valueOf(isCompleted),item);
        System.out.println(todoModel.getIsCompleted());

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
        String taskId=TodoSteps.getTaskID(token);
      Response res= TodoApis.GetTodoByID(token,taskId);
        assertThat(res.statusCode(),equalTo(200));
        TodoModel todoModelResponse =res.body().as(TodoModel.class);
        assertThat(todoModelResponse.getIsCompleted(),equalTo(false));
    }
    @Test
    public void ShouldBeAbleToDeleteTodoById(){
        String taskId="6716b2950f212c00148e5251";
        Response res = TodoApis.DeleteTodoByID(token,taskId);
        assertThat(res.statusCode(),equalTo(400));
        Error error =res.body().as(Error.class);
        assertThat(error.getMessage(),equalTo(Errors.Task_not_available));

    }
}
