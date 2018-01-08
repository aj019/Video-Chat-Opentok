package in.androidmate.anujgupta.video_chat_opentok.ui.splash;

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

    }
}
