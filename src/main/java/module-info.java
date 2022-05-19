module healthinspectorproject.healthinspectorr {
    requires javafx.controls;
    requires javafx.fxml;


    opens healthinspectorproject.healthinspectorr to javafx.fxml;
    exports healthinspectorproject.healthinspectorr;
}