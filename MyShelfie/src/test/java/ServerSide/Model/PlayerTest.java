package ServerSide.Model;

import Networking.ListNode;
import Networking.ServerManager;
import ServerSide.Controller.LobbyManager;
import org.junit.Test;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    @Test
    void PlayerTest() throws IOException {
        ListNode node = new ListNode(null, null, new ObjectOutputStream(new OutputStream() {
            @Override
            public void write(int b) throws IOException {

            }
        }));
        ServerManager serverman = new ServerManager(null, null, node, null, null);
        Player player1 = new Player("andbna", false, "id", serverman);
        assertTrue(player1.getUsername() != null);
        assertTrue(player1.getPoints() == 0);
        player1.addPoints(5);
        assertTrue(player1.getPoints() == 5);
    }

}