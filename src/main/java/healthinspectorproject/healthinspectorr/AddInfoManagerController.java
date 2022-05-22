package healthinspectorproject.healthinspectorr;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.ResourceBundle;

public class AddInfoManagerController implements Initializable {




    @FXML
    private TextField tf_name;

    @FXML
    private TextField tf_username;
    @FXML
    private TextField tf_surname;
    @FXML
    private TextField tf_email;
    @FXML
    private TextField tf_adress;
    @FXML
    private TextField tf_phone;

    @FXML
    private Button bt_apply;
    @FXML
    private Button bt_back;



    public void initialize(URL url, ResourceBundle resourceBundle) {

        bt_apply.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(!tf_username.getText().trim().isEmpty() && !tf_name.getText().trim().isEmpty() &&!tf_surname.getText().trim().isEmpty() &&!tf_phone.getText().trim().isEmpty() &&!tf_adress.getText().trim().isEmpty() &&!tf_email.getText().trim().isEmpty()){
                    DBUtils.addInfo(event,tf_username.getText(),tf_name.getText(),tf_surname.getText(),tf_phone.getText(),tf_adress.getText(),tf_email.getText());
                }else {
                    System.out.println("Please fill in all information");
                    Alert alert= new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Please fill in all information");
                    alert.show();

                }


            }
        });
        bt_back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeScene(event, "loggedinmanager.fxml","ManagerMain",null,null);

            }
        });
    }
}
