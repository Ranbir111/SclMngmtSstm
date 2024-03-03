package rvg.sclmngmtsstm;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import rvg.sclmngmtsstm.modals.StudentInfoModal;
import rvg.sclmngmtsstm.modals.TeacherInfoModal;
import rvg.sclmngmtsstm.operations.InsertOperation;

public class TeacherFormController {
    @FXML
    TextField teacherNameField, teacherSubjectField,teacherContactField;
    boolean saveData(){
        new InsertOperation().addNewTeacher(new TeacherInfoModal(teacherNameField.getText(),teacherSubjectField.getText(),teacherContactField.getText()));
        return true;
    }

    protected void onSaveDataBtnClick(ActionEvent actionEvent) {
        if(saveData()) {
            MainController.stage.close();
            System.out.println("Data saved successfully");
        }else{
            System.out.println("Error occurred while storing data!");
        }
    }
}
