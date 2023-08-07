package project3;

import java.io.Serializable;
import java.util.Random;

/***********************************************************************
 * @Author Jacob Neiheisel
 * @Author Professor Ferguson (previous author)
 *
 * @version 4/13/2021
 *
 * The following is the My Single Without Tail Linked list class,
 * this class performs many functions and is an integral part of
 * the entire program.
 *
 * This class creates and initializes a single linked list that is
 * in an order where game rentals are on top and console rentals are
 * below. In that ordering, the game rentals are ordered by due date
 * and if two or more game rentals have the same due date, order goes
 * by the name of the renter. This is what this class helps to
 * accomplish. There are methods for addition, removal, size, getting
 * a particular node, constructing the list, displaying the list, and
 * representing the list as a string.
 ***********************************************************************/

public class MySingleWithOutTailLinkedList implements Serializable
{


    /**
     * The node that sits at the top of the list
     */

    private Node top;


    /**
     * Default Constructor for MySingleWithOutTailLinkedList
     */

    public MySingleWithOutTailLinkedList() {
        top = null;
    }


    /**************************************************************************
     *
     *   size() - returns the size of the MySingleWithOutTailLinkedList as an
     *   integer and counts all of the rental units in the
     *   MySingleWithOutTailLinkedList. Note that this method was not to be
     *   modified and Professor Ferguson wrote this method.
     *
     *
     *
     * @return total the total number of entries and rentals in the list
     * as an integer.
     */

    public int size() {
        if (top == null)
            return 0;

        int total = 0;
        Node temp = top;
        // keep traversing the list and adding to the total until the list end
        while (temp != null) {
            total++;
            temp = temp.getNext();
        }

        return total;
    }


    /**********************************************************************
     *
     *   clear() - clears the MySingleWithOutTailLinkedList by generating a
     *   random seed and then generating random numbers based on the size
     *   and finally, taking out the needed index that is going on. Note
     *   that this method was not to be modified and Professor Ferguson
     *   wrote this method.
     *
     *
     *
     *
     */

    public void clear () {
        Random rand = new Random();
        while (size() > 0) {
            int number = rand.nextInt(size());
            remove(number);
        }
    }


    /************************************************************************
     *
     *   add() - Adds the rental that is passed in as an input parameter into
     *   the MySingleWithOutTailLinkedList. A temporary node is placed at the
     *   top. If it is null, add it to the top. If the rental is a game and
     *   due back before top, alter the top. If top and rental are both
     *   Games or Consoles, both with equal due dates, order them with
     *   the helper method. If rental is due after the top and size is one,
     *   there is a method for that. There is also a case for a console being
     *   the rental and putting that in the list properly. This as well as
     *   when rental is a game as well.
     *
     *
     *
     * @param rental the unit begin rented as a rental
     */

    public void add(Rental rental) {
        Node temp = top;

        // No list
        if (top == null) {
            top = new Node(rental, null);
        }

        // Rental is a Game, and Rental goes on top of a game
        else if (rental instanceof Game &&
                top.getData().getDueBack().after(rental.getDueBack())
                && top.getData() instanceof Game) {

                top = new Node(rental, top);
        }

       //Rental is a game, need to see about top
        else if (rental instanceof Game &&
                top.getData().getDueBack().equals(rental.getDueBack()) &&
                top.getData() instanceof Game) {
            topDateGameConsoleOrderer(rental, temp);
        }

        // Rental is a Console, and Rental goes on top of a list of consoles
        else if (rental instanceof Console &&
                top.getData().getDueBack().after(rental.getDueBack()) &&
                top.getData() instanceof Console) {
            // since the rental is due back before top
            // make rental new top and top the next one
            top = new Node(rental, top);
        }

        //rental is console in list of consoles, with equal due dates
        else if (rental instanceof Console &&
                top.getData().getDueBack().equals(rental.getDueBack()) &&
                top.getData() instanceof Console) {
            //execute method to see about position
            topDateGameConsoleOrderer(rental, temp);
        }

        // Rental is a Game
        // Rental goes on top of another game only at the beginning
        else if (rental instanceof Game &&
                top.getData().getDueBack().before(rental.getDueBack()) &&
                size() == 1 && top.getData() instanceof Game) {
                //set the rental as the next from the top
                //quick worker when one other item
                top.setNext(new Node(rental, null));
        }



        // Rental is a Console
        // Rental goes on top of another Console only at the beginning
        else if (rental instanceof Console &&
                top.getData().getDueBack().before(rental.getDueBack()) &&
                size() == 1 && top.getData() instanceof Console) {
            //set the rental as the next from the top
            //quick worker when one other item
            top.setNext(new Node(rental, null));
        }

        // Rental is a game on top of consoles
        else if (rental instanceof Game &&
                top.getData() instanceof Console) {
            top = new Node(rental, top);
        }

        //  insert game in already formed list of games
        else if (rental instanceof Game) {
            gameSorter(rental, temp);
        }

        //  list of games, need to traverse to add consoles
        else if (rental instanceof Console) {
            // traverses the games
            while (temp.getNext() != null && (temp.getData()
                    instanceof Game) &&
                    (temp.getNext().getData() instanceof Game)) {
                temp = temp.getNext();
            }

            //case: console is the first to be added in the consoles
            if (temp.getNext() == null) {

                temp.setNext(new Node(rental, null));
            }
            //temp is the game before the rentals, so order the top
            // there are at least 2 entries
            else if (temp.getNext() != null &&
                    temp.getNext().getNext() != null) {
                onePlusConsoleOrdered(rental, temp);
            }
            //one console in the list, need to properly order
            else if (temp.getNext() != null &&
                    temp.getNext().getNext() == null) {
                oneListConsoleOrderer(rental, temp);
            }

        }
    }


