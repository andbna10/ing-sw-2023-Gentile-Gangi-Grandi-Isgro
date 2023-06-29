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

        lobbyMan.createLobby("random");
        var serverMan1 = new ServerManager(null, lobbyMan, node, null, null);
        var serverMan2 = new ServerManager(null, lobbyMan, node, null, null);
        var serverMan3 = new ServerManager(null, lobbyMan, node, null, null);
        var player1 = new Player("simo", true, "random", serverMan1);
        var player2 = new Player("fra", false, "random", serverMan2);
        var player3 = new Player("andre", false, "random", serverMan3);
        lobbyMan.getLobby("random").addPlayer(player1);
        lobbyMan.getLobby("random").addPlayer(player2);
        lobbyMan.getLobby("random").addPlayer(player3);

        assertEquals(3, lobbyMan.getLobby("random").getModel().getPlayers().size());
        assertFalse(lobbyMan.getLobby("random").getModel().removePlayer(serverMan1));
        assertNotEquals(3, lobbyMan.getLobby("random").getModel().getPlayers().size());
        assertEquals(2, lobbyMan.getLobby("random").getModel().getPlayers().size());
        assertSame("fra", lobbyMan.getLobby("random").getModel().getPlayers().get(0).getUsername());
        assertFalse(lobbyMan.getLobby("random").getModel().removePlayer(serverMan2));
        assertFalse(lobbyMan.getLobby("random").getModel().getReadyToPlay());
        assertTrue(lobbyMan.getLobby("random").getModel().removePlayer(serverMan3));



    }
}