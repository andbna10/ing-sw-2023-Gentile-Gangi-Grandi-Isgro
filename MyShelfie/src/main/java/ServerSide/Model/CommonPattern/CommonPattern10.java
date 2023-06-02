package ServerSide.Model.CommonPattern;

import ServerSide.Model.Bookshelf;
import ServerSide.Model.CommonGoalCard;

public class CommonPattern10 extends CommonGoalCard {

    public CommonPattern10() {
        super();
    }

    /**
     * Overview: patternNumber getter
     */
    @Override
    public int getPatternNumber() {return 10;}

    /**
     * Overview: controls whether the combo has been achieved on player's bookshelf, returns 1
     */
    @Override
    public boolean validated(Bookshelf bookshelf) {
        boolean ok = false;

        for (int i = 1; i < 6 - 1; i++)
            for(int j = 1; j < 5 - 1; j++)
                if(bookshelf.getTile(i, j) != null &&
                        bookshelf.getTile(i - 1,j - 1) != null &&
                        bookshelf.getTile(i - 1,j + 1) != null &&
                        bookshelf.getTile(i + 1,j - 1) != null &&
                        bookshelf.getTile(i + 1,j + 1) != null &&
                        bookshelf.getTile(i, j).getType() == bookshelf.getTile(i - 1, j - 1).getType() &&
                        bookshelf.getTile(i, j).getType() == bookshelf.getTile(i - 1, j + 1).getType() &&
                        bookshelf.getTile(i, j).getType() == bookshelf.getTile(i + 1, j - 1).getType() &&
                        bookshelf.getTile(i, j).getType() == bookshelf.getTile(i + 1, j + 1).getType())
                    ok = true;

        return ok;
    }
}
