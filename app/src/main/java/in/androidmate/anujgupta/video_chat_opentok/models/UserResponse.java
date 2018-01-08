package in.androidmate.anujgupta.video_chat_opentok.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by anujgupta on 08/01/18.
 */

public class UserResponse {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("error")
    @Expose
    private String error;
    @SerializedName("users")
    @Expose
    private List<User> users = null;

    /**
     * No args constructor for use in serialization
     *
     */
    public UserResponse() {
    }

    /**
     *
     * @param users
     * @param error
     * @param status
     */
    public UserResponse(String status, String error, List<User> users) {
        super();
        this.status = status;
        this.error = error;
        this.users = users;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

}
