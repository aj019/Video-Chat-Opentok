package in.androidmate.anujgupta.video_chat_opentok.ui.signup;

/**
 * Created by anujgupta on 08/01/18.
 */

public class SignUpPresenter implements SignUpPresenterInterface {

    SignUpViewInterface signUpView;

    public SignUpPresenter(SignUpViewInterface signUpView) {
        this.signUpView = signUpView;
    }
}
