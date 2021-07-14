package sample;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.sql.DriverManager;

import java.sql.SQLException;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import java.sql.Connection;
public class mysqlconnect {
    Connection conn=null;
    public static Connection ConnectDb(){
        try {
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn =DriverManager.getConnection("jdbc:mysql://localhost/sinema_java","root","");

        return conn;
        }
        catch (Exception e){
        JOptionPane.showMessageDialog(null,e);
            return null;
        }

    }
}
