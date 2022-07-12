package Controller;

import base.DictionaryManagement;
import base.VoiceRRS;
import base.Word;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

public class HistoryController implements Initializable {

    @FXML
    private AnchorPane bookmarkContainer;

    @FXML
    private ToggleButton bookmarkHistoryFalse;

    @FXML
    private ToggleButton bookmarkHistoryTrue;

    @FXML
    private ToggleButton editHistoryButton;

    @FXML
    private TextField editHistoryCV;

    @FXML
    private TextField editHistoryExplain;

    @FXML
    private TextField editHistoryFurigana;

    @FXML
    private AnchorPane editHistoryPane;

    @FXML
    private TextField editHistoryWord;

    @FXML
    private ListView<Word> listHistory;

    @FXML
    private ToggleButton removeHitstoryButton;

    @FXML
    private TextField searchFieldHistory;

    @FXML
    private Label targetHistoryWord;

    @FXML
    private Label wordHistoryCV;

    @FXML
    private Label wordHistoryExplain;

    @FXML
    private Label wordHistoryHiragana;

    @FXML
    void actionBookmark(ActionEvent event) {
        if (listHistory!=null) {
            Word selectedWord = listHistory.getSelectionModel().getSelectedItem();
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
            bookmarkHistoryFalse.setVisible(0==selectedWord.getIsClick());
            bookmarkHistoryTrue.setVisible(1==selectedWord.getIsClick());
        }
    }

    @FXML
    void actionEdit(ActionEvent event) {
        if (listHistory!=null) {
            Word selectedWord = listHistory.getSelectionModel().getSelectedItem();
            int i = 0;
            if(editHistoryPane.isVisible() && i == 0){
                editHistoryPane.setVisible(false);
                i++;
            }
            if(!editHistoryPane.isVisible() && i == 0){
                editHistoryPane.setVisible(true);
                i++;
            }
            editHistoryWord.setText(selectedWord.getWordTarget());
            editHistoryCV.setText(selectedWord.getWordCV());
            editHistoryFurigana.setText(selectedWord.getPronounce());
            editHistoryExplain.setText(selectedWord.getWordExplain());
        }
    }

    @FXML
    void actionSave(ActionEvent event) {
        if (listHistory!=null) {
            Word selectedWord = listHistory.getSelectionModel().getSelectedItem();
            int stt = selectedWord.getId();
            String word = editHistoryWord.getText();
            String cv = editHistoryCV.getText();
            String explain = editHistoryExplain.getText();
            String furi = editHistoryFurigana.getText();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("確認");
            alert.setContentText("保存しますか");
            if (alert.showAndWait().get()== ButtonType.OK){
                DictionaryManagement.dbUpdate(word,furi,cv,explain,stt);
                Word newWord = new Word(word, explain, furi,cv,selectedWord.getIsClick(), selectedWord.getId(), selectedWord.getHistory());
                listHistory.getItems().set(listHistory.getSelectionModel().getSelectedIndex(), newWord);
                wordHistoryExplain.setText(newWord.getWordExplain());
                if (newWord.getPronounce() != null){
                    wordHistoryHiragana.setText("【"+newWord.getPronounce()+"】");
                }
                targetHistoryWord.setText(newWord.getWordTarget());
                wordHistoryCV.setText(newWord.getWordCV());
            }
        }
    }

    @FXML
    void onActionSearchHistoryBtn(ActionEvent event) {
        if (listHistory != null) {
            ObservableList<Word> list = DictionaryManagement.dbSearchWordHistory("'" + searchFieldHistory.getText().toLowerCase().trim() + "%'");
            listHistory.setItems(list);
        }
    }

    @FXML
    void onMouseClickListView(MouseEvent event) {
        if (listHistory != null) {
            editHistoryPane.setVisible(false);
            bookmarkContainer.setVisible(true);
            targetHistoryWord.setVisible(true);
            wordHistoryCV.setVisible(true);
            wordHistoryExplain.setVisible(true);
            wordHistoryHiragana.setVisible(true);
            Word selectedWord = listHistory.getSelectionModel().getSelectedItem();
            selectedWord.setHistory(1);
            if (selectedWord != null) {
                wordHistoryExplain.setText(selectedWord.getWordExplain());
                if (selectedWord.getPronounce() != null){
                    wordHistoryHiragana.setText("【"+selectedWord.getPronounce()+"】");
                }
                targetHistoryWord.setText(selectedWord.getWordTarget());
                wordHistoryCV.setText(selectedWord.getWordCV());
                bookmarkHistoryFalse.setVisible(0==selectedWord.getIsClick());
                bookmarkHistoryTrue.setVisible(1==selectedWord.getIsClick());
            }
        }

    }

    public void actionRemove () {
        if (listHistory!= null && listHistory.getSelectionModel().getSelectedItem() != null) {
            Word selectedWord = listHistory.getSelectionModel().getSelectedItem();
            int stt = selectedWord.getId();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("確認");
            alert.setContentText("保存しますか");
            if(alert.showAndWait().get() == ButtonType.OK) {
                DictionaryManagement.dbDeleteWord(stt);
                listHistory.getItems().remove(selectedWord);
            }
        }
    }

    @FXML
    void removeHistoryButton(ActionEvent event) {
        if (listHistory!= null && listHistory.getSelectionModel().getSelectedItem() != null) {
            Word selectedWord = listHistory.getSelectionModel().getSelectedItem();
            DictionaryManagement.dbSetHistory(selectedWord.getId(), 0);
            listHistory.getItems().remove(selectedWord);
            bookmarkContainer.setVisible(false);
            targetHistoryWord.setVisible(false);
            wordHistoryCV.setVisible(false);
            wordHistoryExplain.setVisible(false);
            wordHistoryHiragana.setVisible(false);
        }
    }

    @FXML
    void voiceAction(ActionEvent event) {
        if (listHistory!= null && listHistory.getSelectionModel().getSelectedItem() != null){
            Word selectedWord = listHistory.getSelectionModel().getSelectedItem();
            try {
                VoiceRRS.speaker(selectedWord.getWordTarget());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if(DictionaryManagement.dbFetchHistory() != null) {
            ObservableList<Word> list= DictionaryManagement.dbFetchHistory();
            listHistory.setItems(list);
            listHistory.setFixedCellSize(30);
        }
    }
}
