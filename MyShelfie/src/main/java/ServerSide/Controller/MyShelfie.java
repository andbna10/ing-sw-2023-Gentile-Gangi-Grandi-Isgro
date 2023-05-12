package ServerSide.Controller;
import ServerSide.Model.*;
import ServerSide.Model.CommonPattern.*;
import ServerSide.Model.PersonalPattern.*;

import java.util.Random;
import java.util.ArrayList;

public class MyShelfie {
    private ArrayList<ItemTile> tiles;
    private ArrayList<ScoringToken> scoringTokens;

    /**
     * Overview: MyShelfie constructor
     */
    public MyShelfie(){
        System.out.println("entrato in myshelfie");
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
            switch (x) {
                case 1 -> output.add(new PersonalPattern1());
                case 2 -> output.add(new PersonalPattern2());
                case 3 -> output.add(new PersonalPattern3());
                case 4 -> output.add(new PersonalPattern4());
                case 5 -> output.add(new PersonalPattern5());
                case 6 -> output.add(new PersonalPattern6());
                case 7 -> output.add(new PersonalPattern7());
                case 8 -> output.add(new PersonalPattern8());
                case 9 -> output.add(new PersonalPattern9());
                case 10 -> output.add(new PersonalPattern10());
                case 11 -> output.add(new PersonalPattern11());
                case 12 -> output.add(new PersonalPattern12());
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
        switch (i) {
            case 1 -> output.add(new CommonPattern1());
            case 2 -> output.add(new CommonPattern2());
            case 3 -> output.add(new CommonPattern3());
            case 4 -> output.add(new CommonPattern4());
            case 5 -> output.add(new CommonPattern5());
            case 6 -> output.add(new CommonPattern6());
            case 7 -> output.add(new CommonPattern7());
            case 8 -> output.add(new CommonPattern8());
            case 9 -> output.add(new CommonPattern9());
            case 10 -> output.add(new CommonPattern10());
            case 11 -> output.add(new CommonPattern11());
            case 12 -> output.add(new CommonPattern12());
        }
        // second common goal card
        switch (j) {
            case 1 -> output.add(new CommonPattern1());
            case 2 -> output.add(new CommonPattern2());
            case 3 -> output.add(new CommonPattern3());
            case 4 -> output.add(new CommonPattern4());
            case 5 -> output.add(new CommonPattern5());
            case 6 -> output.add(new CommonPattern6());
            case 7 -> output.add(new CommonPattern7());
            case 8 -> output.add(new CommonPattern8());
            case 9 -> output.add(new CommonPattern9());
            case 10 -> output.add(new CommonPattern10());
            case 11 -> output.add(new CommonPattern11());
            case 12 -> output.add(new CommonPattern12());
        }
        return output;
    }

    /**
     * Overview: select scoring tokens depending on the number of players
     */
    public ArrayList<ScoringToken> selectScoringToken(int nPlayers){
        ArrayList<ScoringToken> output = new ArrayList<>();
        if(nPlayers == 2){
            for (ScoringToken scoringToken : scoringTokens) {
                if (scoringToken.getPoints() == 4 || scoringToken.getPoints() == 8) {
                    output.add(scoringToken);
                }
            }
        } else if (nPlayers == 3){
            for (ScoringToken scoringToken : scoringTokens) {
                if (scoringToken.getPoints() == 4 || scoringToken.getPoints() == 8 || scoringToken.getPoints() == 6) {
                    output.add(scoringToken);
                }
            }
        } else {
            output.addAll(scoringTokens);
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
