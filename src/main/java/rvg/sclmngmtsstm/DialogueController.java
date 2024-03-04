package rvg.sclmngmtsstm;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DialogueController implements Initializable {
    @FXML
    Button studentBtn, teacherBtn, courseBtn;
    @FXML
    BorderPane formContainer;
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
}
