package ServerSide.Model.CommonPattern;

import ServerSide.Model.Bookshelf;
import ServerSide.Model.CommonGoalCard;

public class CommonPattern4 extends CommonGoalCard {

    public CommonPattern4() {
        super();
    }

    /**
     * Overview: patternNumber getter
     */
    @Override
    public int getPatternNumber() {return 4;}

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
        int count = 0;

        for(int i=0; i<5; i++){
            for(int j=0; j<4; j++){
                if(bookshelf.getTile(i,j) != null && bookshelf.getTile(i+1,j) != null &&
                        bookshelf.getTile(i, j).getType() == bookshelf.getTile(i + 1, j).getType())
                    count++;
                if(bookshelf.getTile(i,j) != null && bookshelf.getTile(i,j+1)!=null &&
                        bookshelf.getTile(i, j).getType() == bookshelf.getTile(i , j+1).getType())
                    count++;
            }
        }

        return count > 5;
    }
}
