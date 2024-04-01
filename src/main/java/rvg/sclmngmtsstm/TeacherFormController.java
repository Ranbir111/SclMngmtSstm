package rvg.sclmngmtsstm;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import rvg.sclmngmtsstm.binHandel.RecentHandler;
import rvg.sclmngmtsstm.modals.StudentInfoModal;
import rvg.sclmngmtsstm.modals.TeacherInfoModal;
import rvg.sclmngmtsstm.operations.InsertOperation;
import rvg.sclmngmtsstm.operations.UpdateOperation;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TeacherFormController implements Initializable {
    JSONObject jsonObject;

    @FXML
    TextField teacherNameField, teacherSubjectField,teacherContactField;
    boolean saveData(){
        if (jsonObject==null){
            new InsertOperation().addNewTeacher(new TeacherInfoModal(teacherNameField.getText(), teacherSubjectField.getText(), teacherContactField.getText()));
            return true;
        }else{
            new UpdateOperation().updateTeacherData(new TeacherInfoModal(Integer.parseInt(jsonObject.get("ID").toString()),teacherNameField.getText(),teacherSubjectField.getText(),teacherContactField.getText()));
            TeacherTableController.stage.close();
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
            teacherNameField.setText(jsonObject.get("name").toString());
            teacherSubjectField.setText(jsonObject.get("subject").toString());
            teacherContactField.setText(jsonObject.get("contact").toString());
            try {
                new RecentHandler().clearRecent();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
