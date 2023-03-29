package Controller;
import Model.*;
import java.util.Random;
import java.util.ArrayList;

public class MyShelfie {
    private ArrayList<ItemTile> tiles;
    private ArrayList<PersonalGoalCard> personalGoalCards;
    private ArrayList<CommonGoalCard> commonGoalCards;
    private ArrayList<ScoringToken> scoringTokens;

    /**
     * Overview: MyShelfie constructor
     */
    public MyShelfie(){

        // initialize all the Item Tiles of MyShelfie
        for(ItemType type: ItemType.values()){
            for(int i=0; i<22; i++){
                tiles.add(new ItemTile(type));
            }
        }

        // what about the goals cards?

        // initialize the scoring tokens
        for(int i=2; i<=8; i+=2){
            scoringTokens.add(new ScoringToken(Roman.I, i));

            scoringTokens.add(new ScoringToken(Roman.II, i));
        }
    }

    /**
     * Overview: select a personal goal card
     */
    public PersonalGoalCard selectPersonalGoal(){
        Random r = new Random();
        int index = r.nextInt(personalGoalCards.size());
        return personalGoalCards.get(index);
    }

    /**
     * Overview: select 2 common goal cards
     */
    public ArrayList<CommonGoalCard> selectCommonGoals(){
        Random r = new Random();
        Boolean ok = false;
        int i = 0;
        int j = 0;
        while (!ok){
            i = r.nextInt(commonGoalCards.size());
            j = r.nextInt(commonGoalCards.size());
            if(i != j){ ok = true; }
        }
        ArrayList<CommonGoalCard> output = new ArrayList<>();
        output.add(commonGoalCards.get(i));
        output.add(commonGoalCards.get(j));
        return output;
    }

    /**
     * Overview: select scoring tokens depending on the number of players
     */
    public ArrayList<ScoringToken> selectScoringToken(int nPlayers){
        ArrayList<ScoringToken> output = new ArrayList<>();
        if(nPlayers == 2){
            for(int i=0; i<scoringTokens.size();i++){
                if(scoringTokens.get(i).getPoints()==4 || scoringTokens.get(i).getPoints()== 8){
                    output.add(scoringTokens.get(i));
                }
            }
        } else if (nPlayers == 3){
            for(int i=0; i<scoringTokens.size();i++){
                if(scoringTokens.get(i).getPoints()==4 || scoringTokens.get(i).getPoints()==8 || scoringTokens.get(i).getPoints()==6){
                    output.add(scoringTokens.get(i));
                }
            }
        } else {
            for(int i=0; i<scoringTokens.size();i++){
                output.add(scoringTokens.get(i));
            }
        }
        return output;
    }

    /**
     * Overview: select the tiles to feed the board
     */
    public ArrayList<ItemTile> selectItemTiles(int nTilesNeeded){
        ArrayList<ItemTile> output = new ArrayList<>();
        Random r = new Random();
        for(int i=0; i<nTilesNeeded;i++){
            int j = r.nextInt(this.tiles.size());
            tiles.get(j).setInGame(true);
            output.add(tiles.remove(j));
        }
        return output;
    }



}
