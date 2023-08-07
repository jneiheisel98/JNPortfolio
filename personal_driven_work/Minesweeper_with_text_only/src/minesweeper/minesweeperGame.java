package minesweeper;
import javax.swing.*;
import java.io.*;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;


public class minesweeperGame {
    private MinesweeperBoard board;
    private boolean play = true;
    private int lives;
    private int livesUsed;

    //https://stackoverflow.com/questions/4801386/how-do-i-add-an-image-to-a-jbutton
    public minesweeperGame() {
        board = new MinesweeperBoard();
        lives = 10;
//        lives = 0;
        livesUsed = 0;
        playGame();
    }

    public minesweeperGame(int rows, int columns) {
        board = new MinesweeperBoard(rows, columns);
        lives = (int) Math.ceil(rows * columns * 0.10);
        livesUsed = 0;
        playGame();
    }

    public void playGame() {
        Scanner scan = new Scanner(System.in);

        while (play) {
            System.out.println();
            System.out.println("Type in x-coordinate and y-coordinate you want swept with a space - x y, or");
            System.out.println("Type in f and after a space then the x-coordinate and y-coordinate you want flagged/unflagged with a space - f x y, or");
            System.out.println("Type in s to save the game to a file - s, or");
            System.out.println("Type in o and after a space then the filename that contains the game to open in the same directory - o filename, or");
            System.out.println("Type in d for a default-sized new game, p to print the board, q to quit, or type in n for a new game");

            String input = scan.nextLine();
            if (input.split(" ")[0].toUpperCase().equals("F")){
                try{
                    board.setPieceFlagged(Integer.parseInt(input.split(" ")[2]) - 1,Integer.parseInt(input.split(" ")[1]) - 1, !board.getPieceFlagged(Integer.parseInt(input.split(" ")[2]) - 1,Integer.parseInt(input.split(" ")[1]) - 1));

                    board.printBlankBoard();
                }
                catch (ArrayIndexOutOfBoundsException e){
                    board.printBlankBoard();
                }

            }
            else if (input.split(" ")[0].toUpperCase().equals("S")) {
                try {
                    String fileName = JOptionPane.showInputDialog(null,
                            "Type in the name for the file ",
                            "File Name", JOptionPane.PLAIN_MESSAGE);
                    BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
                    String mineboard = "";

                    for (int row = 0; row < board.getRows(); row++) {

                        for (int col = 0; col < board.getColumns(); col++) {
                            String tempStatus = "";
                            if (board.getRevealed(row, col)) {
                                tempStatus = "r";
                            } else {
                                tempStatus = "n";
                            }

                            String isFlagged = "";
                            if (board.getPieceFlagged(row, col)) {
                                isFlagged = "f";
                            } else {
                                isFlagged = "n";
                            }
                            mineboard += board.getPieceValue(row, col) + tempStatus + isFlagged + ",";
                        }
                        mineboard += "\n";
                    }

                    String game = board.getRows() + " " + board.getColumns() + " " + getLivesUsed() + " " + getLives() + "\n" + mineboard;
                    writer.write(game);
                    writer.close();
                }
                catch (IOException e){
                    System.out.println("File Save Failed, change name or restart program");
                    board.printBlankBoard();
                }
                catch(NullPointerException e){
                    System.out.println("Canceled File Save");
                    board.printBlankBoard();

                }
            }

            else if (input.split(" ")[0].toUpperCase().equals("O")) {
                String[][] pieceArr = new String[1][1];
                int livesUsed = 0;
                int lives = 0;
                JFileChooser fileChoose = new JFileChooser();
                int choice = fileChoose.showOpenDialog(null);
                if (choice == JFileChooser.APPROVE_OPTION) {
                    try {
                        BufferedReader reader = new BufferedReader(new FileReader(fileChoose.getSelectedFile().getPath()));
                        String line = reader.readLine();

                        int rows = Integer.parseInt(line.split(" ")[0]);
                        int cols = Integer.parseInt(line.split(" ")[1]);
                        livesUsed = Integer.parseInt(line.split(" ")[2]);
                        lives = Integer.parseInt(line.split(" ")[3]);
                        pieceArr = new String[rows][cols];
                        int arrIndex = 0;
                        while (line != null) {
                            line = reader.readLine();
                            if (line != null) {
                                String[] splitLine = line.split(",");
                                System.out.println(splitLine.length);
                                for (int ind = 0; ind < splitLine.length; ind++) {
                                    pieceArr[arrIndex][ind] = splitLine[ind];
                                }
                                System.out.println(line);
                                arrIndex++;
                                // read next line
                            }

                        }

                        reader.close();

                    } catch (IOException e) {
                        System.out.println("Load failed");
                    }
                }

                    if(pieceArr[0][0] == null){
                        try {
                            int path =  JOptionPane.showConfirmDialog(null,"Do you want to use the default size (30*24)?", "Select Game Size",
                                    JOptionPane.YES_NO_OPTION,
                                    JOptionPane.QUESTION_MESSAGE);
                            if (path == JOptionPane.YES_OPTION) {
                                board = new MinesweeperBoard();
                            }
                            else {
                                scan = new Scanner(System.in);

                                System.out.println("Type in how many columns you want");
                                int columns = scan.nextInt();
                                System.out.println("Type in how many rows you want");
                                int rows = scan.nextInt();
                                board = new MinesweeperBoard(rows, columns);
                            }
                        }
                        catch(NullPointerException e){
                            System.out.println("File was empty and no new game can be created");

                        }
                    }
                    else {
                        board = new MinesweeperBoard(pieceArr);
                        this.livesUsed = livesUsed;
                        this.lives = lives;
                    }
                }




            else if (input.split(" ")[0].toUpperCase().equals("D")){
                board = new MinesweeperBoard();
//        lives = 10;
                lives = 0;
                livesUsed = 0;
                board.printBlankBoard();
                playGame();
            }
            else if (input.split(" ")[0].toUpperCase().equals("N")){
                try {
                    System.out.println("Type in how many columns you want");
                    int columns = scan.nextInt();
                    System.out.println("Type in how many rows you want");
                    int rows = scan.nextInt();
                    minesweeperGame g = new minesweeperGame(rows, columns);
                }
                catch(NumberFormatException e){
                    System.out.println("New Game Creation Canceled");
                    board.printBlankBoard();
                    scan.nextLine();

                }
                catch(InputMismatchException e){
                    System.out.println("New Game Creation Canceled");
                    board.printBlankBoard();
                    scan.nextLine();

                }

            }
            else if (input.split(" ")[0].toUpperCase().equals("P")) {
                board.printBlankBoard();
            }
            else if (input.split(" ")[0].toUpperCase().equals("Q")) {
                play = false;
                scan.close();
            }
            else{
                try{
                    int column = ((int) Integer.parseInt(input.split(" ")[0])) - 1;
                    int row = ((int) Integer.parseInt(input.split(" ")[1])) - 1;
                    clearSpace(row, column);
                    if(isGameWon()){
                        int score = (((board.getRows() * board.getColumns()) - board.getNumMines()) * 100) - (livesUsed * 50);
                        gameIsWon(score);
                    }
                    else{
                        board.printBlankBoard();
                        if(lives <0){
                            addLives();
                        }
                        System.out.println();
                        System.out.println("Lives: " + lives);
                    }

                }
                catch (ArrayIndexOutOfBoundsException e){
                    board.printBlankBoard();
                }

            }

        }
    }



