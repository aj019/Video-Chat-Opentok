package in.androidmate.anujgupta.video_chat_opentok.ui.home;

import android.util.Log;

import in.androidmate.anujgupta.video_chat_opentok.models.UserResponse;
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

public class HomePresenter implements HomePresenterInterface {

    HomeViewInterface homeView;
    private String TAG = "HomeActivity";

    public HomePresenter(HomeViewInterface homeView) {
        this.homeView = homeView;
    }

    @Override
    public void getUsers() {

        getObservable().subscribeWith(getObserver());
        
    }

    public Observable<UserResponse> getObservable(){
        return NetworkClient.getRetrofit().create(NetworkInterface.class)
                .fetchUsers()
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
}
