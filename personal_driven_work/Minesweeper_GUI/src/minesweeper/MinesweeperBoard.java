package minesweeper;
import java.util.Random;

public class MinesweeperBoard {
    private int columns;
    private int rows;
    private int numMines;
    private boardPiece minearray[][];
    private Random rand;

    private Boolean flagMode = false;

    public MinesweeperBoard(String [][] strArr){

        columns = strArr[0].length;
        rows = strArr.length;
        numMines = 0;
        for (int row = 0; row < strArr.length; row++){
            for (int col = 0; col < strArr[0].length; col++){
                if (strArr[row][col].indexOf("9") != -1){
                    numMines+=1;
                }
            }
        }
        minearray = new boardPiece[rows][columns];

        for (int row = 0; row < strArr.length; row++){
            for (int col = 0; col < strArr[0].length; col++){
                minearray[row][col] = new generalBoardPiece(Character.getNumericValue(strArr[row][col].charAt(0)), strArr[row][col].charAt(1) == 'r');
                minearray[row][col].setReveal(strArr[row][col].charAt(1) == 'r');
                minearray[row][col].setFlagged(strArr[row][col].charAt(2) == 'f');
            }
        }

    }
    public MinesweeperBoard(){


        columns = 19;
        rows = 16;
        numMines = 100;
        minearray = new boardPiece[rows][columns];
        zeroSquares();
        distributeMines(numMines);
        fillSquares(0,0);
        printBlankBoard();
        resetBoard();

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
        else if (columns == 16 && rows == 30){
            numMines = 161;
        }
        //0.3125
        else if (columns == 24 && rows == 30){
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


        fillSquares(0,0);
        System.out.println(this.columns);
        System.out.println(this.rows);
        printBlankBoard();

        resetBoard();
    }



    public static void main(String[] args) {
	MinesweeperBoard board = new MinesweeperBoard();
	System.out.println();
	board.printBoard();
    }

    public void distributeMines(int numMines){
        Random rand = new Random();
        int seed = rand.nextInt();


        rand.setSeed(seed);

        System.out.println(seed);
        for (int count = 0; count < numMines; count++){
            int columnNum = rand.nextInt(columns);
            int rowNum = rand.nextInt(rows);
            minearray[rowNum][columnNum] = new mine(9);
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

    public int getPieceValue(int rowPos, int colPos){
        if(isPositionValid(rowPos, colPos)) {
            return minearray[rowPos][colPos].getValue();
        }
        return -1;
        }


    public void printBoard(){
        for (int countRows = 0; countRows < rows; countRows++){
            System.out.println();
            for(int countColumns = 0; countColumns < columns; countColumns++) {
            System.out.print(minearray[countRows][countColumns].getValue() +" ");
            }

        }
        System.out.println();
    }

    public void printBlankBoard(){
        for (int countRows = 0; countRows < rows; countRows++){
            System.out.println();
            for(int countColumns = 0; countColumns < columns; countColumns++) {
                if(minearray[countRows][countColumns].getReveal()) {
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

    public void setRevealed(int rowPos, int colPos, boolean revealValue){
        minearray[rowPos][colPos].setReveal(revealValue);
    }

    public boolean getRevealed(int rowPos, int colPos){

        return minearray[rowPos][colPos].getReveal();

    }
    public int getRows(){
        return rows;
    }
    public int getColumns(){
        return columns;
    }

    public void setFlagMode(boolean flagMode){
        this.flagMode = flagMode;
    }
    public boolean getFlagMode(){
        return this.flagMode;
    }

    public void setFlagged(int rowPos, int colPos, boolean toBeFlagged){
        minearray[rowPos][colPos].setFlagged(toBeFlagged);
    }

    public boolean getFlaggedPiece(int rowPos, int colPos){
        return minearray[rowPos][colPos].getFlagged();
    }
    public int getNumMines(){
        return numMines;
    }

}

