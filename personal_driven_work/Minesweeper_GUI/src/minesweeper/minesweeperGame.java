package minesweeper;
import java.util.Scanner;
public class minesweeperGame {
    private MinesweeperBoard board;
    private static boolean play = true;
    private int lives;

    private int livesUsed;
    //https://stackoverflow.com/questions/4801386/how-do-i-add-an-image-to-a-jbutton
    public minesweeperGame() {
        board = new MinesweeperBoard();
//        lives = 0;
        lives = 10;

    }

    public minesweeperGame(int rows, int columns) {
        board = new MinesweeperBoard(rows, columns);
        lives = (int) Math.ceil(rows * columns * 0.10);

    }
    public minesweeperGame(String [][] strArr, int livesUsed, int lives){
        this.lives = lives;
        board = new MinesweeperBoard(strArr);
        this.livesUsed = livesUsed;
    }


    public void clearSpace(int row, int column) {
        if (board.isPositionValid(row, column) && play) {
            if (getFlagged()) {
                board.setFlagged(row, column, true);
            } else if (board.getPieceValue(row, column) > 0 && board.getPieceValue(row, column) < 9) {
                if (getPieceFlagged(row, column)) {
                    board.setFlagged(row, column, false);
                } else {
                    board.setRevealed(row, column, true);
                }

            } else if (board.getPieceValue(row, column) > 8) {
                if (getPieceFlagged(row, column)) {
                    board.setFlagged(row, column, false);
                } else {
                    board.setRevealed(row, column, true);
                    lives--;
                }
            } else if (board.getPieceValue(row, column) == 0) {
                clearZeroSpace(row, column);
            }
            continuePlay();
        }
    }

    public boolean getPlay(){
        return play;
    }

    public boolean continuePlay() {
        if(lives<0 ){
            play = false;

        }
        else if (lives >=0){
            play = true;
        }
        return play;
    }

    public void endPlay() {
        play = false;
    }
    public void clearZeroSpace(int row, int column) {
            board.setRevealed(row, column, true);

        if(board.getPieceValue(row,column) == 0){
            if(board.isPositionValid(row+1, column) && board.isPositionValid(row, column-1)){
                if(!((board.getPieceValue(row+1,column) > 0) && (board.getPieceValue(row,column-1) > 0) && (board.getPieceValue(row+1,column-1) == 0))){
                    if(!board.getRevealed(row+1,column-1)){
                        clearZeroSpace(row + 1, column - 1);
                    }

                }
            }
            if(board.isPositionValid(row+1, column) && board.isPositionValid(row, column+1)){
                if(!((board.getPieceValue(row+1,column) > 0) && (board.getPieceValue(row,column+1) > 0) && (board.getPieceValue(row+1,column+1) == 0))){
                    if(!board.getRevealed(row+1,column+1)) {
                        clearZeroSpace(row + 1, column + 1);
                    }
                }
            }
            if(board.isPositionValid(row-1, column) && board.isPositionValid(row, column+1)){
                if(!((board.getPieceValue(row-1,column) > 0) && (board.getPieceValue(row,column+1) > 0) && ((board.getPieceValue(row-1,column+1) == 0)))){
                    if(!board.getRevealed(row-1,column+1)) {
                        clearZeroSpace(row - 1, column + 1);
                    }
                }
            }

            if(board.isPositionValid(row-1, column) && board.isPositionValid(row, column-1)){
                if(!((board.getPieceValue(row-1,column) > 0) && (board.getPieceValue(row,column-1) > 0) && (board.getPieceValue(row-1,column-1) == 0))){
                    if(!board.getRevealed(row-1,column-1)) {
                        clearZeroSpace(row - 1, column - 1);
                    }
                }
            }
            if(board.isPositionValid(row+1,column)){
                if(!board.getRevealed(row+1,column)) {
                    clearZeroSpace(row + 1, column);
                }
            }
            if(board.isPositionValid(row,column-1)) {
                if(!board.getRevealed(row,column-1)) {
                    clearZeroSpace(row, column - 1);
                }
            }



            if(board.isPositionValid(row,column+1)) {
                if(!board.getRevealed(row,column+1)) {
                    clearZeroSpace(row, column + 1);
                }
            }

            if(board.isPositionValid(row-1,column)) {
                if(!board.getRevealed(row-1,column)) {
                    clearZeroSpace(row - 1, column);
                }
            }
        }
    }

    public int getRows() {
        return board.getRows();
    }
    public int getColumns() {
        return board.getColumns();
    }
    public boolean getRevealed(int row, int column) {
        return board.getRevealed(row, column);
    }
    public int getValueOfPeace(int row, int column) {
        return board.getPieceValue(row, column);
    }
    public int getLives() {
        return lives;
    }
    public void addLives() {
        lives+=1;
        livesUsed++;
    }
    public int getLivesUsed(){
        return livesUsed;
    }
    public void setFlagged (boolean flagged){
        board.setFlagMode(flagged);
    }
    public boolean getFlagged(){
        return board.getFlagMode();
    }
    public boolean getPieceFlagged(int row, int col){
        return board.getFlaggedPiece(row, col);
    }

    public boolean isGameWon(){
        for (int i = 0; i < board.getRows(); i++){
            for (int j = 0; j < board.getColumns(); j++){
                if(!getRevealed(i,j) && !board.isPieceMine(i,j)){
                    return false;
                }
            }
        }
        return true;
    }
    public MinesweeperBoard getBoard(){
        return board;
    }
public static void main(String[] args){
        minesweeperGame g = new minesweeperGame();
}
}





