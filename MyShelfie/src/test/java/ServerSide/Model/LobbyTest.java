package ServerSide.Model;

import Networking.ListNode;
import Networking.ServerManager;
import ServerSide.Controller.LobbyManager;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import static org.junit.jupiter.api.Assertions.*;


class LobbyTest {

    @Test
    void lobbyTest() throws IOException, InterruptedException {
        var lobbyMan = new LobbyManager();
        var node = new ListNode(null, null, new ObjectOutputStream(new OutputStream() {
            @Override
            public void write(int b) throws IOException {

            }
        }));

        var serverMan1 = new ServerManager(null, lobbyMan, node, null, null);
        var serverMan2 = new ServerManager(null, lobbyMan, node, null, null);
        var player1 = new Player("simo", true, "random", serverMan1);
        var player2 = new Player("fra", false, "random", serverMan2);
        lobbyMan.getLobby("random").addPlayer(player1);
        lobbyMan.getLobby("random").addPlayer(player2);

        assertTrue(lobbyMan.getLobby("random").getModel().getPlayers().size() == 2);
        assertFalse(lobbyMan.getLobby("random").getModel().removePlayer(serverMan1));
        assertFalse(lobbyMan.getLobby("random").getModel().getPlayers().size() == 2);
        assertTrue(lobbyMan.getLobby("random").getModel().getPlayers().size() == 1);
        assertTrue(lobbyMan.getLobby("random").getModel().getPlayers().get(0).getUsername() == "fra");
        assertTrue(lobbyMan.getLobby("random").getModel().removePlayer(serverMan2));



    }
}