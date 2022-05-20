module healthinspectorproject.healthinspectorr {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens healthinspectorproject.healthinspectorr to javafx.fxml;
    exports healthinspectorproject.healthinspectorr;
}