package Server.Controller;
import Server.Model.*;
import Server.VirtualView.*;
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
        String[] usernamePlayers = lobby.getUsernames();
        ArrayList<VirtualPlayerView> playersview = lobby.getPlayerViews();

        this.players = new ArrayList<>(usernamePlayers.length);
        this.gameName = new MyShelfie();
        this.model = new Game(lobby, gameName.selectCommonGoals(), this.selectFirstToPlay(usernamePlayers.length));

        this.virtualview = new VirtualGameView(this.model);
        this.virtualview.setGameViewObserver(this);
        for(int i=0; i<usernamePlayers.length; i++){
            this.players.add(new PlayerController(model.getPlayers().get(i), this, playersview.get(i)));
        }

        // notify the players they're in game
        for(Player p: lobby.getPlayers()){
            p.setInGame(true);
        }
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
        /*while(!model.getIsFinish()){
            this.players.get(model.getCurrentTurnPlayer()).play();

            // qui bisogna cambiare il current turn player (seguendo order)
        }*/

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
    public VirtualGameView getVirtualview(){ return this.virtualview; }



}
