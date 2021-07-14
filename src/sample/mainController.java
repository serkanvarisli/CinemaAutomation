package sample;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class mainController {

    @FXML
    private Button btn_login;
    @FXML
    private Label loginlable;
    @FXML
    private TextField kullaniciadi;

    @FXML
    private PasswordField sifre;
    @FXML
    private TextField eposta;

    @FXML
    private TextField adsoyad;

    @FXML
    private Button btn_kaydol;

    @FXML
    private TextField kullaniciadi2;

    @FXML
    private PasswordField sifrekayit;

    @FXML
    Connection conn=null;
    ResultSet rs=null;
    PreparedStatement pst=null;

    public void btn_login_click(ActionEvent event) throws IOException {

        conn=mysqlconnect.ConnectDb();
        String sql ="Select * from login where kullaniciadi=? or eposta=? and sifre=?";
        try {
            pst=conn.prepareStatement(sql);
            pst.setString(1,kullaniciadi.getText());
            pst.setString(2,kullaniciadi.getText());
            pst.setString(3,sifre.getText());
            rs=pst.executeQuery();
            if(rs.next()){
                btn_login.getScene().getWindow().hide();
                Parent root = FXMLLoader.load(getClass().getResource("tableView.fxml"));
                Stage stage= new Stage();
                stage.setTitle("Filmler Paneli");
                stage.setScene(new Scene(root));
                stage.show();
                stage.resizableProperty().setValue(false);
                loginlable.setText("Giriş Başarılı");
            }
            else JOptionPane.showMessageDialog(null,"Giriş Başarısız, Tekrar Deneyin");
            sifre.clear();
        }

        catch (Exception e){
            JOptionPane.showMessageDialog(null,e);
        }

    }
    @FXML
    void btn_kaydol_click(ActionEvent event) {
        conn=mysqlconnect.ConnectDb();
        String sql="insert into login (eposta, adsoyad, kullaniciadi, sifre) values (?,?,?,?)";
        try {
            pst=conn.prepareStatement(sql);
            pst.setString(1,eposta.getText());
            pst.setString(2,adsoyad.getText());
            pst.setString(3,kullaniciadi2.getText());
            pst.setString(4,sifrekayit.getText());
            pst.execute();
            JOptionPane.showMessageDialog(null,"Kayıt Başarıyla Oluşturuldu");
            eposta.clear();
            adsoyad.clear();
            kullaniciadi2.clear();
            sifrekayit.clear();
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(null,e);
        }
    }
}

