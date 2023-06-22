package ServerSide.Controller;

import static org.junit.jupiter.api.Assertions.*;

import Networking.ListNode;
import Networking.ServerManager;
import ServerSide.Model.Player;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

class LobbyManagerTest {
    @Test
    void Test() throws IOException, InterruptedException {
        var lobbyMan = new LobbyManager();
        var node = new ListNode(null, null, new ObjectOutputStream(new OutputStream() {
            @Override
            public void write(int b) throws IOException {

            }
        }));
        lobbyMan.createLobby("id");
        var serverMan = new ServerManager(null, lobbyMan, node, null, null);
        var player1 = new Player("simo", true, "random", serverMan);
        var player2 = new Player("fra", false, "random", serverMan);
        var player3 = new Player("andre", false, "id", serverMan);
        lobbyMan.getLobby("random").addPlayer(player1);
        lobbyMan.getLobby("random").addPlayer(player2);
        lobbyMan.getLobby("id").addPlayer(player3);

        //checkUsername test
        assertTrue(lobbyMan.checkUsername("simo"));
        assertFalse(lobbyMan.checkUsername("lorenzo"));
        assertTrue(lobbyMan.checkUsername("andre"));

        //checkInGame test
        assert lobbyMan.checkInGame("random") == -1;
        var gameController = new GameController("random", lobbyMan);
        assert lobbyMan.checkInGame("random") == 1;

        assert lobbyMan.checkInGame("prova") == 0;

        //getIdByUser test
        assertEquals("random", lobbyMan.getIdByUser("fra"));
        assertEquals("id", lobbyMan.getIdByUser("andre"));
        assertNull(lobbyMan.getIdByUser("lorenzo"));

        //closeLobby test
        lobbyMan.closeLobby("id");
        assertNull(lobbyMan.getLobby("id"));
    }
}