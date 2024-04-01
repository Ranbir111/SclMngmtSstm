package rvg.sclmngmtsstm;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Orientation;
import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.StringConverter;
import org.json.simple.JSONArray;
import rvg.sclmngmtsstm.operations.SearchOperation;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class MainController implements Initializable {
//    public ListView searchList;
    @FXML
    private Button homeBtn, studentsBtn, teachersBtn, coursesBtn, settingsBtn;
    @FXML
    private TextField searchField;
    @FXML
    private BorderPane pageContainer;
    @FXML
    private AnchorPane topBar;

    ContextMenu contextMenu;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        pageContainer.getChildren().removeAll();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("page1-home.fxml"));
        try {
            pageContainer.setCenter(fxmlLoader.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
//        ObservableList<String> observableList = FXCollections.observableArrayList("Hello","Hello");
//        searchList.setOrientation(Orientation.VERTICAL);
//        searchList.setItems(observableList);
//        ListView<String> listView = new ListView<String>(observableList);
//        listView.setMaxSize(200, 160);
//        topBar.getChildren().add(listView);
//        searchList.setCellFactory(new Callback<ListView<String>, ListCell<String>>() {
//
//            @Override
//            public ListCell<String> call(ListView<String> param) {
//                return new TextFieldListCell<>(new StringConverter<String>() {
//
//                    @Override
//                    public String toString(String object) {
//                        return object;
//                    }
//
//                    @Override
//                    public String fromString(String string) {
//                        return string;
//                    }
//                });
//            }
//        });
    }

    void addCurrentBtnEffect(Button currentBtn){
        homeBtn.getStyleClass().remove("current_btn");
        teachersBtn.getStyleClass().remove("current_btn");
        coursesBtn.getStyleClass().remove("current_btn");
        settingsBtn.getStyleClass().remove("current_btn");
        studentsBtn.getStyleClass().remove("current_btn");
        if(!currentBtn.getStyleClass().contains("current_btn")){
            currentBtn.getStyleClass().add("current_btn");
        }
    }

    @FXML
    protected void onHomeBtnClick() throws IOException {
        addCurrentBtnEffect(homeBtn);

        pageContainer.getChildren().removeAll();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("page1-home.fxml"));
        pageContainer.setCenter(fxmlLoader.load());
    }
    @FXML
    protected void onStudentsBtnClick() throws IOException {
        addCurrentBtnEffect(studentsBtn);

        pageContainer.getChildren().removeAll();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("page2-student_table.fxml"));
        pageContainer.setCenter(fxmlLoader.load());
    }
    @FXML
    protected void onTeachersBtnClick() throws IOException{
        addCurrentBtnEffect(teachersBtn);

        pageContainer.getChildren().removeAll();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("page3-teacher_table.fxml"));
        pageContainer.setCenter(fxmlLoader.load());
    }
    @FXML
    protected void onCoursesBtnClick() throws IOException {
        addCurrentBtnEffect(coursesBtn);

        pageContainer.getChildren().removeAll();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("page4-course_table.fxml"));
        pageContainer.setCenter(fxmlLoader.load());
    }
    @FXML
    protected void onSettingsBtnClick() throws IOException {
        addCurrentBtnEffect(settingsBtn);

        pageContainer.getChildren().removeAll();
        FXMLLoader fxmlLoader = new FXMLLoader(MainController.class.getResource("page1-home.fxml"));
        pageContainer.setCenter(fxmlLoader.load());
    }

    public void onInsertDataBtnClick(ActionEvent actionEvent) throws IOException {
        display();
    }

    //It is also used in dialog controller to close the dialog
    public static Stage stage;
    public static void display() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainController.class.getResource("addData-dialogue.fxml"));
        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        Scene scene = new Scene(fxmlLoader.load());
        stage.setResizable(false);
        stage.setTitle("Insert New Data");
        stage.setAlwaysOnTop(true);
        stage.setScene(scene);
        stage.show();
    }

    public void searchHandler(){
//            contextMenu.show(searchField, Side.BOTTOM, 0, 0);
        System.out.println("Action");
    }

    JSONArray getSearchData(String input){
        SearchOperation searchOperation = new SearchOperation();
        return searchOperation.searchData(input);
    }
}