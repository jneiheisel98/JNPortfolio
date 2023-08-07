
/**
 * GVdateTest - tests default constructor and a set month, day,
 * and year constructor. Also tests various setter and getter methods.
 * 
 *
 * @author Jacob Neiheisel
 * @for_class CIS 162 - Computer Science I - Section 09 - Professor Posada
 * @version 1.0.1 - 1 October 2020 (around 45 mins to complete and format P1)
 */

public class GVdateTest
{
    public static void main (String []  args) {
        int errors = 0;
        System.out.println ("Testing begins");

        //********** phase 1 testing ************

        
        // testing the default constructor - for month, date, and year
        GVdate today = new GVdate();
        if (today.getMonth() != 10){
            System.out.println("month should be 10");
            errors++;
        }
        
        if (today.getDay() != 12){
            System.out.println("day should be 12");
            errors++;
        }
        
        if (today.getYear() != 2020){
            System.out.println("year should be 2020");
            errors++;
        }
        
       
    

        // testing constructor 2 - using non-default constructor
        GVdate theDay = new GVdate(1, 10, 1995);
        
        if (theDay.getMonth() != 1){
            System.out.println("month should be 1");
            errors++;
        }
        
        if (theDay.getDay() != 10){
            System.out.println("day should be 10");
            errors++;
        }
        
        if (theDay.getYear() != 1995){
            System.out.println("year should be 1995");
            errors++;
        }
        
       

        // testing setter methods 
        //testing setMonth
        theDay.setMonth(8);
        
        if (theDay.getMonth() != 8){
            System.out.println("month should be 8");
            errors++;
        }
        
        //testing setDay
        theDay.setDay(10);
        
        if (theDay.getDay() != 10){
            System.out.println("day should be 10");
            errors++;
        }
        
        //testing setYear
        theDay.setYear(2022);
        
        if (theDay.getYear() != 2022){
            System.out.println("year should be 2022");
            errors++;
        }
        
        //testing toString
        
        if (theDay.toString().equals("8/10/2022")){
            errors = errors;
        }
        
        
        else {
            System.out.println("string should be 8/10/2022");
            errors++;
        }
        
        
        //test isLeapYear
        if(theDay.isLeapYear(2022)){
            errors++;
        }

        
        if(theDay.isLeapYear(2024)){
            errors = errors;
        } 
        
        
        else{
            errors++;
        }
        
        if(theDay.isLeapYear(1700)){
            errors++;
        } 
        
        if(theDay.isLeapYear(2000)){
            errors = errors;
        } 
        
        else{
            errors++;
        }
        
        if(theDay.isLeapYear(1600)){
            errors = errors;
        } 
        
        else{
            errors++;
        }
        
        if(theDay.isLeapYear(852)){
            errors = errors;
        } 
        else{
            errors++;
        }
        
        if(theDay.isLeapYear(936)){
            errors = errors;
        } 
        
        else{
            errors++;
        }
        
        if(theDay.isLeapYear(1008)){
            errors = errors;
        } 
        
        else{
            errors++;
        }
        
        if(theDay.isLeapYear(1249)){
            errors++;
        } 
        
        if(theDay.isLeapYear(1346)){
            errors++;
        }
        
        if(theDay.isLeapYear(1447)){
            errors++;
        }
        
        if(theDay.isLeapYear(1458)){
            errors++;
        }
        
        if(theDay.isLeapYear(1333)){
            errors++;
        }
        
        if(theDay.isLeapYear(2001)){
            errors++;
        }
        
        if(theDay.isLeapYear(2102)){
            errors++;
        }
        
        
        
        //testing GVdate constructor with string
        //also test invalid dates
        GVdate dateStr = new GVdate("-1/6/2007");
        GVdate dateStrDay = new GVdate("-1/-6/2007");
        GVdate dateStrYear = new GVdate("-1/-6/-2007");
        GVdate dateStrDayYear = new GVdate("1/-6/-2007");
        GVdate dateStrDayOnly = new GVdate("1/-6/2007");
        GVdate dateStrYearOnly = new GVdate("1/6/-2007");
        if(dateStr.toString().equals("10/12/2020")){
            errors = errors;
        }
        
        else {
            errors++;
        }
        
        if(dateStrDay.toString().equals("10/12/2020")){
            errors = errors;
        }
        
        else {
            errors++;
        }
        
        if(dateStrYear.toString().equals("10/12/2020")){
            errors = errors;
        }
        
        else {
            errors++;
        }
        
        if(dateStrDayYear.toString().equals("10/12/2020")){
            errors = errors;
        }
        
        else {
            errors++;
        }
        
        if(dateStrDayOnly.toString().equals("10/12/2020")){
            errors = errors;
        }
        
        else {
            errors++;
        }
        
        if(dateStrYearOnly.toString().equals("10/12/2020")){
            errors = errors;
        }
        else {
            errors++;
        }
        
        
        //test GV date with new fail preventers
        
        
        GVdate dateStr1 = new GVdate(1,36,2007);
        GVdate dateStrDay1 = new GVdate(2,29,2008);
        GVdate dateStrYear1 = new GVdate(2,29,2010);
        GVdate dateStrDayYear1 = new GVdate(3,36,2007);
        GVdate dateStrDayOnly1 = new GVdate(5,30,2007);
        GVdate dateStrYearOnly1 = new GVdate(1,32,2017);
        GVdate yr1 = new GVdate(1,6,2017);
        GVdate yr2 = new GVdate(-1,-6,-2017);
        GVdate yr3 = new GVdate(-1,-6,2017);
        GVdate yr4 = new GVdate(1,-6,-2017);
        GVdate yr5 = new GVdate(-1,6,2017);
        GVdate yr6 = new GVdate(1,-6,2017);
        GVdate yr7 = new GVdate(1,6,-2017);
        GVdate yr8 = new GVdate(6,30,2020);
        
        if(dateStr1.toString().equals("10/12/2020")){
            errors = errors;
        }
        
        else {
            errors++;
        }
        
        if(dateStrDay1.toString().equals("2/29/2008")){
            errors = errors;
        }
        
        else {
            errors++;
        }
        
        if(dateStrYear1.toString().equals("10/12/2020")){
            errors = errors;
        }
        
        else {
            errors++;
        }
        
        if(dateStrDayYear1.toString().equals("10/12/2020")){
            errors = errors;
        }
        else {
            errors++;
        }
        
        if(dateStrDayOnly1.toString().equals("5/30/2007")){
            errors = errors;
        }
        
        else {
            errors++;
        }
        
        if(dateStrYearOnly1.toString().equals("10/12/2020")){
            errors = errors;
        }
        
        else {
            errors++;
        }
        
        if(yr1.toString().equals("1/6/2017")){
            errors = errors;
        }
        
        else {
            errors++;
        }
        
        if(yr2.toString().equals("10/12/2020")){
            errors = errors;
        }
        
        else {
            errors++;
        }
        
        if(yr3.toString().equals("10/12/2020")){
            errors = errors;
        }
        
        else {
            errors++;
        }
        
        if(yr4.toString().equals("10/12/2020")){
            errors = errors;
        }
        
        else {
            errors++;
        }
        
        if(yr5.toString().equals("10/12/2020")){
            errors = errors;
        }
        
        else {
            errors++;
        }
        
        if(yr6.toString().equals("10/12/2020")){
            errors = errors;
        }
        
        else {
            errors++;
        }
        
        if(yr7.toString().equals("10/12/2020")){
            errors = errors;
        }
        
        else {
            errors++;
        }
        
        if(yr8.toString().equals("6/30/2020")){
            errors = errors;
        }
        
        else {
            errors++;
        }
        
      
        
       //test setters
       
       yr8.setMonth(8);
       if(yr8.toString().equals("8/30/2020")){
           errors = errors;
       }
       
       else {
           errors++;
       }
       yr8.setDay(32);
       
       if(yr8.toString().equals("8/32/2020")){
           errors++;
       }
        
       yr8.setDay(3);
       if(yr8.toString().equals("8/3/2020")){
           errors = errors;
       }
       
       else {
           errors++;
       }
       yr8.setMonth(2);
       
       if(yr8.toString().equals("2/30/2020")){
           errors++;
        }
    
        
       yr8.setDay(29);
      
       
       if(yr8.toString().equals("2/29/2020")){
           errors = errors;
        }
       
       else {
           errors++;
       }
        
       yr8.setMonth(2);
     
        
        if(yr8.toString().equals("2/29/2020")){
            errors = errors;
        }
        
        else {
            errors++;
        }
        
       yr8.setYear(2019);
       
       if(yr8.toString().equals("2/29/2019")){
           errors++;
       }
        
       yr8.setYear(2004);
       
       if(yr8.toString().equals("2/29/2004")){
           errors = errors;
       }
       
        else{
            errors++;
        }
       
       
       yr8.setMonth(14);
       
       if(yr8.toString().equals("14/29/2004")){
           errors++;
       }
       
        yr8.setMonth(-6);
       
        if(yr8.toString().equals("-6/29/2004")){
            errors++;
        }
       
        yr8.setYear(-1);
       
        if(yr8.toString().equals("2/29/-1")){
            errors++;
        }
       
        yr8.setYear(-500);
       
        if(yr8.toString().equals("2/29/-500")){
           errors++;
        }
        
        //for testing date format
        GVdate forFormat2 = new GVdate("7/9/2008");
        System.out.println("======================================");
        System.out.println("Testing date format functions.");
        System.out.println("Testing date is 9 July 2008.");
        System.out.println("======================================");
        
        if(forFormat2.toString(1).equals("7/9/2008")) {
            errors = errors;
        }
        
        else {
            errors++;
        }
         
         System.out.print("Format 1: ");
         System.out.println(forFormat2.toString(1));
        
        if(forFormat2.toString(2).equals("07/09/2008")) {
            errors = errors;
            }
        
        else {
            errors++;
        }
         
         System.out.print("Format 2: ");
         System.out.println(forFormat2.toString(2));
         
         if(forFormat2.toString(3).equals("Jul 09, 2008")) {
             errors = errors;   
         }
         
         else {
             errors++;  
         }
          
          System.out.print("Format 3: ");
          System.out.println(forFormat2.toString(3));
          
          if(forFormat2.toString(4).equals("July 9, 2008")) {
              errors = errors;   
          }
         
          else {
              errors++;  
          }
         
           System.out.print("Format 4: ");
           System.out.println(forFormat2.toString(4));
          
          GVdate before2000 = new GVdate(10, 8, 500);
          
          if(before2000.toString(4).equals("October 8, 0500")) {
              errors = errors;   
          }
         
          else {
            errors++;
          }
          
            
            System.out.println("======================================");
            System.out.println("Testing date jumps and different date"+
            " configurations.");
            System.out.println("======================================");
            GVdate dayBeforeDue = new GVdate();
            
            System.out.print("New day created - project due date: ");
            System.out.println(dayBeforeDue.toString());
            
            
            if(dayBeforeDue.toString().equals("10/12/2020")) {
                errors = errors;
            }
            
            else { 
                errors++;
            }
            
            dayBeforeDue.nextDay();
            
            System.out.print("One day after project due date: ");
            System.out.println(dayBeforeDue.toString());
            
            
            if(dayBeforeDue.toString().equals("10/13/2020")) {
                errors = errors;
            }
            
            else { 
                errors++;
            }
           
           
            dayBeforeDue.nextDay();
            
            System.out.print("Two days after project due date: ");
            System.out.println(dayBeforeDue.toString());
            
            
            if(dayBeforeDue.toString().equals("10/14/2020")) {
                errors = errors;
            }
            else { 
                errors++;
            }
           
           
           
           dayBeforeDue.skipAhead(365);
           System.out.print("One year after project due date: ");
           System.out.println(dayBeforeDue.toString());
            
            
          if(dayBeforeDue.toString().equals("10/14/2021")) {
               errors = errors;
          }
          
          else { 
                errors++;
          }
           
            dayBeforeDue.skipAhead(32);
            System.out.print("One year, 32 days after project due"+ 
            " date: ");
            System.out.println(dayBeforeDue.toString());
           if(dayBeforeDue.toString().equals("11/15/2021")) {
               errors = errors;
           }
           
           else { 
               errors++;
           }
           
           
           System.out.println("======================================");
           System.out.println("Testing leap year");
           System.out.println("======================================");
           System.out.print("Is 1700 a leap year? ");
           System.out.println(dayBeforeDue.isLeapYear(1700));
           System.out.print("Is 2024 a leap year? ");
           System.out.println(dayBeforeDue.isLeapYear(2024));
           System.out.print("Is 2000 a leap year? ");
           System.out.println(dayBeforeDue.isLeapYear(2000));
            
             
           System.out.println("======================================");
           System.out.println("Testing my birthday");
           System.out.println("======================================");
           forFormat2.setDate(6, 30, 2002);
           System.out.print("Is June 30, 2002 my birthday? ");
           System.out.println(forFormat2.isMyBirthday());
           
           GVdate nextBirthday = new GVdate(6, 29, 2021);
            
           System.out.print("Is June 29, 2021 my birthday? ");
           System.out.println(nextBirthday.isMyBirthday());
            
          System.out.println("Errors: " + errors);
          System.out.println ("Testing ends");
    }  

}
