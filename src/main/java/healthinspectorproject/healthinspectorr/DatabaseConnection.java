package healthinspectorproject.healthinspectorr;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {

    public Connection databaseLink;
    public Connection getDBConnection(){

        String databaseName="healthinspectordb";
        String databaseUser="root";
        String databasePassword="passwordroot1";
        String url="jdbc:mysql://localhost:3306/"+databaseName;

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            databaseLink= DriverManager.getConnection(url,databaseUser,databasePassword);

        }catch (Exception e){
            e.printStackTrace();
        }
        return databaseLink;
    }
}
