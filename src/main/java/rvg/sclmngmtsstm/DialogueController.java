package rvg.sclmngmtsstm;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import rvg.sclmngmtsstm.modals.StudentInfoModal;
import rvg.sclmngmtsstm.operations.InsertOperation;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DialogueController implements Initializable {
    @FXML
    Button studentBtn, teacherBtn, courseBtn;
    @FXML
    BorderPane formContainer;

//    TextField studentNameField,studentGradeField,studentLevelField,studentAddressField,studentContactField,teacherNameField,
//            teacherSubjectField,teacherContactField,courseNameField,courseTeacherField,courseSheduleField,noOfStudEnrolField;
    FXMLLoader fxmlLoader;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        fxmlLoader = new FXMLLoader(getClass().getResource("student-form.fxml"));
        try {
            formContainer.setCenter(fxmlLoader.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    void addCurrentBtnEffect(Button currentBtn){
        courseBtn.getStyleClass().remove("current_btn");
        teacherBtn.getStyleClass().remove("current_btn");
        studentBtn.getStyleClass().remove("current_btn");
        if(!currentBtn.getStyleClass().contains("current_btn")){
            currentBtn.getStyleClass().add("current_btn");
        }
    }
    @FXML
    protected void onStudentBtnClick() throws IOException {
        addCurrentBtnEffect(studentBtn);
        formContainer.getChildren().removeAll();
        fxmlLoader = new FXMLLoader(getClass().getResource("student-form.fxml"));
        formContainer.setCenter(fxmlLoader.load());
    }
    @FXML
    protected void onTeacherBtnClick() throws IOException {
        addCurrentBtnEffect(teacherBtn);
        formContainer.getChildren().removeAll();
        fxmlLoader = new FXMLLoader(getClass().getResource("teacher-form.fxml"));
        formContainer.setCenter(fxmlLoader.load());
    }
    @FXML
    protected void onCourseBtnClick() throws IOException {
        addCurrentBtnEffect(courseBtn);
        formContainer.getChildren().removeAll();
        fxmlLoader = new FXMLLoader(getClass().getResource("course-form.fxml"));
        formContainer.setCenter(fxmlLoader.load());
    }

    InsertOperation insertOperation = new InsertOperation();
    public boolean saveData(){
        if (studentBtn.getStyleClass().contains("current_btn")) {
            return true;
        } else if (teacherBtn.getStyleClass().contains("current_btn")) {
            return true;
        } else if (courseBtn.getStyleClass().contains("current_btn")) {
            return true;
        } else {
            System.out.println("Data is unavaliable to save!");
            return false;
        }
    }
}
