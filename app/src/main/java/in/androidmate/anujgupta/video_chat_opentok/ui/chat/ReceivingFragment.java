package in.androidmate.anujgupta.video_chat_opentok.ui.chat;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.androidmate.anujgupta.video_chat_opentok.R;
import in.androidmate.anujgupta.video_chat_opentok.utils.Typefacer;

/**
 * Created by anujgupta on 11/01/18.
 */

public class ReceivingFragment extends Fragment {

    @BindView(R.id.tvConnecting)
    TextView tvConnecting;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_receiving,container,false);
        ButterKnife.bind(this,v);
        initView();
        return v;
    }

    private void initView() {
        ButterKnife.bind(getActivity());

        tvConnecting.setTypeface(Typefacer.getRegular(getActivity()));
    }
}
