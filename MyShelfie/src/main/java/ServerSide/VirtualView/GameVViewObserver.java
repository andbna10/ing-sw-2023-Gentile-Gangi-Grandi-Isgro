package ServerSide.VirtualView;

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
}
