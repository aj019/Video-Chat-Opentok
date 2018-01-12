package in.androidmate.anujgupta.video_chat_opentok.ui.signup;

import android.util.Log;

import in.androidmate.anujgupta.video_chat_opentok.models.ApiResponse;
import in.androidmate.anujgupta.video_chat_opentok.network.NetworkClient;
import in.androidmate.anujgupta.video_chat_opentok.network.NetworkInterface;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by anujgupta on 08/01/18.
 */

public class SignUpPresenter implements SignUpPresenterInterface {

    SignUpViewInterface signUpView;
    private String TAG = "SignUpActivity";

    public SignUpPresenter(SignUpViewInterface signUpView) {
        this.signUpView = signUpView;
    }

    @Override
    public void signUp(String username, String password, String email, String device_id) {

        signUpView.showProgressDialog("Creating Account");
        getObservable(username,password,email,device_id).subscribeWith(getObserver());

    }


    public Observable<ApiResponse> getObservable(String username, String password, String email, String device_id){
        return NetworkClient.getRetrofit().create(NetworkInterface.class)
                .signup(username,email,password,device_id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public DisposableObserver<ApiResponse> getObserver(){
        return new DisposableObserver<ApiResponse>() {

            @Override
            public void onNext(@NonNull ApiResponse apiResponse) {
                Log.d(TAG,"OnNext"+apiResponse.getStatus());

                if(apiResponse.getStatus().equals("success")){
                    signUpView.showToast("Signup Sucessful");
                    signUpView.moveToLogin();
                }else{
                    signUpView.showToast("Signup Error - "+apiResponse.getError());
                }

            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.d(TAG,"Error"+e);
                e.printStackTrace();
                signUpView.showToast("Error");

            }

            @Override
            public void onComplete() {
                Log.d(TAG,"Completed");
                signUpView.hideProgressDialog();

            }
        };
    }
}
