package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

public class biletsatisController {

    @FXML
    private Button filmsec;

    @FXML
    private TableView<Bilet> biletTable;
    @FXML
    private TableColumn<Bilet, String> idc;
    @FXML
    private TextField Filmt;

    @FXML
    private TextField Seanst;
    @FXML
    private Button sil;
    @FXML
    private Button goster;
    @FXML
    private Button geri;
    @FXML
    private Button guncellebutton;
    @FXML
    private TextField adSoyadt;
    @FXML
    private TextField koltukt;
    @FXML
    private TableColumn<Bilet, String> adSoyadc;
    @FXML
    private TableColumn<Bilet, String> Filmc;
    @FXML
    private TableColumn<Bilet, String> sCol;
    @FXML
    private TextField aratxt;

    @FXML
    private Button arabtn;
    @FXML Button temizle;

    @FXML
    void arabtn_click(ActionEvent event) {
        sql="select *from kullanici where film like'%"+aratxt.getText()+"%' or id like'%"+aratxt.getText()+"%' or seans like'%"+aratxt.getText()+"%' or adsoyad like'%"+aratxt.getText()+"%'";;

        //sql="select *from kullanici where id like'%"+aratxt.getText()+"%'";
        //sql="select *from kullanici where seans like'%"+aratxt.getText()+"%'";

        DegerGetir2(biletTable,sql);

    }
    @FXML
    void arakeypress(KeyEvent event) {
        sql="select *from kullanici where film like'%"+aratxt.getText()+"%' or id like'%"+aratxt.getText()+"%' or seans like'%"+aratxt.getText()+"%' or adsoyad like'%"+aratxt.getText()+"%'";;
     //   sql="select *from kullanici where seans like'%"+aratxt.getText()+"%'";
        //sql="select *from kullanici where adsoyad like'%"+aratxt.getText()+"%'";
        DegerGetir2(biletTable,sql);
    }

    public void initialize(URL url, ResourceBundle resourceBundle){
        DegerGetir(biletTable,sql);

    }
    String sql;
    Connection connection;
    PreparedStatement preparedStatement;
    Bilet Bilet;
    public void DegerGetir(TableView biletTable , String sql) {
        connection=mysqlconnect.ConnectDb();
        sql="select * from kullanici";
        ObservableList<Bilet> kayitlarliste= FXCollections.observableArrayList();
        try {
            preparedStatement=connection.prepareStatement(sql);
            ResultSet getirilen = preparedStatement.executeQuery();
            while (getirilen.next()) {
                kayitlarliste.add(new Bilet(getirilen.getInt("id"),getirilen.getString("adSoyad"),getirilen.getString("Film"),getirilen.getString("Seans")));
            }
            idc.setCellValueFactory(new PropertyValueFactory<>("id"));
            adSoyadc.setCellValueFactory(new PropertyValueFactory<>("adSoyad"));
            Filmc.setCellValueFactory(new PropertyValueFactory<>("Film"));
            sCol.setCellValueFactory(new PropertyValueFactory<>("Seans"));
            biletTable.setItems(kayitlarliste);

        } catch (Exception e) {
            // TODO: handle exception
            System.out.print(e.getMessage().toString());
            JOptionPane.showMessageDialog(null,e);
        }
    }
    public void DegerGetir2(TableView biletTable , String sql) {
        connection=mysqlconnect.ConnectDb();
        //sql="select * from kullanici";
        ObservableList<Bilet> kayitlarliste= FXCollections.observableArrayList();
        try {
            preparedStatement=connection.prepareStatement(sql);
            ResultSet getirilen = preparedStatement.executeQuery();
            while (getirilen.next()) {
                kayitlarliste.add(new Bilet(getirilen.getInt("id"),getirilen.getString("adSoyad"),getirilen.getString("Film"),getirilen.getString("Seans")));
            }
            idc.setCellValueFactory(new PropertyValueFactory<>("id"));
            adSoyadc.setCellValueFactory(new PropertyValueFactory<>("adSoyad"));
            Filmc.setCellValueFactory(new PropertyValueFactory<>("Film"));
            sCol.setCellValueFactory(new PropertyValueFactory<>("Seans"));
            biletTable.setItems(kayitlarliste);

        } catch (Exception e) {
            // TODO: handle exception
            System.out.print(e.getMessage().toString());
            JOptionPane.showMessageDialog(null,e);
        }
    }
    @FXML

