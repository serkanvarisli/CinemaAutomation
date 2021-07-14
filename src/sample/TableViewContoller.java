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

public class TableViewContoller {

    @FXML
    private TableView<Filmler> filmTable;

    @FXML
    private TableColumn<Filmler,String> idCol;

    @FXML
    private TableColumn<Filmler,String> filmCol;

    @FXML
    private TableColumn<Filmler,String> turCol;

    @FXML
    private TableColumn<Filmler,String> seans1Col;

    @FXML
    private TableColumn<Filmler,String> seans1salonCol;

    @FXML
    private TableColumn<Filmler,String> seans2Col;

    @FXML
    private TableColumn<Filmler,String> seans2salonCol;

    @FXML
    private TableColumn<Filmler,String> seans3Col;

    @FXML
    private TableColumn<Filmler,String> seans3salonCol;

    @FXML
    private TableColumn<Filmler,String> tarihCol;


    @FXML
    private Button goster;

    @FXML
    private Button filmekle;

    @FXML
    private Button film1;
    @FXML
    private TextField seans3fld;

    @FXML
    private TextField seans3salonfld;

    @FXML
    private TextField tarihfld;

    @FXML
    private TextField filmfld;

    @FXML
    private TextField turfld;

    @FXML
    private TextField seans1fld;

    @FXML
    private TextField seans1salonfld;

    @FXML
    private TextField seans2fld;

    @FXML
    private TextField seans2salonfld;
    @FXML
    private TextField idfld;
    @FXML
    private TextField aratxt;
    @FXML
    private Button filmguncelle;

    @FXML
    private Button filmsil;
    @FXML
    private Button biletgit;
    @FXML
    private Button cikis;
    @FXML
    private Button temizle;
    @FXML
    private Button arabtn;


    String sql;
    Connection connection;
    PreparedStatement preparedStatement;
    Filmler filmler;


    public void DegerGetir(TableView filmTable , String sql) {
        connection=mysqlconnect.ConnectDb();
        sql="select * from filmler";
        ObservableList<Filmler> kayitlarliste=FXCollections.observableArrayList();
        try {
            preparedStatement=connection.prepareStatement(sql);
            ResultSet getirilen = preparedStatement.executeQuery();
            while (getirilen.next()) {
                kayitlarliste.add(new Filmler(getirilen.getInt("id"),getirilen.getString("film"),getirilen.getString("filmturu"),getirilen.getString("seans1"),getirilen.getString("seans1salon"),getirilen.getString("seans2"),getirilen.getString("seans2salon"),getirilen.getString("seans3"),getirilen.getString("seans3salon"),getirilen.getString("tarih")));
            }
            idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
            filmCol.setCellValueFactory(new PropertyValueFactory<>("film"));
            turCol.setCellValueFactory(new PropertyValueFactory<>("filmturu"));
            seans1Col.setCellValueFactory(new PropertyValueFactory<>("seans1"));
            seans1salonCol.setCellValueFactory(new PropertyValueFactory<>("seans1salon"));
            seans2Col.setCellValueFactory(new PropertyValueFactory<>("seans2"));
            seans2salonCol.setCellValueFactory(new PropertyValueFactory<>("seans2salon"));
            seans3Col.setCellValueFactory(new PropertyValueFactory<>("seans3"));
            seans3salonCol.setCellValueFactory(new PropertyValueFactory<>("seans3salon"));
            tarihCol.setCellValueFactory(new PropertyValueFactory<>("tarih"));
            filmTable.setItems(kayitlarliste);
        } catch (Exception e) {
            // TODO: handle exception
            System.out.print(e.getMessage().toString());
        }
    }

    public void initialize(URL url, ResourceBundle resourceBundle){
        DegerGetir(filmTable,sql);
    }

    @FXML
    void goster_click(ActionEvent event) {
        DegerGetir(filmTable,sql);
    }

