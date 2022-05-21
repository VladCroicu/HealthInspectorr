package healthinspectorproject.healthinspectorr;


import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class DBUtils {


    public static void changeScene(ActionEvent event, String fxmlFile, String title , String username, String role){
        Parent root= null;

        if(username!=null && role != null){
            try {
                FXMLLoader loader= new FXMLLoader(DBUtils.class.getResource(fxmlFile));
                root = loader.load();
                LoggedInController loggedInController = loader.getController();
                loggedInController.set_user_information(username,role);
            }catch (IOException e){
                e.printStackTrace();
            }
        }else {
            try{
                root=FXMLLoader.load(DBUtils.class.getResource(fxmlFile));
            }catch (IOException e){
                e.printStackTrace();
            }
        }

        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setTitle(title);
        stage.setScene(new Scene(root, 800,400));
        stage.show();
    }
    public static void changeScenetoinspector(ActionEvent event, String fxmlFile, String title , String username, String role){
        Parent root= null;

        if(username!=null && role != null){
            try {
                FXMLLoader loader= new FXMLLoader(DBUtils.class.getResource(fxmlFile));
                root = loader.load();
                LoggedInInspectorController loggedInController = loader.getController();
                loggedInController.set_user_information(username,role);
            }catch (IOException e){
                e.printStackTrace();
            }
        }else {
            try{
                root=FXMLLoader.load(DBUtils.class.getResource(fxmlFile));
            }catch (IOException e){
                e.printStackTrace();
            }
        }

        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setTitle(title);
        stage.setScene(new Scene(root, 800,400));
        stage.show();
    }
    public static void changeScenetomanager(ActionEvent event, String fxmlFile, String title , String username, String role){
        Parent root= null;

        if(username!=null && role != null){
            try {
                FXMLLoader loader= new FXMLLoader(DBUtils.class.getResource(fxmlFile));
                root = loader.load();
                LoggedInManagerController loggedInController = loader.getController();
                loggedInController.set_user_information(username,role);
            }catch (IOException e){
                e.printStackTrace();
            }
        }else {
            try{
                root=FXMLLoader.load(DBUtils.class.getResource(fxmlFile));
            }catch (IOException e){
                e.printStackTrace();
            }
        }

        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setTitle(title);
        stage.setScene(new Scene(root, 800,400));
        stage.show();
    }
    public static void addProperty(ActionEvent event,String ManagerUsername, String CompanyName, String PropertyName,String Adress,String PhoneNumber,String Status, String Description)
    {
        Connection connection =null;
        PreparedStatement psInsert=null;
        PreparedStatement psCheckPropertyExists=null;
        ResultSet resultSet =null;

        try {
            connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/healthinspectordb","root","passwordroot1");
            psCheckPropertyExists=connection.prepareStatement("SELECT * FROM companies WHERE PropertyName=?");
            psCheckPropertyExists.setString(1,PropertyName);
            resultSet=psCheckPropertyExists.executeQuery();

            if(resultSet.isBeforeFirst()){
                System.out.println("Property name already exists");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("You can not use this Property Name.");
                alert.show();
            }
            else {

                psInsert=connection.prepareStatement("INSERT INTO companies(ManagerUsername,CompanyName,PropertyName,Adress,PhoneNumber,Status,Description) VALUES (?,?,?,?,?,?,?)");
                psInsert.setString(1,ManagerUsername);
                psInsert.setString(2,CompanyName);
                psInsert.setString(3,PropertyName);
                psInsert.setString(4,Adress);
                psInsert.setString(5,PhoneNumber);
                psInsert.setString(6,Status);
                psInsert.setString(7,Description);
                psInsert.executeUpdate();
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setContentText("Added Property Succesfully");
                alert.show();

            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            if(resultSet!=null){
                try{
                    resultSet.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
            if( psCheckPropertyExists!= null){
                try{
                    psCheckPropertyExists.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }

            if( psInsert!= null){
                try{
                    psInsert.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }

            if( connection!= null){
                try{
                    connection.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }

        }

    }



    public static void signUpUser(ActionEvent event, String username, String password, String role){
    Connection connection =null;
        PreparedStatement psInsert=null;
        PreparedStatement psCheckUserExists=null;
        ResultSet resultSet =null;

        try{
            connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/healthinspectordb","root","passwordroot1");
            psCheckUserExists=connection.prepareStatement("SELECT* FROM users WHERE username=?");
            psCheckUserExists.setString(1,username);
            resultSet=psCheckUserExists.executeQuery();

            if(resultSet.isBeforeFirst()){
                System.out.println("User already exists");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("You can not use this username.");
                alert.show();
            }
            else {
                psInsert=connection.prepareStatement("INSERT INTO users (username, password, role) VALUES (? , ? , ?)");
                psInsert.setString(1,username);
                psInsert.setString(2,password);
                psInsert.setString(3,role);
                psInsert.executeUpdate();

                String s1="Manager";
                if(role.equals(s1)){
                    changeScenetomanager(event,"loggedinmanager.fxml","Welcome, Manager "+username+"!",username, role);}
                else {
                    changeScenetoinspector(event,"loggedininspector.fxml","Welcome, Inspector "+username+"!",username, role);
                }
            }
        } catch (SQLException e){
            e.printStackTrace();
        }finally {
            if( resultSet!= null){
                try{
                    resultSet.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }

            if( psCheckUserExists!= null){
                try{
                    psCheckUserExists.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }

            if( psInsert!= null){
                try{
                    psInsert.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }

            if( connection!= null){
                try{
                    connection.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }

        }

    }


        public static void logInUser(ActionEvent event, String username, String password){

        Connection connection =null;
        PreparedStatement preparedStatement =null;
        ResultSet resultSet=null;

        try{
            connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/healthinspectordb","root","passwordroot1");
            preparedStatement=connection.prepareStatement("SELECT password, role FROM users Where username=?");
            preparedStatement.setString(1,username);
            resultSet=preparedStatement.executeQuery();

            if(!resultSet.isBeforeFirst()){
                System.out.println("User not found in the database");
                Alert alert= new Alert(Alert.AlertType.ERROR);
                alert.setContentText("The provided credentials are incorrect!");
                alert.show();
            }else {
                while(resultSet.next()){
                String retrievedPasssword= resultSet.getString("password");
                String retrievedRole=resultSet.getString("role");

                    if(retrievedPasssword.equals(password)){
                        String s1="Manager";
                        if(retrievedRole.equals(s1)){
                            changeScenetomanager(event,"loggedinmanager.fxml","Welcome, Manager "+username+"!",username, retrievedRole);}
                        else {
                            changeScenetoinspector(event,"loggedininspector.fxml","Welcome, Inspector "+username+"!",username, retrievedRole);
                        }
                    }
                    else{
                        System.out.println("Passwords did not match");
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setContentText("The provided credentials are incorrect!");
                        alert.show();
                    }
            }

            }

        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            if( resultSet!= null){
            try{
                resultSet.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }

            if( preparedStatement!= null){
                try{
                    preparedStatement.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }

            if( connection!= null){
                try{
                    connection.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }

        }

        }



}
