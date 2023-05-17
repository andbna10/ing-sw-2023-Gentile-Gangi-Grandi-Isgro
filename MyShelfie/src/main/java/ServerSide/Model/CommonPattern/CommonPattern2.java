package ServerSide.Model.CommonPattern;

import ServerSide.Model.Bookshelf;
import ServerSide.Model.CommonGoalCard;

public class CommonPattern2 extends CommonGoalCard {

    public CommonPattern2() {
        super();
    }

    @Override
    public int getPatternNumber() {return 2;}
    @Override
    /**
     * Overview: controls whether the combo has been achieved on player's bookshelf, returns 1
     */
    public boolean validated(Bookshelf bookshelf) {
        boolean ok = false;
        int flag = 1;

        for (int j = 0; j < 5; j++, flag = 1) {
            for (int i = 1; i < 6 - 1; i++)
                for (int k = i + 1; k < 6; k++)
                    if (bookshelf.getTile(i,j)!=null && bookshelf.getTile(k,j) != null &&
                            bookshelf.getTile(i, j).getType() == bookshelf.getTile(k, j).getType())
                        flag = 0;
            if(flag != 0) {
                ok = true;
                break;
            }
        }

        return ok;
    }
}
