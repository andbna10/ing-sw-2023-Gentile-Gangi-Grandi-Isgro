package Model;

public class ItemTile {
    private int id;
    private ItemType type;
    private Boolean inGame;
    private static int numTiles = 0;

    /**
     * Overview: ItemTile constructor
     */
    public ItemTile(ItemType type){
        this.type = type;
        inGame = false;
        id = numTiles;
        numTiles++;
    }

    /**
     * Overview: inGame getter
     */
    public Boolean getInGame(){ return inGame; }

    /**
     * Overview: inGame status setter
     */
    public void setInGame(Boolean status){ this.inGame = status; }

    /**
     * Overview: id getter
     */
    public int getId(){ return id; }

    /**
     * Overview: type getter
     */
    public ItemType getType(){ return type; }
}
