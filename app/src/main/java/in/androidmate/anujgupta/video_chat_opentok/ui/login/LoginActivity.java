package in.androidmate.anujgupta.video_chat_opentok.ui.login;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import in.androidmate.anujgupta.video_chat_opentok.R;
import in.androidmate.anujgupta.video_chat_opentok.ui.signup.SignUpActivity;
import in.androidmate.anujgupta.video_chat_opentok.utils.NetwokConnectionManager;
import in.androidmate.anujgupta.video_chat_opentok.utils.ShowInternetAlertDialog;
import in.androidmate.anujgupta.video_chat_opentok.utils.Typefacer;

public class LoginActivity extends AppCompatActivity implements LoginViewInteraface {

    LoginPresenter loginPresenter;

    @BindView(R.id.link_signup)
    TextView tvCreateAccount;

    @BindView(R.id.btn_login)
    Button btLogin;

    @BindView(R.id.etEmail)
    EditText etEmail;

    @BindView(R.id.etPassword)
    EditText etPassword;

    @BindView(R.id.tvTitle)
    TextView tvTitle;

    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        initViews();
        setupMVP();
    }

    private void initViews(){
        tvTitle.setTypeface(Typefacer.getBoldItalic(this));
        progressDialog = new ProgressDialog(this,R.style.AppCompatAlertDialogStyle);
    }

    private void setupMVP() {

        loginPresenter = new LoginPresenter(this);

    }


    @OnClick(R.id.link_signup)
    public void goToSignUp(View view){
        startActivity(new Intent(LoginActivity.this, SignUpActivity.class));
        overridePendingTransition(R.anim.enter_from_right,R.anim.exit_from_left);
    }

    @OnClick(R.id.btn_login)
    public void login(){

        if(NetwokConnectionManager.isOnline(this)){
            String password = etPassword.getText().toString();
            String email = etEmail.getText().toString();
            String device_id = FirebaseInstanceId.getInstance().getToken();

            if(password.equals("") || email.equals("")){
                showToast("Please enter all fields");
            }else{
                loginPresenter.login(password,email,device_id);
            }
        }else{
            ShowInternetAlertDialog.noInternet(this);
        }


    }


    @Override
    public void showToast(String msg) {
        Toast.makeText(LoginActivity.this,msg,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void goToActivity(Class<? extends Activity> activity) {
        Intent i = new Intent(LoginActivity.this,activity);
        startActivity(i);
        finish();
    }

    @Override
    public void showProgressDialog(String str) {
        progressDialog.setTitle(str);
        progressDialog.show();
    }

    @Override
    public void hideProgressDialog() {
        progressDialog.dismiss();
    }
}
