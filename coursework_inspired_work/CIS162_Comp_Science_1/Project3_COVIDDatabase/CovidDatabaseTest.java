
/**
 * This is to mainly test the data found in the Covid-19
 * database. This test helps to ensure the methods called
 * for the GUI execute correctly in order to 
 * allow for the GUI to display correct and 
 * accurate results. In this test document
 * are a few test cases that highlight 
 * that testing in certain circumstances and 
 * needs that have arisen in the testing phase.
 *
 * @author Jacob Neiheisel
 * @forclass Computer Science 1 - CIS 162 Section 09, Professor Posada
 * @version 1.0.4 - 28 November 2020
 */

import java.util.ArrayList;

public class CovidDatabaseTest {
    
    public static void main () {
        System.out.println ("Testing starts");
        CovidDatabase db = new CovidDatabase() ;
        db.readCovidData("covid_data.csv");

        // check number of records, total infections, and total deaths
        assert db.countRecords() == 10346 : "database should have 10,346"; 
        assert db.getTotalDeaths() == 196696 : "Total deaths should be:" +
        " 196,696"; 
        assert db.getTotalInfections() ==  7032090 : "infections should be:" +
        " 7,032,090"; 

        // check peak daily deaths for 5/5
        CovidEntry mostDeaths = db.peakDailyDeaths(5, 5);
        assert mostDeaths.getState().equals("PA") : "State with most deaths" +
        " for 5/5 is PA";
        
        assert mostDeaths.getDailyDeaths() ==  554 : "Deaths for 5/5" +
        " is PA: 554";
        
        // check most deaths for Michigan
       CovidEntry mostDeathsState = db.peakDailyDeaths("MI");
       assert mostDeathsState.getDailyDeaths() == 169:"Top deaths for" +
       " Michigan is 169 on 4/16";
       
       // check most deaths for Ohio 
      
       CovidEntry mostDeathsStateOhio = db.peakDailyDeaths("OH");
       assert mostDeathsStateOhio.getDailyDeaths() == 138:"Top deaths for" +
       " Ohio is 138 on 4/29";
       
       // check most deaths for 5/8
       CovidEntry mostDeathsDate = db.peakDailyDeaths(5,8);
       assert mostDeathsDate.getDailyDeaths() == 217:"Top deaths for 5/8 is" +
       " New York with 217";
       
       // check most deaths for 6/29
       CovidEntry mostDeathsDateBetter = db.peakDailyDeaths(6,29);
       assert mostDeathsDateBetter.getDailyDeaths() == 35:"Top deaths for" +
       " 6/29" + "is Pennsylvania with 35";
       
       //check top ten deaths for 5/5
       ArrayList <CovidEntry> topTen = db.topTenDeaths(5,5);
       assert topTen.get(0).getState().equals("PA"): "State for" +
       " first entry" + " should be Pennsylvania";
       assert topTen.get(0).getDailyInfections() == 865:"Top infections" + 
       " first entry for 5/5 should be Pennsylvania with 865 infections";
       assert topTen.get(1).getState().equals("NJ"): "Top deaths second" +
       " entry for 5/5 is New Jersey with 341 deaths";
       assert topTen.get(1).getDailyDeaths() == 341: "Top deaths" +
       " second entry" + " for 5/5 is New Jersey with 341 deaths";
       assert topTen.get(2).getState().equals("NY"): "Top deaths" +
       " third entry" + " for 5/5 is New York";
       assert topTen.get(2).getDailyInfections() == 2239: "Top infections" +
       " third entry for 5/5 is New York with 2239 infections";
       assert topTen.get(3).getState().equals("IL"): "Top infections" + 
       " fourth entry for 5/5 is Illinois";
       assert topTen.get(3).getDailyDeaths() == 176: "Top deaths" + 
       " fourth entry for 5/5 is Illinois with 176 deaths";
       assert topTen.get(4).getState().equals("CT"): "Top infections" +
       " fifth entry for 5/5 is Connecticut";
       assert topTen.get(4).getDailyInfections() == 1334: "Top infections" + 
       " fifth entry for 5/5 is Connecticut with 1334 infections";
       assert topTen.get(5).getState().equals("MA"): "Top infections" +
       " sixth entry for 5/5 is Massachusetts";
       assert topTen.get(5).getDailyDeaths() == 122: "Top deaths" +
       " sixth entry for 5/5 is Massachusetts with 122 deaths";
       assert topTen.get(6).getState().equals("FL"): "Top infections" +
       " seventh entry for 5/5 is Florida";
       assert topTen.get(6).getDailyInfections() == 542: "Top infections" +
       " seventh entry for 5/5 is Florida with 542 infections";
       assert topTen.get(7).getState().equals("OH"): "Top infections" +
       " eighth entry for 5/5 is Ohio";
       assert topTen.get(7).getDailyDeaths() == 79: "Top deaths eighth" +
       " entry for 5/5 is Ohio with 79 deaths";
       assert topTen.get(8).getState().equals("GA"): "Top infections" + 
       " nineth entry for 5/5 is Georgia";
       assert topTen.get(8).getDailyInfections() == 343: "Top infections" +
       " nineth entry for 5/5 is Georgia with 343 infections";
       assert topTen.get(9).getState().equals("CA"): "Top infections" + 
       " tenth entry for 5/5 is California";
       assert topTen.get(9).getDailyDeaths() == 63: "Top deaths tenth" + 
       " entry for 5/5 is California with 63 deaths";
       
       //check top ten deaths for 6/29
       ArrayList <CovidEntry> topTenJune = db.topTenDeaths(6,29);
       assert topTenJune.get(0).getState().equals("PA"): 
       "State for first" + 
       " entry for 6/29 top deaths should be Pennsylvania";
       assert topTenJune.get(0).getDailyInfections() == 492:
       "Top infections" +
       " first entry for 6/29 should be Pennsylvania with 492 infections";
       assert topTenJune.get(1).getState().equals("MA"): "State for" +
       " second entry for 6/29 top deaths should be Massachusetts";
       assert topTenJune.get(1).getDailyDeaths() == 35: "Top deaths" +
       " second entry for 6/29 should be Massachusetts with 35 deaths";
       assert topTenJune.get(2).getState().equals("CA"): 
       "State for third" +
       " entry for 6/29 top deaths should be California";
       assert topTenJune.get(2).getDailyInfections() == 8009: "Top infections" +
       " third entry for 6/29 should be California with 8009 infections";
       assert topTenJune.get(3).getState().equals("FL"): 
       "State for fourth" +
       " entry for 6/29 top deaths should be Florida";
       assert topTenJune.get(3).getDailyDeaths() == 28: "Top deaths" +
       " fourth entry for 6/29 should be Florida with 28 deaths";
       assert topTenJune.get(4).getState().equals("MS"): "State for" +
       " fifth entry for 6/29 top deaths should be Mississippi";
       assert topTenJune.get(4).getDailyInfections() == 675: 
       "Top infections" +
       " fifth entry for 6/29 should be Missouri with 675 infections";
       assert topTenJune.get(5).getState().equals("RI"): "State for" + 
       " sixth entry for 6/29 top deaths should be Rhode Island";
       assert topTenJune.get(5).getDailyDeaths() == 19: "Top deaths" + 
       " sixth entry for 6/29 should be Rhode Island with 19 deaths";
       assert topTenJune.get(6).getState().equals("NJ"): "State for" +
       " seventh entry for 6/29 top deaths should be New Jersey";
       assert topTenJune.get(6).getDailyInfections() == 90: 
       "Top infections" +
       " seventh entry for 6/29 should be New Jersey with 90 infections";
       assert topTenJune.get(7).getState().equals("IL"): "State for" +
       " eighth entry for 6/29 top deaths should be Illinois";
       assert topTenJune.get(7).getDailyDeaths() == 14: "Top deaths" +
       " eighth entry for 6/29 should be Illinois with 14 deaths";
       assert topTenJune.get(8).getState().equals("OH"): "State for" +
       " nineth entry for 6/29 top deaths should be Ohio";
       assert topTenJune.get(8).getDailyInfections() == 737: 
       "Top infections" + 
       " nineth entry for 6/29 should be Ohio with 737 infections";
       assert topTenJune.get(9).getState().equals("TX"): "State for" +
       " tenth entry for 6/29 top deaths should be Texas";
       assert topTenJune.get(9).getDailyDeaths() == 10: "Top deaths" +
       " tenth entry for 6/29 should be Texas with 10 deaths";
      
        // check if Michigan is safe to open
       ArrayList <CovidEntry> safeToOpenState = db.safeToOpen("MI");
       
       
       assert safeToOpenState.get(0).getDailyInfections() > 
       safeToOpenState.get(1).getDailyInfections():"Cases from day 1" +
       " to day 2 should decrease";
       assert safeToOpenState.get(1).getDailyInfections() > 
       safeToOpenState.get(2).getDailyInfections():"Cases from day 2" +
       " to day 3 should decrease";
       assert safeToOpenState.get(2).getDailyInfections() > 
       safeToOpenState.get(3).getDailyInfections():"Cases from day 3" +
       " to day 4 should decrease";
       assert safeToOpenState.get(3).getDailyInfections() > 
       safeToOpenState.get(4).getDailyInfections():"Cases from day 4" +
       " to day 5 should decrease";
       
       //check if Ohio is safe to open
       ArrayList <CovidEntry> safeToOpenOhio = db.safeToOpen("OH");
       
       
       assert safeToOpenOhio.get(0).getDailyInfections() > 
       safeToOpenOhio.get(1).getDailyInfections():"Cases from day 1" +
       " to day 2 should decrease";
       assert safeToOpenOhio.get(1).getDailyInfections() > 
       safeToOpenOhio.get(2).getDailyInfections():"Cases from day 2" +
       " to day 3 should decrease";
       assert safeToOpenOhio.get(2).getDailyInfections() > 
       safeToOpenOhio.get(3).getDailyInfections():"Cases from day 3" +
       " to day 4 should decrease";
       assert safeToOpenOhio.get(3).getDailyInfections() > 
       safeToOpenOhio.get(4).getDailyInfections():"Cases from day 4" + 
       "to day 5 should decrease";
    
    
        //check list minimum daily infections
       ArrayList <CovidEntry> minimumBar = 
       db.listMinimumDailyInfections(6,12,1000);  
        assert minimumBar.get(0).getDailyInfections() >= 1000: "Daily" +
        " infections to be greater than 1000";
        assert minimumBar.get(1).getDailyInfections() >= 1000: "Daily" +
        " infections to be greater than 1000";
        assert minimumBar.get(2).getDailyInfections() >= 1000: "Daily" +
        " infections to be greater than 1000";
        assert minimumBar.get(3).getDailyInfections() >= 1000: "Daily" +
        " infections to be greater than 1000";
                               
       ArrayList <CovidEntry> miniBar = 
       db.listMinimumDailyInfections(6,29,1000);  
        assert miniBar.get(0).getDailyInfections() >= 1000: "Daily" +
        " infections to be greater than 1000";
        assert miniBar.get(1).getDailyInfections() >= 1000: "Daily" +
        " infections to be greater than 1000";
        assert miniBar.get(2).getDailyInfections() >= 1000: "Daily" +
        " infections to be greater than 1000";
        assert miniBar.get(3).getDailyInfections() >= 1000: "Daily" +
        " infections to be greater than 1000";
        System.out.println ("Testing ends");
               }
}
