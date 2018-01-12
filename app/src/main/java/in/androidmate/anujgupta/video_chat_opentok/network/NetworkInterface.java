package in.androidmate.anujgupta.video_chat_opentok.network;


import in.androidmate.anujgupta.video_chat_opentok.models.ApiResponse;
import in.androidmate.anujgupta.video_chat_opentok.models.LoginResponse;
import in.androidmate.anujgupta.video_chat_opentok.models.SessionDataResponse;
import in.androidmate.anujgupta.video_chat_opentok.models.UserResponse;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by anujgupta on 08/01/18.
 */

public interface NetworkInterface {

    @GET("signup.php")
    Observable<ApiResponse> signup(@Query("username") String username, @Query("email") String email, @Query("password") String password, @Query("device_id") String device_id);

    @GET("login.php")
    Observable<LoginResponse> login(@Query("email") String email, @Query("password") String password, @Query("device_id") String device_id);

    @GET("fetchUsers.php")
    Observable<UserResponse> fetchUsers(@Query("device_id") String device_id);

    @GET("generateSession.php")
    Observable<SessionDataResponse> generateSession(@Query("device_id") String device_id,@Query("caller_name") String username);
}
