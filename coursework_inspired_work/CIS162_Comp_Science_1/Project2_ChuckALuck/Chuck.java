
/**
 * Chuck - a class that can determine multiple properties about 3 dice.
 * Whether it be about the creation of a new game, dealing with new bets,
 * or simply trying to reset the board or determine whether or not to 
 * continue with the game. In this class are methods to determine 
 * if there triples of dice, if there are doubles of dice, a 
 * certain number in the set of dice, or if the sum of the dice fall 
 * into certain parameters. There is also game management of clearing,
 * adding, and checking bets.
 *
 * @author Jacob Neiheisel
 * @classfor: Computer Science 1 - CIS 162 Section 09: Professor Posada
 * @version 1.0.3 (as of 03 November 2020)
 */


public class Chuck {
    
    /**
     * Start instance variables
     */
    
    
    private GVdie[] dice;
    private int credits;
    private String currentMessage;
    private  boolean[] bets;
    
    
    /**
     * Start constructor methods
     */

    
    /**
     * Defualt Constructor for objects of class Chuck 
     */
    public Chuck(){
        credits = 10;
        dice = new GVdie[3];
        bets = new boolean[9];
        for(int count = 0; count< dice.length; count++){
            dice[count] = new GVdie();
        }
        
        
        for(int count = 0; count<dice.length; count++){
            dice[count].setBlank();
        }
        
        
        for(int count = 0; count<bets.length; count++){
            bets[count] = false;
        }

    }
       
    
    /**
      * Start getter methods
     */
    
    
    /**
     * getMessage() - gets message as a string
     *
     * 
     * @return    message at that time, displayed as a string
     */
    public String getMessage(){
        return currentMessage;
    } 

    
    /**
     * getCredits() - gets credits as an integer
     *
     * 
     * @return    number of credits at time of call as an integer
     */
    public int getCredits(){
        return credits;
    } 

    
    /**
     * getDice() - gets array of dice
     *
     * 
     * @return    array of dice at time of call
     */
    public GVdie[] getDice(){
        return dice; 
    }

    
    /**
      * Start setter methods
     */
    
    
    
