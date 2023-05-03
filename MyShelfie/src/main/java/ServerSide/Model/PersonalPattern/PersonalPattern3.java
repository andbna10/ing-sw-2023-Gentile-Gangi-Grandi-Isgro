package ServerSide.Model.PersonalPattern;

import ServerSide.Model.Bookshelf;
import ServerSide.Model.ItemType;
import ServerSide.Model.PersonalGoalCard;

public class PersonalPattern3 extends PersonalGoalCard {

    public PersonalPattern3() {
        super();
    }

    @Override
    /**
     * Overview: controls whether the combo has been achieved on player's bookshelf, returns points
     */
    public int validated(Bookshelf bookshelf) {
        int count = 0;

        if (bookshelf.getTile(1, 0).getType() != ItemType.FRAMES) count++;
        if (bookshelf.getTile(1, 3).getType() != ItemType.GAMES) count++;
        if (bookshelf.getTile(2, 2).getType() != ItemType.PLANTS) count++;
        if (bookshelf.getTile(3, 4).getType() != ItemType.TROPHIES) count++;
        if (bookshelf.getTile(5, 0).getType() != ItemType.BOOKS) count++;
        if (bookshelf.getTile(3, 1).getType() != ItemType.CATS) count++;


        return pointsConverter(count);
    }
}