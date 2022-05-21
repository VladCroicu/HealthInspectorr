package healthinspectorproject.healthinspectorr;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class AddPropertyController implements Initializable {

    @FXML
    private Button b_apply;

    @FXML
    private Button b_back;
    @FXML
    private TextField tf_id;
    @FXML
    private TextField tf_mu;
    @FXML
    private TextField tf_cn;
    @FXML
    private TextField tf_pn;
    @FXML
    private TextField tf_a;
    @FXML
    private TextField tf_pnb;
    @FXML
    private TextField tf_d;

    @FXML
    private TextField tf_s;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        b_apply.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(!tf_s.getText().trim().isEmpty() && !tf_mu.getText().trim().isEmpty() && !tf_cn.getText().trim().isEmpty() && !tf_pn.getText().trim().isEmpty() && !tf_a.getText().trim().isEmpty() && !tf_pnb.getText().trim().isEmpty() && !tf_d.getText().trim().isEmpty()  ){
                    DBUtils.addProperty(event,tf_mu.getText(),tf_cn.getText(),tf_pn.getText(),tf_a.getText(),tf_pnb.getText(),tf_s.getText(),tf_d.getText());
                }else {
                    System.out.println("Please fill in all information");
                    Alert alert= new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Please fill in all information");
                    alert.show();

                }
            }
        });
        b_back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeScene(event, "loggedinmanager.fxml","Log in!",null,null);

            }
        });


    }
}
