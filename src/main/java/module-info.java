module rvg.sclmngmtsstm {
    requires javafx.controls;
    requires javafx.fxml;


    opens rvg.sclmngmtsstm to javafx.fxml;
    exports rvg.sclmngmtsstm;
}