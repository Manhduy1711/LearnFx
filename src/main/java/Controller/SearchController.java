package Controller;

import base.DictionaryManagement;
import base.VoiceRRS;
import base.Word;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SearchController implements Initializable {
    @FXML
    private AnchorPane bookmarkContainer;

    @FXML
    private ToggleButton bookmarkFalse;

    @FXML
    private ToggleButton bookmarkTrue;

    @FXML
    private TextField editExplain;

    @FXML
    private TextField editFurigana;

    @FXML
    private TextField editCV;

    @FXML
    private AnchorPane editPane;

    @FXML
    private TextField editWord;

    @FXML
    private ListView<Word> listResult;

    @FXML
    private TextField searchField;

    @FXML
    private Label targetWord;

    @FXML
    private Label wordCV;

    @FXML
    private Label wordExplain;

    @FXML
    private Label wordHiragana;

    @FXML
    void onActionSearchBtn(ActionEvent event) {
        ObservableList<Word> list = DictionaryManagement.dbSearchWord("'" + searchField.getText().toLowerCase().trim() + "%'");
        listResult.setItems(list);
    }
    public void onMouseClickListView() {
        bookmarkContainer.setVisible(true);
        targetWord.setVisible(true);
        wordCV.setVisible(true);
        wordExplain.setVisible(true);
        wordHiragana.setVisible(true);
        editPane.setVisible(false);
        Word selectedWord = listResult.getSelectionModel().getSelectedItem();
        DictionaryManagement.dbSetHistory(selectedWord.getId(),DictionaryManagement.dbGetHistory() + 1);
        if (selectedWord != null) {
            wordExplain.setText(selectedWord.getWordExplain());
            if (selectedWord.getPronounce() != null){
                wordHiragana.setText("【"+selectedWord.getPronounce()+"】");
            }
            targetWord.setText(selectedWord.getWordTarget());
            wordCV.setText(selectedWord.getWordCV());
            bookmarkFalse.setVisible(0==selectedWord.getIsClick());
            bookmarkTrue.setVisible(1==selectedWord.getIsClick());
        }

    }

    public void actionBookmark(ActionEvent event){
        Word selectedWord = listResult.getSelectionModel().getSelectedItem();
        int i = 0;
        if (selectedWord.getIsClick()==0 && i == 0) {
            DictionaryManagement.dbSetIsClick(selectedWord.getId(),1);
            selectedWord.setIsClick(1);
            i++;
        }
        if (selectedWord.getIsClick()==1 && i == 0) {
            DictionaryManagement.dbSetIsClick(selectedWord.getId(),0);
            selectedWord.setIsClick(0);
            i++;

        }
        bookmarkFalse.setVisible(0==selectedWord.getIsClick());
        bookmarkTrue.setVisible(1==selectedWord.getIsClick());
    }

    public void actionEdit() {
        Word selectedWord = listResult.getSelectionModel().getSelectedItem();
        int i = 0;
        if(editPane.isVisible() && i == 0){
            editPane.setVisible(false);
            i++;
        }
        if(!editPane.isVisible() && i == 0){
            editPane.setVisible(true);
            i++;
        }
        editWord.setText(selectedWord.getWordTarget());
        editCV.setText(selectedWord.getWordCV());
        editFurigana.setText(selectedWord.getPronounce());
        editExplain.setText(selectedWord.getWordExplain());
    }

    public void actionSave() {
        Word selectedWord = listResult.getSelectionModel().getSelectedItem();
        int stt = selectedWord.getId();
        String word = editWord.getText();
        String cv = editCV.getText();
        String explain = editExplain.getText();
        String furi = editFurigana.getText();
        if(word.equals("") || explain.equals("")) {
            Alert alert2 = new Alert(Alert.AlertType.ERROR);
            alert2.setTitle("ERROR");
            alert2.setHeaderText("ERROR");
            alert2.setContentText("ERROR");
        }
        else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("確認");
            alert.setContentText("保存しますか");
            if (alert.showAndWait().get()== ButtonType.OK){
                DictionaryManagement.dbUpdate(word,furi,cv,explain,stt);
                Word newWord = new Word(word, explain, furi,cv,selectedWord.getIsClick(), selectedWord.getId(), selectedWord.getHistory());
                listResult.getItems().set(listResult.getSelectionModel().getSelectedIndex(), newWord);
                wordExplain.setText(newWord.getWordExplain());
                if (newWord.getPronounce() != null){
                    wordHiragana.setText("【"+newWord.getPronounce()+"】");
                }
                targetWord.setText(newWord.getWordTarget());
                wordCV.setText(newWord.getWordCV());
            }
        }

    }
    public void actionRemove () {
        Word selectedWord = listResult.getSelectionModel().getSelectedItem();
        int stt = selectedWord.getId();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("確認");
        alert.setContentText("削除しますか");
        if(alert.showAndWait().get() == ButtonType.OK) {
            DictionaryManagement.dbDeleteWord(stt);
            listResult.getItems().remove(selectedWord);
            bookmarkContainer.setVisible(false);
            targetWord.setVisible(false);
            wordCV.setVisible(false);
            wordExplain.setVisible(false);
            wordHiragana.setVisible(false);
            editPane.setVisible(false);
        }
    }

    @FXML
    void voiceAction(ActionEvent event) {
        Word selectedWord = listResult.getSelectionModel().getSelectedItem();
        try {
            VoiceRRS.speaker(selectedWord.getWordTarget());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<Word> list = DictionaryManagement.dbFetchAll();
        listResult.setItems(list);
        listResult.setFixedCellSize(30);
    }

}
