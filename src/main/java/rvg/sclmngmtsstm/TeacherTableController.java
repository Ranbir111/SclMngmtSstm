package rvg.sclmngmtsstm;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.json.simple.JSONObject;
import rvg.sclmngmtsstm.modals.TeacherInfoModal;
import rvg.sclmngmtsstm.operations.RetrieveOperation;

import java.net.URL;
import java.util.ResourceBundle;

public class TeacherTableController implements Initializable {
    // For teacher table
    public TableView<TeacherInfoModal> teacherTableView;
    public TableColumn<TeacherInfoModal,Integer> teacherIdColumn;
    public TableColumn<TeacherInfoModal,String> teacherNameColumn;
    public TableColumn<TeacherInfoModal,String> teacherSubjectColumn;
    public TableColumn<TeacherInfoModal,String> teacherContactColumn;

    ObservableList<TeacherInfoModal> teacherList = FXCollections.observableArrayList();
    public void setTeacherCellFactory(){
        teacherIdColumn.setCellValueFactory(new PropertyValueFactory<>("ID"));
        teacherNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        teacherSubjectColumn.setCellValueFactory(new PropertyValueFactory<>("subject"));
        teacherContactColumn.setCellValueFactory(new PropertyValueFactory<>("contact"));
    }
    void setTeacherDataToTable(){
        for (Object obj : new RetrieveOperation().getAllTeacherData()){
            JSONObject jsonObject = (JSONObject) obj;
            teacherList.add(new TeacherInfoModal(Integer.parseInt(jsonObject.get("ID").toString()),jsonObject.get("name").toString(),jsonObject.get("subject").toString(),jsonObject.get("contact").toString()));
        }
        teacherTableView.setItems(teacherList);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setTeacherCellFactory();
        setTeacherDataToTable();
    }
}
