package in.androidmate.anujgupta.video_chat_opentok.ui.home;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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

public class HomeActivity extends AppCompatActivity implements HomeViewInterface {

    HomePresenter homePresenter;

    @BindView(R.id.rvUsers)
    RecyclerView rvUsers;

    RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);


        setupMVP();
        setupViews();

        getUsers();
    }

    private void setupMVP() {

        homePresenter = new HomePresenter(this);
    }

    private void setupViews(){
        rvUsers.setLayoutManager(new LinearLayoutManager(this));
    }

    private void getUsers() {
        String device_id = FirebaseInstanceId.getInstance().getToken();
        homePresenter.getUsers(device_id);
    }


    @Override
    public void displayUsers(final UserResponse userResponse) {
        if(userResponse!=null) {

            adapter = new UserAdapter(HomeActivity.this,userResponse.getUsers());
            rvUsers.setAdapter(adapter);
            rvUsers.addOnItemTouchListener(new RecyclerItemClickListener(this, new RecyclerItemClickListener.OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                    String device = userResponse.getUsers().get(position).getDeviceId();
                    Log.d("Device id",device);
                    homePresenter.startVideoChat(device);
                }
            }));

        }else{
            showToast("User Response returned null");
        }
    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(HomeActivity.this,msg,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showProgressBar() {

    }

    @Override
    public void hideProgressBar() {

    }

    @Override
    public void goToChat(SessionDataResponse sessionDataResponse) {
        if(sessionDataResponse != null){
            Intent i = new Intent(HomeActivity.this,ChatActivity.class);
            Bundle b = new Bundle();
            b.putString("session_id",sessionDataResponse.getSessionId());
            b.putString("token",sessionDataResponse.getToken());
            i.putExtras(b);
            startActivity(i);
            showToast("Starting Video Chat");
        }else{
            showToast("Could Not Generate Session");
        }
    }
}
