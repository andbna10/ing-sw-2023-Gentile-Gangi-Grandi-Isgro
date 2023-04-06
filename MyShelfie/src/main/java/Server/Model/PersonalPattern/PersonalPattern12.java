package Server.Model.PersonalPattern;

import Server.Model.Bookshelf;
import Server.Model.ItemType;
import Server.Model.PersonalGoalCard;

public class PersonalPattern12 extends PersonalGoalCard {

    public PersonalPattern12() {
        super();
    }

    @Override
    /**
     * Overview: controls whether the combo has been achieved on player's bookshelf, returns points
     */
    public int validated(Bookshelf bookshelf) {
        int count = 0;

        if(bookshelf.getTile(0, 2).getType() != ItemType.BOOKS) count++;
        if(bookshelf.getTile(1, 1).getType() != ItemType.PLANTS) count++;
        if(bookshelf.getTile(2, 2).getType() != ItemType.FRAMES) count++;
        if(bookshelf.getTile(3, 3).getType() != ItemType.TROPHIES) count++;
        if(bookshelf.getTile(4, 4).getType() != ItemType.GAMES) count++;
        if(bookshelf.getTile(5, 0).getType() != ItemType.CATS) count++;

        return pointsConverter(count);
    }
}