    /************************************************************************
     *
     *   gameSorter() - called upon to sort the games in order. Starts off by
     *   seeing whether or not the rental in mind is due before the current
     *   one and the next one. Once it does that, it determines if there
     *   are dates being equal to one another and what position the
     *   renter name goes to in relation to the equal dates. Otherwise, it
     *   places the rental in its given position.
     *
     *
     *
     * @param rental the unit begin rented as a rental
     * @param temp the temporary node that is in question as a node
     */

    private void gameSorter(Rental rental, Node temp) {
        //traverse until dates equal or the dates are finally after the rental
        while (temp.getNext() != null &&
                temp.getNext().getData().getDueBack()
                        .before(rental.getDueBack()) &&
                temp.getNext().getData() instanceof Game) {
            temp = temp.getNext();
        }
        //if dates are equal and rental name comes before
        if(temp.getNext()!= null &&
                rental.getNameOfRenter().compareTo
                        (temp.getNext().getData().getNameOfRenter()) <= 0 &&
                rental.getDueBack().equals
                        (temp.getNext().getData().getDueBack())){
            temp.setNext(new Node(rental, temp.getNext()));
        }
        else{
            //traverse while dates are equal and the name comes after
            while (temp.getNext()!= null &&
                    rental.getNameOfRenter()
                            .compareTo(temp.getNext().getData()
                                    .getNameOfRenter()) > 0 &&
                    rental.getDueBack().equals(temp.getNext().getData()
                            .getDueBack())){
                temp = temp.getNext();
            }
            //set because date is after, dates are equal
            //name next renter comes after current name
            temp.setNext(new Node(rental, temp.getNext()));
        }
    }


    /***********************************************************************
     *
     *   topDateGameConsoleOrderer() - see about the temporary node vs
     *   current one and the next one in order to see if the next node needs
     *   to be gotten to or needs to be traversed to as the dates are equal
     *   and the name of renter comes after the next or current one. It
     *   then sees if the node is at the top and the comparison to the top
     *   to see if it is before or after to order correctly.
     *
     *
     *
     * @param rental the unit begin rented as a rental
     * @param temp the temporary node that is in question as a node
     */

    private void topDateGameConsoleOrderer(Rental rental, Node temp) {
        //traverse list to get to right instance and lettering
        while (temp.getNext() != null &&
                rental.getNameOfRenter().compareTo(temp.getNext().getData()
                        .getNameOfRenter()) > 0 &&
                rental.getDueBack()
                        .equals(temp.getNext().getData().getDueBack())) {
            temp = temp.getNext();
        }
        //stopped at top and name at top is before rental name
        if (temp == top && rental.getNameOfRenter()
                .compareTo(temp.getData().getNameOfRenter()) <= 0) {
            top = new Node(rental, top);
        }
        //stopped at top and name at top is after rental name
        else if (temp == top && rental.getNameOfRenter()
                .compareTo(temp.getData().getNameOfRenter()) > 0) {
            temp.setNext(new Node(rental, temp.getNext()));
        }
        // non top case and in the correct order
        else {
            temp.setNext(new Node(rental, temp.getNext()));
        }
    }


    /*************************************************************************
     *
     *   onePlusConsoleOrdered() - helper method for the ordering of a console
     *   rental and list with more than one console and a more than
     *   two deep null. First the consoles are traversed to get the rental
     *   slotted in the correct place. Once the date is proper, then it can be
     *   seen if the node needs to go on the bottom due to due date
     *   or even slotted properly with equal dates.
     *   Ultimately, if the next node needs to go on the bottom due to date,
     *   a case exists for that.
     *
     *
     *
     * @param rental the unit begin rented as a rental
     * @param temp the temporary node that is in question as a node
     */

    private void onePlusConsoleOrdered(Rental rental, Node temp) {
        //no more consoles that are due before the rental after loop
        //make sure not to fall off list
        while (temp.getNext().getNext() != null &&
                rental.getDueBack()
                        .after(temp.getNext().getData().getDueBack())) {
            temp = temp.getNext();
        }

        //temp not changed and rental is due before the final list item
        if(temp.getNext().getData().getDueBack().after(rental.getDueBack())){
            temp.setNext(new Node(rental, temp.getNext()));
        }
        //temp is positioned correctly, can be before due back
        //accounts for equal names and to get in order right
        else {
            while (temp.getNext() != null &&
                    temp.getNext().getData().getDueBack()
                            .equals(rental.getDueBack()) &&
                    rental.getNameOfRenter().compareTo(temp.getNext()
                                    .getData().getNameOfRenter()) > 0){
                temp = temp.getNext();
            }
            if(temp.getNext() != null && temp.getNext().getData()
                    .getDueBack().before(rental.getDueBack())){
                temp = temp.getNext();
            }
            temp.setNext(new Node(rental, temp.getNext()));
        }
    }


