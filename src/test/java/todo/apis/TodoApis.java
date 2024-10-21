package todo.apis;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import todo.base.Specs;
import todo.data.route;
import todo.models.TodoModel;

import static io.restassured.RestAssured.given;

public class TodoApis {
    public static Response AddTodo(TodoModel todo,String token){
        return  given()
                .spec(Specs.getRequestSpec())
                .auth().oauth2(token)
                .body(todo)
                .when().post(route.Todos_Route)
                .then().extract().response();

    }
   public static Response GetTodoByID(String token, String taskId){
      return given()
              .spec(Specs.getRequestSpec())
               .auth().oauth2(token)
               .when().get(route.Todos_Route+taskId)
               .then().extract().response();
   }
    public static Response DeleteTodoByID(String token, String taskId){
            return given()
                    .spec(Specs.getRequestSpec())
                    .auth().oauth2(token)
                    .when().delete(route.Todos_Route+taskId)
                    .then().extract().response();
    }}
