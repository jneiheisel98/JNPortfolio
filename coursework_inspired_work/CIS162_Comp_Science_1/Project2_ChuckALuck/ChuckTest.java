
/**
 * This is mainly to test the Chuck class methods and procedures
 * so that the tests for getting certain numbers for 
 * the die can be done or there can be determinations
 * of the sums of the die for various chances to win
 * the game and have functionality.
 *
 * @author Jacob Neiheisel
 * @forclass Computer Science 1 - CIS 162 Section 09, Professor Posada
 * @version 1.0.4 - 03 November 2020
 */
public class ChuckTest {

    public static void main(){

        Chuck chk = new Chuck();
        int before = 0;
        final int ONES = 1;
        final int TWOS = 2;
        final int THREES = 3;
        final int FOURS = 4;
        final int FIVES = 5;
        final int SIXES = 6;
        final int FIELD = 7;
        final int SMALL = 8;
        final int LARGE = 9;
        Chuck game = new Chuck();
        System.out.println("Testing begins...");
        assert game.getCredits() == 10 : "credits should start at 10";   

        // wins bet on Large
        before = game.getCredits();
        game.placeBet(LARGE);
        game.testRoll(new int [] {6,3,3});    
        assert game.getCredits() == before-1 + 2 : "should have won betting on Large";
        System.out.println("won betting on Large");
        
        // loses bet on Large
        before = game.getCredits();
        game.placeBet(LARGE);
        game.testRoll(new int [] {2,3,3});       
        assert game.getCredits() == before - 1 : "should have lost betting on Large";
        before = game.getCredits();
        System.out.println("lost betting on Large");
        
        //win bet on small
        before = game.getCredits();
        game.placeBet(SMALL);
        game.testRoll(new int [] {2,3,3});       
        assert game.getCredits() == before-1 + 2 : "should have won betting on small";
        System.out.println("won bet on small");
        
        //win bet on small
        before = game.getCredits();
        game.placeBet(SMALL);
        game.placeBet(FIELD);
        game.testRoll(new int [] {2,3,3});       
        assert game.getCredits() == before-2 + 2 : "should have won betting on" +
        " small, losing on field";
        System.out.println("won bet on small, lost field");
        
        //lose bet on small
        before = game.getCredits();
        game.placeBet(SMALL);
        game.testRoll(new int [] {3,3,3});       
        assert game.getCredits() == before - 1 : "should have lost betting" +
        " on small";
        System.out.println("lost bet on small");
        
        // win bet on field
        before = game.getCredits();
        game.placeBet(FIELD);
        game.testRoll(new int [] {3,3,3});       
        assert game.getCredits() == before-1 : "should have lost betting" 
        + " on field";
        System.out.println("lost bet on field");
        
        //win bet one field and large
        before = game.getCredits();
        game.placeBet(FIELD);
        game.placeBet(LARGE);
        game.testRoll(new int [] {3,4,6});       
        assert game.getCredits() == before-2 + 2+2 : "should have" +
        " won betting on field and large";
        System.out.println("won bet on field and large");

        //lose small, lose field
        before = game.getCredits();
        game.placeBet(FIELD);
        game.placeBet(SMALL);
        game.testRoll(new int [] {3,3,3});       
        assert game.getCredits() == before-2 : "should have" +
        " lost betting on small and field";
        System.out.println("lost bet on field and small");
        
        //
        //
        //threes bets
        //
        //
        //
        
        //lose Large, win threes
        before = game.getCredits();
        game.placeBet(LARGE);
        game.placeBet(THREES);
        game.testRoll(new int [] {2,3,3});       
        assert game.getCredits() == before - 2 +3 : "should have lost" + 
        "betting on Large won betting on 3s";
        before = game.getCredits();
        System.out.println("lose bet on large, win on threes");

        // wins bet on Large and threes
        before = game.getCredits();
        game.placeBet(LARGE);
        game.placeBet(THREES);
        game.testRoll(new int [] {6,3,3});    
        assert game.getCredits() == before-2 + 2 + 3 : "should have" +
        " won betting on Large and threes";

        // wins bet on Large and threes and sixes
        before = game.getCredits();
        game.placeBet(LARGE);
        game.placeBet(THREES);
        game.placeBet(SIXES);
        game.testRoll(new int [] {6,3,3});    
        assert game.getCredits() == before-3 + 2 + 3 + 2: "should have won" +
        " betting on Large, Threes, Sixes";
        System.out.println("win bet on threes, large, and sixes");
               
        //win bet on small and threes
        before = game.getCredits();
        game.placeBet(SMALL);
        game.placeBet(THREES);
        game.testRoll(new int [] {2,3,3});       
        assert game.getCredits() == before -2+ 2 +3 : "should have" +
        " won betting on small and threes";

        //win bets on large, threes, and sixes
        before = game.getCredits();
        game.placeBet(LARGE);
        game.placeBet(SIXES);
        game.placeBet(THREES);
        game.testRoll(new int [] {6,3,3});    
        assert game.getCredits() == before-3 + 2+2+3 : "should have won" + 
        "betting on Larges, threes, and sixes";

        //win on Large and threes, lose on fives
        before = game.getCredits();
        game.placeBet(LARGE);
        game.placeBet(FIVES);
        game.placeBet(THREES);
        game.testRoll(new int [] {6,3,3});    
        assert game.getCredits() == before-3 +2+3 : "should have" +
        " won betting on Large and threes, losing on fives";

        //
        //
        //fours bets
        //
        //
        //
        
        //lose on Large, win on fours
        before = game.getCredits();
        game.placeBet(LARGE);
        game.placeBet(FOURS);
        game.testRoll(new int [] {1,4,4});       
        assert game.getCredits() == before - 2 + 3 : "should have lost" + 
        "betting on Large won betting on Fours";
        before = game.getCredits();

        // wins bet on Large and fours
        before = game.getCredits();
        game.placeBet(LARGE);
        game.placeBet(FOURS);
        game.testRoll(new int [] {4,4,3});    
        assert game.getCredits() == before - 2 + 3 + 2 : "should have" +
        " won betting on Large and Fours";

        // wins bet on Large, fours, sixes, field, losing on twos
        before = game.getCredits();
        game.placeBet(LARGE);
        game.placeBet(FOURS);
        game.placeBet(SIXES);
        game.placeBet(TWOS);
        game.placeBet(FIELD);
        game.testRoll(new int [] {4,3,6});    
        assert game.getCredits() == before - 5 + 2 + 2 + 2 + 2: "should have " +
        "won betting on Large and Fours, Sixes, and Field with losing on Twos";
        System.out.println("won bets on larges, fours, sixes, and field" + 
        " lost on twos");
               
        //win bet on small and fours
        before = game.getCredits();
        game.placeBet(SMALL);
        game.placeBet(FOURS);
        game.testRoll(new int [] {4,2,1});       
        assert game.getCredits() == before- 2 + 2 + 2 : "should have won" + 
        " betting on small and fours";
        System.out.println("won bets on small and fours");

        //win bets on fours, lose on fives and large
        before = game.getCredits();
        game.placeBet(LARGE);
        game.placeBet(FIVES);
        game.placeBet(FOURS);
        game.testRoll(new int [] {4,4,4});    
        assert game.getCredits() == before -3 + 11 : "should have won betting" +
        " on Fours, losing on fives and large";

        //win bets on fours, lose on larges, fives, and field
        before = game.getCredits();
        game.placeBet(LARGE);
        game.placeBet(FIVES);
        game.placeBet(FOURS);
        game.placeBet(FIELD);
        game.testRoll(new int [] {4,4,4});    
        assert game.getCredits() == before -4 + 11 : "should have won betting" + 
        " on Fours, losing on Large, Fives, and Field";
        System.out.println("won betting on fours, lose on large," + 
        " fives, and field");
        
       //win bets on larges and fours, lose on fives
        before = game.getCredits();
        game.placeBet(LARGE);
        game.placeBet(FIVES);
        game.placeBet(FOURS);
        game.testRoll(new int [] {4,4,6});    
        assert game.getCredits() == before - 3 + 2 + 3 : "should have won betting" +
        " on Large and Fours, losing on fives";

        //win bets on larges and fours and field, lose on fives
        before = game.getCredits();
        game.placeBet(LARGE);
        game.placeBet(FIVES);
        game.placeBet(FOURS);
        game.placeBet(FIELD);
        game.testRoll(new int [] {4,4,6});    
        assert game.getCredits() == before - 4 + 2 + 3 + 2 : "should have won betting" +
        " on Large, Fours, Field, losing on Fives";

        //win bets on small, fours, and field, lose on fives
        before = game.getCredits();
        game.placeBet(SMALL);
        game.placeBet(FIVES);
        game.placeBet(FOURS);
        game.placeBet(FIELD);
        game.testRoll(new int [] {4,2,4});    
        assert game.getCredits() == before - 4 + 2 + 3 : "should have won betting" +
        " on Small and Fours, losing on Fives and Fields";

        //win bets on small and fours and field, lose on sixes
        before = game.getCredits();
        game.placeBet(SMALL);
        game.placeBet(FOURS);
        game.placeBet(SIXES);
        game.placeBet(FIELD);
        game.testRoll(new int [] {4,2,1});    
        assert game.getCredits() == before - 4 + 2 + 2 + 2 : "should have won betting" +
        " on Small, Fours, and Field, losing on Sixes";

        //win bets on small, fours, twos, ones, and field
        before = game.getCredits();
        game.placeBet(SMALL);
        game.placeBet(FOURS);
        game.placeBet(TWOS);
        game.placeBet(ONES);
        game.placeBet(FIELD);
        game.testRoll(new int [] {4,2,1});    
        assert game.getCredits() == before - 5 + 2 + 2 + 2 + 2 + 2: "should have won betting" +
        " on Small, Fours, Twos, Ones, Field";
        System.out.println("won betting on small, fours, twos, ones, and field");

        //
        //
        //
        //fives bets
        //
        //
        //
        
        //win on fives, lose on large
        before = game.getCredits();
        game.placeBet(LARGE);
        game.placeBet(FIVES);
        game.testRoll(new int [] {5,5,5});       
        assert game.getCredits() == before - 2 + 11 : "should have lost" + 
        "betting on Large won betting on fives";
        System.out.println("Won on fives, lost on larges");
        
        //lost on large, won on field
        before = game.getCredits();
        game.placeBet(LARGE);
        game.placeBet(FIELD);
        game.testRoll(new int [] {5,5,5});       
        assert game.getCredits() == before - 2 + 2 : "should have lost" + 
        "betting on Large, won betting on field";
        before = game.getCredits();

        // wins bet on fives, lose on large
        before = game.getCredits();
        game.placeBet(LARGE);
        game.placeBet(FIVES);
        game.testRoll(new int [] {5,3,1});    
        assert game.getCredits() == before - 2 + 2 : "should have won betting" +
        " on Fives, losing on large";

        // wins bet on Large, fives, sixes, and field, losing on twos
        before = game.getCredits();
        game.placeBet(LARGE);
        game.placeBet(FIVES);
        game.placeBet(SIXES);
        game.placeBet(TWOS);
        game.placeBet(FIELD);
        game.testRoll(new int [] {5,3,6});    
        assert game.getCredits() == before - 5 + 2 + 2 + 2 + 2: "should have won betting" +
        " on Large, Fives, Sixes, and Field, losing on twos";
        System.out.println("Won betting on large, fives, sixes, and field" +
        ", losing on twos.");

        //win bet on small and fives
        before = game.getCredits();
        game.placeBet(SMALL);
        game.placeBet(FIVES);
        game.testRoll(new int [] {5,2,1});       
        assert game.getCredits() == before - 2 + 2 + 2 : "should have won betting on" +
        " Small and Fives";

        //win bet on Small and Fives, losing on field
        before = game.getCredits();
        game.placeBet(SMALL);
        game.placeBet(FIVES);
        game.placeBet(FIELD);
        game.testRoll(new int [] {5,2,1});       
        assert game.getCredits() == before - 3 + 2 + 2 : "should have won betting" +
        " on Small and Fives, losing on field";

        // win bets on Small, Fives, twos, lose on Field and Threes
        before = game.getCredits();
        game.placeBet(SMALL);
        game.placeBet(FIVES);
        game.placeBet(TWOS);
        game.placeBet(THREES);
        game.placeBet(FIELD);
        game.testRoll(new int [] {5,2,1});       
        assert game.getCredits() == before - 5 + 2 + 2 + 2 : "should have" +
        " won betting on Small, Fives, Twos, losing on Field and Threes";

        //win bets on Small, fives, twos, ones, and losing on field
        before = game.getCredits();
        game.placeBet(SMALL);
        game.placeBet(FIVES);
        game.placeBet(TWOS);
        game.placeBet(ONES);
        game.placeBet(FIELD);
        game.testRoll(new int [] {5,2,1});       
        assert game.getCredits() == before - 5 + 2 + 2 + 2 + 2 : "should have" + 
        " won betting on Small, Fives, Twos, Ones, losing on Field";

        //win bets on larges, fives, and fours
        before = game.getCredits();
        game.placeBet(LARGE);
        game.placeBet(FIVES);
        game.placeBet(FOURS);
        game.testRoll(new int [] {5,5,4});    
        assert game.getCredits() == before - 3 + 2 + 3 + 2 : "should have won" +
        " betting on Large, Fives, and Fours";
        System.out.println("Won betting on large, fives, fours");
        
        //win bets on larges and Fives, losing on fours
        before = game.getCredits();
        game.placeBet(LARGE);
        game.placeBet(FIVES);
        game.placeBet(FOURS);
        game.testRoll(new int [] {5,5,6});    
        assert game.getCredits() == before - 3 + 2 + 3 : "should have won betting" +
        " on Large and Fives, losing on Fours";
        
        //
        //
        //six bets
        //
        //
        //
        
        //lose on Large, win on sixes
        before = game.getCredits();
        game.placeBet(LARGE);
        game.placeBet(SIXES);
        game.testRoll(new int [] {2,6,6});       
        assert game.getCredits() == before - 2 + 3 + 2 : "should have lost" + 
        "betting on Large won betting on Sixes";
        System.out.println("lost betting on large, won on sixes");
        

        // wins bet on sixes, lose bet on large
        before = game.getCredits();
        game.placeBet(LARGE);
        game.placeBet(SIXES);
        game.testRoll(new int [] {6,6,6});    
        assert game.getCredits() == before - 2 + 11 : "should have won betting" +
        " on sixes and lost on large";

        // wins bet on sixes and field
        before = game.getCredits();
        game.placeBet(FIELD);
        game.placeBet(SIXES);
        game.testRoll(new int [] {6,6,6});    
        assert game.getCredits() == before - 2 + 11 + 2 : "should have won" +
        " betting on Sixes and Field";
        System.out.println("Won bets on Sixes and field");

        // wins bet on Large, fours, sixes, field, lose on twos
        before = game.getCredits();
        game.placeBet(LARGE);
        game.placeBet(FOURS);
        game.placeBet(SIXES);
        game.placeBet(TWOS);
        game.placeBet(FIELD);
        game.testRoll(new int [] {4,3,6});    
        assert game.getCredits() == before - 5 + 2 + 2 + 2 + 2: "should have won " +
        " betting on Large, Fours,  Sixes, and Field, losing on Twos";

        //win bets on large, fours, threes, sixes, and field
        before = game.getCredits();
        game.placeBet(LARGE);
        game.placeBet(FOURS);
        game.placeBet(SIXES);
        game.placeBet(THREES);
        game.placeBet(FIELD);
        game.testRoll(new int [] {4,3,6});    
        assert game.getCredits() == before - 5 + 2 + 2 + 2 + 2 + 2: "should have won" + 
        " betting on Large, Fours, Threes, Sixes, and Field";
        System.out.println("won bets on large, fours, threes, sixes, and field");

               
        //win bet on small and sixes
        before = game.getCredits();
        game.placeBet(SMALL);
        game.placeBet(SIXES);
        game.testRoll(new int [] {6,2,1});       
        assert game.getCredits() == before - 2 + 2 + 2 : "should have won betting" +
        " on Small and Sixes";

        //win bets on large, fives, and sixes
        before = game.getCredits();
        game.placeBet(LARGE);
        game.placeBet(FIVES);
        game.placeBet(SIXES);
        game.testRoll(new int [] {5,6,6});    
        assert game.getCredits() == before - 3 + 2 + 2 + 3 : "should have won betting" + 
        "on Large, Fives, and Sixes";

        //win bets on large, fours, and lose on fives
        before = game.getCredits();
        game.placeBet(LARGE);
        game.placeBet(FIVES);
        game.placeBet(FOURS);
        game.testRoll(new int [] {4,6,6});    
        assert game.getCredits() == before - 3 + 2 + 2 : "should have won betting" +
        " on Large, Fours, and losing on Fives";
        
        //
        //
        //one bets
        //
        //
        //
        
        //lose on large, win on ones
        before = game.getCredits();
        game.placeBet(LARGE);
        game.placeBet(ONES);
        game.testRoll(new int [] {2,1,1});       
        assert game.getCredits() == before - 2 + 3 : "should have lost" + 
        "betting on Large, winning on ones";
        System.out.println("lost betting on Large, won betting on ones");

        // wins bet on field and ones
        before = game.getCredits();
        game.placeBet(ONES);
        game.placeBet(FIELD);
        game.testRoll(new int [] {1,1,1});    
        assert game.getCredits() == before - 2 + 11 + 2 : "should have won betting" +
        " on Field and Ones";

        // wins bet on field and ones
        before = game.getCredits();
        game.placeBet(ONES);
        game.placeBet(FIELD);
        game.testRoll(new int [] {1,1,2});    
        assert game.getCredits() == before - 2 + 3 + 2 : "should have won betting" +
        " on Ones and Field";
        System.out.println("Won betting on ones and field");

        // wins bet on ones and lose on small
        before = game.getCredits();
        game.placeBet(ONES);
        game.placeBet(SMALL);
        game.testRoll(new int [] {1,1,1});    
        assert game.getCredits() == before - 2 + 11 : "should have won betting" +
        " on Ones, losing on Small";

        // wins bet on large, fours, ones, lose on field and twos
        before = game.getCredits();
        game.placeBet(LARGE);
        game.placeBet(FOURS);
        game.placeBet(ONES);
        game.placeBet(TWOS);
        game.placeBet(FIELD);
        game.testRoll(new int [] {1,4,6});    
        assert game.getCredits() == before - 5 + 2 + 2 + 2: "should have won" +
        "betting on Large, Fours, Ones, losing on Field and Twos";
        System.out.println("lost betting on Field and twos" +
        ", won betting on ones, fours, and larges");
               
        //win bet on small and ones
        before = game.getCredits();
        game.placeBet(SMALL);
        game.placeBet(ONES);
        game.testRoll(new int [] {4,2,1});       
        assert game.getCredits() == before - 2 + 2 + 2 : "should have won" +
        " betting on Small and Ones";

        //win on small, fives, and ones
        before = game.getCredits();
        game.placeBet(SMALL);
        game.placeBet(FIVES);
        game.placeBet(ONES);
        game.testRoll(new int [] {5,1,1});    
        assert game.getCredits() == before - 3 + 2 + 2 + 3 : "should have won betting" + 
        "on Small, Fives, and ones";
        System.out.println("Won betting on small, fives, and ones");

        //win bets on ones, lose on large, fives, and twos
        before = game.getCredits();
        game.placeBet(LARGE);
        game.placeBet(FIVES);
        game.placeBet(ONES);
        game.placeBet(TWOS);
        game.testRoll(new int [] {1,1,6});    
        assert game.getCredits() == before - 4 + 3 : "should have won" +
        " betting on Ones, losing on Large, Fives, and Twos";
        
        //win bets on ones, lose bets on large, fives, and twos
        before = game.getCredits();
        game.placeBet(LARGE);
        game.placeBet(FIVES);
        game.placeBet(ONES);
        game.placeBet(TWOS);
        game.testRoll(new int [] {1,7,6});    
        assert game.getCredits() == before - 4 + 3 : "should have won" +
        " betting on Ones, losing on Large, Fives, and Twos";
        
        //wib bets on ones, lose on large, fives, and twos
        before = game.getCredits();
        game.placeBet(LARGE);
        game.placeBet(FIVES);
        game.placeBet(ONES);
        game.placeBet(TWOS);
        game.testRoll(new int [] {1,-1,6});    
        assert game.getCredits() == before- 4 + 3 : "should have won" +
        " betting on Ones, losing on Large, Fives, and Twos";
                
        //
        //
        //two bets
        //
        //
        //
        
        before = game.getCredits();
        game.placeBet(LARGE);
        game.placeBet(TWOS);
        game.testRoll(new int [] {2,2,4});       
        assert game.getCredits() == before - 2 +3 : "should have lost" + 
        "betting on Large won betting on Twos";
        before = game.getCredits();
        System.out.println("lost betting on large, won betting on twos");

        // wins bet on Large and twos
        before = game.getCredits();
        game.placeBet(LARGE);
        game.placeBet(TWOS);
        game.testRoll(new int [] {2,2,2});    
        assert game.getCredits() == before -2 + 11 : "should have won" +
        " betting on Twos, losing on Large";
        System.out.println("lost betting on large, won betting on twos");
        
        // wins bet on Large, six, twos, losing on fours and field
        before = game.getCredits();
        game.placeBet(LARGE);
        game.placeBet(FOURS);
        game.placeBet(SIXES);
        game.placeBet(TWOS);
        game.placeBet(FIELD);
        game.testRoll(new int [] {2,3,6});    
        assert game.getCredits() == before - 5 + 2 + 2 + 2 : "should have won" +
        " betting on Sixes, Twos, and Large, losing on Fours and Field";
        System.out.println("Won betting on sixes, twos, and large, losing" +
        "on fours and field");
               
        //win bet on small and twos
        before = game.getCredits();
        game.placeBet(SMALL);
        game.placeBet(TWOS);
        game.testRoll(new int [] {4,2,1});       
        assert game.getCredits() == before -2 + 2 + 2 : "should have won betting" +
        " on Small and Twos";

        //win bets on large, twos, and fours
        before = game.getCredits();
        game.placeBet(LARGE);
        game.placeBet(TWOS);
        game.placeBet(FOURS);
        game.testRoll(new int [] {2,6,4});    
        assert game.getCredits() == before - 3 + 2 + 2 + 2 : "should have won betting" +
        " on Large, Twos, and Fours";
        System.out.println("Won betting on large, twos, and fours");

        //win bets on small and twos
        before = game.getCredits();
        game.placeBet(SMALL);
        game.placeBet(TWOS);
        game.testRoll(new int [] {2,2,6});    
        assert game.getCredits() == before - 2 + 2 + 3 : "should have won" +
        " betting on Small and Twos";

        //win bets on small, twos, and field
        before = game.getCredits();
        game.placeBet(SMALL);
        game.placeBet(TWOS);
        game.placeBet(FIELD);
        game.testRoll(new int [] {2,2,1});    
        assert game.getCredits() == before - 3 + 2 + 3 + 2  : "should have won" +
        " Small, Twos, and Field";
        System.out.println("Won betting on small, twos, and field");

        //win bets on twos and field, losing on small
        before = game.getCredits();
        game.placeBet(SMALL);
        game.placeBet(TWOS);
        game.placeBet(FIELD);
        game.testRoll(new int [] {2,2,2});    
        assert game.getCredits() == before - 3 + 11 + 2 : "should have won" +
        " betting on Twos and Field, losing on small";

        //win bets on large, twos, and field
        before = game.getCredits();
        game.placeBet(LARGE);
        game.placeBet(TWOS);
        game.placeBet(FIELD);
        game.testRoll(new int [] {2,6,6});    
        assert game.getCredits() == before - 3 + 2 + 2 + 2 : "should have won" + 
        " betting on Large, Twos, and Field";

        System.out.println("Testing completed.");

    }
}
