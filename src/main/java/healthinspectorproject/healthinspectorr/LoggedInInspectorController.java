package healthinspectorproject.healthinspectorr;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoggedInInspectorController implements Initializable {

    @FXML
    private TableView <personInfoModel> personTableView;

    @FXML
    private TableColumn<personInfoModel,String> l_name;
    @FXML
    private TableColumn<personInfoModel,String>l_surname;
    @FXML
    private TableColumn<personInfoModel,String>l_phone;
    @FXML
    private TableColumn<personInfoModel,String>l_username;
    @FXML
    private TextField tf_username;
    @FXML
    private TableColumn<personInfoModel,String>l_adress;
    @FXML
    private TableColumn<personInfoModel,String>l_email;
    @FXML
    private Button b_logout;
    @FXML
    private Button bt_companies;

    @FXML
    private Label label_welcome;

    @FXML
    private Label label_role;
    @FXML
    private Button make_appt;

    @FXML
    private Button bt_giveRev;
@FXML
private Button bt_add;



ObservableList<personInfoModel> personInfoModelObservableList = FXCollections.observableArrayList();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        DatabaseConnection connectNow=new DatabaseConnection();
        Connection connectDB=connectNow.getDBConnection();
        String productViewQuery="SELECT username,Name,Surname,Phone,Adress,Email from users;";

        try {
            Statement statement = connectDB.createStatement();
            ResultSet queryOutput = statement.executeQuery(productViewQuery);

            while (queryOutput.next()) {


                String queryName = queryOutput.getString("Name");
                String queryUsername = queryOutput.getString("Username");
                String querySurname = queryOutput.getString("Surname");
                String queryAdress = queryOutput.getString("Adress");
                String queryPhone = queryOutput.getString("Phone");
                String queryEmail = queryOutput.getString("Email");

                personInfoModelObservableList.add(new personInfoModel(queryUsername,queryName, querySurname, queryPhone, queryAdress, queryEmail));


            }

            l_username.setCellValueFactory(new PropertyValueFactory<>("username"));
            l_name.setCellValueFactory(new PropertyValueFactory<>("name"));
            l_surname.setCellValueFactory(new PropertyValueFactory<>("surname"));
            l_adress.setCellValueFactory(new PropertyValueFactory<>("adress"));
            l_phone.setCellValueFactory(new PropertyValueFactory<>("phone"));
            l_email.setCellValueFactory(new PropertyValueFactory<>("email"));

            personTableView.setItems(personInfoModelObservableList);

            FilteredList<personInfoModel> filteredData = new FilteredList<>(personInfoModelObservableList, b -> true);
            tf_username.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredData.setPredicate(personInfoModel -> {
                    if (newValue.isEmpty() || newValue.isBlank() || newValue == null) {
                        return true;
                    }
                    String searchkeyword = newValue.toLowerCase();
                    if (personInfoModel.getUsername().toLowerCase().indexOf(searchkeyword) > -1 ) {
                        return true;

                    } else {
                        return false;
                    }
                });
            });
            SortedList<personInfoModel> sortedData = new SortedList<>(filteredData);
            sortedData.comparatorProperty().bind(personTableView.comparatorProperty());
            personTableView.setItems(sortedData);

        }catch (SQLException e){
            Logger.getLogger(LoggedInManagerController.class.getName()).log(Level.SEVERE,null,e);
            e.printStackTrace();
        }

        b_logout.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeScene(event, "main.fxml","Log in!",null,null);

            }
        });
        bt_companies.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeScene(event, "propertyfinder.fxml","Companies Page",null,null);

            }
        });
        make_appt.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeScene(event, "makeappointment.fxml","Make Appointment",null,null);

            }
        });
        bt_giveRev.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeScene(event, "givereview.fxml","Give Review",null,null);

            }
        });
        bt_add.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeScene(event, "addinfoinspector.fxml","Add Information",null,null);

            }
        });
    }


    public void set_user_information (String username, String role){
        label_welcome.setText("This is the main page,Inspector"+username+"!");
        label_role.setText("Log out when you finish your work!");

    }

}
