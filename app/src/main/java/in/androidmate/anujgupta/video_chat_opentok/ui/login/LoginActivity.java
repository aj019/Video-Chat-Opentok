package in.androidmate.anujgupta.video_chat_opentok.ui.login;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import in.androidmate.anujgupta.video_chat_opentok.R;

public class LoginActivity extends AppCompatActivity implements LoginViewInteraface {

    LoginPresenter loginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        setupMVP();
    }

    private void setupMVP() {

        loginPresenter = new LoginPresenter(this);

    }
}
