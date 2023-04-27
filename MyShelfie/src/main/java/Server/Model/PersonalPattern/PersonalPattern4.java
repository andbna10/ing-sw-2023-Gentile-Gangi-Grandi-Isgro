package Server.Model.PersonalPattern;

import Server.Model.Bookshelf;
import Server.Model.ItemType;
import Server.Model.PersonalGoalCard;

public class PersonalPattern4 extends PersonalGoalCard {

    public PersonalPattern4() {
        super();
    }

    @Override
    /**
     * Overview: controls whether the combo has been achieved on player's bookshelf, returns points
     */
    public int validated(Bookshelf bookshelf) {
        int count = 0;

        if(bookshelf.getTile(0, 5).getType() != ItemType.GAMES) count++;
        if(bookshelf.getTile(2, 0).getType() != ItemType.TROPHIES) count++;
        if(bookshelf.getTile(2, 2).getType() != ItemType.FRAMES) count++;
        if(bookshelf.getTile(3, 3).getType() != ItemType.PLANTS) count++;
        if(bookshelf.getTile(4, 1).getType() != ItemType.BOOKS) count++;
        if(bookshelf.getTile(4, 2).getType() != ItemType.CATS) count++;

        return pointsConverter(count);
    }
}
