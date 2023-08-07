package minesweeper;

public class mine extends boardPiece{
    public mine(){
        super();
    }

    public mine(int value){
        super(value, false);
    }

    @Override
    public boolean isMine() {
        return true;
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
