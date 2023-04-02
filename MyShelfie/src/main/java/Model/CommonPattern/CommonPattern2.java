package Model.CommonPattern;

import Model.Bookshelf;
import Model.CommonGoalCard;

public class CommonPattern2 extends CommonGoalCard {

    public CommonPattern2(String explanation) {
        super(explanation);
    }

    /**
     * Overview: controls player's bookshelf whether the combo as been achieved  and returns 1
     */

    public int validated(Bookshelf bookshelf) {
        int ok = 0;
        int flag = 1;

        for (int j = 0; j < 5; j++, flag = 1) {
            for (int i = 1; i < 6 - 1; i++)
                for (int k = i + 1; k < 6; k++)
                    if (bookshelf.getTile(i, j).getType() == bookshelf.getTile(k, j).getType())
                        flag = 0;
            if(flag != 0) {
                ok = 1;
                break;
            }
        }

        return ok;
    }
}
