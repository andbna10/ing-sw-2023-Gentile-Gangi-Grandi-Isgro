package Client.View;

import Client.NetworkHandler.LoginHandler;
import Client.NetworkHandler.LoginViewObserver;

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
