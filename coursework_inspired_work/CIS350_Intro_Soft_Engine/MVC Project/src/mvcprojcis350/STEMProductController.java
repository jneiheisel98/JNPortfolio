package mvcprojcis350;
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
 * The following is the STEMProductController class that simply provides
 * the database entry for the storefront and is mainly concerned with
 * providing the user opportunities to quickly upload a database/input
 * item necessities.
 *
 * This class holds the database, enables the user to individually
 * hand-input the data, and allows the user to both load and save files
 * (they control the process when they press the save button from the
 * view)
 ***********************************************************************/
import javax.swing.*;
import java.text.DecimalFormat;
import java.util.*;
import java.io.*;

public class STEMProductController {

    /**
     * The storefront or the database/model that is being used for this
     */
    private STEMProduct[] storefront;

    /**
     * Default Constructor for MySingleWithOutTailLinkedList
     */
    public STEMProductController() {
        String userDir = System.getProperty("user.dir");
        JFileChooser fc = new JFileChooser(userDir);
        int num = JOptionPane.showConfirmDialog(
                null, "Do you have a file already",
                "file question", JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);
        if(num == JOptionPane.YES_OPTION){
            JOptionPane.showMessageDialog(
                    null, "You selected: Yes");
            int returnVal = fc.showOpenDialog(null);

            // did the user select a file?
            if (returnVal == JFileChooser.APPROVE_OPTION) {

                String filename = fc.getSelectedFile().getName();
                try {
                    load(filename);
                } catch (IllegalArgumentException e) {
                    load("|");
                }
            }
            else if (returnVal == JFileChooser.CANCEL_OPTION) {
                load("|");
            }
            else if (returnVal == JFileChooser.ERROR_OPTION) {
                load("|");
            }
        }else if (num == JOptionPane.NO_OPTION){
            JOptionPane.showMessageDialog(null,
                    "You selected: No");
            load("|");
        }
        //case if you put nothing in and hit cancel
        else if (num == JOptionPane.YES_OPTION){
            //JOptionPane.showMessageDialog(null, "You selected: No");
            load("|");
        }
        else if (num == JOptionPane.PLAIN_MESSAGE){
            load("|");
        }
    }

    /**************************************************************************
     *
     *   returnArray() - returns the array of STEMProducts (filled with
     *   STEMProducts) for the other classes such as the STEMProductView
     *   that might need the array for data processing. Provides valuable
     *   information for the STEMProductModel
     *
     *
     *
     * @return an array of each and every STEMProduct here in the controller,
     * given in an array of STEMProduct objects.
     */
    public STEMProduct[] returnArray() {
        return storefront;
    }


    /**************************************************************************
     *
     *   returnArray() - returns the array of STEMProducts (filled with
     *   STEMProducts) for the other classes such as the STEMProductView
     *   that might need the array for data processing. Provides valuable
     *   information for the STEMProductModel
     *
     * @param param -  parameter is named param and determines what information
     *              to volley over or carry over into the STEMProductModel class
     *              in order to best display it on the storefront screen.
     *
     * @return an array of each and every STEMProduct here in the controller,
     * given in an array of STEMProduct objects and the proper given information.
     */
    public String[] returnArray(int param) {
        String[] arrayReturnable = new String[storefront.length];
        if (param == 1) {
            int inserter;
            DecimalFormat dollarSign = new DecimalFormat("$###.##");
            for (inserter = 0; inserter < storefront.length; inserter++) {

                arrayReturnable[inserter] = storefront[inserter]
                        .getProductTitle()
                        + " " + storefront[inserter].getDescription()
                        + " MSRP:" + dollarSign.format(
                                storefront[inserter].getProductPrice())
                        + " Rating:" + storefront[inserter].getRating()
                        + " stars out of 5.0 stars";
            }
        } else if (param == 2) {
            int position;
            for (position = 0; position < storefront.length; position++){
                arrayReturnable[position] = "Units in stock" +
                        storefront[position].getUnitsOnHand() +
                        " Cost to ship:" +
                        storefront[position].getShippingCost() +
                        " Suitable for children from ages " +
                        storefront[position].getLowerAgeInRange() +
                        " to " + storefront[position].getHigherAgeInRange()
                        + " years old.";
            }
        }
        return arrayReturnable;
    }


