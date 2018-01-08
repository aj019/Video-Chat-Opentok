package in.androidmate.anujgupta.video_chat_opentok.ui.signup;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.iid.FirebaseInstanceId;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import in.androidmate.anujgupta.video_chat_opentok.R;
import in.androidmate.anujgupta.video_chat_opentok.ui.login.LoginActivity;

public class SignUpActivity extends AppCompatActivity implements SignUpViewInterface {

    SignUpPresenter signUpPresenter;

    @BindView(R.id.link_login)
    TextView tvGoToLogin;

    @BindView(R.id.etEmail)
    EditText etEmail;

    @BindView(R.id.etUsername)
    EditText etUsername;

    @BindView(R.id.etPassword)
    EditText etPassword;

    @BindView(R.id.btn_signup)
    Button btSignup;

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

    @OnClick(R.id.btn_signup)
    public void signUp(View v){
        FirebaseApp.initializeApp(this);
        showToast("djwhdj");
        String username = etUsername.getText().toString();
        String password = etPassword.getText().toString();
        String email = etEmail.getText().toString();
        String device_id = FirebaseInstanceId.getInstance().getToken();

        if(username.equals("") || password.equals("") || email.equals("")){
            showToast("Please enter all fields");
        }else{
            signUpPresenter.signUp(username,password,email,device_id);
        }


    }


    @Override
    public void showToast(String msg) {
        Toast.makeText(SignUpActivity.this,msg,Toast.LENGTH_SHORT).show();
    }
}
