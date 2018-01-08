package in.androidmate.anujgupta.video_chat_opentok.ui.splash;

import android.util.Log;
import android.widget.Toast;

import in.androidmate.anujgupta.video_chat_opentok.ui.home.HomeActivity;
import in.androidmate.anujgupta.video_chat_opentok.ui.login.LoginActivity;
import in.androidmate.anujgupta.video_chat_opentok.utils.PrefManager;

/**
 * Created by anujgupta on 08/01/18.
 */

public class SplashPresenter implements SplashPresenterInterface {

    SplashViewInterface splashView;

    public SplashPresenter(SplashViewInterface splashView) {
        this.splashView = splashView;
    }

    @Override
    public void checkFirstLogin() {

        boolean isLoggedIn = PrefManager.getBoolean("isLoggedIn",false);
        if(isLoggedIn){
            Log.d("Logged in","True");
            splashView.goToActivity(HomeActivity.class);
        }else{
            Log.d("Logged in","False");
            splashView.goToActivity(LoginActivity.class);
        }

    }
}
