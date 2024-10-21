package todo.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TodoModel {
    private Boolean isCompleted;
    private String item;
    private String userID;
    private String createdAt;
    @JsonProperty("__v")

    private String v;
    @JsonProperty("_id")
    private String id;
    public TodoModel(){}
    public TodoModel(Boolean isCompleted, String item){
        this.isCompleted=isCompleted;
        this.item=item;
    }
    public TodoModel(String item){
        this.item=item;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
    @JsonProperty("__v")

    public String getV() {
        return v;
    }
    @JsonProperty("__v")

    public void setV(String v) {
        this.v = v;
    }



    @JsonProperty("_id")

    public String getId() {
        return id;
    }
    @JsonProperty("_id")

    public void setId(String id) {
        this.id = id;
    }



    public Boolean getIsCompleted() {
        return isCompleted;
    }

    public void setIsCompleted(Boolean isCompleted) {
        this.isCompleted = isCompleted;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }
}
