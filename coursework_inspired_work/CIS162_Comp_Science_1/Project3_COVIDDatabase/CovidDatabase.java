
/**
 * CovidDatabase - mainly forms the data
 * from the Excel document given for the project.
 * There is a sense of
 * then being able to populate an ArrayList
 * of COVID entries to then be able to better 
 * use various methods throughout this 
 * project. These are needed in
 * order to better read necessary
 * data. Within are methods 
 * having to do with hosting a top 
 * 10 list of infections, 
 * seeing if cases have decreased 
 * for a certian period of time,
 * getting highest deaths for
 * along with infections for 
 * states and dates, and 
 * getting infection growth.
 * 
 * @author Jacob Neiheisel
 * @forclass Computer Science 1 - CIS 162 Section 09, Professor Posada
 * @version 1.0.4 - 28 November 2020
 */


import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Collections;


public class CovidDatabase {

    
    /**
     * Start instance variables
     */

    
    private ArrayList<CovidEntry> allDataListed;
    private static final int SAFE = 5;

    
    /**
     * Start constructor methods
     */

    
    public CovidDatabase() {
        
        allDataListed = new ArrayList<CovidEntry>();
        
    }
    
    
    /**
     * Start file reader method
     */

    
    /**
     * readCovidData() - reads data file
     * for entries
     *
     * @param  name of the file as a string
     * 
     */

    
    public void readCovidData(String filename) {
        Scanner inFS = null; 
        FileInputStream fileByteStream = null;

        try{
            // open the File and set delimiters
            fileByteStream = new FileInputStream(filename);
            inFS = new Scanner(fileByteStream);
            inFS.useDelimiter("[,\r\n]+");

            for (int i = 0; i<7; i++){
                inFS.next();
            }
            while(inFS.hasNext()) {
                String state = inFS.next();
                int month = inFS.nextInt();
                int day = inFS.nextInt();
                int dailyInfections = inFS.nextInt(); 
                int dailyDeaths = inFS.nextInt();
                int totalInfections = inFS.nextInt(); 
                int totalDeaths = inFS.nextInt(); 
                allDataListed.add(new CovidEntry(state, month, day, dailyInfections, dailyDeaths, totalInfections, totalDeaths));
            }
            fileByteStream.close();

            // error while reading the file                      
        } catch(IOException error1) {
            System.out.println("Oops! Error related to: " + filename);
        }        

    }

    
    /**
     * Start getter methods
     */
    

    /**
     * countRecords() - gets amount of
     * database records
     *
     * 
     * @return    number of records in database
     * displayed as an integer
     */

    
    public int countRecords() {
        
        return allDataListed.size();
        
    }
    
    
    /**
     * getTotalDeaths() - gets total
     * deaths for overall entries
     *
     * 
     * @return    number of deaths 
     * logged in database, displayed
     * as an integer
     */

    
    public int getTotalDeaths() {
        
        int deaths;
        deaths = 0;

        for(CovidEntry deathCount: allDataListed) {

            deaths += deathCount.getDailyDeaths();

        }

        return deaths;

    }

    
    /**
     * getTotalInfections() - gets amount of
     * all the infections in the database
     *
     * 
     * @return    total number of 
     * infections in database 
     * displayed as an integer
     */

    
    public int getTotalInfections() {
        
        int infections;
        infections = 0;

        for(CovidEntry infectionCount: allDataListed) {

            infections += infectionCount.getDailyInfections();

        }

        return infections;

    }

    
    /**
     * counttotalDeaths() - gets amount of
     * deaths on a day
     *
     * @param m - month for counting deaths
     * must be an integer
     * 
     * @param d - day for counting deaths
     * must be an integer
     * 
     * @return    number of deaths
     * on a given day displayed as
     * an integer
     */

    
    public int countTotalDeaths(int m, int d) {
        
        int deaths;
        deaths = 0;

        for(CovidEntry deathCount: allDataListed) {

            if(deathCount.getMonth() == m && 
            deathCount.getDay() == d) {
                
                deaths += deathCount.getDailyDeaths();
            
            }

        }

        return deaths;

    }

    
    /**
     * countTotalInfections() - gets amount of
     * infections on a given day
     *
     * @param m - month for counting infections
     * must be an integer
     * 
     * @param d - day for counting infections
     * must be an integer
     * 
     * @return    number of deaths
     * on that particular day
     * displayed as an integer
     */

    
    public int countTotalInfections(int m, int d) {
        
        int infections;
        infections = 0;

        for(CovidEntry infectionCount: allDataListed) {

            if(infectionCount.getMonth() == m && 
            infectionCount.getDay() == d) {

                infections += infectionCount.getDailyInfections();

            }

        }

        return infections;
        
    }

    
    /**
     * peakDailyDeaths() - gets a CovidEntry
     * for where daily deaths peaked on a 
     * single day
     *
     * @param  st - state where deaths 
     * peaked, entered as a string
     * 
     * 
     * 
     * @return    CovidEntry of 
     * on that particular day
     * where deaths peaked 
     */
    

