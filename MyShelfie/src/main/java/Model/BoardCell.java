package Model;

enum Status{ IN, OUT }

public class BoardCell {
    private Status status;
    private ItemTile tile;

    /**
     * Overview: constructor 1 of boardcell
     */
    public BoardCell(Status status){
        this.status = status;
    }

    /**
     * Overview: get status
     */
    public Status getStatus(){ return status; }

    /**
     * Overview: get tile
     */
    public ItemTile getTile(){ return tile; }

    /**
     * Overview: set tile
     */
    public void setTile(ItemTile tile){ this.tile = tile; }
}
