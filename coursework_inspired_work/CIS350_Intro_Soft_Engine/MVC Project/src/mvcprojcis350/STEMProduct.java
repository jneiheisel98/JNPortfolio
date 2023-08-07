package mvcprojcis350;


import java.io.Serializable;
import java.text.DecimalFormat;

/***********************************************************************
 * @Author Jacob Neiheisel
 *
 * @version 16 November 2021
 *
 * The following is the STEMProduct class that simply assigns and
 * maintains each database entry for the storefront and is mainly
 * concerned with providing the framework to house each parameter
 * and value that is pertinent to the given object within the
 * framework.
 *
 * This class holds the database data and ensures its maintainability
 * in the sense of housing all the set and get methods in addition to
 * the instance variables that comprise of a complete and full product
 * entry.
 ***********************************************************************/

public class STEMProduct implements Serializable {
    /**
     * Name for the STEMProduct
     */
    private String productTitle;
    /**
     * Price for the STEMProduct
     */
    private double productPrice;
    /**
     * description for the STEMProduct
     */
    private String description;
    /**
     * rating for the the STEMProduct
     */
    private double rating;
    /**
     * Minimum best age for the STEMProduct
     */
    private int lowerAgeInRange;
    /**
     * Maximum best age for the STEMProduct
     */
    private int higherAgeInRange;
    /**
     * Description for the product
     * function for the STEMProduct
     */
    private String functionDescription;
    /**
     * Cost for shipping
     * the STEMProduct
     */
    private double shippingCost;
    /**
     * Amount of STEMProducts that
     * are on hand or in stock.
     */
    private int unitsOnHand;

    /**************************************************************************
     *
     *   setProductTitle(String productTitle) - Changes the title of the
     *   product given that there is a string provided for the method.
     *
     * @param productTitle -  Parameter is named selected to productTitle as it
     *                     is to be the title of the product that is to be
     *                     changed into for the given object, in string form.
     *
     */

    /**
     * Default constructor for STEMProduct
     */

    public STEMProduct (){
        productTitle = "Untitled";
        productPrice = 0.00;
        description = "No description";
        lowerAgeInRange = 0;
        higherAgeInRange = 20;
        rating = 0.00;
        functionDescription = "No description set";
    }

    /**
     * Other constructor for STEMProduct
     *
     * @param higherEndAge - Higher age of the ages for
     *                     range of ages for the
     *                     STEMProduct as an integer.
     * @param lowerEndAge  - Lower age of the ages for
     *                       range of ages for the
     *                       STEMProduct as an integer.
     * @param productDescription - Description of
     *                           the STEMProduct
     *                           that is in question
     *                           as a string.
     * @param productFunction - Function description
     *                        of the STEMProduct that
     *                        is in question as a
     *                        string.
     * @param productPrice - Price of the STEMProduct
     *                     that is in question as
     *                     a double.
     * @param productRating - Rating of the STEMProduct
     *                      that is in question as a
     *                      double.
     * @param shippingCost - Price for shipping the
     *                     STEMProduct that is in
     *                     question as a double.
     * @param titleOfProduct - Title for the
     *                       STEMProduct in
     *                       question as a
     *                       string.
     * @param unitsOnHand - How many units are on
     *                    hand for the STEMProduct
     *                    as represented by an integer.
     */


    public STEMProduct (String titleOfProduct,
                        double productPrice,
                        String productDescription,
                        double productRating, int lowerEndAge,
                        int higherEndAge,
                        String productFunction,
                        double shippingCost, int unitsOnHand){
        productTitle = titleOfProduct;
        this.productPrice = productPrice;
        description = productDescription;
        rating = productRating;
        lowerAgeInRange = lowerEndAge;
        higherAgeInRange = higherEndAge;
        functionDescription = productFunction;
        this.shippingCost = shippingCost;
        this.unitsOnHand = unitsOnHand;
    }

    public void setTitle(String productTitle){
        this.productTitle = productTitle;
    }

    /**************************************************************************
     *
     *   setDescription(String description) - Changes the description of the
     *   product given that there is a string provided for the method.
     *
     * @param description -  Parameter is named selected to description as it
     *                     is to be the description of the product that is to be
     *                     changed into for the given object and is a string.
     *
     */



    public void setDescription(String description){
        this.description = description;
    }

    /**************************************************************************
     *
     *   setProductPrice(double productPrice) - Changes the price of the
     *   product given that there is a double provided for the method.
     *
     * @param productPrice -  Parameter is named selected to productPrice as it
     *                     is to be the price of the product that is to be
     *                     changed into for the given object, represented as a
     *                     double.
     *
     */

    public void setPrice(double productPrice){
        this.productPrice = productPrice;
    }

    /**************************************************************************
     *
     *   setLowerAge(int lowerAge) - Changes the lower age of the
     *   product that is recommended given that there is an integer provided
     *   for the method.
     *
     * @param lowerAge -  Parameter is named selected to lowerAge as it
     *                     is to be the recommended lowerAge of the product
     *                     that is to be changed into for the given object,
     *                     represented as an integer.
     *
     */

    public void setLowerAge(int lowerAge){
        this.lowerAgeInRange = lowerAge;
    }

    /**************************************************************************
     *
     *   setLowerAge(int higherAge) - Changes the higher age of the
     *   product that is recommended given that there is an integer provided
     *   for the method.
     *
     * @param higherAge -  Parameter is named selected to higherAge as it
     *                     is to be the recommended higherAge of the product
     *                     that is to be changed into for the given object,
     *                     represented as an integer.
     *
     */

