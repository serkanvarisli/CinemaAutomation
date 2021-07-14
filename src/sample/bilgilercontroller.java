package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

import java.io.IOException;

public class bilgilercontroller {

    @FXML
    private AnchorPane bilgiler;

    @FXML
    private ImageView img1;

    @FXML
    private Label esaret;

    @FXML
    private Label isim1;

    @FXML
    private Label süre1;

    @FXML
    private Label tur1;

    @FXML
    private Label senarist1;

    @FXML
    private Label yonetmen1;

    @FXML
    private Label yapim1;

    @FXML
    private TextArea txt1;

    @FXML
    private Label konu1;

    @FXML
    private Label kara;

    @FXML
    private ImageView img2;

    @FXML
    private Label isim2;

    @FXML
    private Label sure2;

    @FXML
    private Label tur2;

    @FXML
    private Label senarist2;

    @FXML
    private Label yonetmen2;

    @FXML
    private Label yapim2;

    @FXML
    private TextArea txt2;

    @FXML
    private Label dovus;

    @FXML
    private ImageView img3;

    @FXML
    private Label isim3;

    @FXML
    private Label sure3;

    @FXML
    private Label tur3;

    @FXML
    private Label senarist3;

    @FXML
    private Label yonetmen3;

    @FXML
    private Label yapim3;

    @FXML
    private TextArea txt3;

    @FXML
    private Line uc;

    @FXML
    private ImageView img4;

    @FXML
    private Label isim4;

    @FXML
    private Label sure4;

    @FXML
    private Label tur4;

    @FXML
    private Label yonetmen4;

    @FXML
    private Label senarist4;

    @FXML
    private Label yapim4;

    @FXML
    private TextArea txt4;

    @FXML
    private Label konu2;

    @FXML
    private Label konu3;

    @FXML
    private Label konu4;

    @FXML
    private Label baslangic;

    @FXML
    private ImageView img5;

    @FXML
    private Label isim5;

    @FXML
    private Label sure5;

    @FXML
    private Label yapim5;

    @FXML
    private Label senarist5;

    @FXML
    private Label yonetmen5;

    @FXML
    private Label tur5;

    @FXML
    private Label konu5;

    @FXML
    private TextArea txt5;

    @FXML
    private Label yedi;

    @FXML
    private ImageView img6;

    @FXML
    private Label isim6;

    @FXML
    private Label sure6;

    @FXML
    private Label tur6;

    @FXML
    private Label yonetmen6;

    @FXML
    private Label senarist6;

    @FXML
    private Label yapim6;

    @FXML
    private Label konu6;

    @FXML
    private TextArea txt6;

    @FXML
    private Button geributton;


    @FXML
    void geri_click(ActionEvent event) throws IOException {
        geributton.getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("filmler.fxml"));
        Stage stage= new Stage();
        stage.setTitle("Film Bilgileri");
        stage.setScene(new Scene(root));
        stage.show();
        stage.resizableProperty().setValue(false);


    }

}
