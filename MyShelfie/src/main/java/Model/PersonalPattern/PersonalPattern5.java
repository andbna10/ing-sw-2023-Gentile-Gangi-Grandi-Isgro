package Model.PersonalPattern;

import Model.Bookshelf;
import Model.ItemType;
import Model.PersonalGoalCard;

public class PersonalPattern5 extends PersonalGoalCard {

    public PersonalPattern5(String explanation, int[] points, ItemType[][] position) {
        super(explanation, points, position);
    }

    /**
     * Overview: controls player's bookshelf whether the combo as been achieved  and returns 1
     */

    public int validated(Bookshelf bookshelf) {
        int ok = 1;

        if(bookshelf.getTile(1, 1).getType() != ItemType.TROPHIES ||
                bookshelf.getTile(3, 1).getType() != ItemType.FRAMES ||
                bookshelf.getTile(3, 2).getType() != ItemType.BOOKS ||
                bookshelf.getTile(4, 4).getType() != ItemType.PLANTS ||
                bookshelf.getTile(5, 0).getType() != ItemType.GAMES ||
                bookshelf.getTile(5, 3).getType() != ItemType.CATS)
            ok = 0;

        return ok;
    }
}
