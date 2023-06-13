package ServerSide.Model.CommonPattern;

import ServerSide.Model.Bookshelf;
import ServerSide.Model.CommonGoalCard;
import ServerSide.Model.ItemType;

public class CommonPattern9 extends CommonGoalCard {

    public CommonPattern9() {
        super();
    }

    /**
     * Overview: patternNumber getter
     */
    @Override
    public int getPatternNumber() {return 9;}

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
        boolean ok = false;
        int count = 0;

        for (ItemType type : ItemType.values()) {
            count = 0;
            for (int i = 0; i < 6; i++)
                for (int j = 0; j < 5; j++)
                    if (bookshelf.getTile(i,j) != null && bookshelf.getTile(i, j).getType() == type)
                        count++;
            if(count > 7)
                ok = true;
        }

        return ok;
    }
}
