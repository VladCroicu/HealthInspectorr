package healthinspectorproject.healthinspectorr;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

@FXML
private Button b_login;
    @FXML
    private Button b_signup;
@FXML
private TextField tf_username;
@FXML
private PasswordField tf_password;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        b_login.setOnAction(new EventHandler<ActionEvent>() {


            @Override
            public void handle(ActionEvent event) {
                DBUtils.logInUser(event,tf_username.getText(),Cryptography.encriptCypher(tf_password.getText(),1));
            }
        });
        b_signup.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeScene(event,"signup.fxml","Sign up!",null,null);
            }
        });
    }
}
