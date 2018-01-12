package in.androidmate.anujgupta.video_chat_opentok.ui.home;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.androidmate.anujgupta.video_chat_opentok.R;
import in.androidmate.anujgupta.video_chat_opentok.adapters.UserAdapter;
import in.androidmate.anujgupta.video_chat_opentok.helpers.RecyclerItemClickListener;
import in.androidmate.anujgupta.video_chat_opentok.models.SessionDataResponse;
import in.androidmate.anujgupta.video_chat_opentok.models.UserResponse;
import in.androidmate.anujgupta.video_chat_opentok.ui.chat.ChatActivity;
import in.androidmate.anujgupta.video_chat_opentok.ui.login.LoginActivity;
import in.androidmate.anujgupta.video_chat_opentok.utils.NetwokConnectionManager;
import in.androidmate.anujgupta.video_chat_opentok.utils.PrefManager;
import in.androidmate.anujgupta.video_chat_opentok.utils.ShowInternetAlertDialog;

public class HomeActivity extends AppCompatActivity implements HomeViewInterface {

    HomePresenter homePresenter;

    @BindView(R.id.rvUsers)
    RecyclerView rvUsers;

    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    RecyclerView.Adapter adapter;
    ProgressDialog progressDialog;

    UserResponse userRes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");


        setupMVP();
        setupViews();


    }

    private void setupMVP() {

        homePresenter = new HomePresenter(this);
    }

    private void setupViews(){
        rvUsers.setLayoutManager(new LinearLayoutManager(this));
        rvUsers.addOnItemTouchListener(new RecyclerItemClickListener(this, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                String device = userRes.getUsers().get(position).getDeviceId();
                Log.d("Device id",device);
                String username = PrefManager.getString("username","Someone");

                if(NetwokConnectionManager.isOnline(HomeActivity.this)){
                    homePresenter.startVideoChat(device,username);
                }else{
                    ShowInternetAlertDialog.noInternet(HomeActivity.this);
                }
            }
        }));

        progressDialog = new ProgressDialog(this,R.style.AppCompatAlertDialogStyle);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getUsers();
            }
        });
    }

    private void getUsers() {
        if(NetwokConnectionManager.isOnline(this)){
            String device_id = FirebaseInstanceId.getInstance().getToken();
            homePresenter.getUsers(device_id);
        }else{
            ShowInternetAlertDialog.noInternet(this);
        }

    }


    @Override
    public void displayUsers(final UserResponse userResponse) {
        if(userResponse!=null) {
            userRes = userResponse;
            adapter = new UserAdapter(HomeActivity.this,userRes.getUsers());
            adapter.notifyDataSetChanged();
            rvUsers.invalidate();
            rvUsers.setAdapter(adapter);


        }else{
            showToast("User Response returned null");
        }
    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(HomeActivity.this,msg,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showProgressBar(String msg) {
        progressDialog.setTitle(msg);
        progressDialog.show();
    }

    @Override
    public void hideProgressBar() {

        progressDialog.dismiss();
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void goToChat(SessionDataResponse sessionDataResponse) {
        if(sessionDataResponse != null){
            Intent i = new Intent(HomeActivity.this,ChatActivity.class);
            Bundle b = new Bundle();
            b.putString("session_id",sessionDataResponse.getSessionId());
            b.putString("token",sessionDataResponse.getToken());
            b.putBoolean("isCalling",true);
            i.putExtras(b);
            startActivity(i);
            overridePendingTransition(R.anim.enter_from_right,R.anim.exit_from_left);
        }else{
            showToast("Could Not Generate Session");
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.logout) {
            PrefManager.putBoolean("isLoggedIn",false);
            Intent i = new Intent(HomeActivity.this, LoginActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(i);
            overridePendingTransition(R.anim.stable,R.anim.slide_down);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();

        getUsers();
    }
}
