package healthinspectorproject.healthinspectorr;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class EnterUsername implements Initializable {

    @FXML
    private Button bt_continue;
    @FXML
    private TextField tf_username;

    public void initialize(URL url, ResourceBundle resourceBundle) {



        bt_continue.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (!tf_username.getText().trim().isEmpty()) {

                    DBUtils.changeScene(event, "managerpropertyfinder.fxml", "Companies Page", tf_username.getText(), null);
                } else {
                    System.out.println("Please fill in all information");
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Please fill in all information");
                    alert.show();

                }
            }
        });



    }


}