    public CovidEntry peakDailyDeaths (String st){
       
        int deaths;
        deaths = 0;
        CovidEntry returner = null;
        
        for(CovidEntry dataEntry: allDataListed) {
            
            if(dataEntry.getDailyDeaths() > deaths && 
            st.equalsIgnoreCase(dataEntry.getState())) {
                deaths = dataEntry.getDailyDeaths();
                returner = dataEntry;
            }
            
        }
        
        return returner;
        
    }

    
    /**
     * getDailyDeaths() - gets ArrayList
     * of CovidEntries for daily deaths
     *
     * @param m - month for counting infections
     * expressed as an integer
     * 
     * @param d - day for counting infections
     * expressed as an ineger
     * 
     * @return    ArrayList of 
     * CovidEntries about deaths
     * that particular day
     * displayed as an ArrayList
     */

    
    public ArrayList <CovidEntry> getDailyDeaths(int m, int d) {
        
        ArrayList <CovidEntry> allRecords = new ArrayList <CovidEntry>();

        for(CovidEntry records: allDataListed) {

            if(records.getMonth() == m && records.getDay() == d) {

                allRecords.add(records);

            }

        }

        return allRecords;
        
    }

    
    /**
     * peakDailyDeaths() - gets CovidEntry
     * with highest deaths for month and day
     *
     * @param m - month for counting deaths
     * expressed as an integer
     * 
     * @param d - day for counting deaths
     * expressed as an integer
     * 
     * @return    CovidEntry of what
     * state had for deaths and 
     * infections on that particular
     * day for the inputs.
     */

    
    public CovidEntry peakDailyDeaths(int m, int d) {
        
        ArrayList <CovidEntry> allRecords = new ArrayList <CovidEntry>();
        int deaths = 0;
        CovidEntry peakDeaths = null;

        for(CovidEntry records: allDataListed) {

            if(records.getMonth() == m && records.getDay() == d) {

                allRecords.add(records);

            }

        }

        for(CovidEntry highDeaths: allRecords) {

            if(highDeaths.getDailyDeaths() > deaths) {

                deaths = highDeaths.getDailyDeaths();
                peakDeaths = highDeaths;

            }

        }

        return peakDeaths;
        
    }

    
    /**
     * mostTotalDeaths() - gets CovidEntry
     * with highest death count in database
     *
     * 
     * @return    CovidEntry of what
     * state had for highest deaths 
     * in database.
     */
    
    
    public CovidEntry mostTotalDeaths() {
        
        int deaths = 0;
        CovidEntry mostDeaths = null;
        
        for(CovidEntry records: allDataListed) {
            
            if(records.getTotalDeaths() > deaths) {
                
                deaths = records.getTotalDeaths();
                mostDeaths = records;
                
            }
            
        } 
        
        return mostDeaths;
        
    }

    
   /**
     * listMinimumDailyInfections() - gets ArrayList
     * with states over a minimum infection bar
     * for a given month and date
     *
     * @param  m - month wanted for seeing
     * where states had a minimum number 
     * of infections, expressed as an 
     * integer
     *
     * @param  d - day wanted for seeing
     * where states had a minimum number 
     * of infections, expressed as an 
     * integer
     * 
     * @param  min - minimum number 
     * of infections to sort state
     * infections, expressed as an 
     * integer
     * 
     * @return   ArrayList for 
     * the states with equal to 
     * or more than the minimum
     * infections on that given
     * day
     */
    
    
    public ArrayList <CovidEntry> listMinimumDailyInfections (int m, int d, int min) {
       
        ArrayList <CovidEntry> mins = new ArrayList <CovidEntry>();
        
        for(CovidEntry records: allDataListed) {
            
            if(records.getMonth() == m && records.getDay() == d && 
            records.getDailyInfections() >= min) {
                
                mins.add(records);
                
            }
            
        }
        
        return mins;
        
    }

    
     /**
     * safeToOpen() - gets ArrayList
     * with data suggesting a state
     * is safe to open
     *
     * @param   st - name of a state
     * to see if it can open
     * entered as a two letter
     * string
     * 
     * @return    ArrayList of what
     * data there is that supports 
     * the state to proceed to open.
     */
    
    
    public ArrayList <CovidEntry> safeToOpen(String st) {
        
        int daysDecreasing = 1;
        ArrayList <CovidEntry> days = new ArrayList <CovidEntry>();
        ArrayList <CovidEntry> stateNumbers = new ArrayList <CovidEntry>();
        
        for(CovidEntry count: allDataListed) {
            
            if(count.getState().equalsIgnoreCase(st)) {
                
                stateNumbers.add(count); 

            }
            
        }

        for(int count = 0; count < stateNumbers.size() - 1; count++) {
            
            if(stateNumbers.get(count).getDailyInfections() > 
            stateNumbers.get(count + 1).getDailyInfections()) {

                if(daysDecreasing == 1) {
                    days.add(stateNumbers.get(count));
                    days.add(stateNumbers.get(count + 1));
                    daysDecreasing++;
                } 
                
                else if (daysDecreasing > 1) {
                    days.add(stateNumbers.get(count + 1));
                    daysDecreasing++;
                }
                
                if (daysDecreasing == SAFE) {
                    return days;
                }

            }
            
            else {
                days.clear();
                daysDecreasing = 1;
            }

        }
        if(daysDecreasing ==1) {
            return null;
        }
        
        return days;
        
    }

    
     /**
     * topTenDeaths() - gets CovidEntry
     * ArrayList with top ten deaths
     * for the given month and day
     *
     * @param m - month for counting deaths
     * expressed as an integer
     * 
     * @param d - day for counting deaths
     * expressed as an ineger
     * 
     * @return    CovidEntry of what
     * state had for highest deaths 
     * in database.
     */
    
    
    public ArrayList <CovidEntry> topTenDeaths(int m, int d) {
        
        ArrayList <CovidEntry> topDeaths = getDailyDeaths (m,d);        
        Collections.sort(topDeaths);
        
        for (int count = topDeaths.size() - 1; count > 9; count--) {
            
            topDeaths.remove(count);
            
        }
        
        return topDeaths;
    }

}

