package in.androidmate.anujgupta.video_chat_opentok.ui.signup;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import in.androidmate.anujgupta.video_chat_opentok.R;
import in.androidmate.anujgupta.video_chat_opentok.ui.login.LoginActivity;

public class SignUpActivity extends AppCompatActivity implements SignUpViewInterface {

    SignUpPresenter signUpPresenter;

    @BindView(R.id.link_login)
    TextView tvGoToLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ButterKnife.bind(this);

        setupMVP();

    }

    private void setupMVP() {

        signUpPresenter = new SignUpPresenter(this);
    }

    @OnClick(R.id.link_login)
    public void goToLogin(View v){

        Intent i = new Intent(SignUpActivity.this, LoginActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
    }


}
