package mvcprojcis350;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
 * The following is the STEMProductView class that simply provides
 * the the view for the GUI. It houses many of the GUI buttons and
 * essentially is what the consumer will be interacting with aside
 * from the STEMProductModel class for the view overall.
 *
 * This class holds all the elements needed for its successful user
 * interface hosted in the Java GUI in the sense it has all menus and
 * buttons.
 ***********************************************************************/

public class STEMProductView extends
        JFrame implements ActionListener {
    /**
     * Menu bar at the top.
     */
    private JMenuBar menus;

    /**
     * Sub-section of the menu bar at the
     * top having to do with saves and opens.
     */
    private JMenu fileMenu;

    /**
     * Sub-section of the menu bar at the
     * top having to do with saves and opens.
     */
    private JMenu viewMenu;

    /**
     * Menu bar having to do with seeing
     * more details.
     */
    private JMenu actionMenu;
    /**
     * Section in file menu to open a
     * database.
     */
    private JMenuItem openSerItem;
    /**
     *Button to exit the storefront.
     */
    private JMenuItem exitItem;
    /**
     * Saves the item as a database file,
     * section in file menu
     */
    private JMenuItem saveSerItem;
    /**
     * Section in file menu to open a
     * text file.
     */
    private JMenuItem openTextItem;
    /**
     * Section in file menu to save a
     * text file.
     */
    private JMenuItem saveTextItem;
    /**
     * Section in action menu to see
     * more details.
     */
    private JMenuItem seeMoreDetails;
    /**
     * Menu item to go to the
     * quick glance sub-view.
     */
    private JMenuItem quickGlanceProductScreen;
    /**
     * Menu item to go to the
     * more information sub-view.
     */
    private JMenuItem moreProductInformationScreen;

    /**
     * Menu item to go to the
     * area to change the product
     * title by box
     */
    private JMenuItem productTitleChange;

    /**
     * Menu item to go to the
     * area to change the price
     * of a product
     */
    private JMenuItem productPriceChange;

    /**
     * Menu item to go to the
     * area to change the description
     * or the sales pitch of a product.
     */
    private JMenuItem productDescriptionChange;

    /**
     * Menu item to go to the
     * area to change the rating of a
     * product.
     */
    private JMenuItem productRatingChange;

    /**
     * Menu item to go to the
     * area to change the lowest
     * recommended age for a product
     */
    private JMenuItem productLowerAgeInRangeChange;

    /**
     * Menu item to go to the
     * more information sub-view.
     */
    private JMenuItem productHigherAgeInRangeChange;

    /**
     * Menu item to go to the
     * area to change the highest
     * recommended age for a product
     */
    private JMenuItem productFunctionDescriptionChange;

    /**
     * Menu item to go to the
     * area to change the cost
     * to ship a particular product
     */
    private JMenuItem productShippingCostChange;

    /**
     * Menu item to go to the
     * area to change the amount
     * of a particular product in stock
     */
    private JMenuItem productUnitsOnHandChange;


    /**
     * New panel to house everything
     */
    private JPanel panel;

    /**
     * Table to accommodate what is needed
     * for having the list model work.
     */
    private JTable productTable;

    /**
     * Scroller to be able to scroll within
     * the table
     */
    private JScrollPane listScroller;

    /**
     * Model to accommodate the list
     * model format for the table.
     */
    private STEMProductModel model;


    /*****************************************************************
     *
     * A constructor that starts a new GUI interface/defines gui value
     *
     *****************************************************************/
    public STEMProductView() {
        //adding menu bar and menu items
        menus = new JMenuBar();

        fileMenu = new JMenu("File");
        actionMenu = new JMenu("Action");
        viewMenu = new JMenu("Views");
        openSerItem = new JMenuItem("Open Serial File");
        exitItem = new JMenuItem("Exit");
        saveSerItem = new JMenuItem("Save Serial File");
        openTextItem = new JMenuItem("Open Text File");
        saveTextItem = new JMenuItem("Save Text File");

        seeMoreDetails = new JMenuItem("See more details");

        quickGlanceProductScreen = new JMenuItem("Products " +
                "at a glance");
        moreProductInformationScreen = new JMenuItem("Product " +
                "descriptions at a glance");

        productTitleChange = new JMenuItem("Change Product Title");
        productPriceChange = new JMenuItem("Change Product Price");
        productDescriptionChange = new JMenuItem("Change Product " +
                "Description");
        productUnitsOnHandChange = new JMenuItem("Change Product" +
                "Units on Hand");
        productFunctionDescriptionChange = new JMenuItem("Change" +
                "Function Description");
        productHigherAgeInRangeChange = new JMenuItem("Change " +
                "higher end in age range");
        productLowerAgeInRangeChange = new JMenuItem("Change " +
                "lower end in age range");
        productShippingCostChange = new JMenuItem("Change" +
                " in shipping cost");
        productRatingChange = new JMenuItem("Change" +
                " product rating");
        //adding items to bar
        fileMenu.add(openSerItem);
        fileMenu.add(saveSerItem);
        fileMenu.add(openTextItem);
        fileMenu.add(saveTextItem);
        fileMenu.addSeparator();
        fileMenu.addSeparator();
        fileMenu.add(exitItem);
        fileMenu.addSeparator();
        viewMenu.add(quickGlanceProductScreen);
        viewMenu.add(moreProductInformationScreen);
        actionMenu.add(productTitleChange);
        actionMenu.add(productPriceChange);
        actionMenu.add(productDescriptionChange);
        actionMenu.add(productUnitsOnHandChange);
        actionMenu.add(productFunctionDescriptionChange);
        actionMenu.add(productHigherAgeInRangeChange);
        actionMenu.add(productLowerAgeInRangeChange);
        actionMenu.add(productShippingCostChange);
        actionMenu.add(productRatingChange);


        actionMenu.add(seeMoreDetails);


        menus.add(fileMenu);
        menus.add(actionMenu);
        menus.add(viewMenu);

        openSerItem.addActionListener(this);
        saveSerItem.addActionListener(this);
        openTextItem.addActionListener(this);
        saveTextItem.addActionListener(this);
        exitItem.addActionListener(this);
        seeMoreDetails.addActionListener(this);

        productTitleChange.addActionListener(this);
        productPriceChange.addActionListener(this);
        productDescriptionChange.addActionListener(this);
        productUnitsOnHandChange.addActionListener(this);
        productFunctionDescriptionChange.addActionListener(this);
        productHigherAgeInRangeChange.addActionListener(this);
        productLowerAgeInRangeChange.addActionListener(this);
        productShippingCostChange.addActionListener(this);
        productRatingChange.addActionListener(this);


        quickGlanceProductScreen.addActionListener(this);
        moreProductInformationScreen.addActionListener(this);

        setJMenuBar(menus);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new JPanel();
        model = new STEMProductModel();

        productTable = new JTable(model);
       listScroller = new JScrollPane(productTable);
        listScroller.setPreferredSize(new Dimension(
                800, 800));
        panel.add(listScroller);
        add(panel);
        setVisible(true);
        setSize(950, 850);
        seeMoreDetails.setEnabled(false);
    }
    /**************************************************************************
     *
     *   actionPerformed(ActionEvent e) - determines what happened, what
     *   buttons were pressed, and what ended up happening in order for there
     *   to be the action that occurs
     *
     * @param e -  parameter is named e and is an ActionEvent that houses
     *          information for what happened
     *
     */
    public void actionPerformed(ActionEvent e) {
        Object comp = e.getSource();
 System.out.println(e.getSource().toString());

        if (quickGlanceProductScreen == comp) {

            model.setDisplay(STEMStoreEnums.ProductsAtAGlance);
            seeMoreDetails.setEnabled(false);
        }

        if (moreProductInformationScreen == comp) {
            model.setDisplay(STEMStoreEnums.
                    ProductDescriptionsAtAGlance);
            seeMoreDetails.setEnabled(true);
        }

        if (openSerItem == comp||openTextItem==comp) {
            JFileChooser chooser = new JFileChooser();
            int status = chooser.showOpenDialog(null);
            if (status == JFileChooser.APPROVE_OPTION) {
                String filename = chooser
                        .getSelectedFile().getAbsolutePath();
                model.load(filename);
            }



        }


        if(productTitleChange == comp){

            model.returnBase().setProductTitle();

        }
        if(productPriceChange ==comp){
            model.returnBase().setProductPrice();

        }
        if(productDescriptionChange ==comp){
            model.returnBase().setProductDescription();

        }
        if(productUnitsOnHandChange ==comp){
            model.returnBase().setProductUnitsOnHand();

        }
        if(productFunctionDescriptionChange ==comp){
            model.returnBase()
                    .setProductFunctionalDescription();

        }
        if(productHigherAgeInRangeChange ==comp){
            model.returnBase()
                    .setProductHigherAgeInRange();

        }
        if(productLowerAgeInRangeChange ==comp){
            model.returnBase()
                    .setProductLowerAgeInRange();

        }
        if(productShippingCostChange ==comp){
            model.returnBase().setProductShippingCost();

        }
        if(productRatingChange ==comp){
            model.returnBase().setProductRating();

        }


        if (saveTextItem == comp || saveSerItem == comp) {
            JFileChooser chooser = new JFileChooser();
            int status = chooser.showSaveDialog(null);
            if (status == JFileChooser.APPROVE_OPTION) {
                String filename = chooser
                        .getSelectedFile().getAbsolutePath();
                if (saveTextItem == e.getSource()) {
                    model.saveMethod(filename, 1);
                }
                else if (saveSerItem == e.getSource()) {
                    model.saveMethod(filename, 2);
                }

            }


        }

        if (e.getSource() == exitItem) {
            System.exit(1);
        }
        if (e.getSource() == seeMoreDetails) {
            int index = productTable.getSelectedRow();
            JOptionPane.showMessageDialog(null,
                    model.returnBase().returnArray()[index]
                            .getFunctionDescription());
        }

    }

    public static void main(String[] args) {
        new STEMProductView();
    }



}