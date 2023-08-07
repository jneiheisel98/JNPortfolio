package minesweeper;

public class generalBoardPiece extends boardPiece{
    public generalBoardPiece(){
        super();
    }

    public generalBoardPiece(int value, boolean checked){
        super(value, checked);
    }

    @Override
    public boolean isMine() {
        return false;
    }
    @Override
    public void setValue(int value){
        this.value = value;
    }
    @Override
    public int getValue(){
        return value;
    }
}
