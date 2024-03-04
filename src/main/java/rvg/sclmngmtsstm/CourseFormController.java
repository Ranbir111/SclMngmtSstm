package rvg.sclmngmtsstm;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import rvg.sclmngmtsstm.modals.CourseInfoModal;
import rvg.sclmngmtsstm.operations.InsertOperation;

public class CourseFormController {
    @FXML
    TextField courseNameField,courseTeacherField,courseSheduleField,noOfStudEnrolField;
    boolean saveData(){
        new InsertOperation().addNewCourse(new CourseInfoModal(courseNameField.getText(),courseTeacherField.getText(),courseSheduleField.getText(),Integer.parseInt(noOfStudEnrolField.getText())));
        return true;
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
}
