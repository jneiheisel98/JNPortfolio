
/**
 * GVdate - a class that can advance a date, tell if it is one's birthday, 
 * advance a month or a year, string and construct a date together in a 
 * variety of formats, and do whatever needed if a leap year. This method 
 * can also set a date, make sure a date is valid, skip ahead multiple 
 * days, and see if dates are equal
 *
 * @author Jacob Neiheisel
 * @for_class CIS 162 - Computer Science I - Section 09 - Professor Posada
 * @version 1.0.1 - 1 October 2020 (around 45 mins to complete and format 
 * P1), 12:38 to 1:18 AS WELL, 3:56-4:51, 25 mins, 35 mins
 */


import java.text.DecimalFormat;


public class GVdate
{
    // instance variables - replace the example below with your own
    
    private int month;
    private int day;
    private int year;
    private int monthDaySeparator;
    private int dayYearSeparator;
    private static final int BIRTHMONTH = 6;
    private static final int BIRTHDAYNUMBER = 29;

    
    /**
     * Start constructor methods
     */

    
    /**
     * Defualt Constructor for objects of class GVdate 
     */
    public GVdate() {
        /**sets month, day, and year by default within the method*/
        month = 10;
        day = 12;
        year = 2020;
    }

    
    /**
     * Modified Constructor for objects of class GVdate 
     * @param month - month of year
     * @param day - numerical date in month
     * @param year - number year
     */
    public GVdate(int m, int d, int y) {
        
        //sets month, day, and year within the method
        
        if(isDateValid(m,d,y)) {
            month = m;
            day = d;
            year = y;
        } 
        
        else {
            month = 10;
            day = 12;
            year = 2020;
        }
    }

    
    /**
     * Modified Constructor for objects of class GVdate
     * 
     * 
     * @param date - the date in string form eg."09/08/2020" 
     * 
     */
    public GVdate(String date) {
        
        //parses month, day, and year within the method

        int monthDaySeparator = date.indexOf("/");
        int dayYearSeparator = date.indexOf("/", monthDaySeparator + 1);

        month = Integer.parseInt(date.substring(0,monthDaySeparator));
        day = Integer.parseInt(date.substring(monthDaySeparator + 1,
                dayYearSeparator));
        year = Integer.parseInt(date.substring(dayYearSeparator + 1));
        
        if(isDateValid(month,day,year)) {
            month = month;
            day = day;
            year = year;
        }
        
        else {
            month = 10;
            day = 12;
            year = 2020;
        }
    }

    
    /**
     * Start getter methods
     */
    

    /**
     * getMonth() - gets month in numerical form
     *
     * 
     * @return    month in numerical form
     */
    public int getMonth() {
        return month;
    }

    
    /**
     * getDay() - gets day in numerical form
     *
     * 
     * @return    day in numerical form
     */
    public int getDay() {
        return day;
    }

    
    /**
     * getYear() - gets year in numerical form
     *
     * 
     * @return    year in numerical form
     */
    public int getYear() {
        return year;
    }

    
    /**
     * toString() - gets a complete date set
     *
     * 
     * @return   outputs the date in mm/dd/yyyy form
     */
    public String toString() {
        return month + "/" + day + "/" + year;
    }

    
    /**
     * toString(format) - gets a complete date set, mm or dd may be 
     * shortened at this time format indicates format number
     *
     * 
     * @return   1: outputs the date in (m)m/(d)d/yyyy form
     * @return   2: outputs the date in mm/dd/yyyy form
     * @return   3: outputs the date in mon. (d)d, yyyy form
     * @return   4: outputs the date in month (d)d, yyyy form
     */
    public String toString(int format) {
        DecimalFormat nums = new DecimalFormat("00");
        DecimalFormat years = new DecimalFormat("0000");
        String months = "JanFebMarAprMayJunJulAugSepOctNovDec";
        
        
        //String monthswithnums = "1Jan2Feb3Mar4Apr5May6Jun7Jul8Aug9Sep"+
        //"10Oct11Nov12Dec13"; this was previously used and works for string
        
        
        switch(format) {
            
            
            case 1:
            return month + "/" + day + "/" + years.format(year);
            //break;
            
            
            case 2:
            return nums.format(month) + "/" + nums.format(day) + "/" 
            + years.format(year);
            //break;

            
            //convert integer number for month to a string so that month
            //abbreviations can be gotten easily
            
            case 3:
            return months.substring(((month-1)*3), ((month-1)*3+3)) 
            + " " + nums.format(day) + ", " + years.format(year);
            //return monthswithnums.substring((monthswithnums.indexOf
            //(Integer.toString(month))+ Integer.toString(month)
            //.length()),monthswithnums.indexOf(Integer.toString(month+
            //1))) + " " + nums.format(day) + ", " + years.format(year);
            //break;
            
            
            case 4:
            switch(month){
                case 1:
                return "January " + day + ", " + 
                years.format(year);
                //break;

                
                case 2:
                return "February " + day + ", " + 
                years.format(year);
                //break

                
                case 3:
                return "March " + day + ", " + 
                years.format(year);
                //break

                
                case 4:
                return "April " + day + ", " + 
                years.format(year);
                //break;

                
                case 5:
                return "May " + day + ", " + 
                years.format(year);
                //break

                
                case 6:
                return "June " + day + ", " + 
                years.format(year);
                //break

                
                case 7:
                return "July " + day + ", " + 
                years.format(year);
                //break;

                
                case 8:
                return "August " + day + ", " + 
                years.format(year);
                //break

                
                case 9:
                return "September " + day + ", " + 
                years.format(year);
                //break

                
                case 10:
                return "October " + day + ", " + 
                years.format(year);
                //break;

                
                case 11:
                return "November " + day + ", " + 
                years.format(year);
                //break

                
                case 12:
                return "December " + day + ", " + 
                years.format(year);
                //break
            }
            //break;
        }
        return nums.format(month) + "/" + day + "/" + years.format(year);
    }

    
    /**
     * isMyBirthday() - checks month against preset birth month and preset
     * birth day to see if they match
     *
     * 
     * @return   outputs the date in mm/dd/yyyy form
     */
    public boolean isMyBirthday(){
        
        if (month == BIRTHMONTH && day == BIRTHDAYNUMBER) {
            return true;
        } 

        else {
            return false;
        }
    }

    
    /**
     * isLeapYear() - checks year against leap year rules
     *
     *@param y year that is the leap year in question
     * 
     * @return   outputs true/false if it is a leap year
     */
    public boolean isLeapYear(int y) {
        
        if (y % 100 == 0 && y % 400 == 0) {
            return true;
        } 
        
        else if (y % 100 == 0 && y % 400 != 0) {
            return false;
        }
        
        else if (y % 100 != 0 && y % 4 == 0) {
            return true;
        } 

        else {
            return false;
        } 
    }

