package ClientSide.NetworkHandler;

import ClientSide.View.CLI.GameCLI;
import ClientSide.View.GUI.GameGUI;
import Networking.ClientManager;
import Networking.ClientManagerGUI;

import java.io.IOException;

public class GameHandler implements GameViewObserver{
    private ClientManager manager;
    private ClientManagerGUI managergui;
    private String message;
    private GameGUI gui;
    private GameCLI cli;

    /**
     * Overview: GameHandler constructor1 cli
     */
    public GameHandler(ClientManager manager, String message){
        this.manager = manager;
        this.message = message;
        this.cli = new GameCLI(this);
    }

    /**
     * Overview: GameHandler constructor2 gui
     */
    public GameHandler(ClientManagerGUI manager, String message) throws IOException {
        this.managergui = manager;
        this.message = message;
        runGameGUI();
    }

    /**
     * Overview: cli getter
     */
    public GameCLI getCli(){
        return cli;
    }

    /**
     * Overview: gui getter
     */
    public GameGUI getGui() {
        return gui;
    }

    public void runGameGUI() throws IOException {
        this.gui = new GameGUI(this);
    }
}
