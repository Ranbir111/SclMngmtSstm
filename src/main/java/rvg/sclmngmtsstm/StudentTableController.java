package rvg.sclmngmtsstm;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import org.json.simple.JSONObject;
import rvg.sclmngmtsstm.modals.StudentInfoModal;
import rvg.sclmngmtsstm.operations.DeleteOperation;
import rvg.sclmngmtsstm.operations.RetrieveOperation;

import java.net.URL;
import java.util.ResourceBundle;

public class StudentTableController implements Initializable {
    @FXML
    protected TableView<StudentInfoModal> studentTableView;
    @FXML
    protected TableColumn<StudentInfoModal,Integer> studentIdColumn;
    @FXML
    protected TableColumn<StudentInfoModal, String> studentNameColumn;
    @FXML
    protected TableColumn<StudentInfoModal, String> studentGradeColumn;
    @FXML
    protected TableColumn<StudentInfoModal, String> studentLevelColumn;
    @FXML
    protected TableColumn<StudentInfoModal, String> studentAddressColumn;
    @FXML
    protected TableColumn<StudentInfoModal,String> studentContactColumn;
    @FXML
    protected TableColumn actionColumn;

    ObservableList<StudentInfoModal> studentList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setStudentCellFactory();
        setStudentDataToTable();
    }

    public void setStudentCellFactory(){
        studentIdColumn.setCellValueFactory(new PropertyValueFactory<>("ID"));
        studentNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        studentGradeColumn.setCellValueFactory(new PropertyValueFactory<>("grade"));
        studentLevelColumn.setCellValueFactory(new PropertyValueFactory<>("level"));
        studentAddressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        studentContactColumn.setCellValueFactory(new PropertyValueFactory<>("contact"));
        addButtonToTable();

    }

    void setStudentDataToTable(){
        studentList.clear();
        for (Object obj : new RetrieveOperation().getAllStudentData()){
            JSONObject jsonObject = (JSONObject) obj;
            studentList.add(new StudentInfoModal(Integer.parseInt(jsonObject.get("ID").toString()), jsonObject.get("name").toString(), jsonObject.get("grade").toString(), jsonObject.get("level").toString(), jsonObject.get("address").toString(), jsonObject.get("contact").toString()));
        }
        studentTableView.setItems(studentList);
    }

    private void addButtonToTable(){
        Callback<TableColumn<StudentInfoModal, String>, TableCell<StudentInfoModal, String>> cellFactory = (param)->{
            final TableCell<StudentInfoModal,String> cell = new TableCell<StudentInfoModal,String>(){
                @Override
                public void updateItem(String item,boolean empty){
                    super.updateItem(item, empty);
                    if(empty){
                        setGraphic(null);
                        setText(null);
                    }else {
                        final Button deleteBtn = new Button("Delete");
                        deleteBtn.setOnAction(actionEvent -> {
                            System.out.println("Delete button pressed");
                            StudentInfoModal currentStudent = getTableView().getItems().get(getIndex());
                            int id = currentStudent.ID;
                            new DeleteOperation().deleteStudentData(id);
                            setStudentDataToTable();
                            System.out.println("Data deleted successfully");
                        });
                        setGraphic(deleteBtn);
                        setText(null);
                    }
                };
            };
            return cell;
        };
        actionColumn.setCellFactory(cellFactory);
    }
}
