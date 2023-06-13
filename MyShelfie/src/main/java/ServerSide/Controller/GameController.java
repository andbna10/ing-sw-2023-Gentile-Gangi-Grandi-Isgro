package ServerSide.Controller;
import Networking.ServerManager;
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
     * @author Andrea Isgrò
     * @param id lobby id
     * @param lobbymanager Lobby Manager for network communication
     * @throws InterruptedException if any thread has interrupted the current thread
     */
    public GameController(String id, LobbyManager lobbymanager) throws InterruptedException {
        this.lobbymanager = lobbymanager;
        Lobby lobby = this.lobbymanager.getLobby(id).getModel();
        // objects taken from the lobby
        String[] usernamePlayers = lobby.getUsernames();
        ArrayList<VirtualPlayerView> playersview = lobby.getPlayerViews();
        ArrayList<Player> players = lobby.getPlayers();

        this.players = new ArrayList<>(usernamePlayers.length);
        this.gameName = new MyShelfie();
        this.model = new Game(lobby, gameName.selectCommonGoals(), this.selectFirstToPlay(usernamePlayers.length));
        this.virtualview = new VirtualGameView(this.model, players);
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
        lobby.setInGame(true);
    }

    /**
     * Overview: select first to play
     * @author Andrea Isgrò
     * @param nPlayers number of players
     * @return number of the first user to play
     */
    public int selectFirstToPlay(int nPlayers){
        Random r = new Random();
        return r.nextInt(nPlayers);
    }

    /**
     * Overview: associate scoring tokens to selected common goal cards
     * @author Andrea Isgrò
     * @param nPlayers number of players
     */
    public void associateScoringTokens(int nPlayers){
        ArrayList<ScoringToken> scoringtokens = gameName.selectScoringToken(nPlayers);
        for (ScoringToken scoringtoken : scoringtokens) {
            if (scoringtoken.getRoman() == Roman.I) {
                model.getCommonGoals().get(0).setElementStack(scoringtoken);
            } else {
                model.getCommonGoals().get(1).setElementStack(scoringtoken);
            }
        }
    }

    /**
     * Overview: set personal goal cards for players
     * @author Andrea Isgrò
     */
    public void setPersonalGoals(){
        ArrayList<PersonalGoalCard> personalgoals = gameName.selectPersonalGoals(this.players.size());
        for(int i=0; i<this.players.size(); i++){
            this.players.get(i).setGoal(personalgoals.get(i));
        }
    }

    /**
     * Overview: restore the board with tiles in empty board cells
     * @author Andrea Isgrò
     */
    public void restoreBoard(){
        ArrayList<ItemTile> tiles = gameName.selectItemTiles(model.getBoard().getEmptyCells());
        model.getBoard().setTiles(tiles);
    }

    /**
     * Overview: start the game
     * @author Andrea Isgrò
     * @throws InterruptedException if any thread has interrupted the current thread
     */
    public void startGame() throws InterruptedException {
        this.associateScoringTokens(this.players.size());
        this.restoreBoard();
        this.setPersonalGoals();
        this.checkPickables(model.getBoard());
        this.model.notifyObserverTheStart(model.getCommonGoals().get(0).getPatternNumber(), model.getCommonGoals().get(1).getPatternNumber(), model.getCommonGoals().get(0).getElementStack(model.getCommonGoals().get(0).getStack().size()-1), model.getCommonGoals().get(1).getElementStack(model.getCommonGoals().get(1).getStack().size()-1));
        Thread.sleep(1000);
        callTurn();
    }

    /**
     * Overview: method aimed to call a player to move
     * @author Andrea Isgrò
     */
    @Override
    public void callTurn(){
        if(checkPickables(model.getBoard()) == 0){
            restoreBoard();
            // vedere se crea problemi nella cli (?)
            checkPickables(model.getBoard());
            model.getPlayers().get(model.getCurrentTurnPlayer()).notifyPlayerBoardRestored(model.getBoard().getBoard());
        }
        // take the reference of the current turn player
        Player player = model.getPlayers().get(model.getCurrentTurnPlayer());
        model.getPlayers().get(model.getCurrentTurnPlayer()).notifyPlayerTurn(model.getBoard().getBoard(), model.getPlayersBookshelf(player), model.getPlayersUsernames(player));
    }

    /**
     * Overview: end game
     * @author Simone Grandi
     * @author Andrea Isgrò
     * @param discon disconnection status
     * @param id lobby id
     */
    @Override
    public void endGame(boolean discon, String id){
        int maxpoints=0;
        //calcolo punteggi

        if(!discon) {
            for (PlayerController playerController : players) {
                playerController.getModel().addPoints(playerController.checkPersonalGoal(playerController.getModel().getBookshelf(), playerController.getModel().getGoal()));
                playerController.getModel().addPoints(playerController.checkAdjacentTiles(playerController.getModel().getBookshelf()));
            }
            //decretare il vincitore
            //utilizzando order con il senso orario e il >= copro il caso di parità
            for (int i = 0; i < model.getPlayers().size(); i++) {
                if (model.getPlayers().get(model.getOrder(i)).getPoints() >= maxpoints) {
                    maxpoints = model.getPlayers().get(model.getOrder(i)).getPoints();
                    model.setWinner(model.getPlayers().get(model.getOrder(i)).getUsername());
                }
            }
        }

        model.setEnded(true);
        lobbymanager.getLobby(id).getModel().setInGame(false);
        BoardGame.setInstanceNull();
        for(PlayerController p: players){
            p.getModel().getBookshelf().emptyShelf();
        }
        virtualview.notifyEnd(model.getWinner(), model.getPlayers(), discon, id);
    }

    /**
     * Overview: model getter
     */
    @Override
    public Game getModel(){ return model; }

    /**
     * Overview: VirtualGameView getter
     */
    public VirtualGameView getVirtualView(){ return this.virtualview; }

    /**
     * Overview: method aimed to check whether the boardCell can be picked.
     * To be called at startGame() and on every endTurn() (a boardCell can't turn pickable during player's turn)
     * @author Andrea Isgrò
     * @author Mirko Gentile
     * @param boardGame board game instance
     * @return number of pickable tiles
     */
    @Override
    public int checkPickables (BoardGame boardGame) {
        int numPickables = 0;

        for (int i = 0; i < 9; i++)
            for (int j = 0; j < 9; j++)
                if ( (i == 0 || i == 8 || j == 0 || j == 8) &&
                        boardGame.getBoard()[i][j].getStatus() == Status.IN &&
                        boardGame.getBoard()[i][j].getTile() != null ) {

                    boardGame.getBoard()[i][j].setPickable(true);
                    numPickables++;
                }


        for (int i = 1; i < 8; i++) {
            for (int j = 1; j < 8; j++) {
                if (boardGame.getBoard()[i][j].getStatus() == Status.IN && boardGame.getBoard()[i][j].getTile() != null) {

                    boolean changed = false;
                    if (boardGame.getTile(i - 1, j) == null &&
                            boardGame.getTile(i + 1, j) == null &&
                            boardGame.getTile(i, j + 1) == null &&
                            boardGame.getTile(i, j - 1) == null) {
                        boardGame.getBoard()[i][j].setPickable(false);
                        changed = true;
                    }
                    if (boardGame.getTile(i - 1, j) != null &&
                            boardGame.getTile(i + 1, j) != null &&
                            boardGame.getTile(i, j - 1) != null &&
                            boardGame.getTile(i, j + 1) != null) {
                        boardGame.getBoard()[i][j].setPickable(false);
                        changed = true;
                    }
                    if (!changed) {
                        boardGame.getBoard()[i][j].setPickable(true);
                        numPickables++;
                    }
                }
            }
        }
        return numPickables;
    }

    /**
     * Overview: method aimed to verify played turn
     * @author Andrea Isgrò
     * @param picked chosen tiles
     * @param column column index to insert tiles in
     * @param manager Server Manager for network communication
     * @return error type: (0) if selected tiles are not pickable, (1) if selected tiles are not consecutive,
     * (2) if there's not enough space in the bookshelf, (3) otherwise
     */
    // probabilmente come picked è meglio passargli quelle ordinate
    @Override
    public int verifyTurn(int[] picked, int column, ServerManager manager){

        // check pickables
        for(int i=0;i<picked.length; i=i+2){
            if(!model.getBoard().getBoard()[picked[i]][picked[i+1]].getPickable()){
                return 0;
            }
        }

        // 2 pick
        if(picked.length == 4){
            if(!((picked[0] == picked[2] && Math.abs(picked[1] - picked[3]) == 1) || (picked[1] == picked[3] && Math.abs(picked[0]-picked[2]) == 1))){
                return 1;
            }
        }

        // 3 pick
        if(picked.length == 6){
            if(!
                    ((picked[0] == picked[2] && picked[0] == picked[4] && Math.abs(getMax(picked[1], picked[3], picked[5]) - getMin(picked[1], picked[3], picked[5])) == 2) ||
                            (picked[1] == picked[3] && picked[1] == picked[5] && Math.abs(getMax(picked[0], picked[2], picked[4]) - getMin(picked[0], picked[2], picked[4])) == 2))){

                return 1;
            }
        }

        // check bookshelf room
        for(PlayerController p: this.players){
            if(p.getModel().getManager() == manager){
                if(!p.getModel().getBookshelf().canInsert(picked.length/2, column)){
                    return 2;
                }
            }
        }

        return 3;
    }

    public int getMin(int a, int b, int c){
        int min = a;
        if(min > b){ min = b; }
        if(min > c){ min = c; }

        return min;
    }

    public int getMax(int a, int b, int c){
        int max = a;
        if(max < b){ max = b;}
        if(max < c){ max = c; }

        return max;
    }


}
