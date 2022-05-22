package healthinspectorproject.healthinspectorr;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class RejectAppointmentController implements Initializable {


    @FXML
    private Button bt_back;
    @FXML
    private Button bt_reject;
    @FXML
    private TextField tf_pn;
    @FXML
    private TextField tf_reason;


    public void initialize(URL url, ResourceBundle resourceBundle) {

        bt_reject.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(!tf_pn.getText().trim().isEmpty() && !tf_pn.getText().trim().isEmpty()){
                    DBUtils.acceptAppointment(event,tf_pn.getText(),"Appointment rejected",tf_reason.getText());
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
                DBUtils.changeScene(event, "loggedinmanager.fxml","ManagerMain!",null,null);

            }
        });
    }



}