        public void addLives(){
            Boolean go = false;
        try {
            int path =  JOptionPane.showConfirmDialog(null,"Do you want one more life to continue the game?", "Continue Game",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE);
            if (path == JOptionPane.YES_OPTION) {
                livesUsed +=1;
                lives += 1;
                go = true;
                play = true;
            }
        }
        catch(NullPointerException e){
            JOptionPane.showMessageDialog(null,
                    "Game Over! Close window to start new game.",
                    "Game Over", JOptionPane.PLAIN_MESSAGE);

        }
        if(!go) {
            JOptionPane.showMessageDialog(null,
                    "Game Over! Close window to start new game.",
                    "Game Over", JOptionPane.PLAIN_MESSAGE);
        }

    }

    public void clearSpace(int row, int column) {
        if(board.isPositionValid(row, column) && play) {
            if (board.getPieceValue(row, column) > 0 && board.getPieceValue(row, column) < 9) {
                board.setRevealed(row, column, true);
            } else if (board.getPieceValue(row, column) > 8) {
                board.setRevealed(row, column, true);
                lives--;
            } else if (board.getPieceValue(row, column) == 0) {
                clearZeroSpace(row, column);


            }
            continuePlay();
        }
    }

    public boolean isGameWon(){
        for (int rowInd = 0; rowInd < board.getRows(); rowInd ++){
            for (int colInd = 0; colInd < board.getColumns(); colInd++){
                if(!board.getRevealed(rowInd,colInd) && !board.isPieceMine(rowInd, colInd)){
                    return false;
                }
            }
        }
        return true;
    }


    public void gameIsWon(int score){
        try {
            int path =  JOptionPane.showConfirmDialog(null,"Your score is: " + score + "\nDo you want to play another game?", "You Won!",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE);
            if (path == JOptionPane.YES_OPTION) {
                try {
                    int selection =  JOptionPane.showConfirmDialog(null,"Do you want to use the default size (30*24)?", "Select Game Size",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.QUESTION_MESSAGE);
                    if (selection == JOptionPane.YES_OPTION) {
                        minesweeperGame g = new minesweeperGame();
                    }
                    else {
                        try {
                            Scanner scan = new Scanner(System.in);

                            System.out.println("Type in how many columns you want");
                            int columns = scan.nextInt();
                            System.out.println("Type in how many rows you want");
                            int rows = scan.nextInt();
                            play = false;
                            minesweeperGame g = new minesweeperGame(rows, columns);

                        }
                        catch (InputMismatchException e){
                            main(new String [0]);
                        }
                    }
                }
                catch(NullPointerException e){
                    JOptionPane.showMessageDialog(null,
                            "Game Over! Close window to start new game.",
                            "Game Over", JOptionPane.PLAIN_MESSAGE);

                }
            }
            else{
                play = false;
            }
        }
        catch(NullPointerException e){
            JOptionPane.showMessageDialog(null,
                    "Game Over! Close window to start new game.",
                    "Game Over", JOptionPane.PLAIN_MESSAGE);

        }

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
    public int getLives(){
        return lives;
    }
    public int getLivesUsed(){
        return livesUsed;
    }

public static void main(String[] args){


    try {
        int path =  JOptionPane.showConfirmDialog(null,"Do you want to use the default size (30*24)?", "Select Game Size",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);
        if (path == JOptionPane.YES_OPTION) {
            minesweeperGame g = new minesweeperGame();
        }
        else {
            try {
                Scanner scan = new Scanner(System.in);

                System.out.println("Type in how many columns you want");
                int columns = scan.nextInt();
                System.out.println("Type in how many rows you want");
                int rows = scan.nextInt();
                minesweeperGame g = new minesweeperGame(rows, columns);
            }
            catch (InputMismatchException e){
                main(new String [0]);
            }
        }
    }
    catch(NullPointerException e){
        JOptionPane.showMessageDialog(null,
                "Game Over! Close window to start new game.",
                "Game Over", JOptionPane.PLAIN_MESSAGE);

    }
}
}