    @FXML
    void tableview_mouseclicked(MouseEvent event) {
        filmler = filmTable.getSelectionModel().getSelectedItem();
        idfld.setText(Integer.toString(filmler.getId()));
        filmfld.setText(filmler.getFilm());
        turfld.setText(filmler.getFilmturu());
        seans1fld.setText(filmler.getSeans1());
        seans1salonfld.setText(filmler.getSeans1salon());
        seans2fld.setText(filmler.getSeans2());
        seans2salonfld.setText(filmler.getSeans2salon());
        seans3fld.setText(filmler.getSeans3());
        seans3salonfld.setText(filmler.getSeans3salon());
        tarihfld.setText(filmler.getTarih());
    }
    @FXML
    private void filmekle_click(ActionEvent event)  {

        connection=mysqlconnect.ConnectDb();
        sql="INSERT INTO `filmler`(`film`, `filmturu`, `seans1`, `seans1salon`, `seans2`, `seans2salon`, `seans3`, `seans3salon`, `tarih`) VALUES (?,?,?,?,?,?,?,?,?)";
        try {
            if (filmfld.getText().isEmpty()||turfld.getText().isEmpty()||seans1fld.getText().isEmpty()||seans1salonfld.getText().isEmpty()||seans2fld.getText().isEmpty()||seans2salonfld.getText().isEmpty()||seans3fld.getText().isEmpty()||seans3salonfld.getText().isEmpty()||tarihfld.getText().isEmpty()){
                Alert alert= new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("Lütfen Tüm Verileri Girin");
                alert.showAndWait(); }
            else {
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, filmfld.getText());
                preparedStatement.setString(2, turfld.getText());
                preparedStatement.setString(3, seans1fld.getText());
                preparedStatement.setString(4, seans1salonfld.getText());
                preparedStatement.setString(5, seans2fld.getText());
                preparedStatement.setString(6, seans2salonfld.getText());
                preparedStatement.setString(7, seans3fld.getText());
                preparedStatement.setString(8, seans3salonfld.getText());
                preparedStatement.setString(9, tarihfld.getText());
                preparedStatement.execute();
                DegerGetir(filmTable,sql);
                JOptionPane.showMessageDialog(null, "Film Eklendi");
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
            JOptionPane.showMessageDialog(null,exception);
        }
        idfld.clear();
        filmfld.clear();
        turfld.clear();
        seans1fld.clear();
        seans1salonfld.clear();
        seans2fld.clear();
        seans2salonfld.clear();
        seans3fld.clear();
        seans3salonfld.clear();
        tarihfld.clear();

    }
    @FXML
    void filmguncelle_click(ActionEvent event) {
            sql="UPDATE filmler SET film=? , filmturu=?,seans1=?,seans1salon=?,seans2=?,seans2salon=?,seans3=?,seans3salon=?,tarih=? where id=?";
            try{
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setHeaderText(null);
                alert.setContentText("Veri GÜNCELLENECEK,emin misiniz?");
                Optional<ButtonType> action = alert.showAndWait();
                if (action.get() == ButtonType.OK)
                    preparedStatement=connection.prepareStatement(sql);
                preparedStatement.setString(1,filmfld.getText().trim());
                preparedStatement.setString(2,turfld.getText().trim());
                preparedStatement.setString(3,seans1fld.getText().trim());
                preparedStatement.setString(4,seans1salonfld.getText().trim());
                preparedStatement.setString(5,seans2fld.getText().trim());
                preparedStatement.setString(6,seans2salonfld.getText().trim());
                preparedStatement.setString(7,seans3fld.getText().trim());
                preparedStatement.setString(8,seans3salonfld.getText().trim());
                preparedStatement.setString(9,tarihfld.getText().trim());
                preparedStatement.setString(10,idfld.getText().trim());
                preparedStatement.executeUpdate();
                DegerGetir(filmTable,sql);
            } catch (SQLException exception) {

                JOptionPane.showMessageDialog(null, exception);
            }
            filmfld.clear();
        turfld.clear();
        seans1fld.clear();
        seans1salonfld.clear();
        seans2fld.clear();
        seans2salonfld.clear();
        seans3fld.clear();
        seans3salonfld.clear();
        idfld.clear();
        tarihfld.clear();

    }
    @FXML
    void filmsil_click(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(null);
        alert.setContentText("Seçilen film SİLİNECEK,emin misiniz?");
        Optional<ButtonType> action = alert.showAndWait();
        if (action.get() == ButtonType.OK) {
            try {
                filmler = filmTable.getSelectionModel().getSelectedItem();
                sql = "DELETE FROM `filmler` WHERE id=" + filmler.getId();
                connection = mysqlconnect.ConnectDb();
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.execute();
                JOptionPane.showMessageDialog(null, "Seçtiğiniz Film Silindi");
                DegerGetir(filmTable, sql);

            } catch (SQLException exception) {
                exception.printStackTrace();
                JOptionPane.showMessageDialog(null, exception);
            }

        }
        filmfld.clear();
        turfld.clear();
        seans1fld.clear();
        seans1salonfld.clear();
        seans2fld.clear();
        seans2salonfld.clear();
        seans3fld.clear();
        seans3salonfld.clear();
        idfld.clear();
        tarihfld.clear();

    }

