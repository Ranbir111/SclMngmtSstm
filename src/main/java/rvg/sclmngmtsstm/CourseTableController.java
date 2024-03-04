package rvg.sclmngmtsstm;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.json.simple.JSONObject;
import rvg.sclmngmtsstm.modals.CourseInfoModal;
import rvg.sclmngmtsstm.operations.RetrieveOperation;

import java.net.URL;
import java.util.ResourceBundle;

public class CourseTableController implements Initializable {
    public TableView<CourseInfoModal> courseTableView;
    public TableColumn<CourseInfoModal,Integer> courseIdColumn;
    public TableColumn<CourseInfoModal,String> courseNameColumn;
    public TableColumn<CourseInfoModal,String> courseTeacherColumn;
    public TableColumn<CourseInfoModal,String> courseSheduleColumn;
    public TableColumn<CourseInfoModal, Integer> courseNoOfStudColumn;

    ObservableList<CourseInfoModal> courseList = FXCollections.observableArrayList();
    public void setCourseCellFactory(){
        courseIdColumn.setCellValueFactory(new PropertyValueFactory<>("ID"));
        courseNameColumn.setCellValueFactory(new PropertyValueFactory<>("courseName"));
        courseTeacherColumn.setCellValueFactory(new PropertyValueFactory<>("teacher"));
        courseSheduleColumn.setCellValueFactory(new PropertyValueFactory<>("schedule"));
        courseNoOfStudColumn.setCellValueFactory(new PropertyValueFactory<>("numOfStudInrolled"));
    }
    void setCourseDataToTable(){
        for (Object obj : new RetrieveOperation().getAllCourseData()){
            JSONObject jsonObject = (JSONObject) obj;
            courseList.add(new CourseInfoModal(Integer.parseInt(jsonObject.get("ID").toString()),jsonObject.get("courseName").toString(),jsonObject.get("teacher").toString(),jsonObject.get("schedule").toString(),Integer.parseInt(jsonObject.get("numOfStudInrolled").toString())));
        }
        courseTableView.setItems(courseList);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setCourseCellFactory();
        setCourseDataToTable();
    }
}
