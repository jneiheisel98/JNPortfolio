package minesweeper;
import java.util.Random;

public class MinesweeperBoard {
    private int columns;
    private int rows;
    private int numMines;
    private boardPiece minearray[][];
    private Random rand;

    public MinesweeperBoard(){
        columns = 30;
        rows = 24;
        numMines = 200;
        minearray = new boardPiece[rows][columns];
        zeroSquares();
        distributeMines(numMines);
        fillSquares(0,0);
        printBlankBoard();
        resetBoard();
        rand = new Random();
    }

    public MinesweeperBoard(int rows, int columns){
        //0.765625
        rand = new Random();
        if (columns == 8 && rows == 8){
            numMines = 49;
        }
        //0.518518518518
        else if (columns == 9 && rows == 9){
            numMines = 42;
        }
        //0.37109375
        else if (columns == 16 && rows == 16){
            numMines = 95;
        }
        //0.335416666666
        else if (rows == 16 && columns == 30){
            numMines = 161;
        }
        //0.3125
        else if (rows == 30 && columns == 24){
            numMines = 225;
        }
        else if (columns * rows <= 64){
            numMines = (int) (columns * rows * (3.0/4.0));
        }
        else if (columns * rows <= 81 && columns * rows > 64){
            numMines = (int) (columns * rows * (5185.0/10000.0));
    }
        else if (columns * rows <= 256 && columns * rows > 81){
            numMines = (int) (columns * rows * (371.0/1000.0));

        }
        else if (columns * rows <= 480 && columns * rows > 256){
            numMines = (int) (columns * rows * (3354.0/10000.0));
        }

        else if (columns * rows <= 720 && columns * rows > 480){
            numMines = (int) (columns * rows * (3125.0/10000.0));
        }
        else if (columns * rows > 720){
            numMines = (int) ((columns*rows) * (0.3125-((columns*rows-720)/24000)));
        }
        this.columns = columns;
        this.rows = rows;
        minearray = new boardPiece[rows][columns];
        zeroSquares();
        distributeMines(numMines);
       printBlankBoard();

        fillSquares(0,0);
        rand = new Random();
    }

    public MinesweeperBoard (String[][] board){
        this.rows = board.length;
        this.columns = board[0].length;
        this.minearray = new boardPiece[board.length][board[0].length];
        for (int i = 0; i < board.length; i++){
            for (int j = 0; j < board[0].length; j++){
                int value = Character.valueOf(board[i][j].charAt(0));
                char revealed = board[i][j].charAt(1);
                char flagged = board[i][j].charAt(2);
                if (value == 9){
                    this.minearray[i][j] = new mine(value, revealed == 'r');
                    this.minearray[i][j].setReveal(revealed == 'r');
                    this.minearray[i][j].setFlagged(flagged == 'f');
                }
                else {
                    this.minearray[i][j] = new generalBoardPiece(value, revealed == 'r');
                    this.minearray[i][j].setReveal(revealed == 'r');
                    this.minearray[i][j].setFlagged(flagged == 'f');
                }
            }
        }
    }


    public static void main(String[] args) {
	MinesweeperBoard board = new MinesweeperBoard();
	System.out.println();
	board.printBlankBoard();
    }

    public void distributeMines(int numMines){
        rand = new Random();
        for (int count = 0; count < numMines; count++){
            int columnNum = rand.nextInt(columns);
            int rowNum = rand.nextInt(rows);
            minearray[rowNum][columnNum] = new mine(9, false);
        }
    }

