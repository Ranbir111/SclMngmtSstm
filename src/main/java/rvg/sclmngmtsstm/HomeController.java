package rvg.sclmngmtsstm;

import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import org.json.simple.parser.ParseException;
import rvg.sclmngmtsstm.operations.TotalOperation;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HomeController implements Initializable {

    public Label studentsCounter;
    public Label teachersCounter;
    public Label courcesCounter;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        TotalOperation totalOperation = new TotalOperation();
        try {
            studentsCounter.setText(Integer.toString(totalOperation.getNoOfStudent()));
            teachersCounter.setText(Integer.toString(totalOperation.getNoOfTeacher()));
            courcesCounter.setText(Integer.toString(totalOperation.getNoOfCourse()));
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
