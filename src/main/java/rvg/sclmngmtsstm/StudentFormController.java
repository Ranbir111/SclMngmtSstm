package rvg.sclmngmtsstm;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import rvg.sclmngmtsstm.binHandel.RecentHandler;
import rvg.sclmngmtsstm.modals.StudentInfoModal;
import rvg.sclmngmtsstm.operations.InsertOperation;
import rvg.sclmngmtsstm.operations.UpdateOperation;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class StudentFormController implements Initializable {
    JSONObject jsonObject;

    @FXML
    TextField studentNameField,studentGradeField,studentLevelField,studentAddressField,studentContactField;

    boolean saveData(){
        if(jsonObject == null) {
            new InsertOperation().addNewStudent(new StudentInfoModal(studentNameField.getText(), studentGradeField.getText(), studentLevelField.getText(), studentAddressField.getText(), studentContactField.getText()));
            return true;
        }else{
            new UpdateOperation().updateStudentData(new StudentInfoModal(Integer.parseInt(jsonObject.get("ID").toString()), studentNameField.getText(), studentGradeField.getText(), studentLevelField.getText(), studentAddressField.getText(), studentContactField.getText()));
            StudentTableController.stage.close();
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
            studentNameField.setText(jsonObject.get("name").toString());
            studentGradeField.setText(jsonObject.get("grade").toString());
            studentLevelField.setText(jsonObject.get("level").toString());
            studentAddressField.setText(jsonObject.get("address").toString());
            studentContactField.setText(jsonObject.get("contact").toString());
            try {
                new RecentHandler().clearRecent();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
