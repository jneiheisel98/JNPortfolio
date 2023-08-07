package project3;
import org.junit.Test;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import static org.junit.Assert.*;
public class TestSingleTaillessList2 {
    @Test
    public void testGamesThenConsolesRound2() {
        boolean x = true;
        for (int count = 0; count < 1000; count++){
            ListModel model =
                    new ListModel(count); //this already creates a
            //new list with generated data in it

            for (int i = 0; i < model.getRowCount() - 2; i++) {
                if (model.get(i) instanceof Game &&
                        model.get(i + 1) instanceof Console &&
                        model.get(i + 2) instanceof Game) {
                    x = false;
                } else if (model.get(i) instanceof Console &&
                        model.get(i + 1) instanceof Console &&
                        model.get(i + 2) instanceof Game) {
                    x = false;
                } else if (model.get(i) instanceof Console &&
                        model.get(i + 1) instanceof Game &&
                        model.get(i + 2) instanceof Game) {
                    x = false;
                } else if (model.get(i) instanceof Console &&
                        model.get(i + 1) instanceof Game &&
                        model.get(i + 2) instanceof Game) {
                    x = false;
                } else if (model.get(i) instanceof Console &&
                        model.get(i + 1) instanceof Game &&
                        model.get(i + 2) instanceof Console) {
                    x = false;
                }
                assertTrue(x);
            }
        }
        ListModel model = new ListModel(13);
        if (model.get(0) instanceof Console &&
                model.get(1) instanceof Game &&
                model.getRowCount() == 2) {
            x = false;
        }
        assertTrue(x);
    }




    @Test
    public void testGamesAndConsolesInAlphaChronologicalOrderRound2() {
        for (int count = 0; count < 1000; count++) {
            ListModel model = new ListModel(count); //this already creates a
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
                } else if (model.get(i) instanceof Game &&
                        model.get(i + 1) instanceof Game &&
                        model.get(i).getDueBack()
                                .after(model.get(i + 1).getDueBack())) {
                    x = false;
                } else if (model.get(i) instanceof Console &&
                        model.get(i + 1) instanceof Console &&
                        model.get(i).getDueBack()
                                .equals(model.get(i + 1).getDueBack()) &&
                        model.get(i).getNameOfRenter()
                                .compareTo(model.get(i + 1)
                                        .getNameOfRenter()) > 0) {
                    x = false;
                } else if (model.get(i) instanceof Console &&
                        model.get(i + 1) instanceof Console &&
                        model.get(i).getDueBack()
                                .after(model.get(i + 1).getDueBack())) {
                    x = false;
                }
            }

            assertTrue(x);
        }
    }
}