    /**************************************************************************
     *
     *  load(String filename) - returns the array of STEMProducts (filled with
     *   STEMProducts) for the other classes such as the STEMProductView
     *   that might need the array for data processing. Provides valuable
     *   information for the STEMProductModel
     *
     * @param filename -  parameter is named param and determines what information
     *              to volley over or carry over into the STEMProductModel class
     *              in order to best display it on the storefront screen.
     *
     * @return an array of each and every STEMProduct here in the controller,
     * given in an array of STEMProduct objects and the proper given information.
     */
    public void load(String filename) {
        // create a scanner to determine if there are
        //entries at the top so that it knows how many
        int counter = 0;
        Scanner scanner = null;
        int entriesForArray;
        String entries = null;
        try {
            if (filename == null) {
                throw new IllegalArgumentException();
            }
            if (filename.equals("|")) {
                throw new IllegalAccessException();
            }
            scanner = new Scanner(new File(filename));
            //try to get the number first
            if (scanner.hasNext()) {
                entries = scanner.nextLine();
            }
            //if it can't get the entry number, assume database
            if (entries == null) {
                FileInputStream fis = new FileInputStream(filename);
                ObjectInputStream is = new ObjectInputStream(fis);
                storefront = (STEMProduct[]) is.readObject();
                is.close();
            }
            // create strings and then input correct information
            // to storefront
            else {

                String productTitle;
                String productDollarPrice;
                String description;
                String userRating;
                String lowerEndAgeInRange;
                String higherEndAgeInRange;
                String productFunctionDescription;
                String costToShip;
                String onHandUnits;

                int sizeOfText = 0;

                sizeOfText = Integer.parseInt(entries);
                storefront = new STEMProduct[sizeOfText];
                int count;
                for (count = 0; count < sizeOfText; count++) {
                    productTitle = scanner.nextLine();
                    productDollarPrice = scanner.nextLine();
                    description = scanner.nextLine();
                    userRating = scanner.nextLine();
                    lowerEndAgeInRange = scanner.nextLine();
                    higherEndAgeInRange = scanner.nextLine();
                    productFunctionDescription = scanner.nextLine();
                    costToShip = scanner.nextLine();
                    onHandUnits = scanner.nextLine();
                    storefront[count] = new STEMProduct(productTitle,
                            Double.parseDouble(productDollarPrice),
                            description, Double.parseDouble(userRating),
                            Integer.parseInt(lowerEndAgeInRange),
                            Integer.parseInt(higherEndAgeInRange),
                            productFunctionDescription,
                            Double.parseDouble(costToShip),
                            Integer.parseInt(onHandUnits));
                }

            }
        }
        //if something should go amiss from above or any
        //other line in code come down here to catch and
        // user gets chance again to re-input information
        // and if the user doesn't do it right in 2 tries,
        // the information comes in for them.

        catch (IllegalArgumentException ex) {

            try {
                entriesForArray = Integer.parseInt(JOptionPane
                        .showInputDialog(null,
                        "Input number of entries, " +
                                "press enter for sample",
                        "Customized Dialog",
                                JOptionPane.PLAIN_MESSAGE));


                storefront = new STEMProduct[entriesForArray];
                try {
                for (counter = 0; counter < entriesForArray;
                     counter++) {

                        String productTitle = JOptionPane
                                .showInputDialog(null,
                                "Input product title",
                                "Customized Dialog",
                                        JOptionPane.PLAIN_MESSAGE);
                        double cost = Double.parseDouble(JOptionPane
                                .showInputDialog(null,
                                "Input product cost",
                                "Customized Dialog",
                                        JOptionPane.PLAIN_MESSAGE));
                        String productDescription = JOptionPane
                                .showInputDialog(null,
                                "Input product description",
                                "Customized Dialog",
                                        JOptionPane.PLAIN_MESSAGE);
                        double rating = Double.parseDouble(JOptionPane
                                .showInputDialog(null,
                                "Input the product rating",
                                "Customized Dialog",
                                        JOptionPane.PLAIN_MESSAGE));
                        int shortAge = Integer.parseInt(JOptionPane
                                .showInputDialog(null,
                                "Input the small end of " +
                                        "the age range",
                                "Customized Dialog",
                                        JOptionPane.PLAIN_MESSAGE));
                        int tallAge = Integer.parseInt(JOptionPane
                                .showInputDialog(null,
                                        "Input high end of " +
                                                "the age range ",
                                "Customized Dialog",
                                        JOptionPane.PLAIN_MESSAGE));
                        String productFunction = JOptionPane
                                .showInputDialog(null,
                                "Input an extend functional " +
                                        "description of the product",
                                "Customized Dialog", JOptionPane
                                                .PLAIN_MESSAGE);
                        int stockUnits = Integer.parseInt(JOptionPane
                                .showInputDialog(null,
                                "Input number of units on hand",
                                "Customized Dialog",
                                        JOptionPane.PLAIN_MESSAGE));
                        double shippingCost = Double.parseDouble(JOptionPane
                                .showInputDialog(null,
                                "Input shipping cost",
                                "Customized Dialog",
                                        JOptionPane.PLAIN_MESSAGE));
                        storefront[counter] = new STEMProduct(productTitle,
                                cost, productDescription, rating,
                                shortAge, tallAge, productFunction,
                                shippingCost, stockUnits);

                    }
                }catch (NumberFormatException e) {
                        int forCounter;
                        for (forCounter = 0; forCounter < 3; forCounter++) {
                            storefront = new STEMProduct[3];
                            storefront = new STEMProduct[3];
                            storefront[0] = new STEMProduct("The Giga Gizmo",
                                    150.95,
                                    "Send yourself out of this world " +
                                            "with a gigantic exercise ball " +
                                            "and massager in one",
                                    4.0, 7, 80,
                                    "To provide soothing relief for the whole body by " +
                                            "giving someone a soft yet very comfortable and pulsating " +
                                            "place to relax. This ball will be perfect for that post " +
                                            "workout routine or any time that you want to sit and " +
                                            "just enjoy whatever is going on. It is also " +
                                            "customizable on the inside including " +
                                            "with more LED lights", 9.95, 20);
                            storefront[1] = new STEMProduct("One Fossilised Dinosaur",
                                    45.95, "Why did the dinosaurs " +
                                    "go extinct? Why do certain things happen the way that " +
                                    "they do here on earth? This kit explores it all and " +
                                    "more in the quest to answer burning questions before " +
                                    "the time of humans", 3.8, 2,
                                    20, "How the dinosaurs " +
                                    "went down still is a mystery. With this it, " +
                                    "users are able to figure out the freezing point " +
                                    "of water. They also have experiments dealing with " +
                                    "borax and corn starch. One experiment shows just " +
                                    "how fast magma turns to rock.", 6.95,
                                    38);
                            storefront[2] = new STEMProduct("Race Car Mania",
                                    85.95, "For the racing fan " +
                                    "in everyone! Perform a variety of experinece related " +
                                    "to racing and other racing effects such as drag, " +
                                    "friction, and acceleration in the quest to build a " +
                                    "fast and aerodynamic car and learning more about cars " +
                                    "and racing in general", 4.8, 10,
                                    35, "Rev up the engines! It " +
                                    "is time to experience how race car tires need just " +
                                    "the right amount of friction to run. Figure out what " +
                                    "makes an aerodynamic car in the quest to make the car " +
                                    "go as fast as possible. Launch the car and propel it on " +
                                    "to success on the race track!",
                                    12.95, 50);
                        }
                    } catch (Exception e) {
                        storefront[counter] = new STEMProduct();
                    }


                }
            catch (NumberFormatException e) {
                int forCounter;

                    storefront = new STEMProduct[3];

                    storefront[0] = new STEMProduct("The Giga Gizmo",
                            150.95,
                            "Send yourself out of this world " +
                                    "with a gigantic exercise ball " +
                                    "and massager in one",
                            4.0, 7, 80,
                            "To provide soothing relief for the whole body by " +
                                    "giving someone a soft yet very comfortable and pulsating " +
                                    "place to relax. This ball will be perfect for that post " +
                                    "workout routine or any time that you want to sit and " +
                                    "just enjoy whatever is going on. It is also " +
                                    "customizable on the inside including " +
                                    "with more LED lights", 9.95, 20);
                    storefront[1] = new STEMProduct("One Fossilised Dinosaur",
                            45.95, "Why did the dinosaurs " +
                            "go extinct? Why do certain things happen the way that " +
                            "they do here on earth? This kit explores it all and " +
                            "more in the quest to answer burning questions before " +
                            "the time of humans", 3.8, 2,
                            20, "How the dinosaurs " +
                            "went down still is a mystery. With this it, " +
                            "users are able to figure out the freezing point " +
                            "of water. They also have experiments dealing with " +
                            "borax and corn starch. One experiment shows just " +
                            "how fast magma turns to rock.", 6.95,
                            38);
                    storefront[2] = new STEMProduct("Race Car Mania",
                            85.95, "For the racing fan " +
                            "in everyone! Perform a variety of experinece related " +
                            "to racing and other racing effects such as drag, " +
                            "friction, and acceleration in the quest to build a " +
                            "fast and aerodynamic car and learning more about cars " +
                            "and racing in general", 4.8, 10,
                            35, "Rev up the engines! It " +
                            "is time to experience how race car tires need just " +
                            "the right amount of friction to run. Figure out what " +
                            "makes an aerodynamic car in the quest to make the car " +
                            "go as fast as possible. Launch the car and propel it on " +
                            "to success on the race track!",
                            12.95, 50);
                }
             catch (Exception e) {
                storefront = new STEMProduct[3];
                storefront[0] = new STEMProduct("The Giga Gizmo",
                        150.95,
                        "Send yourself out of this world " +
                                "with a gigantic exercise ball " +
                                "and massager in one",
                        4.0, 7, 80,
                        "To provide soothing relief for the whole body by " +
                                "giving someone a soft yet very comfortable and pulsating " +
                                "place to relax. This ball will be perfect for that post " +
                                "workout routine or any time that you want to sit and " +
                                "just enjoy whatever is going on. It is also " +
                                "customizable on the inside including " +
                                "with more LED lights", 9.95, 20);
                storefront[1] = new STEMProduct("One Fossilised Dinosaur",
                        45.95, "Why did the dinosaurs " +
                        "go extinct? Why do certain things happen the way that " +
                        "they do here on earth? This kit explores it all and " +
                        "more in the quest to answer burning questions before " +
                        "the time of humans", 3.8, 2,
                        20, "How the dinosaurs " +
                        "went down still is a mystery. With this it, " +
                        "users are able to figure out the freezing point " +
                        "of water. They also have experiments dealing with " +
                        "borax and corn starch. One experiment shows just " +
                        "how fast magma turns to rock.", 6.95,
                        38);
                storefront[2] = new STEMProduct("Race Car Mania",
                        85.95, "For the racing fan " +
                        "in everyone! Perform a variety of experinece related " +
                        "to racing and other racing effects such as drag, " +
                        "friction, and acceleration in the quest to build a " +
                        "fast and aerodynamic car and learning more about cars " +
                        "and racing in general", 4.8, 10,
                        35, "Rev up the engines! It " +
                        "is time to experience how race car tires need just " +
                        "the right amount of friction to run. Figure out what " +
                        "makes an aerodynamic car in the quest to make the car " +
                        "go as fast as possible. Launch the car and propel it on " +
                        "to success on the race track!",
                        12.95, 50);
            }

        }


        catch (Exception ex) {
            try {
                entriesForArray = Integer.parseInt(JOptionPane
                        .showInputDialog(null,
                        "Input number of entries, " +
                                "press enter for sample",
                        "Customized Dialog",
                                JOptionPane.PLAIN_MESSAGE));

                storefront = new STEMProduct[entriesForArray];
                for (counter = 0; counter < entriesForArray;
                     counter++) {
                    try {
                        String productTitle = JOptionPane
                                .showInputDialog(null,
                                "Input product title",
                                "Customized Dialog",
                                        JOptionPane.PLAIN_MESSAGE);
                        double cost = Double.parseDouble(JOptionPane
                                .showInputDialog(null,
                                "Input product cost",
                                "Customized Dialog",
                                        JOptionPane.PLAIN_MESSAGE));
                        String productDescription = JOptionPane
                                .showInputDialog(null,
                                "Input product description",
                                "Customized Dialog", JOptionPane
                                                .PLAIN_MESSAGE);
                        double rating = Double.parseDouble(JOptionPane
                                .showInputDialog(null,
                                "Input the product rating",
                                "Customized Dialog",
                                        JOptionPane.PLAIN_MESSAGE));
                        int shortAge = Integer.parseInt(JOptionPane
                                .showInputDialog(null,
                                "Input the small end " +
                                        "of the age range",
                                "Customized Dialog",
                                        JOptionPane.PLAIN_MESSAGE));
                        int tallAge = Integer.parseInt(JOptionPane
                                .showInputDialog(null,
                                "Input high end " +
                                        "of the age range ",
                                "Customized Dialog",
                                        JOptionPane.PLAIN_MESSAGE));
                        String productFunction = JOptionPane
                                .showInputDialog(null,
                                "Input an extend functional " +
                                        "description of the product",
                                "Customized Dialog",
                                        JOptionPane.PLAIN_MESSAGE);
                        int stockUnits = Integer.parseInt(JOptionPane
                                .showInputDialog(null,
                                "Input number of units on hand",
                                "Customized Dialog",
                                        JOptionPane.PLAIN_MESSAGE));
                        double shippingCost = Double.parseDouble(
                                JOptionPane
                                        .showInputDialog
                                                (null,
                                "Input shipping cost",
                                "Customized Dialog", JOptionPane
                                                                .PLAIN_MESSAGE));
                        storefront[counter] = new STEMProduct(productTitle, cost,
                                productDescription, rating, shortAge,
                                tallAge, productFunction,
                                shippingCost, stockUnits);
                    } catch (Exception e) {
                        storefront[counter] = new STEMProduct();
                    }
                }

            } catch (NumberFormatException e) {
                int forCounter;
                storefront = new STEMProduct[3];
                storefront[0] = new STEMProduct("The Giga Gizmo",
                        150.95,
                        "Send yourself out of this world " +
                                "with a gigantic exercise ball " +
                                "and massager in one",
                        4.0, 7, 80,
                        "To provide soothing relief for the whole body by " +
                                "giving someone a soft yet very comfortable and pulsating " +
                                "place to relax. This ball will be perfect for that post " +
                                "workout routine or any time that you want to sit and " +
                                "just enjoy whatever is going on. It is also " +
                                "customizable on the inside including " +
                                "with more LED lights", 9.95, 20);
                storefront[1] = new STEMProduct("One Fossilised Dinosaur",
                        45.95, "Why did the dinosaurs " +
                        "go extinct? Why do certain things happen the way that " +
                        "they do here on earth? This kit explores it all and " +
                        "more in the quest to answer burning questions before " +
                        "the time of humans", 3.8, 2,
                        20, "How the dinosaurs " +
                        "went down still is a mystery. With this it, " +
                        "users are able to figure out the freezing point " +
                        "of water. They also have experiments dealing with " +
                        "borax and corn starch. One experiment shows just " +
                        "how fast magma turns to rock.", 6.95,
                        38);
                storefront[2] = new STEMProduct("Race Car Mania",
                        85.95, "For the racing fan " +
                        "in everyone! Perform a variety of experinece related " +
                        "to racing and other racing effects such as drag, " +
                        "friction, and acceleration in the quest to build a " +
                        "fast and aerodynamic car and learning more about cars " +
                        "and racing in general", 4.8, 10,
                        35, "Rev up the engines! It " +
                        "is time to experience how race car tires need just " +
                        "the right amount of friction to run. Figure out what " +
                        "makes an aerodynamic car in the quest to make the car " +
                        "go as fast as possible. Launch the car and propel it on " +
                        "to success on the race track!",
                        12.95, 50);
            }
        }
    }


