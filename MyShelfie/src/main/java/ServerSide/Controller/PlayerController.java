package ServerSide.Controller;
import ServerSide.Model.*;
import ServerSide.VirtualView.GameVViewObserver;
import ServerSide.VirtualView.PlayerVViewObserver;
import ServerSide.VirtualView.VirtualPlayerView;

import java.util.ArrayList;

public class PlayerController implements GameVViewObserver, PlayerVViewObserver {
    private Player model;
    private GameController game;

    /**
     * Overview: PlayerController constructor
     */
    public PlayerController(Player player, GameController game, VirtualPlayerView view){
        this.model = player;
        this.game = game;

        // vedere se tenere un riferimento tra gli attributi
        view.setPlayerViewObserver(this);

    }

    /**
     * Overview: set the goal card in the model for the corresponding player
     */
    public void setGoal(PersonalGoalCard goal){
        model.setGoal(goal);
    }

    /**
     * Overview: insert taken tiles in the bookshelf
     */
    public void feedColumn(int column, ArrayList<ItemTile> tiles){
        if(model.getBookshelf().canInsert(tiles.size(), column)){
            model.getBookshelf().setTiles(column, tiles);
        }
    }

    /**
     * Overview: method aimed to check the accomplishment of a common goal, it returns true if the goal has been reached
     */
    public int checkCommonGoal(Bookshelf bookshelf, CommonGoalCard goal, int index){
        boolean ok = goal.validated(bookshelf);
        int points=0;
        if(ok){
            if((index==1 && !bookshelf.getCommonOne()) || (index==2 && !bookshelf.getCommonTwo())){
                points=goal.getStack().get(goal.getStack().size()-1).getPoints();
                goal.getStack().remove(goal.getStack().size()-1);
            }
            /*controllo che non abbia già preso il token
            * se non lo ha preso lo aggiungo al suo punteggio e lo tolgo dall'array di scoringtokens*/

            // to do (queste cose forse potrebbero essere fatte durante il turno fuori da questo metodo ma in base al suo output)
            // viene rilasciato la prima tessera scoring token associata a quel common goal
            // aggiungere i rispettivi punti al giocatore
            return points;
        } else {
            return 0;
        }
    }

    /**
     * Overview: method aimed to check the accomplishment level of a personal goal, it returns the points achieved till now
     */
    public int checkPersonalGoal(Bookshelf bookshelf, PersonalGoalCard goal){
        return goal.validated(bookshelf);
    }

    /**
     * Overview: method aimed to check the adjacent tiles
     */
    public int checkAdjacentTiles(Bookshelf bookshelf){
        int points=0,count;
        //ho aggiunto una matrice visited in bookshelf
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 5; j++) {
                count=floodFill(i,j,bookshelf,0,bookshelf.getTile(i,j).getType());
                if(count==3) points+=2;
                if(count==4) points+=3;
                if(count==5) points+=5;
                if(count>=6) points+=8;
            }
        }
        return points;
    }
    int floodFill(int i, int j, Bookshelf bookshelf, int count, ItemType itemType ){
        if(i>=6 || j>=5)
            return 0;
        if(i<0 || j<0)
            return 0;
        if(bookshelf.getVisited(i,j) || !bookshelf.getTile(i,j).getType().equals(itemType))
            return 0;
        bookshelf.setVisited(i,j);
        count++;
        count+=floodFill(i-1,j-1,bookshelf,count,itemType);
        count+=floodFill(i-1,j,bookshelf,count, itemType);
        count+=floodFill(i-1,j+1,bookshelf,count, itemType);
        count+=floodFill(i,j-1,bookshelf,count, itemType);
        count+=floodFill(i,j+1,bookshelf,count, itemType);
        count+=floodFill(i+1,j-1,bookshelf,count, itemType);
        count+=floodFill(i+1,j,bookshelf,count, itemType);
        count+=floodFill(i+1,j+1,bookshelf,count,itemType);
        return count;

    }

    /**
     * Overview: method aimed to let the player play a turn
     */
    public void play(int columnIndex, ArrayList<ItemTile> tiles) {

        /*

        togliere le tiles dal board game (gestire anche l'ingame delle tiles) ---> (nel momento in cui il giocatore
        clicca su una tile dal boardgame per prenderla dev'essere lanciato un mess verso il server per lanciare
        il metodo "pickable" dal boardgame. se è true si cambia l'inGame della tile da false a true

        dopo questo, ho una lista di tiles e le devo mettere nella bookshelf (feedcolumn())
        controllo dei goals
        controllo sulla bookshlef
        aggiornamento punti e stato in generale delle classi modello (tiles in gioco, obiettivi raggiunti)

        cambiare il current turn player alla fine del turno


         */
    }

    /**
     * Overview: tiles draft
     */
    public void pickTiles (BoardGame boardGame, int i, int j, int column) {

        //VARIABILI i, j, column IN INGRESSO DA IMPLEMENTARE CORRETTAMENTE

        //condizione per uscire anticipatamente dal pescaggio
        boolean flag = false;
        //coordinate, vanno prese in input (nella view (?) )

        ArrayList<ItemTile> picked = new ArrayList<ItemTile>();

        while(picked.size() < 3 && !flag) {

            //i, j, flag input

            picked.add(game.getModel().getBoard().getTile(i,j));
            boardGame.getBoard()[i][j].setTile(null);
        }

        //column to feed, !!manca controllo preventivo sul numero tessere prese
        // (se ne pesco 3 ma ho max 2 slot liberi su ogni colonna)

        feedColumn(column, picked);

    }


}
