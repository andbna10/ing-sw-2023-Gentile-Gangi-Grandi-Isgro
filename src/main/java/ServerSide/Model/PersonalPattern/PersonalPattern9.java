package ServerSide.Model.PersonalPattern;

import ServerSide.Model.Bookshelf;
import ServerSide.Model.ItemType;
import ServerSide.Model.PersonalGoalCard;

public class PersonalPattern9 extends PersonalGoalCard {
    private Bookshelf personalGoal = new Bookshelf();
    public PersonalPattern9() {
        super();
        personalGoal.setTile(0,2,ItemType.GAMES);
        personalGoal.setTile(2,2,ItemType.CATS);
        personalGoal.setTile(3,4,ItemType.BOOKS);
        personalGoal.setTile(4,1,ItemType.TROPHIES);
        personalGoal.setTile(4,4,ItemType.PLANTS);
        personalGoal.setTile(5,0,ItemType.FRAMES);
    }

    /**
     * Overview: patternNumber getter
     */
    @Override
    public int getPatternNumber() {return 9;}

    /**
     * Overview: controls whether the combo has been achieved on player's bookshelf
     * @author Mirko Gentile
     * @author Simone Grandi
     * @author Andrea Isgrò
     * @param bookshelf bookshelf to be checked
     * @return points earned
     */
    @Override
    public int validated(Bookshelf bookshelf) {
        int count = 0;

        if(bookshelf.getTile(0,2) != null){
            if(bookshelf.getTile(0, 2).getType() == ItemType.GAMES) count++;
        }
        if(bookshelf.getTile(2,2) != null){
            if(bookshelf.getTile(2, 2).getType() == ItemType.CATS) count++;
        }
        if(bookshelf.getTile(3,4) != null){
            if(bookshelf.getTile(3, 4).getType() == ItemType.BOOKS) count++;
        }
        if(bookshelf.getTile(4,1) != null){
            if(bookshelf.getTile(4, 1).getType() == ItemType.TROPHIES) count++;
        }
        if(bookshelf.getTile(4,4) != null){
            if(bookshelf.getTile(4, 4).getType() == ItemType.PLANTS) count++;
        }
        if(bookshelf.getTile(5,0) != null){
            if(bookshelf.getTile(5, 0).getType() == ItemType.FRAMES) count++;
        }

        return pointsConverter(count);
    }

    @Override
    public Bookshelf getPersonalGoal() {
        return personalGoal;
    }
}
