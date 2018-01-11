package in.androidmate.anujgupta.video_chat_opentok.ui.login;

import android.app.Activity;

/**
 * Created by anujgupta on 08/01/18.
 */

public interface LoginViewInteraface {

    void showToast(String msg);
    void goToActivity(Class<? extends Activity> activity);
    void showProgressDialog(String str);
    void hideProgressDialog();
}
