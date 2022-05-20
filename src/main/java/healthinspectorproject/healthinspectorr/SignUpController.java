package healthinspectorproject.healthinspectorr;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class SignUpController implements Initializable {



    @FXML
    private Button b_login;
    @FXML
    private Button b_signup;
    @FXML
    private RadioButton rb_manager;
    @FXML
    private  RadioButton rb_inspector;
    @FXML
    private TextField tf_username;
    @FXML
    private TextField tf_password;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        ToggleGroup toggleGroup =new ToggleGroup();
        rb_inspector.setToggleGroup(toggleGroup);
        rb_manager.setToggleGroup(toggleGroup);

        rb_inspector.setSelected(true);

        b_signup.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            String togglename=((RadioButton) toggleGroup.getSelectedToggle()).getText();
            if(!tf_username.getText().trim().isEmpty() && !tf_password.getText().trim().isEmpty()){
                DBUtils.signUpUser(event,tf_username.getText(),tf_password.getText(),togglename);
            }else {
                System.out.println("Please fill in all information");
                Alert alert= new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Please fill in all information");
                alert.show();

            }
            }
        });


        b_login.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeScene(event,"main.fxml","Log in",null,null);

            }
        });

    }
}
