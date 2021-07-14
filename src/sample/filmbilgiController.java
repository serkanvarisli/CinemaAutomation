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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.*;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

public class filmbilgiController {
    @FXML
    private TableView<filmbilgi> film1Table;
    @FXML
    private AnchorPane anchorpane;
    @FXML
    private Label label;

    @FXML
    private Button button1;
    @FXML
    private Button goster;
    @FXML
    private Button geri;
    @FXML
    private Button ekle;

    @FXML
    private Button guncelle;

    @FXML
    private Button sil;

    @FXML
    private TableColumn<filmbilgi, String> idc;

    @FXML
    private TableColumn<filmbilgi, String> filmc;

    @FXML
    private TableColumn<filmbilgi, String> bilgicol;

    @FXML
    private TableColumn<filmbilgi, String> konucol;
    @FXML
    private TableColumn<filmbilgi, String> oyuncular;
    @FXML
    private Button temizle;
    @FXML

    private ImageView imageview;

    @FXML

    private Image image;

    @FXML
    private FileChooser fileChooser;
    @FXML
    private FileInputStream fis;
    @FXML

    private File file;
    @FXML

    private Stage stage;

    @FXML
    private TextField idt;
    @FXML
    private TextField filmt;

    @FXML
    private TextArea bilgit;

    @FXML
    private TextArea konut;

    @FXML
    private TextArea oyunculart;


    @FXML
    private TextField aratxt;

    @FXML
    private Button arabtn;



    String sql;
    Connection connection;
    PreparedStatement preparedStatement;
    filmbilgi film1;
    ResultSet resultSet;
    public filmbilgiController() {
    }

    @FXML
    void goster_click(ActionEvent event) {
        DegerGetir(film1Table, sql);

    }

    @FXML
    void tableview_mouseclicked(MouseEvent event) throws SQLException, IOException {
        film1 = film1Table.getSelectionModel().getSelectedItem();
        filmt.setText(film1.getFilm());
        bilgit.setText(film1.getBilgi());
        konut.setText(film1.getKonu());
        idt.setText(Integer.toString(film1.getId()));
        oyunculart.setText(film1.getOyuncular());

        showProductImage(film1.getFilm());

    }
    public void showProductImage(String film) throws SQLException {
        try {
            preparedStatement=connection.prepareStatement("select image from filmbilgi where film=?");
            preparedStatement.setString(1,film);
            resultSet=preparedStatement.executeQuery();
            if (resultSet.next()){
                InputStream is=resultSet.getBinaryStream(1);
                OutputStream os=new FileOutputStream(new File("photo.jpg"));
                byte[] contents=new byte[1024];
                int size=0;
                while ((size=is.read(contents))!=-1){
                os.write(contents,0,size);
                image=new Image("file:photo.jpg",imageview.getFitWidth(),imageview.getFitHeight(),true,true);
                imageview.setImage(image);
                }

            }
        } catch (SQLException | FileNotFoundException exception) {
            exception.printStackTrace();
        } catch (IOException exception) {
            exception.printStackTrace();
        }

    }

