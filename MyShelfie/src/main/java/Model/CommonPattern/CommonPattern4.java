package Model.CommonPattern;

import Model.Bookshelf;
import Model.CommonGoalCard;

public class CommonPattern4 extends CommonGoalCard {

    public CommonPattern4(String explanation) {
        super(explanation);
    }

    /**
     * Overview: controls player's bookshelf whether the combo as been achieved  and returns 1
     */

    public int validated(Bookshelf bookshelf) {
        int ok = 0;
        int count = 0;
        int pairs = 0;

        for (int i = 0; i < 6; i++, count = 0) {
            for (int j = 0; j < 5 - 1; j++)
                if (bookshelf.getTile(i, j).getType() == bookshelf.getTile(i + 1, j).getType())
                    count++;
                else count = 0;
            if (count > 3) pairs++;
        }

        if (pairs > 3) ok = 1;
        return ok;
    }
}
