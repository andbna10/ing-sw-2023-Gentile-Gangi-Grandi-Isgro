package Model.CommonPattern;

import Model.Bookshelf;
import Model.CommonGoalCard;

public class CommonPattern1 extends CommonGoalCard {

    public CommonPattern1(String explanation) {
        super(explanation);
    }

    /**
     * Overview: controls whether the combo has been achieved on player's bookshelf, returns 1
     */

    public int validated(Bookshelf bookshelf) {
        int ok = 0;
        int count = 0;

        for (int i = 0; i < 6 - 1; i++)
            for(int j = 0; j < 5 - 1; j++)
                if(bookshelf.getTile(i, j).getType() == bookshelf.getTile(i + 1, j).getType() &&
                        bookshelf.getTile(i, j).getType() == bookshelf.getTile(i, j + 1).getType() &&
                        bookshelf.getTile(i, j).getType() == bookshelf.getTile(i + 1, j + 1).getType() )
                    count++;

        if(count > 1) ok = 1;

        return ok;
    }
}