    /**************************************************************************
     *
     *   saveDatabase(String filename) - Saves the database as a serializable
     *   document file (is not a text file viewable in notepad) so that it can
     *   be opened at a later date.
     *
     * @param filename -  Name of the file that the user wants to save as
     *                 inputted into the method.
     *
     */

    public void saveDatabase(String filename) {
        try {
            FileOutputStream fos = new FileOutputStream(filename);
            ObjectOutputStream os = new ObjectOutputStream(fos);
            System.out.println(storefront.toString());
            os.writeObject(storefront);
            os.flush();
            os.close();
        } catch (IOException ex) {
            throw new RuntimeException("Saving problem! ");
        }
    }

    /**************************************************************************
     *
     *   saveAsText(String filename) - Saves the database as a text
     *   document file (text file viewable in notepad) so that it can
     *   be opened at a later date.
     *
     * @param filename -  Name of the file that the user wants to save as
     *                 inputted into the method.
     *
     * @return true or false boolean that says whether or not the process
     * was a successful one or not or tells if there was any errors in the data\
     * in general.
     */

    public boolean saveAsText(String filename) {
            if (filename.equals("")) {
                throw new IllegalArgumentException();
            }

            try {
                PrintWriter out = new PrintWriter(new BufferedWriter(
                        new FileWriter(filename)));
                out.println(storefront.length);
                for (int i = 0; i < storefront.length; i++) {
                    STEMProduct product = storefront[i];
                    out.println(product.getProductTitle());
                    out.println(product.getProductPrice());
                    out.println(product.getDescription());
                    out.println(product.getRating());
                    out.println(product.getLowerAgeInRange());
                    out.println(product.getHigherAgeInRange());
                    out.println(product.getFunctionDescription());
                    out.println(product.getShippingCost());
                    out.println(product.getUnitsOnHand());

                }
                out.close();
                return true;
            } catch (IOException ex) {
                return false;
            }

        }

