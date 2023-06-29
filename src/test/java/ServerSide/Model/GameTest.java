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
        lobbyMan.createLobby("random");
        var serverMan1 = new ServerManager(null, lobbyMan, node, null, null);
        var serverMan2 = new ServerManager(null, lobbyMan, node, null, null);
        var serverMan3 = new ServerManager(null, lobbyMan, node, null, null);
        var player1 = new Player("simo", true, "random", serverMan1);
        var player2 = new Player("fra", false, "random", serverMan2);
        var player3 = new Player("andre", false, "random", serverMan3);
        lobbyMan.getLobby("random").addPlayer(player1);

        var gameController = new GameController("random", lobbyMan);

        //getPlayersUsernames test
        var names = new ArrayList<String>();
        names.add("fra");
        names.add("andre");


        //getPlayersBookshelf test
        player1.getBookshelf().setTile(5,0,ItemType.PLANTS);
        player1.getBookshelf().setTile(4,3,ItemType.GAMES);
        player3.getBookshelf().setTile(5,1,ItemType.TROPHIES);
        player3.getBookshelf().setTile(3,0,ItemType.BOOKS);

        var shelfSet = new ArrayList<ItemTile[][]>();
        shelfSet.add(player1.getBookshelf().getGameTiles());
        shelfSet.add(player3.getBookshelf().getGameTiles());


        //advance
        assertTrue(gameController.getModel().advance());
        lobbyMan.getLobby("random").addPlayer(player2);
        lobbyMan.getLobby("random").addPlayer(player3);
        assertEquals(gameController.getModel().getPlayersUsernames(player1),names);
        assertEquals(shelfSet,gameController.getModel().getPlayersBookshelf(player2));
        var gameController1 = new GameController("random", lobbyMan);
        assertFalse(gameController1.getModel().advance());
        int oldcurrentturnplayer = gameController1.getModel().getCurrentTurnPlayer();
        assertTrue(gameController1.getModel().getCurrentTurnPlayer() == oldcurrentturnplayer + 1 || gameController.getModel().getCurrentTurnPlayer() == 0);

        //advance finish
        assertTrue(gameController1.getModel().advanceFinish() == 0);
    }
}