package ServerSide.Controller;
import ServerSide.Model.*;
import ServerSide.VirtualView.*;
import java.util.Random;

import java.util.ArrayList;

public class GameController implements GameVViewObserver {
    private Game model;
    private MyShelfie gameName;
    private ArrayList<PlayerController> players;
    private VirtualGameView virtualview;
    private LobbyManager lobbymanager;

    /**
     * Overview: GameController constructor, initialization of MyShelfie and Game classes
     */
    public GameController(String id, LobbyManager lobbymanager){
        this.lobbymanager = lobbymanager;

        Lobby lobby = this.lobbymanager.getLobby(id).getModel();
        // objects taken from the lobby
        String[] usernamePlayers = lobby.getUsernames();
        ArrayList<VirtualPlayerView> playersview = lobby.getPlayerViews();
        ArrayList<Player> players = lobby.getPlayers();

        this.players = new ArrayList<>(usernamePlayers.length);
        this.gameName = new MyShelfie();
        this.model = new Game(lobby, gameName.selectCommonGoals(), this.selectFirstToPlay(usernamePlayers.length));
        this.virtualview = new VirtualGameView(this.model);
        this.virtualview.setGameViewObserver(this);

        for(int i=0; i<usernamePlayers.length; i++){
            // initialize a PlayerController for each player of the lobby
            this.players.add(new PlayerController(model.getPlayers().get(i), this, playersview.get(i)));

            // set the manager of all players in the VirtualGameView
            this.virtualview.setManager(players.get(i).getManager());

            // set the VirtualGameView reference in the ServerManager of all players
            players.get(i).getManager().setGameView(this.virtualview);

            // notify the players they're in game
            players.get(i).setInGame(true);
        }

        startGame();
    }

    /**
     * Overview: select first to play
     */
    public int selectFirstToPlay(int nPlayers){
        Random r = new Random();
        int index = r.nextInt(nPlayers);
        return index;
    }

    /**
     * Overview: associate scoring tokens to selected common goal cards
     */
    public void associateScoringTokens(int nPlayers){
        Random r = new Random();
        ArrayList<ScoringToken> scoringtokens = gameName.selectScoringToken(nPlayers);
        for(int i=0; i<scoringtokens.size();i++){
            if(scoringtokens.get(i).getRoman() == Roman.I){
                model.getCommonGoals().get(0).setElementStack(scoringtokens.get(i));
            } else {
                model.getCommonGoals().get(1).setElementStack(scoringtokens.get(i));
            }
        }
    }

    /**
     * Overview: set personal goal cards for players
     */
    public void setPersonalGoals(){
        ArrayList<PersonalGoalCard> personalgoals = gameName.selectPersonalGoals(this.players.size());
        for(int i=0; i<this.players.size(); i++){
            this.players.get(i).setGoal(personalgoals.get(i));
        }
    }

    /**
     * Overview: restore the board with tiles in empty board cells
     */
    public void restoreBoard(){
        ArrayList<ItemTile> tiles = gameName.selectItemTiles(model.getBoard().getEmptyCells());
        model.getBoard().setTiles(tiles);
    }

    /**
     * Overview: start the game
     */
    public void startGame(){
        this.associateScoringTokens(this.players.size());
        this.restoreBoard();
        this.setPersonalGoals();
        this.model.notifyObserver();
    }

    /**
     * Overview: end game
     */
    // to be implemented (qui si deve vedere chi ha fatto piu punti)

    /**
     * Overview: model getter
     */
    public Game getModel(){ return model; }

    /**
     * Overview: Virtualview getter
     */
    public VirtualGameView getVirtualView(){ return this.virtualview; }

    /**
     * Overview: check whether boardcell can be picked,
     *  to be called at startgame and on every endturn (a cell can't turn pickable during player's turn )
     */
    public void checkPickables (BoardGame boardGame) {

        boolean flag = true;

        for (int i = 1; i < 8; i++)
            for(int j = 1; j < 8; j++)
                if(boardGame.getBoard()[i][j].getStatus() == Status.IN) {

                    //una cell non è pickable se tutte le adiacenti sono null
                    // o tutte le adiacenti sono piene, si altrimenti

                    if (boardGame.getTile(i - 1, j - 1) == null &&
                            boardGame.getTile(i - 1, j + 1) == null &&
                            boardGame.getTile(i + 1, j - 1) == null &&
                            boardGame.getTile(i + 1, j + 1) == null) flag = false;
                    if (boardGame.getTile(i - 1, j - 1) != null &&
                            boardGame.getTile(i - 1, j + 1) != null &&
                            boardGame.getTile(i + 1, j - 1) != null &&
                            boardGame.getTile(i + 1, j + 1) != null) flag = false;

                    boardGame.getBoard()[i][j].setPickable(flag);

                }

    }


}