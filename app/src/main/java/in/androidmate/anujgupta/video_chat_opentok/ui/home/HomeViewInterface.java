package in.androidmate.anujgupta.video_chat_opentok.ui.home;

import in.androidmate.anujgupta.video_chat_opentok.models.SessionDataResponse;
import in.androidmate.anujgupta.video_chat_opentok.models.UserResponse;

/**
 * Created by anujgupta on 08/01/18.
 */

public interface HomeViewInterface {

    void displayUsers(UserResponse userResponse);
    void showToast(String msg);
    void showProgressBar(String msg);
    void hideProgressBar();
    void goToChat(SessionDataResponse sessionDataResponse);

}