    public void DegerGetir(TableView film1Table, String sql) {
        connection = mysqlconnect.ConnectDb();
        sql = "select * from filmbilgi";
        ObservableList<Bilet> kayitlarliste = FXCollections.observableArrayList();
        try {
            preparedStatement = connection.prepareStatement(sql);
            ResultSet getirilen = preparedStatement.executeQuery();
            while (getirilen.next()) {
                kayitlarliste.add(new filmbilgi(getirilen.getInt("id"), getirilen.getString("film"), getirilen.getString("bilgi"), getirilen.getString("konu"),getirilen.getString("oyuncular")));
            }
            idc.setCellValueFactory(new PropertyValueFactory<>("id"));
            filmc.setCellValueFactory(new PropertyValueFactory<>("film"));
            bilgicol.setCellValueFactory(new PropertyValueFactory<>("bilgi"));
            konucol.setCellValueFactory(new PropertyValueFactory<>("konu"));
            oyuncular.setCellValueFactory(new PropertyValueFactory<>("oyuncular"));
            film1Table.setItems(kayitlarliste);

        } catch (Exception e) {
            // TODO: handle exception
            System.out.print(e.getMessage().toString());
            JOptionPane.showMessageDialog(null, e);
        }
    }
    @FXML
    void ekle_click(ActionEvent event) {
        connection=mysqlconnect.ConnectDb();
        sql="INSERT INTO `filmbilgi`(`film`, `bilgi`, `konu`,`oyuncular`,`image`) VALUES (?,?,?,?,?)";
        try {
            if (filmt.getText().isEmpty()||bilgit.getText().isEmpty()||konut.getText().isEmpty()){
                Alert alert= new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("Lütfen Tüm Bilgileri Girin");
                alert.showAndWait(); }
            else {
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, filmt.getText());
                preparedStatement.setString(2, bilgit.getText());
                preparedStatement.setString(3, konut.getText());
                preparedStatement.setString(4, oyunculart.getText());
                fis=new FileInputStream(file);
                preparedStatement.setBinaryStream(5,(InputStream)fis,(int)file.length());
                preparedStatement.execute();
                DegerGetir(film1Table,sql);
                JOptionPane.showMessageDialog(null, "Bilgiler Eklendi");
            }
        } catch (SQLException | FileNotFoundException exception) {
            exception.printStackTrace();
           JOptionPane.showMessageDialog(null,exception);

        }
        filmt.clear();
        bilgit.clear();
        konut.clear();
        oyunculart.clear();
    }
    @FXML
    void guncelle_click(ActionEvent event) {
        sql="UPDATE `filmbilgi` SET `film`=?,`bilgi`=?,`konu`=?,`oyuncular`=?,`image`=? WHERE id=?";
        try{
            JOptionPane.showMessageDialog(null, "Lütfen resim seçiniz (seçtiyseniz devam edebilirsiniz)");
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText(null);
            alert.setContentText("Veri GÜNCELLENECEK,emin misiniz?");
            Optional<ButtonType> action = alert.showAndWait();
            if (action.get() == ButtonType.OK)
                preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setString(1,filmt.getText().trim());
            preparedStatement.setString(2,bilgit.getText().trim());
            preparedStatement.setString(3,konut.getText().trim());
            preparedStatement.setString(4,oyunculart.getText().trim());
            fis=new FileInputStream(file);
            preparedStatement.setBinaryStream(5,(InputStream)fis,(int)file.length());
            preparedStatement.setString(6,idt.getText().trim());
            preparedStatement.executeUpdate();
            DegerGetir(film1Table,sql);
        } catch (SQLException | FileNotFoundException exception) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Lütfen resim dahil tüm verileri girin");
            JOptionPane.showMessageDialog(null, exception);

        }
        filmt.clear();
        bilgit.clear();
        konut.clear();
        oyunculart.clear();
    }

    @FXML
    void sil_click(ActionEvent event) {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(null);
        alert.setContentText("Seçilen silinecek,emin misiniz?");
        Optional<ButtonType> action = alert.showAndWait();
        if (action.get() == ButtonType.OK) {
            try {
                film1 = film1Table.getSelectionModel().getSelectedItem();
                sql = "DELETE FROM `filmbilgi` WHERE id=" + film1.getId();
                connection = mysqlconnect.ConnectDb();
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.execute();
                JOptionPane.showMessageDialog(null, "Seçtiğiniz Silindi");
                DegerGetir(film1Table, sql);
            } catch (SQLException exception) {
                exception.printStackTrace();
                JOptionPane.showMessageDialog(null, exception);
            }
        }
        DegerGetir(film1Table,sql);
        filmt.clear();
        bilgit.clear();
        konut.clear();
        oyunculart.clear();
    }

    @FXML
    void geri_click(ActionEvent event) throws IOException {
        geri.getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("tableView.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Filmler Paneli");
        stage.setScene(new Scene(root));
        stage.show();
        stage.resizableProperty().setValue(false);
    }
    public void initialize(URL url, ResourceBundle rb){

    }
    public void Button1Action(ActionEvent event){
        fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files","*.jpg"));
        stage=(Stage)anchorpane.getScene().getWindow();
        file=fileChooser.showOpenDialog(stage);
        if (file!=null){
            System.out.println(""+file.getAbsolutePath());
            image=new Image(file.getAbsoluteFile().toURI().toString());
            imageview.setImage(image);
        }
    }
    @FXML
    void arabtn_click(ActionEvent event) {
        sql="select *from filmbilgi where film like'%"+aratxt.getText()+"%' or id like'%"+aratxt.getText()+"%'";;


        DegerGetir2(film1Table,sql);
    }

    @FXML
    void arakeypress(KeyEvent event) {
        sql="select *from filmbilgi where film like'%"+aratxt.getText()+"%' or id like'%"+aratxt.getText()+"%'";;


        DegerGetir2(film1Table,sql);
    }
    public void DegerGetir2(TableView film1Table, String sql) {
        connection = mysqlconnect.ConnectDb();
        //sql = "select * from filmbilgi";
        ObservableList<Bilet> kayitlarliste = FXCollections.observableArrayList();
        try {
            preparedStatement = connection.prepareStatement(sql);
            ResultSet getirilen = preparedStatement.executeQuery();
            while (getirilen.next()) {
                kayitlarliste.add(new filmbilgi(getirilen.getInt("id"), getirilen.getString("film"), getirilen.getString("bilgi"), getirilen.getString("konu"),getirilen.getString("oyuncular")));
            }
            idc.setCellValueFactory(new PropertyValueFactory<>("id"));
            filmc.setCellValueFactory(new PropertyValueFactory<>("film"));
            bilgicol.setCellValueFactory(new PropertyValueFactory<>("bilgi"));
            konucol.setCellValueFactory(new PropertyValueFactory<>("konu"));
            oyuncular.setCellValueFactory(new PropertyValueFactory<>("oyuncular"));
            film1Table.setItems(kayitlarliste);

        } catch (Exception e) {
            // TODO: handle exception
            System.out.print(e.getMessage().toString());
            JOptionPane.showMessageDialog(null, e);
        }
    }
    @FXML
    void temizle_click(ActionEvent event) {
        filmt.clear();
        bilgit.clear();
        konut.clear();
        oyunculart.clear();
        oyunculart.clear();
    }
        }




