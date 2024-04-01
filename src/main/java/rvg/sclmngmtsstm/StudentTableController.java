package rvg.sclmngmtsstm;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import rvg.sclmngmtsstm.binHandel.RecentHandler;
import rvg.sclmngmtsstm.modals.StudentInfoModal;
import rvg.sclmngmtsstm.operations.DeleteOperation;
import rvg.sclmngmtsstm.operations.RetrieveOperation;

import java.io.IOException;
import java.net.URL;
import java.net.URLDecoder;
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

    public static Stage stage;

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
                        HBox hBox = new HBox(20);
                        hBox.setAlignment(Pos.CENTER);
                        final Button editBtn = new Button("📝");
                        editBtn.setStyle("-fx-background-color: yellow; -fx-padding: 5px 15px;");
                        final Button deleteBtn = new Button("🚮");
                        deleteBtn.setStyle("-fx-background-color: red; -fx-padding: 5px 15px;");
                        editBtn.setOnAction(actionEvent -> {
                            try {
                                FXMLLoader fxmlLoader = new FXMLLoader(MainController.class.getResource("student-form.fxml"));
                                StudentInfoModal studentInfoModal = getTableView().getItems().get(getIndex());
                                JSONObject jsonObject = new JSONObject();
                                jsonObject.put("ID",studentInfoModal.ID);
                                jsonObject.put("name",studentInfoModal.name);
                                jsonObject.put("grade",studentInfoModal.grade);
                                jsonObject.put("level",studentInfoModal.level);
                                jsonObject.put("address",studentInfoModal.address);
                                jsonObject.put("contact",studentInfoModal.contact);
                                new RecentHandler().setData(jsonObject);
                                stage = new Stage();
                                stage.initModality(Modality.APPLICATION_MODAL);
                                Scene scene = new Scene(fxmlLoader.load());
                                stage.setResizable(false);
                                stage.setTitle("Insert New Data");
                                stage.setAlwaysOnTop(true);
                                stage.setScene(scene);
                                stage.show();
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            } catch (ParseException e) {
                                throw new RuntimeException(e);
                            }
                        });
                        deleteBtn.setOnAction(actionEvent -> {
                            System.out.println("Delete button pressed");
                            Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Are sure you want to delete this Data!");
                            alert.getButtonTypes().clear();
                            alert.getButtonTypes().add(ButtonType.YES);
                            alert.getButtonTypes().add(ButtonType.NO);
                                alert.showAndWait().ifPresent(response -> {
                                    if (response == ButtonType.YES) {
                                        StudentInfoModal currentStudent = getTableView().getItems().get(getIndex());
                                        int id = currentStudent.ID;
                                        new DeleteOperation().deleteStudentData(id);
                                        setStudentDataToTable();
                                        System.out.println("Data deleted successfully");
                                    }
                                });
                        });
                        hBox.getChildren().add(editBtn);
                        hBox.getChildren().add(deleteBtn);
                        setGraphic(hBox);
                        setText(null);
                    }
                };
            };
            return cell;
        };
        actionColumn.setCellFactory(cellFactory);
    }
}
