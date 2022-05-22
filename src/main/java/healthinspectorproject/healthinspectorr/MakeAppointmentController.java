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

public class MakeAppointmentController implements Initializable {

    @FXML
    private Label make_app;
    @FXML
    private DatePicker my_date;

    @FXML
    private TextField tf_getpn;

    @FXML
    private Button bt_make_app;
    @FXML
    private Button bt_back;


    public void getDate(ActionEvent event){

        LocalDate myDate=my_date.getValue();
        String myFormattedDate=myDate.format(DateTimeFormatter.ofPattern("dd-MMM-yyyy"));
        make_app.setText("Make an appointment for: "+myFormattedDate+"!");

    }

    public void initialize(URL url, ResourceBundle resourceBundle) {

        bt_make_app.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(!tf_getpn.getText().trim().isEmpty() && !my_date.getValue().toString().trim().isEmpty()){
                DBUtils.addAppointment(event,tf_getpn.getText(),"Appointment made",my_date.getValue().format(DateTimeFormatter.ofPattern("dd-MMM-yyyy")));
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
                DBUtils.changeScene(event, "loggedininspector.fxml","InspectorMain",null,null);

            }
        });
    }
}
