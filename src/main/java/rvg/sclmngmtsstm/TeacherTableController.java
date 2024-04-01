package rvg.sclmngmtsstm;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import rvg.sclmngmtsstm.binHandel.RecentHandler;
import rvg.sclmngmtsstm.modals.TeacherInfoModal;
import rvg.sclmngmtsstm.modals.TeacherInfoModal;
import rvg.sclmngmtsstm.operations.DeleteOperation;
import rvg.sclmngmtsstm.operations.RetrieveOperation;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TeacherTableController implements Initializable {
    // For teacher table
    public TableView<TeacherInfoModal> teacherTableView;
    public TableColumn<TeacherInfoModal,Integer> teacherIdColumn;
    public TableColumn<TeacherInfoModal,String> teacherNameColumn;
    public TableColumn<TeacherInfoModal,String> teacherSubjectColumn;
    public TableColumn<TeacherInfoModal,String> teacherContactColumn;
    public TableColumn actionColumn;
    public static Stage stage;

    ObservableList<TeacherInfoModal> teacherList = FXCollections.observableArrayList();
    public void setTeacherCellFactory(){
        teacherIdColumn.setCellValueFactory(new PropertyValueFactory<>("ID"));
        teacherNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        teacherSubjectColumn.setCellValueFactory(new PropertyValueFactory<>("subject"));
        teacherContactColumn.setCellValueFactory(new PropertyValueFactory<>("contact"));
        addButtonToTable();
    }
    void setTeacherDataToTable(){
        teacherList.clear();
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
    private void addButtonToTable(){
        Callback<TableColumn<TeacherInfoModal, String>, TableCell<TeacherInfoModal, String>> cellFactory = (param)->{
            final TableCell<TeacherInfoModal,String> cell = new TableCell<TeacherInfoModal,String>(){
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
                                FXMLLoader fxmlLoader = new FXMLLoader(MainController.class.getResource("teacher-form.fxml"));
                                TeacherInfoModal teacherInfoModal = getTableView().getItems().get(getIndex());
                                JSONObject jsonObject = new JSONObject();
                                jsonObject.put("ID",teacherInfoModal.ID);
                                jsonObject.put("name",teacherInfoModal.name);
                                jsonObject.put("subject",teacherInfoModal.subject);
                                jsonObject.put("contact",teacherInfoModal.contact);
                                new RecentHandler().setData(jsonObject);
                                stage = new Stage();
                                stage.initModality(Modality.APPLICATION_MODAL);
                                Scene scene = new Scene(fxmlLoader.load());
                                stage.setResizable(false);
                                stage.setTitle("Insert New Data");
                                stage.setAlwaysOnTop(true);
                                stage.setScene(scene);
                                stage.show();
                            } catch (IOException | ParseException e) {
                                throw new RuntimeException(e);
                            }
                        });
                        deleteBtn.setOnAction(actionEvent -> {
                            Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Are sure you want to delete this Data!");
                            alert.getButtonTypes().clear();
                            alert.getButtonTypes().add(ButtonType.YES);
                            alert.getButtonTypes().add(ButtonType.NO);
                            alert.showAndWait().ifPresent(response -> {
                                if (response == ButtonType.YES) {
                                    TeacherInfoModal currentTeacher = getTableView().getItems().get(getIndex());
                                    int id = currentTeacher.ID;
                                    new DeleteOperation().deleteTeacherData(id);
                                    setTeacherDataToTable();
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
