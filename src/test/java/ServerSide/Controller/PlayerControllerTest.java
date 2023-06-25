package ServerSide.Controller;

import Networking.ListNode;
import Networking.ServerManager;
import ServerSide.Model.*;
import ServerSide.Model.CommonPattern.CommonPattern1;
import ServerSide.Model.CommonPattern.CommonPattern2;
import ServerSide.Model.PersonalPattern.PersonalPattern3;
import ServerSide.VirtualView.VirtualPlayerView;
import org.junit.jupiter.api.Test;

import java.awt.print.Book;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class PlayerControllerTest {

    @Test
    void checkPointsTest() {
        var shelf = new Bookshelf();
        var shelf1 = new Bookshelf();
        var pg = new PersonalPattern3();
        var vview = new VirtualPlayerView(null);
        var playerController = new PlayerController(null, null, vview);

        shelf.setTile(0,0,ItemType.PLANTS);
        shelf.setTile(0,1,ItemType.PLANTS);
        shelf.setTile(1,0,ItemType.PLANTS);
        shelf.setTile(1,1,ItemType.PLANTS);
        shelf.setTile(1,2,ItemType.PLANTS);
        shelf.setTile(1,3,ItemType.PLANTS);
        shelf.setTile(2,2,ItemType.PLANTS);
        shelf.setTile(2,3,ItemType.PLANTS);

        shelf.setTile(2,0,ItemType.FRAMES);
        shelf.setTile(2,1,ItemType.FRAMES);
        shelf.setTile(3,0,ItemType.FRAMES);
        shelf.setTile(4,0,ItemType.FRAMES);

        shelf.setTile(2,4,ItemType.BOOKS);
        shelf.setTile(3,4,ItemType.BOOKS);

        shelf.setTile(3,1,ItemType.GAMES);
        shelf.setTile(3,2,ItemType.GAMES);
        shelf.setTile(3,3,ItemType.GAMES);

        shelf.setTile(5,0,ItemType.TROPHIES);
        shelf.setTile(5,1,ItemType.TROPHIES);
        shelf.setTile(5,2,ItemType.TROPHIES);
        shelf.setTile(4,1,ItemType.TROPHIES);

        shelf.setTile(4,2,ItemType.CATS);
        shelf.setTile(4,3,ItemType.CATS);
        shelf.setTile(4,4,ItemType.CATS);
        shelf.setTile(5,3,ItemType.CATS);
        shelf.setTile(5,4,ItemType.CATS);

        //shelf1
        /*
         0 1 2 3 4
        |C|F|P|F|C|
        |G|G|C|F|F|
        |T|B|P|B|F|
        |P|P|F|G|P|
        |T|P|G|P|P|
        |C|P|T|F|C|
        8 punti
         */
        shelf1.setTile(0,0,ItemType.CATS);
        shelf1.setTile(0,1,ItemType.FRAMES);
        shelf1.setTile(0,2,ItemType.PLANTS);
        shelf1.setTile(0,3,ItemType.FRAMES);
        shelf1.setTile(0,4,ItemType.CATS);

        shelf1.setTile(1,0,ItemType.GAMES);
        shelf1.setTile(1,1,ItemType.GAMES);
        shelf1.setTile(1,2,ItemType.CATS);
        shelf1.setTile(1,3,ItemType.FRAMES);
        shelf1.setTile(1,4,ItemType.FRAMES);

        shelf1.setTile(2,0,ItemType.TROPHIES);
        shelf1.setTile(2,1,ItemType.BOOKS);
        shelf1.setTile(2,2,ItemType.PLANTS);
        shelf1.setTile(2,3,ItemType.BOOKS);
        shelf1.setTile(2,4,ItemType.FRAMES);

        shelf1.setTile(3,0,ItemType.PLANTS);
        shelf1.setTile(3,1,ItemType.PLANTS);
        shelf1.setTile(3,2,ItemType.FRAMES);
        shelf1.setTile(3,3,ItemType.GAMES);
        shelf1.setTile(3,4,ItemType.PLANTS);

        shelf1.setTile(4,0,ItemType.TROPHIES);
        shelf1.setTile(4,1,ItemType.PLANTS);
        shelf1.setTile(4,2,ItemType.GAMES);
        shelf1.setTile(4,3,ItemType.PLANTS);
        shelf1.setTile(4,4,ItemType.PLANTS);

        shelf1.setTile(5,0,ItemType.CATS);
        shelf1.setTile(5,1,ItemType.PLANTS);
        shelf1.setTile(5,2,ItemType.TROPHIES);
        shelf1.setTile(5,3,ItemType.FRAMES);
        shelf1.setTile(5,4,ItemType.CATS);

        System.out.println(playerController.checkAdjacentTiles(shelf));
        System.out.println(playerController.checkAdjacentTiles(shelf1));
        System.out.println(playerController.checkPersonalGoal(shelf1,pg));

    }

    @Test
    void checkCommonGoalTest() throws InterruptedException, IOException {
        var shelf = new Bookshelf();
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

        var playerController = new PlayerController(null, gameController, new VirtualPlayerView(null));

        shelf.setTile(0,0,ItemType.PLANTS);
        shelf.setTile(0,1,ItemType.PLANTS);
        shelf.setTile(1,0,ItemType.PLANTS);
        shelf.setTile(1,1,ItemType.PLANTS);

        shelf.setTile(2,0,ItemType.FRAMES);
        shelf.setTile(2,1,ItemType.FRAMES);
        shelf.setTile(3,0,ItemType.FRAMES);
        shelf.setTile(3,1,ItemType.FRAMES);

        var common = new CommonPattern1();
        var shelfie =new MyShelfie();
        ArrayList<ScoringToken> scoringtokens = shelfie.selectScoringToken(3);
        for(int i=0; i<scoringtokens.size(); i+=2) {
            common.setElementStack(scoringtokens.get(i));
        }

        System.out.println("first");
        for (int i=0; i<common.getStack().size();i++)
            System.out.println(common.getStack().get(i).getPoints());
        assert playerController.checkCommonGoal(shelf, common, 1)==8;

        System.out.println("second");
        for (int i=0; i<common.getStack().size();i++)
            System.out.println(common.getStack().get(i).getPoints());
        assert playerController.checkCommonGoal(shelf, common, 1)==6;
        System.out.println("third");
        for (int i=0; i<common.getStack().size();i++)
            System.out.println(common.getStack().get(i).getPoints());
        assert playerController.checkCommonGoal(shelf, common, 1)==4;

    }


    @Test
    void feedColumnTest() throws InterruptedException, IOException {

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

        var playerController = new PlayerController(player1, gameController, new VirtualPlayerView(null));
        ArrayList<ItemTile> tiles = new ArrayList<>();
        tiles.add(new ItemTile(ItemType.CATS));
        tiles.add(new ItemTile(ItemType.TROPHIES));
        tiles.add(new ItemTile(ItemType.GAMES));

        playerController.feedColumn(1, tiles);

        assertEquals(player1.getBookshelf().getTile(5,1), tiles.get(0));
        assertEquals(player1.getBookshelf().getTile(4,1), tiles.get(1));
        assertEquals(player1.getBookshelf().getTile(3,1), tiles.get(2));

    }

    @Test
    void playTurnTest() throws IOException, InterruptedException {
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

        var playerController = new PlayerController(player1, gameController, new VirtualPlayerView(null));

        var toTake = new int[6];
        var order = new int [toTake.length/2];
        ItemTile res1 = gameController.getModel().getBoard().getBoard()[0][3].getTile();
        ItemTile res2 = gameController.getModel().getBoard().getBoard()[1][3].getTile();
        ItemTile res3 = gameController.getModel().getBoard().getBoard()[2][3].getTile();
        toTake[0]=0;
        toTake[1]=3;
        toTake[2]=1;
        toTake[3]=3;
        toTake[4]=2;
        toTake[5]=3;
        order[0]=2;
        order[1]=1;
        order[2]=0;
        playerController.playTurn(toTake, order, 1);

        //fix and place test
        assertEquals(player1.getBookshelf().getTile(5,1),res3);
        assertEquals(player1.getBookshelf().getTile(4,1),res2);
        assertEquals(player1.getBookshelf().getTile(3,1),res1);
        //picktiles test
        assertNull(gameController.getModel().getBoard().getBoard()[0][3].getTile());
        assertNull(gameController.getModel().getBoard().getBoard()[1][3].getTile());
        assertNull(gameController.getModel().getBoard().getBoard()[2][3].getTile());
        assertFalse(gameController.getModel().getBoard().getBoard()[0][3].getPickable());
        assertFalse(gameController.getModel().getBoard().getBoard()[1][3].getPickable());
        assertFalse(gameController.getModel().getBoard().getBoard()[2][3].getPickable());
    }

    @Test
    void checkTest() throws IOException, InterruptedException {
        var lobbyMan = new LobbyManager();
        var node = new ListNode(null, null, new ObjectOutputStream(new OutputStream() {
            @Override
            public void write(int b) throws IOException {

            }
        }));
        var serverMan = new ServerManager(null, lobbyMan, node, null, null);
        var player1 = new Player("simo", true, "random", serverMan);
        var player2 = new Player("fra", false, "random", serverMan);
        lobbyMan.getLobby("random").addPlayer(player1);
        lobbyMan.getLobby("random").addPlayer(player2);
        var gameController = new GameController("random", lobbyMan);

        var playerController = new PlayerController(player1, gameController, new VirtualPlayerView(null));
        var playerController2 = new PlayerController(player2, gameController, new VirtualPlayerView(null));

        //common1
        player1.getBookshelf().setTile(0,0,ItemType.PLANTS);
        player1.getBookshelf().setTile(0,1,ItemType.PLANTS);
        player1.getBookshelf().setTile(1,0,ItemType.PLANTS);
        player1.getBookshelf().setTile(1,1,ItemType.PLANTS);

        player1.getBookshelf().setTile(2,0,ItemType.FRAMES);
        player1.getBookshelf().setTile(2,1,ItemType.FRAMES);
        player1.getBookshelf().setTile(3,0,ItemType.FRAMES);
        player1.getBookshelf().setTile(3,1,ItemType.FRAMES);

        //common2
        player1.getBookshelf().setTile(0,3,ItemType.PLANTS);
        player1.getBookshelf().setTile(1,3,ItemType.TROPHIES);
        player1.getBookshelf().setTile(2,3,ItemType.FRAMES);
        player1.getBookshelf().setTile(3,3,ItemType.BOOKS);
        player1.getBookshelf().setTile(4,3,ItemType.CATS);
        player1.getBookshelf().setTile(5,3,ItemType.GAMES);
        player1.getBookshelf().setTile(0,4,ItemType.PLANTS);
        player1.getBookshelf().setTile(1,4,ItemType.TROPHIES);
        player1.getBookshelf().setTile(2,4,ItemType.FRAMES);
        player1.getBookshelf().setTile(3,4,ItemType.BOOKS);
        player1.getBookshelf().setTile(4,4,ItemType.CATS);
        player1.getBookshelf().setTile(5,4,ItemType.GAMES);

        var common1 = new CommonPattern1();
        var common2 = new CommonPattern2();
        var commonList = new ArrayList<CommonGoalCard>();
        commonList.add(common1);
        commonList.add(common2);
        var shelfie =new MyShelfie();
        ArrayList<ScoringToken> scoringtokens = shelfie.selectScoringToken(2);
        for(int i=0; i<scoringtokens.size(); i+=2) {
            common1.setElementStack(scoringtokens.get(i));
            common2.setElementStack(scoringtokens.get(i+1));
        }
        playerController.check(commonList,"random");
        assertTrue(player1.getBookshelf().getCommonOne());
        assertTrue(player1.getBookshelf().getCommonTwo());

        //fullness
        for(int i=0;i<6;i++)
            for(int j=0;j<5;j++)
                player2.getBookshelf().setTile(i,j,ItemType.CATS);

        playerController2.check(commonList,"random");
        assertEquals(4+1,player2.getPoints());

        //spaceleft test
        assertEquals(3,playerController.spaceLeft());
        assertEquals(0,playerController2.spaceLeft());
    }

}