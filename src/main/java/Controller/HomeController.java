package Controller;

import base.VoiceRRS;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HomeController implements Initializable {
    public static String voiceName = "Hina";
    @FXML
    private ToggleButton buttonAiri;

    @FXML
    private ToggleButton buttonAkira;

    @FXML
    private ToggleButton buttonFumi;

    @FXML
    private ToggleButton buttonHina;

    @FXML
    private ImageView imageAiri;

    @FXML
    private ImageView imageAkira;

    @FXML
    private ImageView imageFumi;

    @FXML
    private ImageView imageHina;

    @FXML
    private AnchorPane container;


    @FXML
    void actionHomeAddBtn() {
        showComponent("/AddGUI.fxml");
    }

    @FXML
    void actionHomeHistoryBtn() {
        showComponent("/HistoryGUI.fxml");
    }

    @FXML
    void actionHomeSearchBtn() {
        showComponent("/SearchGUI.fxml");
    }

    @FXML
    void actionHomeShowBtn() {
        showComponent("/BookMarkGUI.fxml");
    }

    @FXML
    void actionHomeTranslateBtn() {
        showComponent("/TranslateGUI.fxml");
    }

    private void showComponent(String path) {
        try {
            AnchorPane component = FXMLLoader.load(getClass().getResource(path));
            container.getChildren().clear();
            container.getChildren().add(component);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void voiceAiri(ActionEvent event) {
        buttonAiri.setStyle("-fx-background-color:  #354259; -fx-text-fill: white");
        imageAiri.setImage(new Image("icon/5627742_avatar_face_female_people_profile_icon.png"));
        buttonAkira.setStyle("-fx-background-color:  #C2DED1; -fx-font-size: 24; -fx-text-fill: black;");
        imageAkira.setImage(new Image("icon/6104776.png"));
        buttonFumi.setStyle("-fx-background-color:  #C2DED1; -fx-font-size: 24; -fx-text-fill: black;");
        imageFumi.setImage(new Image("icon/6104758.png"));
        buttonHina.setStyle("-fx-background-color:  #C2DED1; -fx-font-size: 24; -fx-text-fill: black;");
        imageHina.setImage(new Image("icon/6104754.png"));

        voiceName = "Airi";

    }

    @FXML
    void voiceAkira(ActionEvent event) {
        buttonAkira.setStyle("-fx-background-color:  #354259; -fx-text-fill: white");
        imageAkira.setImage(new Image("icon/5627764_avatar_face_male_man_people_icon.png"));
        buttonAiri.setStyle("-fx-background-color:  #C2DED1; -fx-font-size: 24; -fx-text-fill: black;");
        imageAiri.setImage(new Image("icon/6104747.png"));
        buttonFumi.setStyle("-fx-background-color:  #C2DED1; -fx-font-size: 24; -fx-text-fill: black;");
        imageFumi.setImage(new Image("icon/6104758.png"));
        buttonHina.setStyle("-fx-background-color:  #C2DED1; -fx-font-size: 24; -fx-text-fill: black;");
        imageHina.setImage(new Image("icon/6104754.png"));

        voiceName = "Akira";
    }

    @FXML
    void voiceFumi(ActionEvent event) {
        buttonFumi.setStyle("-fx-background-color:  #354259; -fx-text-fill: white");
        imageFumi.setImage(new Image("icon/5627753_avatar_face_female_people_profile_icon.png"));
        buttonAiri.setStyle("-fx-background-color:  #C2DED1; -fx-font-size: 24; -fx-text-fill: black;");
        imageAiri.setImage(new Image("icon/6104747.png"));
        buttonAkira.setStyle("-fx-background-color:  #C2DED1; -fx-font-size: 24; -fx-text-fill: black;");
        imageAkira.setImage(new Image("icon/6104776.png"));
        buttonHina.setStyle("-fx-background-color:  #C2DED1; -fx-font-size: 24; -fx-text-fill: black;");
        imageHina.setImage(new Image("icon/6104754.png"));

        voiceName = "Fumi";

    }

    @FXML
    void voiceHina(ActionEvent event) {
        buttonHina.setStyle("-fx-background-color:  #354259; -fx-text-fill: white");
        imageHina.setImage(new Image("icon/5627749_avatar_face_female_people_profile_icon.png"));
        buttonAiri.setStyle("-fx-background-color:  #C2DED1; -fx-font-size: 24; -fx-text-fill: black;");
        imageAiri.setImage(new Image("icon/6104747.png"));
        buttonAkira.setStyle("-fx-background-color:  #C2DED1; -fx-font-size: 24; -fx-text-fill: black;");
        imageAkira.setImage(new Image("icon/6104776.png"));
        buttonFumi.setStyle("-fx-background-color:  #C2DED1; -fx-font-size: 24; -fx-text-fill: black;");
        imageFumi.setImage(new Image("icon/6104758.png"));

        voiceName = "Hina";

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        showComponent("/SearchGUI.fxml");
        buttonHina.setStyle("-fx-background-color:  #354259; -fx-text-fill: white");
        imageHina.setImage(new Image("icon/5627749_avatar_face_female_people_profile_icon.png"));
        buttonAiri.setStyle("-fx-background-color:  #C2DED1; -fx-font-size: 24; -fx-text-fill: black;");
        imageAiri.setImage(new Image("icon/6104747.png"));
        buttonAkira.setStyle("-fx-background-color:  #C2DED1; -fx-font-size: 24; -fx-text-fill: black;");
        imageAkira.setImage(new Image("icon/6104776.png"));
        buttonFumi.setStyle("-fx-background-color:  #C2DED1; -fx-font-size: 24; -fx-text-fill: black;");
        imageFumi.setImage(new Image("icon/6104758.png"));


    }


}
