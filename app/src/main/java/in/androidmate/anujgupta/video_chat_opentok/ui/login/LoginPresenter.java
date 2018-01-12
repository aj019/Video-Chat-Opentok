package in.androidmate.anujgupta.video_chat_opentok.ui.login;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;

import in.androidmate.anujgupta.video_chat_opentok.models.ApiResponse;
import in.androidmate.anujgupta.video_chat_opentok.models.LoginResponse;
import in.androidmate.anujgupta.video_chat_opentok.network.NetworkClient;
import in.androidmate.anujgupta.video_chat_opentok.network.NetworkInterface;
import in.androidmate.anujgupta.video_chat_opentok.ui.home.HomeActivity;
import in.androidmate.anujgupta.video_chat_opentok.utils.PrefManager;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by anujgupta on 08/01/18.
 */

public class LoginPresenter implements LoginPresenterInterface {

    LoginViewInteraface loginView;
    private String TAG = "LoginActivity";


    public LoginPresenter(LoginViewInteraface loginView) {
        this.loginView = loginView;
    }

    @Override
    public void login(String email, String password, String device_id) {
        loginView.showProgressDialog("Logging In");
        getObservable(email,password,device_id).subscribeWith(getObserver());
    }


    public Observable<LoginResponse> getObservable(String email, String password, String device_id){
        return NetworkClient.getRetrofit().create(NetworkInterface.class)
                .login(email,password,device_id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public DisposableObserver<LoginResponse> getObserver(){
        return new DisposableObserver<LoginResponse>() {

            @Override
            public void onNext(@NonNull LoginResponse apiResponse) {
                Log.d(TAG,"OnNext"+apiResponse.getStatus());

                if(apiResponse.getStatus().equals("success")){

                    PrefManager.putBoolean("isLoggedIn",true);
                    PrefManager.putString("device_id", FirebaseInstanceId.getInstance().getToken());
                    PrefManager.putString("username",apiResponse.getUser().getUsername());
                    loginView.showToast("Welcome "+PrefManager.getString("username","User"));
                    loginView.goToActivity(HomeActivity.class);
                }else{
                    loginView.showToast("Signup Error"+apiResponse.getError());
                }

            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.d(TAG,"Error"+e);
                e.printStackTrace();
                loginView.showToast("Error");

            }

            @Override
            public void onComplete() {
                Log.d(TAG,"Completed");
                loginView.hideProgressDialog();
            }
        };
    }


}
