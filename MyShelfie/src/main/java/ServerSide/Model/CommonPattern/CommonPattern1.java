package ServerSide.Model.CommonPattern;

import ServerSide.Model.Bookshelf;
import ServerSide.Model.CommonGoalCard;

public class CommonPattern1 extends CommonGoalCard {
    public CommonPattern1() {super();}

    @Override
    public int getPatternNumber() {return 1;}
    @Override
    /**
     * Overview: controls whether the combo has been achieved on player's bookshelf, returns 1
     */
    public boolean validated(Bookshelf bookshelf) {
        boolean ok = false;
        int count = 0;

        for (int i = 0; i < 6 - 1; i++)
            for(int j = 0; j < 5 - 1; j++)
                if(bookshelf.getTile(i,j) != null && bookshelf.getTile(i+1,j) != null &&
                        bookshelf.getTile(i,j+1) != null && bookshelf.getTile(i+1,j+1) != null &&
                        bookshelf.getTile(i, j).getType() == bookshelf.getTile(i + 1, j).getType() &&
                        bookshelf.getTile(i, j).getType() == bookshelf.getTile(i, j + 1).getType() &&
                        bookshelf.getTile(i, j).getType() == bookshelf.getTile(i + 1, j + 1).getType() )
                    count++;

        if(count > 1) ok = true;

        return ok;
    }
}
