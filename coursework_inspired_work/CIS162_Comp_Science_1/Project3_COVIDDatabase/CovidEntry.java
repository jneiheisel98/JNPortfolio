
/**
 * CovidEntry - a class that helps to 
 * synthesize what is gotten from the
 * CovidDatabase for the month
 * of the record, day of record,
 * the daily deaths on a day,
 * daily infections on a day,
 * the total deaths on a day,
 * and the total infections
 * on a day.
 * 
 *
 * @author Jacob Neiheisel
 * @classfor: Computer Science 1 - CIS 162 Section 09: Professor Posada
 * @version 1.0.2 (as of 28 November 2020)
 */


import java.text.DecimalFormat;


public class CovidEntry implements Comparable {

    
    /**
     * Start instance variables
     */

    
    private String state;
    private int month;
    private int day;
    private int dailyDeaths;
    private int dailyInfections;
    private int totalDeaths;
    private int totalInfections;

    
    /**
     * Start decimal format for the entire document
     */
    
    
    DecimalFormat thousands = new DecimalFormat ("#,###"); 

    
    /**
     * Start constructor methods
     */

    
    /**
     * Defualt Constructor for a COVID entry,
     * info derived from database document.
     */
    
    
    public CovidEntry(String st, int m, int d, int di, int dd, int ti, int td) {
        state = st;
        month = m;
        day = d;
        dailyInfections = di;
        dailyDeaths = dd;
        totalDeaths = td;
        totalInfections = ti;

    }

    
    /**
     * Start getter methods
     */

    
    /**
     * getMonth() - gets month as an integer
     *
     * 
     * @return    month of the COVID entry in question
     */
    
    
    public int getMonth() {
        return month;
    }

    
    /**
     * getDay() - gets day as an integer
     *
     * 
     * @return    day of the COVID entry in question
     */
    
    
    public int getDay() {
        return day;
    }

    
    /**
     * getState() - gets state as a string
     *
     * 
     * @return    state of the COVID entry in question
     */
    
    
    public String getState() {
        return state;
    }

    
    /**
     * getDailyInfections() - gets daily infections as an integer
     *
     * 
     * @return    daily infections for the given COVID entry
     */
    
    
    public int getDailyInfections() {
        return dailyInfections;
    }

    
    /**
     * getDailyDeaths() - gets daily deaths as an integer
     *
     * 
     * @return    daily deaths for the given COVID entry
     */
    
    
    public int getDailyDeaths() {
        return dailyDeaths;
    }

    
    /**
     * getTotalInfections() - gets total infections as an integer
     *
     * 
     * @return    total infections for the given COVID entry
     */
    
    
    public int getTotalInfections() {
        return totalInfections;
    }

    
    /**
     * getTotalDeaths() - gets total deaths as an integer
     *
     * 
     * @return    total deaths for the given COVID entry
     */
    
    
    public int getTotalDeaths() {
        return totalDeaths;
    }

    
    /**
     * toString() - gets the COVID entry and defines needed string
     *
     * 
     * @return     COVID entry as a readable string
     */
    
    
    public String toString() {
        return state + " " + month 
        + "/" + day + " " + thousands.format(dailyInfections) 
        + " infections, " + thousands.format(dailyDeaths) 
        + " deaths";
    }

    
    /**
     * compareTo() - gets the COVID entry and COVID entry to compare
     * returning the difference between the daily deaths in the COVID 
     * entry to determine the next in the list.
     *
     * 
     * @return     COVID entry as a readable string
     */
    
    
    public int compareTo (Object other) {
        CovidEntry c = (CovidEntry) other;
        return c.dailyDeaths - dailyDeaths;
    }
    
    
}
