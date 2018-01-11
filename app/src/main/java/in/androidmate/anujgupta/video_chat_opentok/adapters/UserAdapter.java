package in.androidmate.anujgupta.video_chat_opentok.adapters;

import android.content.Context;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import in.androidmate.anujgupta.video_chat_opentok.R;
import in.androidmate.anujgupta.video_chat_opentok.models.User;
import in.androidmate.anujgupta.video_chat_opentok.utils.Typefacer;

/**
 * Created by anujgupta on 08/01/18.
 */

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.Users>{

    Context context;
    List<User> users;

    public UserAdapter(Context context, List<User> users) {
        this.context = context;
        this.users = users;
    }

    @Override
    public Users onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.row_users,parent,false);
        Users u = new Users(v);
        return u;
    }

    @Override
    public void onBindViewHolder(Users holder, int position) {
        holder.tvUsername.setText(users.get(position).getUsername());
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public class Users extends RecyclerView.ViewHolder {

        ImageView ivUser;
        TextView tvUsername;
        TextView tvStatus;
        public Users(View v) {
            super(v);

            ivUser = (ImageView) v.findViewById(R.id.ivUserImage);
            tvUsername = (TextView) v.findViewById(R.id.tvUsername);
            tvStatus = v.findViewById(R.id.tvStatus);

            tvUsername.setTypeface(Typefacer.getBold(context));
            tvStatus.setTypeface(Typefacer.getRegular(context));
        }
    }
}
