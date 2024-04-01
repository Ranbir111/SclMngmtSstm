module rvg.sclmngmtsstm {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;

    opens rvg.sclmngmtsstm to javafx.fxml;
    exports rvg.sclmngmtsstm;
}