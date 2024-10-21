package todo.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class userModel {
    public userModel(){}
    public  userModel(String email,String password,String firstName,String lastName){
        this.email=email;
        this.password=password;
        this.firstName=firstName;
        this.lastName=lastName;

    }
    public  userModel(String email,String password){
        this.email=email;
        this.password=password;

    }
     private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String userID;
  @JsonProperty("access_token")
    private String accessToken;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    @JsonProperty("access_token")

    public String getAccessToken() {
        return accessToken;
    }

    @JsonProperty("access_token")

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
