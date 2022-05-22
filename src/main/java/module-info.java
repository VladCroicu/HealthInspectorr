module healthinspectorproject.healthinspectorr {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires javafx.graphics;
    requires javafx.base;


    opens healthinspectorproject.healthinspectorr to javafx.fxml;
    exports healthinspectorproject.healthinspectorr;
}