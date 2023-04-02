package Model.PersonalPattern;

import Model.Bookshelf;
import Model.ItemType;
import Model.PersonalGoalCard;

public class PersonalPattern3 extends PersonalGoalCard {

    public PersonalPattern3(String explanation, int[] points, ItemType[][] position) {
        super(explanation, points, position);
    }

    /**
     * Overview: controls player's bookshelf whether the combo as been achieved  and returns 1
     */

    public int validated(Bookshelf bookshelf) {
        int ok = 1;

        if(bookshelf.getTile(1, 0).getType() != ItemType.FRAMES ||
                bookshelf.getTile(1, 3).getType() != ItemType.GAMES ||
                bookshelf.getTile(2, 2).getType() != ItemType.PLANTS ||
                bookshelf.getTile(3, 1).getType() != ItemType.CATS ||
                bookshelf.getTile(3, 4).getType() != ItemType.TROPHIES ||
                bookshelf.getTile(5, 0).getType() != ItemType.BOOKS)
            ok = 0;

        return ok;
    }
}