    /**
     * addCredits() - adds amount of credits to game at that time
     *
     *@param    amount - amount of credits to add
     */
    public void addCredits (int amount){
        credits+=amount;
    }

    
    /**
     * placeBet() - places a bet at a given index
     *
     *@param    bet - bet wanted to be placed from one to nine 
     */
    public void placeBet (int bet){
        
        if(bet-1 >=0 && bet-1<=8){
            bets[bet-1] = true;
        }
        
    }

    
    /**
     * cancelBet() - cancels a bet at a given index
     *
     *@param    bet - bet wanted to be canceled from one to nine 
     */
    public void cancelBet (int bet){
        
        if(bet-1 >=0 && bet-1<=8){
            bets[bet-1] = false;
        }
        
    }

    
    /**
     * clearAllBets() - clears all bets from the array of bets
     *
     *@param    bet - bet wanted to be canceled from one to nine 
     */
    public void clearAllBets(){
        
        for(int count = 0; count<bets.length; count++){
            bets[count] = false;
        }
        
    }

    
    /**
     * roll() - rolls the dice, checks all bets then sees which ones won,
     * and clears the bets
     *
     */
    public void roll(){
        if(enoughCredits()){
            
            for(int count = 0; count<dice.length; count++){
                dice[count].roll();
            }
            
            checkAllBets();
            clearAllBets();
        }
        
        
        else{
            currentMessage = "Not enough credits";
            
            for(int count = 0; count<dice.length; count++){
                dice[count].setBlank();
            }
            
        }

    }

    
    /**
     * reset() - resets the game, sets credits to 10, message to be blank,
     * and clears all bets
     *
     */
    public void reset(){
        for(int count = 0; count<dice.length; count++){
            dice[count].setBlank();
        }
        currentMessage = "";
        credits = 10;
        clearAllBets();
    }

    
    /**
      * Start method that prevent player errors
     */
    
    
    /**
     * enoughCredits() - clears all bets from the array of bets
     *
     *@return    boolean, whether there is enough credits or not to roll
     */
    public boolean enoughCredits(){
        int neededCredits = 0;
        for(int count = 0; count<bets.length; count++){
            
            if (bets[count] == true){
                neededCredits++;
            }
            
        }

        if(neededCredits> credits){
            return false;
        }

        
        else {
            return true;
        }
        
    }

    
    /**
      * Start methods that help in testing
     */
    
    
    /**
     * diceToString() - goes through array of dice, then displays or 
     * returns values in a string
     *
     *@return    string of dice separated by commas ex: 2,3,5
     */
    public String diceToString(){
        String returned = "";
        for(int count = 0; count<dice.length; count++){
            returned+=Integer.toString(dice[count].getValue());
            
            
            if(count>=0 && count<dice.length-1){
                returned+=",";
            }
            
            
        }

        return returned;

    }

    
    /**
     * testRoll() - goes through array of dice, getting each die to equal 
     * the right value in the values array.
     *
     *@param int[] values, an array of needed values for the 
     *testing of the game. If array has neg or 
     *value greater than 6, value becomes 1.
     */
    public void testRoll (int[] values){

        for(int count = 0; count< values.length; count++){
            
            if (values[count]>6|| values[count]<1){
            values[count] = 1;
            }
            
            
            while(values[count] != dice[count].getValue()){
                dice[count].roll();
            }
            
        }

        checkAllBets();
        clearAllBets();
    }
    
    
    /**
      * Start private methods that help for the game
     */
    
    
    /**
     * getSumDiceValues() - goes through array of dice, getting value of 
     * each die, all added to an integer called sum
     *
     *@return integer called sum, created in the method, and added to
     */
    private int getSumDiceValues(){
        int sum = 0;
        
        for(int count = 0; count< dice.length; count++){
            sum+=dice[count].getValue();
        }

        return sum;    
    }

    
    /**
     * isDoubles() - goes through array of dice, getting value of 
     * each die and seeing if it is equal to the number input
     *
     *@param number that is needed to see if there is a duplicate for in 
     *dice array
     *
     *@return boolean - true if two or more dice are equal, false if not
     */
    private boolean isDoubles(int num){
        boolean equals = false;   
        for (int count = 0; count<dice.length; count++){
            
            
            for (int count2 = count+1; count2<dice.length; count2++){
            if((dice[count].getValue()== num && 
            dice[count2].getValue() ==num) ){
            equals =  true;
                }
                
                
            }
        
        
        }
        
        
        return equals;
    }
    
    
    /**
     * isTriplets() - goes through array of dice, getting value of 
     * each die, seeing if all three dice are equal
     *
     *
     *
     *@return boolean - true if three dice are equal, false if not
     */
    private boolean isTriplets(){
        boolean get = true;
        
        for(int count = 0; count< dice.length-2; count++){
            
            if(dice[count].getValue()== dice[count+1].getValue() 
            && dice[count+1].getValue()== dice[count+2].getValue() 
            && dice[count].getValue() == dice[count+2].getValue() ){
                get =  true;
            }
            
            
            else {
                get =  false;
            }
            
        }
        
        return get;
    }

    
    /**
     * checkLarge() - uses getSumOfDice() method to see if the values
     * are greater than 10, uses the method isTriplets
     * as all 3 dice cannot be the same
     *
     *
     *
     */
    public void checkLarge(){
        
        if(getSumDiceValues()>10 && !isTriplets()){
            addCredits(2);
            currentMessage = "Player won!";
        }

    }
        
    
    /**
     * checkSmall() - uses getSumOfDice() method to see if the values
     * are less than 11, uses the method isTriplets
     * as all 3 dice cannot be the same
     *
     *
     */
    public void checkSmall(){
        
        if(getSumDiceValues()<11 && !isTriplets()){
            addCredits(2);
            currentMessage = "Player won!";
        }

    }

    
    /**
     * checkField() - uses getSumOfDice() method to see if the values
     * are less than 8 or greater than 12
     *
     *
     */
    public void checkField(){
        
        if((getSumDiceValues()<8 || getSumDiceValues()>12)){
            addCredits(2);
            currentMessage = "Player won!";
        }
        
    }

    
    /**
     * checkNumber() - uses isDoubles() and isTriplets() 
     * methods to determine how many dice exactly are
     * equal to the given number
     * 
     * @param num - the numerical value for the dice 
     * that has to be compared with a bet or
     * in the case of testing, seeing 
     * if a tested rolled array of dice has
     * a given number.
     *
     *
     */
    public void checkNumber(int num){

        if(isTriplets()&& dice[0].getValue()== num){
            addCredits(11);

            currentMessage = "Player won!";
        } 
        
        
        else if(isDoubles(num)){
            addCredits(3);

            currentMessage = "Player won!";
        } 
        
        
        else if(dice[0].getValue() == num || dice[2].getValue()==num 
        || dice[1].getValue()==num){
            addCredits(2);
            currentMessage = "Player won!";
        }
        
    }

    
    /**
     * checkAllBets() - calls the various methods corresponding
     * to the bets, it runs in a for loop and if a bet or
     * combination of bets is true, than that given bet 
     * is executed. A series of if statements 
     * calls the checkNumber() with the bet overall
     * and corresponding number range 
     * with the bet, checkField() is called after the numbers and if 
     * selected as a bet, and the same goes for 
     * both checkSmall()
     * and checkLarge()
     * 
     *
     *
     */
    public void checkAllBets(){
        currentMessage = "Player lost.";
        for (int count = 0; count<bets.length; count++){
            
            if((count >=0 && count<= 5) && bets[count]==true){                
                checkNumber(count+1);
                addCredits(-1);
            } 
            
            
            else if(count == 6 && bets[count]==true){
                checkField();
                addCredits(-1);
            }
            
            
            else if(count == 7 && bets[count]==true){                
                checkSmall();
                addCredits(-1);
            }
            
            
            else if(count == 8 &&bets[count]==true){
                checkLarge();
                addCredits(-1);
            }
            
        }
        
        
    }
    
    
}
