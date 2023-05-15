package ServerSide.VirtualView;

import Networking.ServerManager;
import ServerSide.Model.Game;

public interface GameVViewObserver {
     /**
      * Overview: method aimed to call a player to move
      */
     void callTurn();

     /**
      * Overview: method aimed to call the model of the GameController
      */
     public Game getModel();

     /**
      * Overview: method aimed to verify the move of the player
      */
     public Boolean verifyTurn(int[] picked, int column, ServerManager manager);
}
