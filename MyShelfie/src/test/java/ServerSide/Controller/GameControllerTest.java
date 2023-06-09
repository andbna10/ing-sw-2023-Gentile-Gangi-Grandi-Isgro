package ServerSide.Controller;

import Networking.ListNode;
import Networking.Server;
import Networking.ServerManager;
import ServerSide.Model.Player;
import ServerSide.Model.Roman;
import ServerSide.Model.ScoringToken;
import ServerSide.Model.Status;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class GameControllerTest {

    @Test
    void associateTokensTest() throws InterruptedException, IOException {
        var lobbyMan = new LobbyManager();
        Socket socket = new Socket("localhost", 59090);
        var server = new Server(59090);
        server.start();
        var serverMan1 = new ServerManager(socket, lobbyMan, new ListNode(null, null, null), server, null);
        var serverMan2 = new ServerManager(socket, lobbyMan, new ListNode(null, null, null), server, null);
        var serverMan3 = new ServerManager(socket, lobbyMan, new ListNode(null, null, null), server, null);
        lobbyMan.getLobby("random").addPlayer(new Player("simo", true, "random", serverMan1));
        lobbyMan.getLobby("random").addPlayer(new Player("fra", false, "random", serverMan2));
        lobbyMan.getLobby("random").addPlayer(new Player("andre", false, "random", serverMan3));
        var controller = new GameController("random", lobbyMan);
        ArrayList<ScoringToken> tokens = new ArrayList<>();
        tokens.add(new ScoringToken(Roman.I, 4));
        tokens.add(new ScoringToken(Roman.I, 6));
        tokens.add(new ScoringToken(Roman.I, 8));

        controller.associateScoringTokens(3);

        assertEquals(tokens, controller.getModel().getCommonGoals().get(0).getStack());
    }


    @Test
    void restoreBoardTest() throws InterruptedException {
        var lobbyMan = new LobbyManager();
        var controller = new GameController("random", lobbyMan);

        for(int i=0; i<9; i++){
            for(int j=0; j<9; j++){
                if(controller.getModel().getBoard().getBoard()[i][j].getStatus().equals(Status.IN))
                    assertNotNull(controller.getModel().getBoard().getBoard()[i][j]);
            }
        }
    }

}