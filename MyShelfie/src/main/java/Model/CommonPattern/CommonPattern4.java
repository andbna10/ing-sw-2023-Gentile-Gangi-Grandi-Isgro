package Model.CommonPattern;

import Model.Bookshelf;
import Model.CommonGoalCard;

public class CommonPattern4 extends CommonGoalCard {

    public CommonPattern4(String explanation) {
        super(explanation);
    }

    /**
     * Overview: controls whether the combo has been achieved on player's bookshelf, returns 1
     */

    public int validated(Bookshelf bookshelf) {
        int ok = 0;
        int count = 0;

        for (int j = 0; j < 5; j++)
            for (int i = 0; i < 6 - 1; i++)
                if (bookshelf.getTile(i, j).getType() == bookshelf.getTile(i + 1, j).getType() &&
                        (bookshelf.getTile(i, j).getType() != bookshelf.getTile(i + 2, j).getType() || i == 4))
                    count++;

        if (count > 5) ok = 1;
        return ok;
    }
}