    /**************************************************************************
     *
     *   setProductTitle() - sets the title for the STEMProduct in question as
     *   determined by the JOptionPanes and dialog boxes for the user that pop
     *   up. Error checking is included.
     *
     *
     *
     */

    public void setProductTitle(){

        try {
            int item = Integer.parseInt(JOptionPane.showInputDialog(null,
                    "Item you want modified ",
                    "Customized Dialog", JOptionPane.PLAIN_MESSAGE));
            String productTitle= JOptionPane.showInputDialog(null,
                    "Retype product title",
                    "Customized Dialog", JOptionPane.PLAIN_MESSAGE);
            STEMProduct product = storefront[item-1];
            product.setTitle(productTitle);
        }
        catch(NumberFormatException e){

        }
        catch(Exception e){

        }

    }

    /**************************************************************************
     *
     *   setProductPrice() - sets the price for the STEMProduct in question as
     *   determined by the JOptionPanes and dialog boxes for the user that pop
     *   up. Error checking is included.
     *
     *
     *
     */

    public void setProductPrice(){
        try {
            int item = Integer.parseInt(JOptionPane.showInputDialog(null,
                    "Item you want modified ",
                    "Customized Dialog", JOptionPane.PLAIN_MESSAGE));
           double productPrice= Double.parseDouble(JOptionPane.showInputDialog(null,
                   "Type Product Price",
                   "Customized Dialog", JOptionPane.PLAIN_MESSAGE));
            STEMProduct product = storefront[item-1];
            product.setPrice(productPrice);
        }
        catch(NumberFormatException e){

        }
        catch(Exception e){

        }

    }

