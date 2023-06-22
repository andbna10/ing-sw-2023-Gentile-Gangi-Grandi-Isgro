package ServerSide.Controller;

import Networking.ListNode;
import Networking.ServerManager;
import ServerSide.Model.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class GameControllerTest {

    @Test
    void associateTokensTest() throws InterruptedException, IOException {
        var lobbyMan = new LobbyManager();
        //Socket socket = new Socket("localhost", 59090);
        //var server = new Server(59090);
        //server.start();
        //var serversocket = new ServerSocket(59090);
        //Socket clientsocket = serversocket.accept();
        var node = new ListNode(null, null, new ObjectOutputStream(new OutputStream() {
            @Override
            public void write(int b) throws IOException {

            }
        }));
        var serverMan = new ServerManager(null, lobbyMan, node, null, null);
        lobbyMan.getLobby("random").addPlayer(new Player("simo", true, "random", serverMan));
        lobbyMan.getLobby("random").addPlayer(new Player("fra", false, "random", serverMan));
        lobbyMan.getLobby("random").addPlayer(new Player("andre", false, "random", serverMan));
        var controller = new GameController("random",lobbyMan);
        ArrayList<ScoringToken> tokens = new ArrayList<>();
        tokens.add(new ScoringToken(Roman.I, 0));
        tokens.add(new ScoringToken(Roman.I, 4));
        tokens.add(new ScoringToken(Roman.I, 6));
        tokens.add(new ScoringToken(Roman.I, 8));

        controller.associateScoringTokens(3);
        for (int i=0; i<4;i++)
            assertEquals(tokens.get(i).getPoints(), controller.getModel().getCommonGoals().get(0).getStack().get(i).getPoints());
    }


    @Test
    void StartGameTest() throws InterruptedException, IOException {
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
        var controller1 = new GameController("random", lobbyMan);

        //restoreBoard test
        for(int i=0; i<9; i++){
            for(int j=0; j<9; j++){
                if(controller1.getModel().getBoard().getBoard()[i][j].getStatus().equals(Status.IN))
                    assertNotNull(controller1.getModel().getBoard().getBoard()[i][j]);
            }
        }
        //setpersonalGoals test
        assertNotNull(player1.getGoal());
        assertNotNull(player2.getGoal());
        assertNotNull(player3.getGoal());

        //checkPickables test
        assertTrue(controller1.getModel().getBoard().getBoard()[1][3].getPickable());
        assertFalse(controller1.getModel().getBoard().getBoard()[4][4].getPickable());
    }

    @Test
    void endGameTest() throws IOException, InterruptedException {
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
        var controller = new GameController("random", lobbyMan);

        player2.getBookshelf().setTile(0,0, ItemType.PLANTS);
        player2.getBookshelf().setTile(0,1, ItemType.PLANTS);
        player2.getBookshelf().setTile(0,2, ItemType.PLANTS);

        controller.endGame(false,"random");
        assertNotNull(controller.getModel().getWinner());
        assert controller.getModel().getPlayers().get(0).getPoints()==0;

        assertEquals(2,controller.getModel().getPlayers().get(1).getPoints());
    }

    @Test
    void verifyTurnTest() throws InterruptedException, IOException {
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
        var controller = new GameController("random", lobbyMan);

        var picked = new int[2];
        picked[0]=4;
        picked[1]=4;
        var picked1 = new int[4];
        picked1[0]=1;
        picked1[1]=3;
        picked1[2]=6;
        picked1[3]=3;
        var picked3 = new int[4];
        picked3[0]=1;
        picked3[1]=3;
        picked3[2]=1;
        picked3[3]=4;


        player1.getBookshelf().setTile(1,0,ItemType.BOOKS);
        player1.getBookshelf().setTile(2,0,ItemType.BOOKS);
        player1.getBookshelf().setTile(3,0,ItemType.BOOKS);
        player1.getBookshelf().setTile(4,0,ItemType.BOOKS);
        player1.getBookshelf().setTile(5,0,ItemType.BOOKS);


        assert controller.verifyTurn(picked,1,serverMan)==0;
        assert controller.verifyTurn(picked1,1,serverMan)==1;
        assert controller.verifyTurn(picked3,1,serverMan)==3;
        assert controller.verifyTurn(picked3,0,serverMan)==2;
    }

    @Test
    void getMinMaxTest() throws InterruptedException, IOException {
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
        var controller = new GameController("random", lobbyMan);
        assert controller.getMin(1,2,3)==1;
        assert controller.getMax(1,2,3)==3;
    }
}