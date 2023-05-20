package ServerSide.Controller;

import Networking.ServerManager;
import ServerSide.Model.Bookshelf;
import ServerSide.Model.ItemType;
import ServerSide.Model.PersonalPattern.PersonalPattern3;
import ServerSide.Model.Player;
import ServerSide.VirtualView.VirtualPlayerView;
import org.junit.jupiter.api.Test;

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

}