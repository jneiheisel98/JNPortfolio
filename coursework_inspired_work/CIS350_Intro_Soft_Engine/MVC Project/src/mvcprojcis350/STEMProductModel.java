package mvcprojcis350;

import javax.swing.table.AbstractTableModel;
import java.text.DecimalFormat;

/***********************************************************************
 * @Author Jacob Neiheisel
 * @Author Roger Ferguson
 * @Author Devin Elenbaas
 * @Author Gage Elenbaas
 *  (bottom three authors are previous contributors to the codebase
 *  or model that I ended up pulling from for this)
 *
 * @version 16 November 2021
 *
 * The following is the STEMProductModel class that simply provides
 * the database format so that there could be an application in the
 * view that made sense and ends up making the whole database be
 * able to be displayed. Methods here are extending abstract table
 * model and also link to other controller methods to help out
 * displaying.
 *
 * This class holds the database format and is able to be that template
 * for how data should be shown in general. It is the engine behind the
 * display of the data that is shown in the actual model. This is to be
 * a view helper as opposed to an actual model.
 ***********************************************************************/

public class
STEMProductModel extends AbstractTableModel {

    /**
     * Column names for AtAGlance Screen
     */
    private String[] columnNamesAtAGlance = {"Title",
            "Description", "Price", "Rating"};
    /**
     * Column names for more descriptive or more descriptions Screen
     */
    private String[] columnNamesMoreDescriptions = {"Title",
            "Units in Stock", "Cost to ship", "Lower end age",
            "Higher end age"};
    /**
     * Sets the screen for the opening of the program
     */
    private STEMStoreEnums screen =
            STEMStoreEnums.ProductsAtAGlance;
    /**
     * Establishes controller communication/method
     * dependencies for view.
     */
    private STEMProductController base;

    /**
     * Default Constructor for the STEMProductModel
     */
    public STEMProductModel(){

        base = new STEMProductController();
        UpdateScreen();

    }
    /**************************************************************************
     *
     *   setDisplay(STEMStoreEnums selected) - changes what goes on within
     *   the screen updates the screen to any one of the enumerated options
     *
     * @param selected -  parameter is named selected to symbolize the screen
     *                 that the user selects to go to in the future after
     *                 they have run the method
     *
     */
    public void setDisplay(STEMStoreEnums selected) {
        screen = selected;
        UpdateScreen();
    }

    /**************************************************************************
     *
     *   UpdateScreen() - updates the screen displayes as a result of the
     *   current enum selection and screen state
     */
    private void UpdateScreen() {
        switch (screen) {
            case ProductsAtAGlance:
                    base.returnArray(1);
                break;

            case ProductDescriptionsAtAGlance:
                    base.returnArray(2);
                break;


            default:
                throw new RuntimeException("update is in " +
                        "undefined state: " + screen);
        }
        fireTableStructureChanged();
    }
    /**************************************************************************
     *
     *   getRowCount() - returns the number of data entries or store products
     *   that are in the storefront database in order to display all of them.
     *
     *
     *
     * @return the length of the array of STEMProducts gotten from the
     * created STEMProductController
     */
    @Override
    public int getRowCount() {
        return base.returnArray().length;
    }
    /**************************************************************************
     *
     *   getColumnName(int col) - returns the string of the name of the column
     *   in the given screen. Based on what is passed in as a parameter.
     *
     * @param col - An integer representation of the column number that is to
     *            be represented and returned by the method and also concerned
     *            with the current screen.
     *
     *
     * @return the name of the particular column that is specified in the
     * screen and by the integer parameter to figure out the name for the
     * column.
     */
    @Override
    public String getColumnName(int col) {
        switch (screen) {
            case ProductsAtAGlance:
                return columnNamesAtAGlance[col];
            case ProductDescriptionsAtAGlance:
                return columnNamesMoreDescriptions[col];
        }
        throw new RuntimeException("Undefined state " +
                "for Col Names: " + screen);
    }
    /**************************************************************************
     *
     *   getColumnCount() - Returns the number of columns for a particular
     *   screen in order for the model to function better.
     *
     *
     *
     * @return The amount of the columns for a particular screen, returned as
     * an integer.
     */
    @Override
    public int getColumnCount() {
        switch (screen) {
            case ProductsAtAGlance:
                return columnNamesAtAGlance.length;
            case ProductDescriptionsAtAGlance:
                return columnNamesMoreDescriptions.length;
        }
        throw new IllegalArgumentException();
    }


    /**************************************************************************
     *
     *   currentProductsAtAGlance(int row, int col) - returns the value or object
     *   at the given row and column of a table.
     *
     * @param row the row of the specified object or element that is desired,
     *            represented as an integer.
     *
     * @param col the column of the specified object or element that is desired,
     *            represented as an integer.
     *
     * @return an object that is at the given perceived row and perceived column
     * of the given database in order to make the model function.
     */
    private Object currentProductsAtAGlance(int row,
                                            int col) {
        DecimalFormat dollarSign =
                new DecimalFormat("$###.##");
        switch (col) {
            case 0:
                return (base.returnArray()[row]
                        .getProductTitle());

            case 1:
                return (base.returnArray()[row]
                        .getDescription());

            case 2:
                return (dollarSign.format(base
                        .returnArray()[row]
                        .getProductPrice()));

            case 3:
                return (base.returnArray()[row]
                        .getRating());

            default:
                throw new RuntimeException("Row,col out of range: "
                        + row + " " + col);
        }
    }
    /**************************************************************************
     *
     *   currentDescriptivesAtAGlance(int row, int col) returns the value or object
     *   at the given row and column of a table.
     *
     * @param row the row of the specified object or element that is desired,
     *            represented as an integer.
     *
     * @param col the column of the specified object or element that is desired,
     *            represented as an integer.
     *
     * @return an object that is at the given perceived row and perceived column
     * of the given database in order to make the model function.
     */
    private Object currentDescriptivesAtAGlance(int row,
                                                int col) {
        DecimalFormat dollarSign =
                new DecimalFormat("$###.##");
        switch (col) {
            case 0:
                return (base.returnArray()[row]
                        .getProductTitle());

            case 1:
                return (base.returnArray()[row]
                        .getUnitsOnHand());
            case 2:
                return (dollarSign.format(base.returnArray()[row]
                        .getShippingCost()));
            case 3:
                return (base.returnArray()[row]
                        .getLowerAgeInRange());

            case 4:
                return (base.returnArray()[row]
                        .getHigherAgeInRange());

            default:
                throw new RuntimeException("Row,col out of " +
                        "range: " + row + " " + col);
        }
    }
    /**************************************************************************
     *
     *   returnBase() - returns the STEMProductController object that will then
     *   give the access in order to access the array.
     *
     *
     * @return a STEMProductController object in order to easier get data..
     */
    public STEMProductController returnBase(){
        return base;
    }
    /**************************************************************************
     *
     *   getValueAt(int row, int col) - gets the particular value for what is
     *   the current row and column in order to then get back out the object in
     *   general. Needed for the model adn in general helps get values very
     *   quickly if used right.
     *
     * @param row the row of the specified object or element that is desired,
     *            represented as an integer.
     *
     * @param col the column of the specified object or element that is desired,
     *            represented as an integer.
     *
     *
     * @return an object as a result of the searching that has been done.
     * In light of providing the row and column with then being able to
     * search, there can be an object returned.
     */
    @Override
    public Object getValueAt(int row, int col) {
        switch (screen) {
            case ProductsAtAGlance:
                return currentProductsAtAGlance(row,
                        col);
            case ProductDescriptionsAtAGlance:s:
                return currentDescriptivesAtAGlance(row,
                        col);
        }
        throw new IllegalArgumentException();
    }
    /**************************************************************************
     *
     *   saveMethod() - flow for how there is supposed to be saving within the
     *   document in the sense of there being a method in order to do that as
     *   it is called for in the model.
     *
     * @param filename - string that simply carries the name of the file that
     *                 is being desired to be saved to or rather the file
     *                 in question.
     *
     * @param function - specifies which type of saving is done (database or
     *                 text file). This is an int to trip the if statement
     *                 with size to get to right function.
     *
     *
     */
   public void saveMethod(String filename,
                          int function){

        if(function==1){
            base.saveAsText(filename);
        }
       else if(function==2){
           base.saveDatabase(filename);
       }
   }

    /**************************************************************************
     *
     *   load() - flow for how there is supposed to be loading the
     *   document in the sense of there being a method in order to do that as
     *   it is called for in the model.
     *
     * @param filename - string that simply carries the name of the file that
     *                 is being desired to be saved to or rather the file
     *                 in question.
     *
     *
     *
     *
     */
   public void load(String filename){
        base.load(filename);
   }


}
