package in.androidmate.anujgupta.video_chat_opentok.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by anujgupta on 09/01/18.
 */

public class SessionDataResponse {

    @SerializedName("api_key")
    @Expose
    private String apiKey;
    @SerializedName("session_id")
    @Expose
    private String sessionId;
    @SerializedName("token")
    @Expose
    private String token;

    /**
     * No args constructor for use in serialization
     *
     */
    public SessionDataResponse() {
    }

    /**
     *
     * @param token
     * @param sessionId
     * @param apiKey
     */
    public SessionDataResponse(String apiKey, String sessionId, String token) {
        super();
        this.apiKey = apiKey;
        this.sessionId = sessionId;
        this.token = token;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}