    /**
     * equals() - checks the two dates, one inputted and one of the 
     * current object to see if the dates are the same 
     *
     * @param otherDate - date as a GVdate object
     * 
     * @return   outputs true or false depending on if 
     * two dates are equal
     */
    public boolean equals (GVdate otherDate){
        
        if(toString().equals(otherDate.toString())){
            return true;
        }
        
        else{
            return false;
        }
    }
    //month == other.month&& day == other.date && year==other.year;

    /**
     * Start setter methods
     */

    /**
     * setMonth(int m) - sets month to whatever m is 
     *
     * 
     * @param   month in int form
     */

    public void setMonth(int m) {
       
        if(isDateValid(m, day, year)) {
            month = m;
        } 
        
        else {
            month = month;
        }
    }

    /**
     * setDay(int d) - sets day to whatever number d is 
     *
     * 
     * @param  day in int form
     */
    public void setDay(int d) {
        
        if(d > 0 && d <= getLastDayOfMonth(month, year)) {
            day = d;
        }
        
        else {
            day = day;
        }
    }

    /**
     * setYear(int y) - sets year to whatever number y is 
     *
     * 
     * @param  year in an int form
     */
    public void setYear(int y) { 
        
        if(y > 0 && !isLeapYear(y) && month != 2 && day != 29) {
            year = y;
        }
        
        else if(isLeapYear(y) && month == 2 && day == 29) {
            year = y;
        }
        
        else{
            year = year;
        }
    }

    /**
     * setDate(int m, int d, int y) - sets month, day, 
     * and year to the numbers within the parameters 
     *
     * 
     * @param  month in int form
     * @param  day in int form
     * @param  year in int form
     */
    public void setDate(int m, int d, int y) {
        
        if(isDateValid(m,d,y) && d <= getLastDayOfMonth(m,y)) {
            month = m;
            day = d;
            year = y;
        }
        
        else {
            month = month;
            day = day;
            year = year;
        }
    }

    /**
     * nextDay() - goes to the next day and adjusts as needed 
     * based on year or time
     *
     * 
     * 
     */
    public void nextDay() {
        
        if(isDateValid(month,day + 1,year) && day < getLastDayOfMonth(month,year)) {
            day++;
        }
        
        else if (!isDateValid(month,day + 1,year) && day >= getLastDayOfMonth(month,year)) {

            month++;
            day = 1;
            year = year;
            
            if(isDateValid(month,day,year)) {
                month = month;
                day = day;
                year = year;
            }
            
            else {
                month = 1;
                day = 1;
                year++;
            }
        }
    }

    /**
     * skipAhead(int numDays) - sets the calendar to however 
     * much further ahead as needed in the calendar
     *
     * @param numDays the amount of days to go 
     * forward from the current day
     * 
     */
    public void skipAhead(int numDays) {
        
        if(numDays>0){
            
            for(int count = 0; count < numDays; count++) {
                nextDay();
            }
        }
    }

    /**
     * Start private helper methods
     */

    /**
     * getLastDayOfMonth(int m, int y) - gets last day of month for 
     * specific month and year 
     *
     * 
     * @param  month in int form
     * @param  year in int form
     */
    private int getLastDayOfMonth(int m, int y) {
        int daysInMonth[]=new int[12];
        
        for(int count = 0; count<12; count++) {
            
            if(count == 0 || count == 2 || count == 4 || count == 6 || 
            count == 7 || count == 9 || count == 11) {
                daysInMonth[count] = 31;
            }
            
            else if(count == 3 || count == 5 || count == 8 || count == 10) { 
                daysInMonth[count] = 30;
            } 
            
            else if(count == 1 && isLeapYear(y)){
                daysInMonth[count] = 29;
            }
            
            else if(count == 1 && !isLeapYear(y)){
                daysInMonth[count] = 28;
            }
            
        }
        
        if(m > 0 && m < 13 && y > 0){
            return daysInMonth[m-1];
        }
        
        else {
            return 0;
        }
    }

    /**
     * isDateValid(int m, int d, int y) - gets date and sees if it is 
     * valid or not 
     *
     * 
     * @param  month in int form
     * @param day in int form
     * @param  year in int form
     */
    private boolean isDateValid(int m, int d, int y){
        
        if(m < 13 && m > 0 && d > 0 && d <= getLastDayOfMonth(m,y) 
        && y > 0) {
            return true;
        }
        
        else{
            return false;
        }
    }
}
