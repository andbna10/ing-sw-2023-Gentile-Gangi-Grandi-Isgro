package ServerSide.Model.PersonalPattern;

import ServerSide.Model.Bookshelf;
import ServerSide.Model.ItemType;
import ServerSide.Model.PersonalGoalCard;

public class PersonalPattern9 extends PersonalGoalCard {

    public PersonalPattern9() {
        super();
    }

    @Override
    /**
     * Overview: controls whether the combo has been achieved on player's bookshelf, returns points
     */
    public int validated(Bookshelf bookshelf) {
        int count = 0;

        if(bookshelf.getTile(0, 2).getType() != ItemType.GAMES) count++;
        if(bookshelf.getTile(2, 2).getType() != ItemType.CATS) count++;
        if(bookshelf.getTile(3, 4).getType() != ItemType.BOOKS) count++;
        if(bookshelf.getTile(4, 1).getType() != ItemType.TROPHIES) count++;
        if(bookshelf.getTile(4, 4).getType() != ItemType.PLANTS) count++;
        if(bookshelf.getTile(5, 0).getType() != ItemType.FRAMES) count++;

        return pointsConverter(count);
    }
}
