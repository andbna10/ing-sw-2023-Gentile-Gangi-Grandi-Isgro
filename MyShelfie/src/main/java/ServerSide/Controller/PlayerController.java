package ServerSide.Controller;
import ServerSide.Model.*;
import ServerSide.VirtualView.PlayerVViewObserver;
import ServerSide.VirtualView.VirtualPlayerView;

import java.util.ArrayList;

public class PlayerController implements PlayerVViewObserver {
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
     * Overview: method aimed to check the accomplishment of a common goal, it returns the points if the goal has been reached
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
     * Overview: method aimed to check the adjacent tiles, returns the points for adjacent tiles
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

    /**
     * Overview: recursive method based on the floodfill algorithm called to check adjacent tiles in the bookshelf
     */
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
    /*public void play(int columnIndex, ArrayList<ItemTile> tiles) {



        togliere le tiles dal board game (gestire anche l'ingame delle tiles) ---> (nel momento in cui il giocatore
        clicca su una tile dal boardgame per prenderla dev'essere lanciato un mess verso il server per lanciare
        il metodo "pickable" dal boardgame. se è true si cambia l'inGame della tile da false a true

        dopo questo, ho una lista di tiles e le devo mettere nella bookshelf (feedcolumn())
        controllo dei goals
        controllo sulla bookshlef
        aggiornamento punti e stato in generale delle classi modello (tiles in gioco, obiettivi raggiunti)

        cambiare il current turn player alla fine del turno



    }*/

    /**
     * this method check if in the bookshelf there is space left to inset 3 or less tiles
     */
    public int spaceLeft(){
        int maxNullCount=0;
        //itero per ogni colonna
        for(int j=0;j< 5;j++){
            int nullCount=0;
            //conto le celle nulle nella singola colonna
            for(int i=0; i<6;i++){
                if(model.getBookshelf().getTile(i,j)==null)
                    nullCount++;
            }
            //tengo memoria dello spazio maggiore trovato in una colonna
            if(nullCount>maxNullCount){
                maxNullCount=nullCount;
            }
        }
        if(maxNullCount>3)
            return 3;
        return maxNullCount;
    }

    @Override
    /**
     * Overview: method aimed place in order the tiles picked from the board in the bookshelf
     */
    public void playTurn(int[] toTake, int[] oreder, int column ){
        for(int i=0; i<toTake.length;i=i+2){
            pickTiles(game.getModel().getBoard(),i,i+1 );
        }
        game.getModel().notifyObserverEndTurn();
        fixAndPlace(oreder,column);
    }


    /*public void pickTakenTiles(int[] toTake){
        for(int i=0; i<toTake.length;i=i+2){
            pickTiles(game.getModel().getBoard(),i,i+1 );
        }
    }*/
    /**
     * Overview: tiles draft
     */
    public void pickTiles (BoardGame boardGame, int i, int j) {
        //inserisco la tile nell'array che sarà poi inserito nella colonna scelta
        model.getPickedTiles().add(boardGame.getTile(i,j));
        //setto la posizione della tile tolta a null così che non sia più visibile
        boardGame.getBoard()[i][j].setTile(null);
    }

    /**
     * Overview: the user give the order of position to rearrange the picked tiles
     * the method fix the order of the tiles and insert them in the given column
     */
    public void fixAndPlace(int[] order, int column){
        //riarrangio il vettore di tiles
        ArrayList<ItemTile> reordered = new ArrayList<ItemTile>();
        for(int i=0;i<model.getPickedTiles().size();i++)
            reordered.add(model.getPickedTiles().get(order[i]));
        //viene inserito nella colonna scelta
        feedColumn(column, reordered);
        //svuoto l'arraylist di pickedtiles
        model.getPickedTiles().clear();
    }

    @Override
    /**
     * player getter
     */
    public Player getModel() { return model;}
}
