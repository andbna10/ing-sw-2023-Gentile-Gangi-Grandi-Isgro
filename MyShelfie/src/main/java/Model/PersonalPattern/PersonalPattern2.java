package Model.PersonalPattern;

import Model.Bookshelf;
import Model.ItemType;
import Model.PersonalGoalCard;

public class PersonalPattern2 extends PersonalGoalCard {

    public PersonalPattern2(String explanation, int[] points, ItemType[][] position) {
        super(explanation, points, position);
    }

    /**
     * Overview: controls player's bookshelf whether the combo as been achieved  and returns 1
     */

    public int validated(Bookshelf bookshelf) {
        int ok = 1;

        if(bookshelf.getTile(1, 1).getType() != ItemType.PLANTS ||
                bookshelf.getTile(2, 0).getType() != ItemType.CATS ||
                bookshelf.getTile(2, 2).getType() != ItemType.GAMES ||
                bookshelf.getTile(3, 4).getType() != ItemType.BOOKS ||
                bookshelf.getTile(4, 3).getType() != ItemType.TROPHIES ||
                bookshelf.getTile(5, 4).getType() != ItemType.FRAMES)
            ok = 0;

        return ok;
    }
}
