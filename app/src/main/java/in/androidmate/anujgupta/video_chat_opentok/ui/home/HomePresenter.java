package in.androidmate.anujgupta.video_chat_opentok.ui.home;

import android.util.Log;

import in.androidmate.anujgupta.video_chat_opentok.models.SessionDataResponse;
import in.androidmate.anujgupta.video_chat_opentok.models.UserResponse;
import in.androidmate.anujgupta.video_chat_opentok.network.NetworkClient;
import in.androidmate.anujgupta.video_chat_opentok.network.NetworkInterface;
import in.androidmate.anujgupta.video_chat_opentok.utils.PrefManager;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by anujgupta on 08/01/18.
 */

public class HomePresenter implements HomePresenterInterface {

    HomeViewInterface homeView;
    private String TAG = "HomeActivity";

    public HomePresenter(HomeViewInterface homeView) {
        this.homeView = homeView;
    }

    @Override
    public void getUsers(String device_id) {
        homeView.showProgressBar("Fetching Users. Please Wait ...");
        getObservable(device_id).subscribeWith(getObserver());
        
    }

    @Override
    public void startVideoChat(String device_id,String username) {

        homeView.showProgressBar("Starting Session ...");
        getVideoChatObservable(device_id,username).subscribeWith(getVideoChatObserver());
    }

    public Observable<UserResponse> getObservable(String device_id){
        return NetworkClient.getRetrofit().create(NetworkInterface.class)
                .fetchUsers(device_id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public DisposableObserver<UserResponse> getObserver(){
        return new DisposableObserver<UserResponse>() {

            @Override
            public void onNext(@NonNull UserResponse UserResponse) {
                Log.d(TAG,"OnNext"+UserResponse.getUsers());
                homeView.displayUsers(UserResponse);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.d(TAG,"Error"+e);
                e.printStackTrace();
                homeView.showToast("Error");
            }

            @Override
            public void onComplete() {
                Log.d(TAG,"Completed");
                homeView.hideProgressBar();
            }
        };
    }

    public Observable<SessionDataResponse> getVideoChatObservable(String device_id,String username){
        return NetworkClient.getRetrofit().create(NetworkInterface.class)
                .generateSession(device_id,username)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public DisposableObserver<SessionDataResponse> getVideoChatObserver(){
        return new DisposableObserver<SessionDataResponse>() {

            @Override
            public void onNext(@NonNull SessionDataResponse response) {
                Log.d(TAG,"OnNext"+response.getSessionId());
                homeView.goToChat(response);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.d(TAG,"Error"+e);
                e.printStackTrace();
                homeView.showToast("Error");
            }

            @Override
            public void onComplete() {
                Log.d(TAG,"Completed");
                homeView.hideProgressBar();
            }
        };
    }

}
