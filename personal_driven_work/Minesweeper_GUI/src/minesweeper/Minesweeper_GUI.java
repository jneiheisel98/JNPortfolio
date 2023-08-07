package minesweeper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;


public class Minesweeper_GUI extends JFrame implements ActionListener {
    private JMenuBar menus;
    private Game_GUI gamePanel;

    private JMenu actionMenu;
    private JMenu fileMenu;

    private JMenu flagMenu;
    private JMenuItem openSerItem;
    private JMenuItem openTextItem;
    private JMenuItem exitItem;
    private JMenuItem saveSerItem;
    private JMenuItem saveTextItem;

    private JMenuItem toggleFlagOn;

    private JMenuItem toggleFlagOff;

    private JMenuItem defaultGame;
    private JMenuItem chosenSizeGame;
    private JFrame frame;



    public Minesweeper_GUI(){
        frame = new JFrame ("Minesweeper");

        createJFrame(frame, new String[1][1], 0, 0);


    }

    private void createJFrame(JFrame frame, String[][] strarr, int livesUsed, int lives) {

        frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);

        menus = new JMenuBar();
        actionMenu = new JMenu("New Game");
        fileMenu = new JMenu("File");
        flagMenu = new JMenu("Toggle Flag Mode");
        openTextItem = new JMenuItem("Open Text File");
        openSerItem = new JMenuItem("Open File");
        saveSerItem = new JMenuItem("Save Game");
        saveTextItem = new JMenuItem("Save Game Text");
        toggleFlagOn = new JMenuItem("Flag Mode On");
        toggleFlagOff = new JMenuItem("Flag Mode Off");
        exitItem = new JMenuItem("Exit");
        defaultGame = new JMenuItem("Default Game");
        chosenSizeGame = new JMenuItem("Choose Game Size");

        actionMenu.add(defaultGame);
        actionMenu.addSeparator();
        actionMenu.add(chosenSizeGame);
        fileMenu.add(openSerItem);
        fileMenu.add(saveSerItem);

        flagMenu.add(toggleFlagOn);
        flagMenu.add(toggleFlagOff);


        menus.add(actionMenu);
        menus.add(fileMenu);
        menus.add(flagMenu);

        openSerItem.addActionListener(this);
        saveSerItem.addActionListener(this);
        openTextItem.addActionListener(this);
        saveTextItem.addActionListener(this);
        chosenSizeGame.addActionListener(this);
        defaultGame.addActionListener(this);
        exitItem.addActionListener(this);
        toggleFlagOn.addActionListener(this);
        toggleFlagOff.addActionListener(this);
        //error check and default check
       if( strarr[0][0]==null && lives != -1) {
           try {
               int cols = Integer.parseInt(JOptionPane.showInputDialog(null,
                       "Input number of columns in game, press enter for default ",
                       "Columns", JOptionPane.PLAIN_MESSAGE));
               int rows = Integer.parseInt(JOptionPane.showInputDialog(null,
                       "Input number of rows in game, press enter for default again and do not type anything in if you want default already",
                       "Row", JOptionPane.PLAIN_MESSAGE));
               gamePanel = new Game_GUI(rows, cols);
           } catch (NumberFormatException e) {
               gamePanel = new Game_GUI();
           }
       }
       else if (strarr[0][0]==null && lives == -1){
           gamePanel = new Game_GUI();
       }
       else{
           int rows = strarr.length;
           int cols = strarr[0].length;
           gamePanel = new Game_GUI(rows, cols, strarr, livesUsed, lives);
       }


        frame.setJMenuBar(menus);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.getContentPane().add(gamePanel);

        frame.setSize(950,850);
        frame.setVisible(true);
    }

    public void writeFile() throws IOException{
        String fileName = JOptionPane.showInputDialog(null,
                "Type in the name for the file ",
                "File Name", JOptionPane.PLAIN_MESSAGE);
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
        String board = "";

        for (int row = 0; row < gamePanel.getGame().getRows(); row++){

            for (int col = 0; col < gamePanel.getGame().getColumns(); col++){
                String tempStatus = "";
                if(gamePanel.getGame().getRevealed(row,col)){
                    tempStatus = "r";
                }
                else{
                    tempStatus = "n";
                }

                String isFlagged = "";
                if(gamePanel.getGame().getPieceFlagged(row, col)){
                    isFlagged = "f";
                }
                else{
                    isFlagged = "n";
                }
                board += gamePanel.getGame().getValueOfPeace(row, col) + tempStatus + isFlagged + ",";
            }
            board += "\n";
        }

        String game = gamePanel.getGame().getRows() + " " + gamePanel.getGame().getColumns() + " " + gamePanel.getGame().getLivesUsed() + " "+ gamePanel.getGame().getLives()+ "\n" + board;
        writer.write(game);
        writer.close();
    }
    private JFrame getFrame(){
        return frame;
    }
    public void readFile(){
        getFrame().setVisible(false);

        String [][] pieceArr = new String [1] [1];
        int livesUsed = 0;
        int lives = 0;
        JFileChooser fileChoose = new JFileChooser();
        int choice = fileChoose.showOpenDialog(null);
        if(choice == JFileChooser.APPROVE_OPTION){
            try{
                BufferedReader reader = new BufferedReader(new FileReader(fileChoose.getSelectedFile().getPath()));
                String line = reader.readLine();

                int rows = Integer.parseInt(line.split(" ")[0]);
                int cols = Integer.parseInt(line.split(" ")[1]);
                livesUsed = Integer.parseInt(line.split(" ")[2]);
                lives = Integer.parseInt(line.split(" ")[3]);
                pieceArr = new String [rows][cols];
                int arrIndex = 0;
                while (line != null) {
                    line = reader.readLine();
                    if(line != null) {
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

            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        getFrame().dispose();
        JFrame frame = new JFrame();
        createJFrame(frame, pieceArr, livesUsed, lives);
        frame.setVisible(true);
    }
    public void actionPerformed(ActionEvent e)  {
        if (e.getSource() == openSerItem){
            readFile();
        }
        else if (e.getSource() == saveSerItem) {
            try {
                writeFile();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
        else if (e.getSource() == toggleFlagOn) {
            gamePanel.getGame().setFlagged(true);
        }
        else if (e.getSource() == toggleFlagOff) {
            gamePanel.getGame().setFlagged(false);
        }
        else if (e.getSource() == defaultGame){
            getFrame().dispose();
            frame = new JFrame();
            createJFrame(frame, new String [1][1], 0, -1);
            frame.setVisible(true);
        }
        else if (e.getSource() == chosenSizeGame){
            getFrame().dispose();
            frame = new JFrame();
            createJFrame(frame, new String [1][1], 0, 0);
            frame.setVisible(true);
        }
    }
    public static void main (String args[]){
    new Minesweeper_GUI();
    }

}