    void filmsec_click(ActionEvent event) {
        connection=mysqlconnect.ConnectDb();
        sql="INSERT INTO `kullanici`(`adSoyad`,  `Film`, `Seans`,`id`) VALUES (?,?,?,?)";
        try {
            if (adSoyadt.getText().isEmpty()||Filmt.getText().isEmpty()||Seanst.getText().isEmpty()){
                Alert alert= new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("Lütfen Tüm Verileri Girin");
                alert.showAndWait(); }
            else {
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, adSoyadt.getText());
                preparedStatement.setString(2, Filmt.getText());
                preparedStatement.setString(3, Seanst.getText());
                preparedStatement.setString(4, koltukt.getText());
                preparedStatement.execute();
                DegerGetir(biletTable,sql);
                JOptionPane.showMessageDialog(null, "Bilet Alındı");
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
            Alert alert= new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Koltuk Numarası Alınmış");
            alert.showAndWait();

        }
        adSoyadt.clear();
        Filmt.clear();
        Seanst.clear();
        koltukt.clear();
    }
    @FXML
    void sil_click(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(null);
        alert.setContentText("Seçilen silinecek,emin misiniz?");
        Optional<ButtonType> action = alert.showAndWait();
        if (action.get() == ButtonType.OK) {
            try {
                Bilet = biletTable.getSelectionModel().getSelectedItem();
                sql = "DELETE FROM `kullanici` WHERE id=" + Bilet.getId();
                connection = mysqlconnect.ConnectDb();
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.execute();
                JOptionPane.showMessageDialog(null, "Seçtiğiniz Silindi");
                DegerGetir(biletTable, sql);
            } catch (SQLException exception) {
                exception.printStackTrace();
                JOptionPane.showMessageDialog(null, exception);
            } adSoyadt.clear();
            Filmt.clear();
            Seanst.clear();
            koltukt.clear();
        }
    }
    @FXML
    void guncelle_click(ActionEvent event) {
        sql="UPDATE kullanici SET adSoyad=? , Film=?,Seans=? where id=?";
        try{
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText(null);
            alert.setContentText("Veri GÜNCELLENECEK,emin misiniz?");
            Optional<ButtonType> action = alert.showAndWait();
            if (action.get() == ButtonType.OK)
                preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setString(1,adSoyadt.getText().trim());
            preparedStatement.setString(2,Filmt.getText().trim());
            preparedStatement.setString(3,Seanst.getText().trim());
            preparedStatement.setString(4,koltukt.getText().trim());
            preparedStatement.executeUpdate();
            DegerGetir(biletTable,sql);
        } catch (SQLException exception) {

            JOptionPane.showMessageDialog(null, exception);
        } adSoyadt.clear();
        Filmt.clear();
        Seanst.clear();
        koltukt.clear();

    }
    @FXML
    void tableview_mouseclicked(MouseEvent event) {
        Bilet = biletTable.getSelectionModel().getSelectedItem();
        adSoyadt.setText(Bilet.getAdSoyad());
        Filmt.setText(Bilet.getFilm());
        Seanst.setText(Bilet.getSeans());
        koltukt.setText(Integer.toString(Bilet.getId()));


    }
    @FXML
    void temizle_click(ActionEvent event) {
        adSoyadt.clear();
        Filmt.clear();
        Seanst.clear();
        koltukt.clear();
    }

    @FXML
    void goster_click(ActionEvent event) {

    DegerGetir(biletTable,sql);

    }
    @FXML
    void geri_click(ActionEvent event) throws IOException {
        geri.getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("tableView.fxml"));
        Stage stage= new Stage();
        stage.setTitle("Film Bilgileri");
        stage.setScene(new Scene(root));
        stage.show();
        stage.resizableProperty().setValue(false);
    }
}
