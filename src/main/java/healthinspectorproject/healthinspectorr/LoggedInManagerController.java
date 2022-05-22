package healthinspectorproject.healthinspectorr;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class LoggedInManagerController implements Initializable {
    @FXML
    private Button b_logout;

    @FXML
    private Label label_welcome;

    @FXML
    private Label label_role;
    @FXML
    private Label l_name;
    @FXML
    private Label l_surname;
    @FXML
    private Label l_phone;
    @FXML
    private Label l_company;
    @FXML
    private Label l_email;

    @FXML
    private Button bt_acceptapp;
    @FXML
    private Button bt_rejectapp;
    @FXML
    private Button bt_gotoyourproperties;

    @FXML
    private Button b_addProperty;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        b_logout.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeScene(event, "main.fxml","Log in!",null,null);

            }
        });
        bt_gotoyourproperties.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeScene(event, "enterusername.fxml","EnterUsername",null,null);

            }
        });

        b_addProperty.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeScene(event, "addproperty.fxml","AddProperty",null,null);

            }
        });
        bt_rejectapp.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeScene(event, "rejectappointment.fxml","AddProperty",null,null);

            }
        });
        bt_acceptapp.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeScene(event, "acceptappointment.fxml","AddProperty",null,null);

            }
        });
    }


    public void set_user_information (String username, String role){
        label_welcome.setText("This is the main page,"+username+"!");
        label_role.setText("Log out when you finish your work!");

    }



}