    /*************************************************************************
     *
     *   oneListConsoleOrderer() - helper method for ordering a console rental
     *   where there is one other console rental only. It determines whether
     *   or not the rental in question goes on top or on bottom of the one
     *   console that is already in the list.
     *
     *
     *
     * @param rental the unit begin rented as a rental
     * @param temp the temporary node that is in question as a node
     */

    private void oneListConsoleOrderer(Rental rental, Node temp) {
        //date of rental comes before temp date
        if(rental.getDueBack()
                .before(temp.getNext().getData().getDueBack())){
            temp.setNext(new Node(rental, temp.getNext()));
        }

        //dates are equal to one another
        else if(rental.getDueBack()
                .equals(temp.getNext().getData().getDueBack())){
           //dates equal and name comes after
            if(rental.getNameOfRenter()
                    .compareTo(temp.getNext().getData()
                            .getNameOfRenter()) > 0){
                temp = temp.getNext();
                temp.setNext(new Node (rental, temp.getNext()));
            }
            //dates equal and name comes before
            else{
                temp.setNext(new Node (rental, temp.getNext()));
            }
        }
        //date after, move to the next node and add
        else{
            temp = temp.getNext();
            temp.setNext(new Node(rental, null));
        }
    }


    /*************************************************************************
     *
     *    remove() - removes the node at the given index in the input
     *    parameter. Traverses the list in order to get to before the correct
     *    node and then sets that node to the next appropriate node.
     *
     *
     *
     * @param index the index of the unit wanted for removal, beginning at 0
     *              and expressed as an integer.
     * @throws IllegalArgumentException if index < 0 or index >= size of list
     * @return temp Temp is a rental that is returned once the node before has
     * been reached.
     */

    public Rental remove(int index) {
        Rental temp = null;
        Node temporaryNode = top;

        //throw an exception if
        if(index < 0 || index >= size()){
            throw new IllegalArgumentException();
        }

        else if (index == 0 && temporaryNode != null){
            temp = temporaryNode.getData();
            top = temporaryNode.getNext();
        }
        else if (index == 0 && temporaryNode == null){
            throw new IllegalArgumentException();
        }
        else{
            int tempCounter = index;
            while (tempCounter > 1){
                temporaryNode = temporaryNode.getNext();
                tempCounter--;
            }
            temp = temporaryNode.getNext().getData();
            temporaryNode.setNext(temporaryNode.getNext().getNext());
        }
        return temp;

    }


    /*************************************************************************
     *
     *    get() - Obtains for the user the node at the specific index and then
     *    gets the rental information at that node.
     *
     *
     *
     * @param index the index of the unit wanted for removal, beginning at 0
     *              and expressed as an integer.
     * @return returner Returner is what houses the rental that is gotten in
     * the method. It is returned at the end of the method with the
     * rental info.
     * @throws IllegalArgumentException if the top is null and any index
     * greater than zero is called or if the index is less than zero or
     * greater than or equal to the list length.
     */

    public Rental get(int index) {
        Node temp = top;
        Rental returner = null;
        //start at the top, see if null
        if (top == null)
            return null;
        //if top is null and index greater than 0, throw exception
        else if(temp == null && index > 0){
            throw new IllegalArgumentException();
        }
        //if top is not null and index is 0, return top
        else if(index == 0 && temp !=null){
            returner = temp.getData();
        }
        //if there is something at the top and the index is within the list
        else if(temp != null && index > 0 && index < size()) {
            int tempCounter = index;
            while (tempCounter > 0) {
                temp = temp.getNext();
                tempCounter--;
            }
            returner = temp.getData();
        }
        else if(index < 0 || index >= size()){
            throw new IllegalArgumentException();
        }

        return returner;
    }


    /*************************************************************************
     *
     *    display() - A method that continuously prints out the data of the
     *    MySingleWithOutTailLinkedList for all the elements in the list. This
     *    method was written by Professor Ferguson and remains
     *    unchanged due to its use in displaying and running the code
     *    properly.
     *
     *
     *
     *
     */

    public void display() {
        Node temp = top;
        while (temp != null) {
            System.out.println(temp.getData());
            temp = temp.getNext();
        }
    }


    /*************************************************************************
     *
     *    toString() - A method that formulates a summary string of the
     *    MySingleWithOutTailLinkedList and prints that it is a linked list,
     *    the top node address, and the size.
     *
     *
     *
     * @return a pre-built string, a string displaying the Linked List,
     * top node address, and size
     */

    @Override
    public String toString() {
        return "LL {" +
                "top=" + top +
                ", size=" + size() +
                '}';
    }
}

