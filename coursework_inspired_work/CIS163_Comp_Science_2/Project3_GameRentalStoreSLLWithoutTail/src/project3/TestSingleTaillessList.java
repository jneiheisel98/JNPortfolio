package project3;

import org.junit.Test;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import static org.junit.Assert.*;

public class TestSingleTaillessList {
    @Test
    public void sinpleAddTest(){
        SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        GregorianCalendar g1 = new GregorianCalendar();
        GregorianCalendar g2 = new GregorianCalendar();
        GregorianCalendar g3 = new GregorianCalendar();
        GregorianCalendar g4 = new GregorianCalendar();
        GregorianCalendar g5 = new GregorianCalendar();
        GregorianCalendar g6 = new GregorianCalendar();
        GregorianCalendar g7 = new GregorianCalendar();
        GregorianCalendar g8 = new GregorianCalendar();

        try {
            MySingleWithOutTailLinkedList bareList =
                    new MySingleWithOutTailLinkedList();
            Date d1 = df.parse("1/20/2020");
            g1.setTime(d1);
            Date d2 = df.parse("12/22/2020");
            g2.setTime(d2);
            Date d3 = df.parse("12/20/2019");
            g3.setTime(d3);
            Date d4 = df.parse("7/02/2020");
            g4.setTime(d4);
            Date d5 = df.parse("1/20/2010");
            g5.setTime(d5);
            Date d6 = df.parse("9/29/2020");
            g6.setTime(d6);
            Date d7 = df.parse("7/25/2020");
            g7.setTime(d7);
            Date d8 = df.parse("7/29/2020");
            g8.setTime(d8);

            Console console1 = new Console("Person1", g4, g6,
                    null, ConsoleTypes.PlayStation4);
            Console console2 = new Console("Person2", g5, g3,
                    null, ConsoleTypes.PlayStation4);
            Console console3 = new Console("Person5", g4, g8,
                    null, ConsoleTypes.SegaGenesisMini);
            Console console4 = new Console("Person6", g4, g7,
                    null, ConsoleTypes.SegaGenesisMini);


            Game game1 = new Game("Person1", g3, g2,
                    null, "title1",
                    new Console("Person1", g3, g2,
                            null, ConsoleTypes.PlayStation4));
            Game game2 = new Game("Person1", g3, g1,
                    null, "title2",
                    new Console("Person1", g3, g1,
                            null, ConsoleTypes.PlayStation4));
            Game game3 = new Game("Person1", g5, g3,
                    null, "title2",
                    new Console("Person1", g5, g3,
                            null, ConsoleTypes.SegaGenesisMini));
            Game game4 = new Game("Person7", g4, g8,
                    null, "title2", null);
            Game game6 = new Game("Person6", g4, g7,
                    null, "title1",
                    new Console("Person6", g4, g7,
                            null, ConsoleTypes.NintendoSwitch));
            Game game7 = new Game("Person5", g4, g8,
                    null, "title1",
                    new Console("Person5", g4, g8,
                            null, ConsoleTypes.NintendoSwitch));

            bareList.add(console3);
            bareList.add(console4);
            bareList.add(game7);
            bareList.add(game2);
            bareList.add(console2);
            bareList.add(game6);
            bareList.add(game4);
            bareList.add(game3);
            bareList.add(game1);
            bareList.add(console1);

            assertTrue(bareList.get(0).getRentedOn().equals(g5) &&
                    bareList.get(0).getDueBack().equals(g3) &&
                    bareList.get(0) instanceof Game);
            assertTrue(bareList.get(1).getRentedOn().equals(g3) &&
                    bareList.get(1).getDueBack().equals(g1) &&
                    bareList.get(1) instanceof Game);
            assertTrue(bareList.get(2).getRentedOn().equals(g4) &&
                    bareList.get(2).getDueBack().equals(g7) &&
                    bareList.get(2) instanceof Game);
            assertTrue(bareList.get(3).getRentedOn().equals(g4) &&
                    bareList.get(3).getDueBack().equals(g8) &&
                    bareList.get(3) instanceof Game);
            assertTrue(bareList.get(4).getRentedOn().equals(g4) &&
                    bareList.get(4).getDueBack().equals(g8) &&
                    bareList.get(4) instanceof Game);
            assertTrue(bareList.get(5).getRentedOn().equals(g3) &&
                    bareList.get(5).getDueBack().equals(g2) &&
                    bareList.get(5) instanceof Game);
            assertTrue(bareList.get(6).getRentedOn().equals(g5) &&
                    bareList.get(6).getDueBack().equals(g3) &&
                    bareList.get(6) instanceof Console);
            assertTrue(bareList.get(7).getRentedOn().equals(g4) &&
                    bareList.get(7).getDueBack().equals(g7) &&
                    bareList.get(7) instanceof Console);
            assertTrue(bareList.get(8).getRentedOn().equals(g4) &&
                    bareList.get(8).getDueBack().equals(g8) &&
                    bareList.get(8) instanceof Console);
            assertTrue(bareList.get(9).getRentedOn().equals(g4) &&
                    bareList.get(9).getDueBack().equals(g6) &&
                    bareList.get(9) instanceof Console);
            assertTrue(bareList.size()==10);

            bareList.remove(0);
            bareList.remove(3);
            bareList.remove(4);
            bareList.remove(bareList.size()-1);
            assertTrue(bareList.get(0).getRentedOn().equals(g3) &&
                    bareList.get(0).getDueBack().equals(g1) &&
                    bareList.get(0) instanceof Game);
            assertTrue(bareList.get(1).getRentedOn().equals(g4) &&
                    bareList.get(1).getDueBack().equals(g7) &&
                    bareList.get(1) instanceof Game);
            assertTrue(bareList.get(2).getRentedOn().equals(g4) &&
                    bareList.get(2).getDueBack().equals(g8) &&
                    bareList.get(2) instanceof Game);
            assertTrue(bareList.get(3).getRentedOn().equals(g3) &&
                    bareList.get(3).getDueBack().equals(g2) &&
                    bareList.get(3) instanceof Game);
            assertTrue(bareList.get(4).getRentedOn().equals(g4) &&
                    bareList.get(4).getDueBack().equals(g7) &&
                    bareList.get(4) instanceof Console);
            assertTrue(bareList.get(5).getRentedOn().equals(g4) &&
                    bareList.get(5).getDueBack().equals(g8) &&
                    bareList.get(5) instanceof Console);
            assertTrue(bareList.size()==6);

            bareList.clear();
            assertTrue(bareList.size()==0);
        }
        catch (ParseException e){
            throw new RuntimeException("Error in testing, creation of list");
        }


    }

