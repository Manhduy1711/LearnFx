package Controller;

import base.TranslateAPI;
import base.VoiceRRS;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

import java.io.IOException;

public class TranslateController {

    @FXML
    private Label label1;

    @FXML
    private Label label2;

    @FXML
    private Label textConvert1;

    @FXML
    private Label textConvert2;

    @FXML
    private TextArea textField1;

    @FXML
    private TextArea textField2;

    @FXML
    private Button translateVoiceBtn;

    @FXML
    void actionConvert(ActionEvent event) {
        String temp = textConvert2.getText();
        textConvert2.setText(textConvert1.getText());
        textConvert1.setText(temp);
        label1.setText(textConvert1.getText());
        label2.setText(textConvert2.getText());
        textField1.clear();
        textField2.clear();
        if (textConvert2.getText().equals("日本語")) {
            translateVoiceBtn.setVisible(true);
        }
        else {
            translateVoiceBtn.setVisible(false);
        }
    }

    @FXML
    void actionVoiceTranslate(ActionEvent event) throws Exception {
        VoiceRRS.speaker(textField2.getText());
    }

    @FXML
    void actionTranslate(ActionEvent event) throws IOException {
        if (textConvert1.getText().equals("日本語")){
            textField2.setText(TranslateAPI.translate("ja","vi",textField1.getText()));
        }
        if (textConvert1.getText().equals("ベトナム語")){
            textField2.setText(TranslateAPI.translate("vi","ja",textField1.getText()));
        }
    }

}