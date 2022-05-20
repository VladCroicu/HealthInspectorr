package healthinspectorproject.healthinspectorr;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class LoggedInInspectorController implements Initializable {
    @FXML
    private Button b_logout;

    @FXML
    private Label label_welcome;

    @FXML
    private Label label_role;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        b_logout.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeScene(event, "main.fxml","Log in!",null,null);

            }
        });
    }


    public void set_user_information (String username, String role){
        label_welcome.setText("Welcome "+username+"!");
        label_role.setText("Your role is "+role+"!");
    }

}
