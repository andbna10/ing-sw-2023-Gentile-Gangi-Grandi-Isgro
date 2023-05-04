package Server.Model;

import Networking.ServerManager;
import Server.Controller.LobbyManager;
import org.junit.Test;

import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

public class LobbyTest {

    @Test
    public void getUsernamesTest() throws IOException {
        var csock = new Socket();
        var lobbyman = new LobbyManager();
        ServerManager sm = null;
        try {
            sm = new ServerManager(csock, lobbyman);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        var p1 = new Player("pippo", true, "0", sm);
        //var p2 = new Player("pluto", false, "0", sm);
        //var p3 = new Player("paperino", false, "0", sm);
        assertEquals(p1.getUsername(), "pippo");
    }

}