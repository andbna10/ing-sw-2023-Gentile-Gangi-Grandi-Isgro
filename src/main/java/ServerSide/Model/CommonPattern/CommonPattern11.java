package ServerSide.Model.CommonPattern;

import ServerSide.Model.Bookshelf;
import ServerSide.Model.CommonGoalCard;

public class CommonPattern11 extends CommonGoalCard {

    public CommonPattern11() {
        super();
    }

    /**
     * Overview: patternNumber getter
     */
    @Override
    public int getPatternNumber() {return 11;}

    /**
     * Overview: controls whether the combo has been achieved on player's bookshelf
     * @author Mirko Gentile
     * @author Simone Grandi
     * @author Francesco Gangi
     * @param bookshelf bookshelf to be checked
     * @return (true) if accomplished, (false) if not
     */
    @Override
    public boolean validated(Bookshelf bookshelf) {
        int ok = 2;

        for (int i = 0; i < 4; i++) {
            if(bookshelf.getTile(i,i) == null || bookshelf.getTile(i+1,i+1) == null) {
                ok--;
                break;
            }
            if (bookshelf.getTile(i, i) != null && bookshelf.getTile(i + 1, i + 1) != null &&
                    bookshelf.getTile(i, i).getType() != bookshelf.getTile(i + 1, i + 1).getType()) {
                ok--;
                break;
            }
        }
        for (int i = 0; i < 4; i++) {
            if(bookshelf.getTile(i+1,i) == null || bookshelf.getTile(i+2,i+1) == null) {
                ok--;
                break;
            }
            if (bookshelf.getTile(i + 1, i) != null && bookshelf.getTile(i + 2, i + 1) != null &&
                    bookshelf.getTile(i + 1, i).getType() != bookshelf.getTile(i + 2, i + 1).getType()) {
                ok--;
                break;
            }
        }

        return ok > 0;
    }
}
