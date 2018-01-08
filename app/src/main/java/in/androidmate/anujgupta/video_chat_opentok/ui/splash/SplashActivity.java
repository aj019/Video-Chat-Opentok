package in.androidmate.anujgupta.video_chat_opentok.ui.splash;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import in.androidmate.anujgupta.video_chat_opentok.R;

public class SplashActivity extends AppCompatActivity implements SplashViewInterface {

    SplashPresenter splashPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        setupMVP();
        checkFirstLogin();

    }

    private void setupMVP() {

        splashPresenter = new SplashPresenter(this);

    }

    private void checkFirstLogin(){
        splashPresenter.checkFirstLogin();
    }


    @Override
    public void goToActivity(Class<? extends Activity> activity) {
        startActivity(new Intent(SplashActivity.this,activity));
        finish();
    }
}
