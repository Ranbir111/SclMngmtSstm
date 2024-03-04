module rvg.sclmngmtsstm {
    requires javafx.controls;
    requires javafx.fxml;
    requires json.simple;


    opens rvg.sclmngmtsstm to javafx.fxml;
    exports rvg.sclmngmtsstm;
    exports rvg.sclmngmtsstm.modals;
}