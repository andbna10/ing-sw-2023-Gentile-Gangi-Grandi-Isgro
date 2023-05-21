package ServerSide.Model.CommonPattern;

import ServerSide.Model.Bookshelf;
import ServerSide.Model.CommonGoalCard;

public class CommonPattern12 extends CommonGoalCard {

    public CommonPattern12() {
        super();
    }

    @Override
    public int getPatternNumber() {return 12;}
    @Override
    /**
     * Overview: controls whether the combo has been achieved on player's bookshelf, returns 1
     */
    public boolean validated(Bookshelf bookshelf) {
        boolean ok1 = true, ok2 = true;

        for(int i = 0; i < 5; i++)
            if(bookshelf.getTile(i + 1, i) == null || bookshelf.getTile(i,i)!=null)
                ok1 = false;

        for(int i = 0, j = 4; i < 5; i++, j--)
            if(bookshelf.getTile(i + 1, j) == null || bookshelf.getTile(i,j)!=null)
                ok2 = false;

        return ok1 || ok2;
    }
}