    public void setHigherAge(int higherAge){
        this.higherAgeInRange = higherAge;
    }

    /**************************************************************************
     *
     *   setRating(double rating) - Changes the rating of the
     *   product given that there is a double provided for the method.
     *
     * @param rating -  Parameter is named selected to rating as it
     *                     is to be the rating of the product that is to be
     *                     changed into for the given object, represented as a
     *                     double.
     *
     */

    public void setRating(double rating){
        this.rating = rating;
    }

    /**************************************************************************
     *
     *   setDescription(String functionDescription) - Changes the function
     *   description of the product given that there is a string provided
     *   for the method.
     *
     * @param functionDescription -  Parameter is named selected to
     *                            function description as it is to be
     *                            the functional description of the product
     *                            that is to be changed into for the given
     *                            object and is a string.
     *
     */

    public void setFunctionDescription(String functionDescription){
        this.functionDescription = functionDescription;
    }

    /**************************************************************************
     *
     *   setShippingCost(double shippingCost) - Changes the shipping cost
     *   of the product given that there is a double provided for the method.
     *
     * @param shippingCost -  Parameter is named selected to shippingCost as it
     *                     is to be the shipping cost of the product that
     *                     is to be changed into for the given object,
     *                     represented as a double.
     *
     */

    public void setShippingCost(double shippingCost) {
        this.shippingCost = shippingCost;
    }

    /**************************************************************************
     *
     *   setUnitsOnHand(int unitsOnHand) - Changes the number of units that are
     *   on hand or in stock for the given object. This parameter is
     *   represented by an integer
     *
     * @param unitsOnHand -  Parameter is named selected to unitsOnHand as it
     *                     is to be the stock number of the product that is to be
     *                     changed into for the given object, represented as a
     *                     integer.
     *
     */

    public void setUnitsOnHand(int unitsOnHand) {
        this.unitsOnHand = unitsOnHand;
    }

    /**************************************************************************
     *
     *   getProductTitle() - Returns the STEMProduct's title as an string
     *   for the given object.
     *
     *
     *
     * @return The STEMProduct's title as a string
     */

    public String getProductTitle() {
        return productTitle;
    }

    /**************************************************************************
     *
     *   getProductPrice() - Returns the STEMProduct's price as an double
     *   for the given object.
     *
     *
     *
     * @return The STEMProduct's price as a double
     */

    public double getProductPrice() {
        return productPrice;
    }

    /**************************************************************************
     *
     *   getRating() - Returns the STEMProduct's rating as an double
     *   for the given object.
     *
     *
     *
     * @return The STEMProduct's rating as a double
     */

    public double getRating() {
        return rating;
    }

    /**************************************************************************
     *
     *   getHigherAgeInRange() - Returns the STEMProduct's higher end
     *   recommended age for the product as an integer.
     *
     *
     *
     * @return The STEMProduct's higher end recommended product age as an
     * integer.
     */

    public int getHigherAgeInRange() {
        return higherAgeInRange;
    }

    /**************************************************************************
     *
     *   getLowerAgeInRange() - Returns the STEMProduct's lower end
     *   recommended age for the product as an integer.
     *
     *
     *
     * @return The STEMProduct's lower end recommended product age as an
     * integer.
     */

    public int getLowerAgeInRange() {
        return lowerAgeInRange;
    }

    /**************************************************************************
     *
     *   getDescription() - Returns the STEMProduct's description for the
     *   given product as a string.
     *
     *
     *
     * @return The STEMProduct's description for the product as a string.
     */

    public String getDescription() {
        return description;
    }

    /**************************************************************************
     *
     *   getFunctionDescription() - Returns the STEMProduct's function
     *   description for the given product as a string.
     *
     *
     *
     * @return The STEMProduct's function description for the product as a string.
     */


    public String getFunctionDescription() {
        return functionDescription;
    }

    /**************************************************************************
     *
     *   getShippingCost() - Returns the STEMProduct's shipping cost for the
     *   given product as a double.
     *
     *
     *
     * @return The STEMProduct's shipping cost for the product as a double.
     */

    public double getShippingCost() {
        return shippingCost;
    }

    /**************************************************************************
     *
     *   getUnitsOnHand() - Returns the STEMProduct's stock level for the
     *   given product as an integer
     *
     *
     *
     * @return The STEMProduct's stock level for the product as an integer.
     */

    public int getUnitsOnHand() {
        return unitsOnHand;
    }



    /*************************************************************************
     *
     *    toString() - A method that formulates a string of the
     *    product, printing out every piece of information: name, price,
     *    description, rating, youngest age of user recommended, highest age
     *    of user recommended, function description, shipping cost,
     *    and stock amount.
     *
     *
     *
     * @return a pre-built string, including these elements: name, price,
     * description, rating, youngest age of user recommended, highest age
     * of user recommended, function description, shipping cost,
     * and stock amount.
     */

@Override
    public String toString(){
    DecimalFormat dollarSign =
            new DecimalFormat("$###.##");
        return "Product Name: "+ getProductTitle()
                + " Product price: " + dollarSign
                .format(getProductPrice())
                + " Product description: "
                + getDescription() + " Rating: "
                + getRating() + " Good for people: "
                + getLowerAgeInRange() + " to "
                + getHigherAgeInRange() + " years old. " +
                "Product function: "
                + getFunctionDescription()
                + " Cost to ship: "
                + dollarSign.format(getShippingCost())
                + " Number in stock: " + getUnitsOnHand();
}
}