    /**************************************************************************
     *
     *   setProductDescription() - sets the description for the STEMProduct
     *   in question as determined by the JOptionPanes and dialog boxes for
     *   the user that pop up. Error checking is included.
     *
     *
     *
     */

    public void setProductDescription(){
        try {
            int item = Integer.parseInt(JOptionPane.showInputDialog(null,
                    "Item you want modified ",
                    "Customized Dialog", JOptionPane.PLAIN_MESSAGE));
            String productDescription= JOptionPane.showInputDialog(null,
                    "Retype product description",
                    "Customized Dialog", JOptionPane.PLAIN_MESSAGE);
            STEMProduct product = storefront[item-1];
            product.setDescription(productDescription);

        }
        catch(NumberFormatException e){

        }
        catch(Exception e){

        }

    }




    /**************************************************************************
     *
     *   setProductFunctionalDescription() - sets the functional description
     *   for the STEMProduct in question as determined by the JOptionPanes and
     *   dialog boxes for the user that pop up. Error checking is included.
     *
     *
     *
     */

    public void setProductFunctionalDescription(){
        try {
            int item = Integer.parseInt(JOptionPane.showInputDialog(null,
                    "Item you want modified ",
                    "Customized Dialog", JOptionPane.PLAIN_MESSAGE));
            String productFunctionalDescription= JOptionPane.showInputDialog(null,
                    "Retype product functional description",
                    "Customized Dialog", JOptionPane.PLAIN_MESSAGE);
            STEMProduct product = storefront[item-1];
            product.setFunctionDescription(productFunctionalDescription);

        }
        catch(NumberFormatException e){

        }
        catch(Exception e){

        }


    }

