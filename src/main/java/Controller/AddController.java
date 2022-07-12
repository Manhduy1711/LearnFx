package Controller;

import base.DictionaryManagement;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;

public class AddController {

    @FXML
    private TextField addCV;

    @FXML
    private TextField addExplain;

    @FXML
    private TextField addHiragana;

    @FXML
    private TextField addKanji;

    @FXML
    void actionSaveAdd(ActionEvent event) {
        String word = addKanji.getText();
        String explain = addExplain.getText();
        String furi = addHiragana.getText();
        String cv = addCV.getText();
        if(word.equals("") || explain.equals("")) {
            Alert alert2 = new Alert(Alert.AlertType.ERROR);
            alert2.setTitle("ERROR");
            alert2.setHeaderText("ERROR");
            alert2.setContentText("ERROR");
        }
        else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("追加");
            alert.setContentText("追加しますか");
            if (alert.showAndWait().get()== ButtonType.OK){
                Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("成功");
                DictionaryManagement.dbInsert(word, furi, cv, explain);
            }
        }

    }

}

