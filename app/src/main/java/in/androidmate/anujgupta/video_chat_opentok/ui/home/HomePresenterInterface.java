package in.androidmate.anujgupta.video_chat_opentok.ui.home;

/**
 * Created by anujgupta on 08/01/18.
 */

public interface HomePresenterInterface {

    void getUsers(String device_id);
    void startVideoChat(String device_id,String username);
}
