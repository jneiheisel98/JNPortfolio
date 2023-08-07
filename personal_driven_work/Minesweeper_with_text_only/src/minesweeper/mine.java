package minesweeper;

public class mine extends boardPiece{
    public mine(){
        super();
    }

    public mine(int value, boolean checked){
        super(value, checked);
    }

    @Override
    public boolean isMine() {
        return true;
    }
    @Override
    public void setValue(int value){
        this.value = this.value;
    }
    @Override
    public int getValue(){
        return value;
    }
}
