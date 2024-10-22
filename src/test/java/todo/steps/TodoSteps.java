package todo.steps;

import com.github.javafaker.Faker;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import io.restassured.response.Response;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import todo.apis.TodoApis;
import todo.models.TodoModel;
import todo.testcases.TodoTest;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TodoSteps {
    @DataProvider(name = "AddTodoValidData")

    public Iterator<Object[]> AddTodoValidData()  throws IOException, CsvValidationException {
        CSVReader reader;
        String csvFile = TodoSteps.class.getClassLoader().getResource("TodoData.csv").getPath();
        reader = new CSVReader(new FileReader(csvFile));
        System.out.println(csvFile);
        List<Object[]> testData = new ArrayList();
        String[] line;
        reader.readNext();

        while ((line = reader.readNext()) != null) {
            testData.add(line);
        }
        reader.close();
        return testData.iterator();
    }

    public static String getTaskID(String token){
        Faker faker = new Faker();
        Boolean  isCompleted = false;
        String item = faker.cat().name();
        TodoModel todoModel=new TodoModel(isCompleted,item);
        Response res =TodoApis.AddTodo(todoModel,token);
        return res.body().path("_id");
    }
    public static String getUnauthorizedTaskID(){
       String token= UserSteps.getUserToken();
        Faker faker = new Faker();
        Boolean  isCompleted = false;
        String item = faker.cat().name();
        TodoModel todoModel=new TodoModel(isCompleted,item);
        Response res =TodoApis.AddTodo(todoModel,token);
        return res.body().path("_id");
    }

}