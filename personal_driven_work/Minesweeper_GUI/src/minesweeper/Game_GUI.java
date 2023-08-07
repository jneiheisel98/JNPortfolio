package minesweeper;

import minesweeper.minesweeperGame;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.*;

public class Game_GUI extends JPanel {
    private minesweeperGame game;
    private JButton[][] buttons;
    private JLabel livesCount;



    public Game_GUI() {
        game = new minesweeperGame();
        buttons = new JButton[game.getRows()][game.getColumns()];
        ButtonListener listen = new ButtonListener();


        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        livesCount = new JLabel("Lives: "+Integer.toString(game.getLives()), JLabel.CENTER);
        livesCount.setFont(new Font("Arial",1,12));



        for (int rows = 0; rows < buttons.length; rows++) {
            c.gridy = rows;
            for (int columns = 0; columns < buttons[0].length; columns++) {
                c.gridx = columns;
                buttons[rows][columns] = new JButton("*");
                buttons[rows][columns].addActionListener(listen);
               //https://codereview.stackexchange.com/questions/215081/minesweeper-game-in-java-using-swing-gui
                //https://codereview.stackexchange.com/questions/151827/minesweeper-project


                add(buttons[rows][columns], c);
            }
        }
//        add(grid, BorderLayout.CENTER);
        c.gridy = buttons.length;
        c.gridx=0;
        add(livesCount, c);
        updateButtonLabels();
    }

    public Game_GUI(int rows, int cols) {
        game = new minesweeperGame(rows, cols);
        buttons = new JButton[game.getRows()][game.getColumns()];
        ButtonListener listen = new ButtonListener();


        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

            livesCount = new JLabel("Lives: " + Integer.toString(game.getLives()), JLabel.CENTER);
            livesCount.setFont(new Font("Arial", 1, 12));




        for (int rowCount = 0; rowCount < buttons.length; rowCount++) {
            c.gridy = rowCount;
            for (int columns = 0; columns < buttons[0].length; columns++) {
                c.gridx = columns;
                buttons[rowCount][columns] = new JButton("*");
                buttons[rowCount][columns].addActionListener(listen);
                //https://codereview.stackexchange.com/questions/215081/minesweeper-game-in-java-using-swing-gui
                //https://codereview.stackexchange.com/questions/151827/minesweeper-project


                add(buttons[rowCount][columns], c);
            }
        }
//        add(grid, BorderLayout.CENTER);
        c.gridy = buttons.length;
        c.gridx=0;
        add(livesCount, c);
        updateButtonLabels();
    }
    public Game_GUI (int rows, int cols, String [][] strarr, int livesUsed, int lives) {
        game = new minesweeperGame(strarr, livesUsed, lives);
        buttons = new JButton[game.getRows()][game.getColumns()];
        ButtonListener listen = new ButtonListener();


        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        livesCount = new JLabel("Lives: " + Integer.toString(game.getLives()), JLabel.CENTER);
        livesCount.setFont(new Font("Arial", 1, 12));




        for (int rowCount = 0; rowCount < buttons.length; rowCount++) {
            c.gridy = rowCount;
            for (int columns = 0; columns < buttons[0].length; columns++) {
                c.gridx = columns;
                buttons[rowCount][columns] = new JButton("*");
                buttons[rowCount][columns].addActionListener(listen);
                //https://codereview.stackexchange.com/questions/215081/minesweeper-game-in-java-using-swing-gui
                //https://codereview.stackexchange.com/questions/151827/minesweeper-project


                add(buttons[rowCount][columns], c);
            }
        }
//        add(grid, BorderLayout.CENTER);
        c.gridy = buttons.length;
        c.gridx=0;
        add(livesCount, c);
        updateButtonLabels();
    }

    private class ButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent arg0) {

            for (int row = 0; row < buttons.length; row++) {
                for (int column = 0; column < buttons[0].length; column++) {
                if (arg0.getSource() == buttons[row][column] && game.getPlay()){
                    game.clearSpace(row, column);
                    updateButtonLabels();
                    if (game.isGameWon()){

                        JOptionPane option = new JOptionPane();
                        option.setMessage("You won! Your score is: " + Integer.toString((game.getBoard().getNumMines() * 10) - game.getLivesUsed()*5));
                        option.showMessageDialog(null, "You won! Your score is:" + Integer.toString((game.getBoard().getNumMines() * 10) - game.getLivesUsed()*5));
                        int verdict = option.showConfirmDialog(null, "Do you want to play again?");
                        if(verdict == JOptionPane.YES_OPTION){
                            game = new minesweeperGame();
                            updateButtonLabels();
                        }
                        else{
                            game.endPlay();
                            livesCount.setText("Game Over! Close window to start new game.");

                        }
                    }

                }
                }
            }

            }

        }
    private void updateButtonLabels() {
        for (int rowNum = 0; rowNum < buttons.length; rowNum++) {
            for (int columnNum = 0; columnNum < buttons[0].length; columnNum++) {
                if(game.getPieceFlagged(rowNum, columnNum)){
                    buttons[rowNum][columnNum].setText("F");
                }
                else if (game.getRevealed(rowNum, columnNum)) {
                    buttons[rowNum][columnNum].setText(Integer.toString(game.getValueOfPeace(rowNum, columnNum)));
                }
                else {
                    buttons[rowNum][columnNum].setText("*");
                }


            }
        }
        if(game.getLives() >= 0) {
            livesCount.setText("Lives: " + Integer.toString(game.getLives()));
        }
        else{
            Boolean go = false;
            try {
                int path =  JOptionPane.showConfirmDialog(null,"Do you want one more life to continue the game?", "Continue Game",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE);
                if(path == JOptionPane.YES_OPTION){
                    game.addLives();
                    game.continuePlay();
                    go = true;
                }


            }
            catch(NullPointerException e){
                livesCount.setText("Game Over! Close window to start new game.");
            }
            if(!go) {
                livesCount.setText("Game Over! Close window to start new game.");
            }
        }
    }
    public minesweeperGame getGame(){
        return game;
    }

}
