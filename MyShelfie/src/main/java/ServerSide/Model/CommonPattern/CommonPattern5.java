package ServerSide.Model.CommonPattern;

import ServerSide.Model.Bookshelf;
import ServerSide.Model.CommonGoalCard;
import ServerSide.Model.ItemType;
import java.util.ArrayList;
import java.util.List;

public class CommonPattern5 extends CommonGoalCard {

    public CommonPattern5() {
        super();
    }

    /**
     * Overview: patternNumber getter
     */
    @Override
    public int getPatternNumber() {return 5;}

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
        List<ItemType> found = new ArrayList<ItemType>();

        for (int j = 0; j < 5; j++) {
            if(bookshelf.getTile(0,j)!=null) {
                for (int i = 0; i < 6; i++)
                    if (bookshelf.getTile(i, j) != null && !found.contains(bookshelf.getTile(i, j).getType())) {
                        found.add(bookshelf.getTile(i, j).getType());
                    }
                if (found.size() < 4) count++;
                found.clear();
            }
        }
        if(count > 2) ok = true;
        return ok;
    }
}
