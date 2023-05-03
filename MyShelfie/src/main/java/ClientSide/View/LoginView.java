package ClientSide.View;

import ClientSide.NetworkHandler.LoginViewObserver;

// vedere come modificare per jswing
public class LoginView {
    private LoginViewObserver obs;

    /**
     * Overview: LoginView constructor
     */
    public LoginView(LoginViewObserver observer){
        obs = observer;
    }
}
