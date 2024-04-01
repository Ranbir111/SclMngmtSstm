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
import rvg.sclmngmtsstm.modals.CourseInfoModal;
import rvg.sclmngmtsstm.modals.CourseInfoModal;
import rvg.sclmngmtsstm.operations.DeleteOperation;
import rvg.sclmngmtsstm.operations.RetrieveOperation;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CourseTableController implements Initializable {
    public TableView<CourseInfoModal> courseTableView;
    public TableColumn<CourseInfoModal,Integer> courseIdColumn;
    public TableColumn<CourseInfoModal,String> courseNameColumn;
    public TableColumn<CourseInfoModal,String> courseTeacherColumn;
    public TableColumn<CourseInfoModal,String> courseSheduleColumn;
    public TableColumn<CourseInfoModal, Integer> courseNoOfStudColumn;
    public TableColumn actionColumn;

    ObservableList<CourseInfoModal> courseList = FXCollections.observableArrayList();
    public static Stage stage;

    public void setCourseCellFactory(){
        courseIdColumn.setCellValueFactory(new PropertyValueFactory<>("ID"));
        courseNameColumn.setCellValueFactory(new PropertyValueFactory<>("courseName"));
        courseTeacherColumn.setCellValueFactory(new PropertyValueFactory<>("teacher"));
        courseSheduleColumn.setCellValueFactory(new PropertyValueFactory<>("schedule"));
        courseNoOfStudColumn.setCellValueFactory(new PropertyValueFactory<>("numOfStudInrolled"));
        addButtonToTable();
    }
    void setCourseDataToTable(){
        courseList.clear();
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
    private void addButtonToTable(){
        Callback<TableColumn<CourseInfoModal, String>, TableCell<CourseInfoModal, String>> cellFactory = (param)->{
            final TableCell<CourseInfoModal,String> cell = new TableCell<CourseInfoModal,String>(){
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
                                FXMLLoader fxmlLoader = new FXMLLoader(MainController.class.getResource("course-form.fxml"));
                                CourseInfoModal courseInfoModal = getTableView().getItems().get(getIndex());
                                JSONObject jsonObject = new JSONObject();
                                jsonObject.put("ID",courseInfoModal.ID);
                                jsonObject.put("courseName",courseInfoModal.courseName);
                                jsonObject.put("teacher",courseInfoModal.teacher);
                                jsonObject.put("schedule",courseInfoModal.schedule);
                                jsonObject.put("numOfStudInrolled",courseInfoModal.numOfStudInrolled);
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
                            System.out.println("Delete button pressed");
                            Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Are sure you want to delete this Data!");
                            alert.getButtonTypes().clear();
                            alert.getButtonTypes().add(ButtonType.YES);
                            alert.getButtonTypes().add(ButtonType.NO);
                            alert.showAndWait().ifPresent(response -> {
                                if (response == ButtonType.YES) {
                                    CourseInfoModal currentCourse = getTableView().getItems().get(getIndex());
                                    int id = currentCourse.ID;
                                    new DeleteOperation().deleteCourseData(id);
                                    setCourseDataToTable();
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
