package ServerSide.Model.CommonPattern;

import ServerSide.Controller.MyShelfie;
import ServerSide.Controller.PlayerController;
import ServerSide.Model.Bookshelf;
import ServerSide.Model.ItemType;
import ServerSide.Model.Roman;
import ServerSide.Model.ScoringToken;
import ServerSide.VirtualView.VirtualPlayerView;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class CommonPattern8Test {
    @Test
    void validatedTest() {
        var shelf = new Bookshelf();
        var cp8 = new CommonPattern8();
        var st = new ScoringToken(Roman.I,8);
        var vview = new VirtualPlayerView(null);
        var playerController = new PlayerController(null, null, vview);

        shelf.setTile(0,0, ItemType.TROPHIES);
        shelf.setTile(0,4, ItemType.TROPHIES);
        shelf.setTile(5,0, ItemType.TROPHIES);
        shelf.setTile(5,4, ItemType.TROPHIES);

        assertTrue(cp8.validated(shelf));

        cp8.setElementStack(st);
        System.out.println(playerController.checkCommonGoal(shelf,cp8,1));
    }

}