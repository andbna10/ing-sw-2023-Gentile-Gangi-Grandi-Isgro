package ServerSide.Model;

import Networking.ListNode;
import Networking.ServerManager;
import ServerSide.Controller.GameController;
import ServerSide.Controller.LobbyManager;
import ServerSide.Model.CommonPattern.CommonPattern1;
import ServerSide.Model.CommonPattern.CommonPattern7;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    @Test
    void gameTest1() throws InterruptedException, IOException {
        var lobbyMan = new LobbyManager();
        var node = new ListNode(null, null, new ObjectOutputStream(new OutputStream() {
            @Override
            public void write(int b) throws IOException {

            }
        }));
        var serverMan = new ServerManager(null, lobbyMan, node, null, null);
        var player1 = new Player("simo", true, "random", serverMan);
        var player2 = new Player("fra", false, "random", serverMan);
        var player3 = new Player("andre", false, "random", serverMan);
        lobbyMan.getLobby("random").addPlayer(player1);
        lobbyMan.getLobby("random").addPlayer(player2);
        lobbyMan.getLobby("random").addPlayer(player3);
        var gameController = new GameController("random", lobbyMan);

        //getPlayersUsernames test
        var names = new ArrayList<String>();
        names.add("fra");
        names.add("andre");

        assertEquals(gameController.getModel().getPlayersUsernames(player1),names);

        //getPlayersBookshelf test
        player1.getBookshelf().setTile(5,0,ItemType.PLANTS);
        player1.getBookshelf().setTile(4,3,ItemType.GAMES);
        player3.getBookshelf().setTile(5,1,ItemType.TROPHIES);
        player3.getBookshelf().setTile(3,0,ItemType.BOOKS);

        var shelfSet = new ArrayList<ItemTile[][]>();
        shelfSet.add(player1.getBookshelf().getGameTiles());
        shelfSet.add(player3.getBookshelf().getGameTiles());
        assertEquals(shelfSet,gameController.getModel().getPlayersBookshelf(player2));
    }
}