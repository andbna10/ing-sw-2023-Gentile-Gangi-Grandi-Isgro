package ServerSide.Model.CommonPattern;

import ServerSide.Model.Bookshelf;
import ServerSide.Model.CommonGoalCard;

public class CommonPattern8 extends CommonGoalCard {

    public CommonPattern8() {
        super();
    }

    /**
     * Overview: patternNumber getter
     */
    @Override
    public int getPatternNumber() {return 8;}

    /**
     * Overview: controls whether the combo has been achieved on player's bookshelf, returns 1
     */
    @Override
    public boolean validated(Bookshelf bookshelf) {

        if(bookshelf.getTile(0,0) != null && bookshelf.getTile(0,4) != null &&
                bookshelf.getTile(5,0) != null && bookshelf.getTile(5,4) != null){
            return bookshelf.getTile(0, 0).getType() == bookshelf.getTile(0, 4).getType() &&
                    bookshelf.getTile(0, 4).getType() == bookshelf.getTile(5, 0).getType() &&
                    bookshelf.getTile(5, 0).getType() == bookshelf.getTile(5, 4).getType();
        }
        return false;
    }
}