    @FXML
    void film1_click(ActionEvent event) throws IOException {
        film1.getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("filmbilgi.fxml"));
        Stage stage= new Stage();
        stage.setTitle("Esaretin Bedeli");
        stage.setScene(new Scene(root ));
        stage.show();
        stage.resizableProperty().setValue(false);
    }

    @FXML
    void biletgit_click(ActionEvent event) throws IOException {
        biletgit.getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("biletsatis.fxml"));
        Stage stage= new Stage();
        stage.setTitle("Bilet Satışı");
        stage.setScene(new Scene(root ));
        stage.show();
        stage.resizableProperty().setValue(false);
    }
    @FXML
    void cikis_click(ActionEvent event) throws IOException {
        cikis.getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("panel.fxml"));
        Stage stage= new Stage();
        stage.setTitle("Yönetici Paneli");
        stage.setScene(new Scene(root));
        stage.show();
        stage.resizableProperty().setValue(false);
    }
    public void DegerGetir2(TableView filmTable , String sql) {
        connection=mysqlconnect.ConnectDb();
      //  sql="select * from filmler";
        ObservableList<Filmler> kayitlarliste=FXCollections.observableArrayList();

        try {
            preparedStatement=connection.prepareStatement(sql);
            ResultSet getirilen = preparedStatement.executeQuery();
            while (getirilen.next()) {
                kayitlarliste.add(new Filmler(getirilen.getInt("id"),getirilen.getString("film"),getirilen.getString("filmturu"),getirilen.getString("seans1"),getirilen.getString("seans1salon"),getirilen.getString("seans2"),getirilen.getString("seans2salon"),getirilen.getString("seans3"),getirilen.getString("seans3salon"),getirilen.getString("tarih")));

            }

            idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
            filmCol.setCellValueFactory(new PropertyValueFactory<>("film"));
            turCol.setCellValueFactory(new PropertyValueFactory<>("filmturu"));
            seans1Col.setCellValueFactory(new PropertyValueFactory<>("seans1"));
            seans1salonCol.setCellValueFactory(new PropertyValueFactory<>("seans1salon"));
            seans2Col.setCellValueFactory(new PropertyValueFactory<>("seans2"));
            seans2salonCol.setCellValueFactory(new PropertyValueFactory<>("seans2salon"));
            seans3Col.setCellValueFactory(new PropertyValueFactory<>("seans3"));
            seans3salonCol.setCellValueFactory(new PropertyValueFactory<>("seans3salon"));
            tarihCol.setCellValueFactory(new PropertyValueFactory<>("tarih"));

            filmTable.setItems(kayitlarliste);
        } catch (Exception e) {
            // TODO: handle exception
            System.out.print(e.getMessage().toString());
        }
    }
    @FXML
    void arabtn_click(ActionEvent event) {

        sql="select *from filmler where filmturu like'%"+aratxt.getText()+"%'or film like'%"+aratxt.getText()+"%' or id like'%"+aratxt.getText()+"%'";

        DegerGetir2(filmTable,sql);

    }
    @FXML
    void arakeypress(KeyEvent event) {
        sql="select *from filmler where filmturu like'%"+aratxt.getText()+"%'or film like'%"+aratxt.getText()+"%' or id like'%"+aratxt.getText()+"%'";
        DegerGetir2(filmTable,sql);
    }
    @FXML
    void temizle_click(ActionEvent event) {
        filmfld.clear();
        turfld.clear();
        seans1fld.clear();
        seans1salonfld.clear();
        seans2fld.clear();
        seans2salonfld.clear();
        seans3fld.clear();
        seans3salonfld.clear();
        idfld.clear();
        tarihfld.clear();
    }
}
