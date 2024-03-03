package rvg.sclmngmtsstm;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import rvg.sclmngmtsstm.modals.StudentInfoModal;
import rvg.sclmngmtsstm.operations.InsertOperation;

public class StudentFormController {
    @FXML
    TextField studentNameField,studentGradeField,studentLevelField,studentAddressField,studentContactField;

    boolean saveData(){
        new InsertOperation().addNewStudent(new StudentInfoModal(studentNameField.getText(),studentGradeField.getText(),studentLevelField.getText(),studentAddressField.getText(),studentContactField.getText()));
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
