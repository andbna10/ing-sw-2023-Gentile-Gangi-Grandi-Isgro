package Server.Model.PersonalPattern;

import Server.Model.Bookshelf;
import Server.Model.ItemType;
import Server.Model.PersonalGoalCard;

public class PersonalPattern6 extends PersonalGoalCard {

    public PersonalPattern6() {
        super();
    }

    @Override
    /**
     * Overview: controls whether the combo has been achieved on player's bookshelf, returns points
     */
    public int validated(Bookshelf bookshelf) {
        int count = 0;

        if(bookshelf.getTile(0, 2).getType() != ItemType.TROPHIES) count++;
        if(bookshelf.getTile(0, 4).getType() != ItemType.CATS) count++;
        if(bookshelf.getTile(2, 3).getType() != ItemType.BOOKS) count++;
        if(bookshelf.getTile(4, 1).getType() != ItemType.GAMES) count++;
        if(bookshelf.getTile(4, 3).getType() != ItemType.FRAMES) count++;
        if(bookshelf.getTile(5, 0).getType() != ItemType.PLANTS) count++;

        return pointsConverter(count);
    }
}
