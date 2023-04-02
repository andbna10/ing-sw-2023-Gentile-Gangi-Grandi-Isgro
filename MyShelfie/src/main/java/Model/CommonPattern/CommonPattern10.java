package Model.CommonPattern;

import Model.Bookshelf;
import Model.CommonGoalCard;

public class CommonPattern10 extends CommonGoalCard {

    public CommonPattern10(String explanation) {
        super(explanation);
    }

    /**
     * Overview: controls whether the combo has been achieved on player's bookshelf, returns 1
     */

    public int validated(Bookshelf bookshelf) {
        int ok = 0;
        int count = 0;

        for (int i = 1; i < 6 - 1; i++)
            for(int j = 1; j < 5 - 1; j++)
                if(bookshelf.getTile(i, j).getType() == bookshelf.getTile(i - 1, j - 1).getType() &&
                        bookshelf.getTile(i, j).getType() == bookshelf.getTile(i - 1, j + 1).getType() &&
                        bookshelf.getTile(i, j).getType() == bookshelf.getTile(i + 1, j - 1).getType() &&
                        bookshelf.getTile(i, j).getType() == bookshelf.getTile(i + 1, j + 1).getType())
                    ok = 1;

        return ok;
    }
}
