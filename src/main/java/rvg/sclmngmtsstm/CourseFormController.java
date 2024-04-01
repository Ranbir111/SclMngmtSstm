package rvg.sclmngmtsstm;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import rvg.sclmngmtsstm.binHandel.RecentHandler;
import rvg.sclmngmtsstm.modals.CourseInfoModal;
import rvg.sclmngmtsstm.modals.TeacherInfoModal;
import rvg.sclmngmtsstm.operations.InsertOperation;
import rvg.sclmngmtsstm.operations.UpdateOperation;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CourseFormController implements Initializable {
    JSONObject jsonObject;

    @FXML
    TextField courseNameField,courseTeacherField,courseSheduleField,noOfStudEnrolField;
    boolean saveData(){
        if (jsonObject==null) {
            new InsertOperation().addNewCourse(new CourseInfoModal(courseNameField.getText(), courseTeacherField.getText(), courseSheduleField.getText(), Integer.parseInt(noOfStudEnrolField.getText())));
            return true;
        }else{
            new UpdateOperation().updateCourseData(new CourseInfoModal(Integer.parseInt(jsonObject.get("ID").toString()),courseNameField.getText(), courseTeacherField.getText(), courseSheduleField.getText(), Integer.parseInt(noOfStudEnrolField.getText())));
            CourseTableController.stage.close();
            return false;
        }
    }

    @FXML
    protected void onSaveDataBtnClick(ActionEvent actionEvent) {
        if(saveData()) {
            MainController.stage.close();
            System.out.println("Data saved successfully");
        }else{
            System.out.println("Error occurred while storing data!");
        }
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            jsonObject = new RecentHandler().getData();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        if(jsonObject != null){
            courseNameField.setText(jsonObject.get("courseName").toString());
            courseTeacherField.setText(jsonObject.get("teacher").toString());
            courseSheduleField.setText(jsonObject.get("schedule").toString());
            noOfStudEnrolField.setText(jsonObject.get("numOfStudInrolled").toString());
            try {
                new RecentHandler().clearRecent();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
