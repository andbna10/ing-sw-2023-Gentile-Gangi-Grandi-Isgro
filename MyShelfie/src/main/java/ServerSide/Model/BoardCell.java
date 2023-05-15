package ServerSide.Model;

public class BoardCell {
    private Status status;

    private boolean pickable;
    private ItemTile tile;

    /**
     * Overview: constructor 1 of boardcell
     */
    public BoardCell(Status status){
        pickable = false;
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

    /**
     * Overview: pickable setter
     */
    public void setPickable(boolean arg) {pickable = arg;}

    /**
     * Overview: pickable getter
     */
    public Boolean getPickable(){ return this.pickable; }
}
