package in.androidmate.anujgupta.video_chat_opentok.ui.chat;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import in.androidmate.anujgupta.video_chat_opentok.R;

/**
 * Created by anujgupta on 10/01/18.
 */

public class CallingFragment extends Fragment {


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_calling,container,false);
        return v;
    }
}
