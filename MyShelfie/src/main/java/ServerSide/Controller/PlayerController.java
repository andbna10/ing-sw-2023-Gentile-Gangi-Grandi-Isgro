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
     * @author Andrea Isgrò
     * @param column column index
     * @param tiles arraylist containing the tiles to be inserted
     */
    public void feedColumn(int column, ArrayList<ItemTile> tiles){
        if(model.getBookshelf().canInsert(tiles.size(), column)){
            model.getBookshelf().setTiles(column, tiles);
        }
    }

    /**
     * Overview: method aimed to check the accomplishment of a common goal
     * @author Simone Grandi
     * @author Andrea Isgrò
     * @param bookshelf bookshelf to be checked
     * @param goal common goal to be accomplished
     * @param index index of the common goal to be checked (there are 2 in every match)
     * @return earned points
     */
    public int checkCommonGoal(Bookshelf bookshelf, CommonGoalCard goal, int index){
        boolean ok = goal.validated(bookshelf);
        int points=0;
        if(ok){
            if((index==1 && !bookshelf.getCommonOne()) || (index==2 && !bookshelf.getCommonTwo())){
                points=goal.getStack().get(goal.getStack().size()-1).getPoints();
                goal.getStack().remove(goal.getStack().size()-1);
            }
            return points;
        } else {
            return 0;
        }
    }

    /**
     * Overview: method aimed to check the accomplishment level of a personal goal
     * @author Andrea Isgrò
     * @param bookshelf bookshelf to be checked
     * @param goal personal goal to be accomplished
     * @return earned points
     */
    public int checkPersonalGoal(Bookshelf bookshelf, PersonalGoalCard goal){
        return goal.validated(bookshelf);
    }

    /**
     * Overview: method aimed to check the adjacent tiles, returns the points for adjacent tiles
     * @author Simone Grandi
     * @param bookshelf bookshelf to be checked
     * @return earned points
     */
    public int checkAdjacentTiles(Bookshelf bookshelf){
        int points = 0, count;
        //ho aggiunto una matrice visited in bookshelf
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 5; j++) {
                if(bookshelf.getTile(i,j) != null){
                    count = floodFill(i,j,bookshelf,0,bookshelf.getTile(i,j).getType());
                    if(count==3) points+=2;
                    if(count==4) points+=3;
                    if(count==5) points+=5;
                    if(count>=6) points+=8;
                }
            }
        }
        return points;
    }

    /**
     * Overview: recursive method based on the floodFill algorithm called to check adjacent tiles in the bookshelf
     * @author Simone Grandi
     * @param i row index
     * @param j column index
     * @param bookshelf bookshelf to be checked
     * @param count number of adjacent tiles of the specified itemType
     * @param itemType item tile's type
     * @return updated "count" parameter
     */
    int floodFill(int i, int j, Bookshelf bookshelf, int count, ItemType itemType){
        if(i>=6 || j>=5)
            return 0;

        if(i<0 || j<0)
            return 0;

        if(bookshelf.getTile(i,j) == null)
            return 0;

        if(bookshelf.getTile(i,j) != null){
            if(bookshelf.getVisited(i,j) || !bookshelf.getTile(i,j).getType().equals(itemType))
                return 0;
        }

        bookshelf.setVisited(i,j);

        count++;

        count += floodFill(i-1, j, bookshelf, 0, itemType);

        count += floodFill(i, j-1, bookshelf, 0, itemType);

        count += floodFill(i, j+1, bookshelf, 0, itemType);

        count += floodFill(i+1, j, bookshelf, 0, itemType);

        return count;

    }

    /**
     * Overview: method aimed to check if there is enough space to insert 3 or fewer tiles in the bookshelf
     * @author Simone Grandi
     * @deprecated
     */
    @Deprecated
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
        return Math.min(maxNullCount, 3);
    }

    /**
     * Overview: method aimed to place in a specified order the tiles picked from the board in the bookshelf
     * @author Simone Grandi
     * @author Andrea Isgrò
     * @param toTake tiles to be picked
     * @param order chosen insertion order
     * @param column column index
     */
    @Override
    public void playTurn(int[] toTake, int[] order, int column){
        for(int i=0; i<toTake.length;i=i+2){
            pickTiles(game.getModel().getBoard(), toTake[i], toTake[i+1]);
        }
        game.getModel().notifyObserverEndTurn(model.getUsername());
        fixAndPlace(order,column);
    }

    /**
     * Overview: method aimed to check the goals and the fullness of the bookshelf
     * @author Andrea Isgrò
     * @param commongoals arraylist containing the common goals to be accomplished
     * @param id lobby id
     */
    @Override
    public void check(ArrayList<CommonGoalCard> commongoals, String id){
        int i = 1;
        // common
        for(CommonGoalCard common : commongoals){
            int newPoints = checkCommonGoal(model.getBookshelf(), common, i);
            if(newPoints > 0){
                model.addPoints(newPoints);
                if(i == 1){
                    model.getBookshelf().setCommonOne(true);
                } else {
                    model.getBookshelf().setCommonTwo(true);
                }
                game.getModel().notifyObserverCommon(i, newPoints, model.getUsername());
            }
            i++;
        }

        // fullness
        if(model.getBookshelf().bookshelfIsFull()){
            if(!game.getModel().getIsLastTurnStarted()){
                model.addPoints(1);
                game.getModel().notifyObserverLastTurn(model.getUsername());
                if (game.getModel().getCurrentTurnPlayer() == game.getModel().getOrder(game.getModel().getPlayers().size()-1)){
                    game.endGame(false, id);
                } else {
                    game.getModel().setIsLastTurnStarted(true);
                }
            }
        }
    }

    /**
     * Overview: tiles draft
     * @author Simone Grandi
     * @param boardGame board game instance
     * @param i row index
     * @param j column index
     */
    public void pickTiles (BoardGame boardGame, int i, int j) {
        //inserisco la tile nell'array che sarà poi inserito nella colonna scelta
        model.getPickedTiles().add(boardGame.getTile(i,j));
        boardGame.increment();
        //setto la posizione della tile tolta a null così che non sia più visibile
        boardGame.getBoard()[i][j].setTile(null);
        boardGame.getBoard()[i][j].setPickable(false);
    }

    /**
     * Overview: method aimed to rearrange the tiles using the order selected by the user
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

    /**
     * Overview: player getter
     */
    @Override
    public Player getModel() { return model;}
}
