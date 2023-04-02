package Model.CommonPattern;

import Model.Bookshelf;
import Model.CommonGoalCard;

public class CommonPattern12 extends CommonGoalCard {

    public CommonPattern12(String explanation) {
        super(explanation);
    }

    /**
     * Overview: controls whether the combo has been achieved on player's bookshelf, returns 1
     */

    public int validated(Bookshelf bookshelf) {
        int ok = 1;

        for(int i = 0; i < 5; i++)
            if(bookshelf.getTile(i + 1, i) == null)
                ok = 0;

        return ok;
    }
}