    /**************************************************************************
     *
     *   setProductRating() - sets the rating as a double for the STEMProduct
     *   in question as determined by the JOptionPanes and dialog boxes for
     *   the user that pop up. Error checking is included.
     *
     *
     *
     *
     */

    public void setProductRating(){
        try {
            int item = Integer.parseInt(JOptionPane.showInputDialog(null,
                    "Item you want modified ",
                    "Customized Dialog", JOptionPane.PLAIN_MESSAGE));
            double productRating= Double.parseDouble(JOptionPane.showInputDialog(null,
                    "Type Product Rating",
                    "Customized Dialog", JOptionPane.PLAIN_MESSAGE));
            STEMProduct product = storefront[item-1];
            product.setRating(productRating);
        }
        catch(NumberFormatException e){

        }
        catch(Exception e){

        }



    }

    /**************************************************************************
     *
     *   setProductLowerAgeInRange() - sets the lower age in the recommended
     *   age range for the STEMProduct in question as determined by the
     *   JOptionPanes and dialog boxes for the user that pop up. Error
     *   checking is included.
     *
     *
     *
     */

    public void setProductLowerAgeInRange(){
        try {
            int item = Integer.parseInt(JOptionPane.showInputDialog(null,
                    "Item you want modified ",
                    "Customized Dialog", JOptionPane.PLAIN_MESSAGE));
           int productLowerAge= Integer.parseInt(JOptionPane.showInputDialog(null,
                    "Type lower age in recommmended age",
                    "Customized Dialog", JOptionPane.PLAIN_MESSAGE));
            STEMProduct product = storefront[item-1];
            product.setLowerAge(productLowerAge);
        }
        catch(NumberFormatException e){

        }
        catch(Exception e){

        }

    }