    @Test
    public void testGamesThenConsoles() {
        ListModel model = new ListModel(10); //this already creates a
        //new list with generated data in it
        boolean x = true;
        for (int i = 0; i < model.getRowCount() - 2; i++) {
            if (model.get(i) instanceof Game &&
                    model.get(i + 1) instanceof Console &&
                    model.get(i + 2) instanceof Game) {
                x = false;
            }
            else if (model.get(i) instanceof Console &&
                    model.get(i + 1) instanceof Console &&
                    model.get(i + 2) instanceof Game) {
                x = false;
            }
            else if (model.get(i) instanceof Console &&
                    model.get(i + 1) instanceof Game &&
                    model.get(i + 2) instanceof Game) {
                x = false;
            }
            else if (model.get(i) instanceof Console && model.get(i + 1)
                    instanceof Game &&
                    model.get(i + 2) instanceof Game) {
                x = false;
            }
            else if (model.get(i) instanceof Console && model.get(i + 1)
                    instanceof Game &&
                    model.get(i + 2) instanceof Console) {
                x = false;
            }
            assertTrue(x);
        }

        if (model.get(0) instanceof Console && model.get(1) instanceof Game &&
                model.getRowCount() == 2) {
            x = false;
        }
        assertTrue(x);
    }


    @Test
    public void testGamesAndConsolesInAlphaChronologicalOrder() {
        ListModel model = new ListModel(13); //this already creates a
        boolean x = true;
        //new list with generated data in it
        for (int i = 0; i < model.getRowCount() - 1; i++) {
            if (model.get(i) instanceof Game &&
                    model.get(i + 1) instanceof Game &&
                    model.get(i).getDueBack()
                            .equals(model.get(i + 1).getDueBack()) &&
                    model.get(i).getNameOfRenter()
                            .compareTo(model.get(i + 1)
                                    .getNameOfRenter()) > 0) {
                x = false;
            }
            else if (model.get(i) instanceof Game &&
                    model.get(i + 1) instanceof Game &&
                    model.get(i).getDueBack()
                            .after(model.get(i + 1).getDueBack())) {
                x = false;
            }
            else if (model.get(i) instanceof Console &&
                    model.get(i + 1) instanceof Console &&
                    model.get(i).getDueBack()
                            .equals(model.get(i + 1).getDueBack()) &&
                    model.get(i).getNameOfRenter()
                            .compareTo(model.get(i + 1)
                                    .getNameOfRenter()) > 0) {
                x = false;
            }
            else if (model.get(i) instanceof Console &&
                    model.get(i + 1) instanceof Console &&
                    model.get(i).getDueBack()
                            .after(model.get(i + 1).getDueBack())) {
                x = false;
            }
        }
        assertTrue(x);
    }
}

