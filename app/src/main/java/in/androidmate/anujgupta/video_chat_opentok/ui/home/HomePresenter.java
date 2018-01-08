package in.androidmate.anujgupta.video_chat_opentok.ui.home;

/**
 * Created by anujgupta on 08/01/18.
 */

public class HomePresenter implements HomePresenterInterface {

    HomeViewInterface homeView;

    public HomePresenter(HomeViewInterface homeView) {
        this.homeView = homeView;
    }
}
