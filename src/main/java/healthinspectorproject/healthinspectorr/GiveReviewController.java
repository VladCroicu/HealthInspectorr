package healthinspectorproject.healthinspectorr;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

import java.net.URL;
import java.util.ResourceBundle;

public class GiveReviewController implements Initializable {

    @FXML
    private TextField tf_propertyName;
    @FXML
    private TextField tf_review;
    @FXML
    private Button bt_back;
    @FXML
    private Button bt_give_review;
    @FXML
    private RadioButton rb_notp;
    @FXML
    private RadioButton rb_p;

    public void initialize (URL url, ResourceBundle resourceBundle){

        ToggleGroup toggleGroup =new ToggleGroup();
        rb_notp.setToggleGroup(toggleGroup);
        rb_p.setToggleGroup(toggleGroup);

        rb_p.setSelected(true);

        bt_give_review.setOnAction(new EventHandler<ActionEvent>() {


            @Override
            public void handle(ActionEvent event) {
                String togglename=((RadioButton) toggleGroup.getSelectedToggle()).getText();

                DBUtils.giveReview(event,tf_propertyName.getText(),togglename,tf_review.getText());
            }
        });
        bt_back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeScene(event, "loggedininspector.fxml","Log in!",null,null);

            }
        });
    }



}