    public int fillSquares(int rowNumber, int columnNumber){
        if(!isPositionValid(rowNumber, columnNumber)){
            return 0;
        }
        else if(isPieceMine(rowNumber, columnNumber) && minearray[rowNumber][columnNumber].getChecked()){
            return 1;
        }
        else if(minearray[rowNumber][columnNumber].getChecked()){
            return 0;
        }
        else if(isPieceMine(rowNumber, columnNumber) && !minearray[rowNumber][columnNumber].getChecked()){
            minearray[rowNumber][columnNumber].setChecked(true);
            fillSquares(rowNumber, columnNumber+1);
            fillSquares(rowNumber+1, columnNumber+1);
            fillSquares(rowNumber-1, columnNumber+1);
            fillSquares(rowNumber-1,columnNumber);
           fillSquares(rowNumber+1, columnNumber);
            fillSquares(rowNumber-1, columnNumber-1);
            fillSquares(rowNumber, columnNumber-1);
            fillSquares(rowNumber+1,columnNumber-1);
            return 1;

        }
        else {
            minearray[rowNumber][columnNumber].setChecked(true);
            int a = fillSquares(rowNumber, columnNumber+1);
           int b = fillSquares(rowNumber+1, columnNumber+1);
            int c = fillSquares(rowNumber-1, columnNumber+1);
            int d = fillSquares(rowNumber-1,columnNumber);
        int e = fillSquares(rowNumber+1, columnNumber);
        int f = fillSquares(rowNumber-1, columnNumber-1);
        int g = fillSquares(rowNumber, columnNumber-1);
        int h = fillSquares(rowNumber+1, columnNumber-1);
        minearray[rowNumber][columnNumber] = new generalBoardPiece(a+b+c+d+e+f+g+h, true);
        return 0;
        }
    }

    public void zeroSquares(){
        for (int countRows = 0; countRows < rows; countRows++) {
            for (int countColumns = 0; countColumns < columns; countColumns++) {
            minearray[countRows][countColumns] = new generalBoardPiece();
            }
        }
    }

    public boolean isPositionValid(int rowPos, int colPos){
        boolean returner = true;
        if ((colPos < 0) || (colPos >= columns)) {
            returner = false;
        }
        if ((rowPos < 0) || (rowPos >= rows)) {
            returner = false;
        }
        return returner;
    }

    public boolean isPieceMine(int rowPos, int colPos){
        if(minearray[rowPos][colPos].getValue() == 9){
            return true;
        }
        else{
            return false;
        }
    }

    public int getPieceValue(int rowPos, int colPos) {
        if (isPositionValid(rowPos, colPos)) {
            return minearray[rowPos][colPos].getValue();
        } else {
            return -1;
        }
    }


    public void printBoard(){
        for (int countRows = 0; countRows < rows; countRows++){
            System.out.println();
            for(int countColumns = 0; countColumns < columns; countColumns++) {
            System.out.print(minearray[countRows][countColumns].getValue() +" ");
            }
        }
    }

    public void printBlankBoard(){
        for (int countRows = 0; countRows < rows; countRows++){
            System.out.println();
            for(int countColumns = 0; countColumns < columns; countColumns++) {
                if(minearray[countRows][countColumns].getFlagged()){
                    System.out.print("F ");
                }
                else if(minearray[countRows][countColumns].getReveal()) {
                    System.out.print(minearray[countRows][countColumns].getValue() + " ");
                }
                else{
                    System.out.print("* ");
                }
            }
        }
        System.out.println();
    }

    public void resetBoard(){
        for (int countRows = 0; countRows < rows; countRows++){
            for(int countColumns = 0; countColumns < columns; countColumns++) {
                minearray[countRows][countColumns].setChecked(false);
            }
        }
    }

    public void setPieceFlagged(int row, int col, boolean isPieceFlagged){
        minearray[row][col].setFlagged(isPieceFlagged);
    }

    public boolean getPieceFlagged(int row, int col){
        return minearray[row][col].getFlagged();
    }
    public void setRevealed(int rowPos, int colPos, boolean revealValue){
        minearray[rowPos][colPos].setReveal(revealValue);
    }
    public void setChecked(int rowPos, int colPos, boolean checkValue){
        minearray[rowPos][colPos].setChecked(checkValue);
    }
    public boolean getRevealed(int rowPos, int colPos){
        return minearray[rowPos][colPos].getReveal();
    }

    public boolean isValidPosition(int rowPos, int colPos){
        if(rowPos < 0 || rowPos > rows-1){
            return false;
        }
        if(colPos < 0 || colPos > columns-1){
            return false;
        }
        return true;
    }

    public int getRows(){
        return rows;
    }
    public int getColumns(){
        return columns;
    }
    public int getNumMines(){
        return numMines;
    }
}

