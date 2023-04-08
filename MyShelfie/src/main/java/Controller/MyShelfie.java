package Controller;
import Model.*;
import Model.CommonPattern.*;
import Model.PersonalPattern.*;

import java.util.Random;
import java.util.ArrayList;

public class MyShelfie {
    private ArrayList<ItemTile> tiles;
    private ArrayList<ScoringToken> scoringTokens;

    /**
     * Overview: MyShelfie constructor
     */
    public MyShelfie(){
        this.tiles = new ArrayList<>();
        this.scoringTokens = new ArrayList<>();

        // initialize all the Item Tiles of MyShelfie
        for(ItemType type: ItemType.values()){
            for(int i=0; i<22; i++){
                tiles.add(new ItemTile(type));
            }
        }

        // initialize the scoring tokens
        for(int i=2; i<=8; i+=2){
            scoringTokens.add(new ScoringToken(Roman.I, i));

            scoringTokens.add(new ScoringToken(Roman.II, i));
        }
    }

    /**
     * Overview: select personal goal cards
     */
    public ArrayList<PersonalGoalCard> selectPersonalGoals(int nPlayers){
        ArrayList<PersonalGoalCard> output = new ArrayList<>();
        Random r = new Random();

        int x = 0;
        ArrayList<Integer> found = new ArrayList<Integer>();
        for(int i=0; i<nPlayers;i++){
            do{
                x = r.nextInt(12)+1;
            } while (found.contains(x));
            found.add(x);
            switch(x){
                case 1:
                    output.add(new PersonalPattern1());
                    break;
                case 2:
                    output.add(new PersonalPattern2());
                    break;
                case 3:
                    output.add(new PersonalPattern3());
                    break;
                case 4:
                    output.add(new PersonalPattern4());
                    break;
                case 5:
                    output.add(new PersonalPattern5());
                    break;
                case 6:
                    output.add(new PersonalPattern6());
                    break;
                case 7:
                    output.add(new PersonalPattern7());
                    break;
                case 8:
                    output.add(new PersonalPattern8());
                    break;
                case 9:
                    output.add(new PersonalPattern9());
                    break;
                case 10:
                    output.add(new PersonalPattern10());
                    break;
                case 11:
                    output.add(new PersonalPattern11());
                    break;
                case 12:
                    output.add(new PersonalPattern12());
                    break;
            }
        }
        return output;
    }

    /**
     * Overview: select 2 common goal cards
     */
    public ArrayList<CommonGoalCard> selectCommonGoals(){
        Random r = new Random();
        boolean ok = false;
        int i = 0;
        int j = 0;
        while (!ok){
            i = r.nextInt(12)+1;
            j = r.nextInt(12)+1;
            if(i != j){ ok = true; }
        }
        ArrayList<CommonGoalCard> output = new ArrayList<>();
        // first common goal card
        switch(i){
            case 1:
                output.add(new CommonPattern1());
                break;
            case 2:
                output.add(new CommonPattern2());
                break;
            case 3:
                output.add(new CommonPattern3());
                break;
            case 4:
                output.add(new CommonPattern4());
                break;
            case 5:
                output.add(new CommonPattern5());
                break;
            case 6:
                output.add(new CommonPattern6());
                break;
            case 7:
                output.add(new CommonPattern7());
                break;
            case 8:
                output.add(new CommonPattern8());
                break;
            case 9:
                output.add(new CommonPattern9());
                break;
            case 10:
                output.add(new CommonPattern10());
                break;
            case 11:
                output.add(new CommonPattern11());
                break;
            case 12:
                output.add(new CommonPattern12());
                break;
        }
        // second common goal card
        switch(j){
            case 1:
                output.add(new CommonPattern1());
                break;
            case 2:
                output.add(new CommonPattern2());
                break;
            case 3:
                output.add(new CommonPattern3());
                break;
            case 4:
                output.add(new CommonPattern4());
                break;
            case 5:
                output.add(new CommonPattern5());
                break;
            case 6:
                output.add(new CommonPattern6());
                break;
            case 7:
                output.add(new CommonPattern7());
                break;
            case 8:
                output.add(new CommonPattern8());
                break;
            case 9:
                output.add(new CommonPattern9());
                break;
            case 10:
                output.add(new CommonPattern10());
                break;
            case 11:
                output.add(new CommonPattern11());
                break;
            case 12:
                output.add(new CommonPattern12());
                break;
        }
        return output;
    }

    /**
     * Overview: select scoring tokens depending on the number of players
     */
    public ArrayList<ScoringToken> selectScoringToken(int nPlayers){
        ArrayList<ScoringToken> output = new ArrayList<>();
        if(nPlayers == 2){
            for(int i=0; i<scoringTokens.size(); i++){
                if(scoringTokens.get(i).getPoints()==4 || scoringTokens.get(i).getPoints()== 8){
                    output.add(scoringTokens.get(i));
                }
            }
        } else if (nPlayers == 3){
            for(int i=0; i<scoringTokens.size(); i++){
                if(scoringTokens.get(i).getPoints()==4 || scoringTokens.get(i).getPoints()==8 || scoringTokens.get(i).getPoints()==6){
                    output.add(scoringTokens.get(i));
                }
            }
        } else {
            for(int i=0; i<scoringTokens.size(); i++){
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
        for(int i=0; i<nTilesNeeded; i++){
            int j = r.nextInt(this.tiles.size());
            tiles.get(j).setInGame(true);
            output.add(tiles.remove(j));
        }
        return output;
    }


}
