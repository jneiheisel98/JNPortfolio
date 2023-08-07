package minesweeper;

public abstract class boardPiece {
    protected int value;
    private boolean checked;
    private String pieceName;
    private boolean reveal;

    private boolean flagged;

    public boardPiece(){
        value = 0;
        checked = false;
        pieceName = "generalBoardSpace";
    }

    public boardPiece(int value, boolean checked){
        if(value <= 8) {
            this.value = value;
            this.checked = checked;
            this.pieceName = "boardSpace";
        }
        else{
            this.value = value;
            this.checked = checked;
            this.pieceName = "mine";
        }
        this.reveal = false;
    }

    public abstract void setValue(int value);
    public abstract int getValue();
    public void setChecked(boolean checked){
        this.checked = checked;
    }
    public boolean getChecked(){
        return checked;
    }
    public abstract boolean isMine();
    public String getPieceName(){
        return pieceName;
    }
    public void setReveal(boolean reveal){
        this.reveal = reveal;
    }
    public boolean getReveal(){
        return reveal;
    }
    public void setFlagged(boolean flagged){
        this.flagged = flagged;
    }

    public boolean getFlagged(){
        return flagged;
    }
}
