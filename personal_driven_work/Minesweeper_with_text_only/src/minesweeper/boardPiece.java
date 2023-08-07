package minesweeper;

public abstract class boardPiece {
    protected int value;
    protected boolean checked;
    private String pieceName;
    protected boolean reveal;
    protected boolean flagged;

    public boardPiece(){
        value = 0;
        checked = false;
        pieceName = "generalBoardSpace";
        reveal = false;
        flagged = false;
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
        this.flagged = false;
    }

    public abstract void setValue(int value);
    public abstract int getValue();
    public void setChecked(boolean checked){
        this.checked = checked;
    }
    public void setFlagged(boolean pieceIsFlagged){
        this.flagged = pieceIsFlagged;
    }
    public boolean getFlagged(){
        return this.flagged;
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


}