    /**************************************************************************
     *
     *   setProductLowerAgeInRange() - sets the higher age in the recommended
     *   age range for the STEMProduct in question as determined by the
     *   JOptionPanes and dialog boxes for the user that pop up. Error
     *   checking is included.
     *
     *
     *
     */

    public void setProductHigherAgeInRange(){
        try {
            int item = Integer.parseInt(JOptionPane.showInputDialog(null,
                    "Item you want modified ",
                    "Customized Dialog", JOptionPane.PLAIN_MESSAGE));
            int productHigherAge= Integer.parseInt(JOptionPane.showInputDialog(null,
                    "Type Higher age in recommended age",
                    "Customized Dialog", JOptionPane.PLAIN_MESSAGE));
            STEMProduct product = storefront[item-1];
            product.setHigherAge(productHigherAge);
        }
        catch(NumberFormatException e){

        }
        catch(Exception e){

        }
    }

    /**************************************************************************
     *
     *   setProductShippingCost() - sets the shipping cost for the STEMProduct
     *   in question as determined by the JOptionPanes and dialog boxes for
     *   the user that pop up. Error checking is included.
     *
     *
     *
     */

    public void setProductShippingCost(){
        try {
            int item = Integer.parseInt(JOptionPane.showInputDialog(null,
                    "Item you want modified ",
                    "Customized Dialog", JOptionPane.PLAIN_MESSAGE));
            double costToShip= Double.parseDouble(JOptionPane.showInputDialog(null,
                    "Type Shipping cost",
                    "Customized Dialog", JOptionPane.PLAIN_MESSAGE));
            STEMProduct product = storefront[item-1];
            product.setShippingCost(costToShip);
        }
        catch(NumberFormatException e){

        }
        catch(Exception e){

        }
    }

    /**************************************************************************
     *
     *   setProductUnitsOnHand() - sets the units on hand for the STEMProduct
     *   in question as determined by the JOptionPanes and dialog boxes for
     *   the user that pop up. Error checking is included.
     *
     *
     *
     */

    public void setProductUnitsOnHand(){
        try {
            int item = Integer.parseInt(JOptionPane.showInputDialog(null,
                    "Item you want modified ",
                    "Customized Dialog", JOptionPane.PLAIN_MESSAGE));
            int productUnitsOnHand= Integer.parseInt(JOptionPane.showInputDialog(null,
                    "Type Higher age in recommended age",
                    "Customized Dialog", JOptionPane.PLAIN_MESSAGE));
            STEMProduct product = storefront[item-1];
            product.setUnitsOnHand(productUnitsOnHand);
        }
        catch(NumberFormatException e){

        }
        catch(Exception e){

        }



    }


    }


