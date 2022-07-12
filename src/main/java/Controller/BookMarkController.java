package Controller;

import base.DictionaryManagement;
import base.VoiceRRS;
import base.Word;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import java.net.URL;
import java.util.ResourceBundle;

public class BookMarkController implements Initializable {
    @FXML
    private AnchorPane bookmarkContainer;

    @FXML
    private ToggleButton bookmarkListFalse;

    @FXML
    private ToggleButton bookmarkListTrue;

    @FXML
    private ToggleButton editBookMarkButton;

    @FXML
    private TextField editBookMarkCV;

    @FXML
    private TextField editBookMarkExplain;

    @FXML
    private TextField editBookMarkFurigana;

    @FXML
    private AnchorPane editBookMarkPane;

    @FXML
    private TextField editBookMarkWord;

    @FXML
    private ListView<Word> listBookMark;

    @FXML
    private ToggleButton removeBookMarkButton;

    @FXML
    private TextField searchFieldBookMark;

    @FXML
    private Label targetBookMarkWord;

    @FXML
    private Label wordBookMarkCV;

    @FXML
    private Label wordBookMarkExplain;

    @FXML
    private Label wordBookMarkHiragana;

    @FXML
    void actionBookmark(ActionEvent event) {
        if (listBookMark!=null) {
            Word selectedWord = listBookMark.getSelectionModel().getSelectedItem();
            int i = 0;
            if (selectedWord.getIsClick()==1 && i == 0) {
                DictionaryManagement.dbSetIsClick(selectedWord.getId(),0);
                selectedWord.setIsClick(0);
                listBookMark.getItems().remove(selectedWord);
                targetBookMarkWord.setVisible(false);
                wordBookMarkCV.setVisible(false);
                wordBookMarkExplain.setVisible(false);
                wordBookMarkHiragana.setVisible(false);
                bookmarkContainer.setVisible(false);
                i++;
            }
            bookmarkListFalse.setVisible(0==selectedWord.getIsClick());
            bookmarkListTrue.setVisible(1==selectedWord.getIsClick());
        }
    }

    @FXML
    void actionEdit(ActionEvent event) {
        if (listBookMark!=null) {
            Word selectedWord = listBookMark.getSelectionModel().getSelectedItem();
            int i = 0;
            if(editBookMarkPane.isVisible() && i == 0){
                editBookMarkPane.setVisible(false);
                i++;
            }
            if(!editBookMarkPane.isVisible() && i == 0){
                editBookMarkPane.setVisible(true);
                i++;
            }
            editBookMarkWord.setText(selectedWord.getWordTarget());
            editBookMarkCV.setText(selectedWord.getWordCV());
            editBookMarkFurigana.setText(selectedWord.getPronounce());
            editBookMarkExplain.setText(selectedWord.getWordExplain());
        }
    }

    @FXML
    void actionSave(ActionEvent event) {
        if (listBookMark!=null) {
            Word selectedWord = listBookMark.getSelectionModel().getSelectedItem();
            int stt = selectedWord.getId();
            String word = editBookMarkWord.getText();
            String cv = editBookMarkCV.getText();
            String explain = editBookMarkExplain.getText();
            String furi = editBookMarkFurigana.getText();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("確認");
            alert.setContentText("保存しますか");
            if (alert.showAndWait().get()== ButtonType.OK){
                DictionaryManagement.dbUpdate(word,furi,cv,explain,stt);
                Word newWord = new Word(word, explain, furi,cv,selectedWord.getIsClick(), selectedWord.getId(), selectedWord.getHistory());
                listBookMark.getItems().set(listBookMark.getSelectionModel().getSelectedIndex(), newWord);
                wordBookMarkExplain.setText(newWord.getWordExplain());
                if (newWord.getPronounce() != null){
                    wordBookMarkHiragana.setText("【"+newWord.getPronounce()+"】");
                }
                targetBookMarkWord.setText(newWord.getWordTarget());
                wordBookMarkCV.setText(newWord.getWordCV());
            }
        }
    }

    @FXML
    void onActionSearchBtn(ActionEvent event) {
        if (listBookMark != null) {
            ObservableList<Word> list = DictionaryManagement.dbSearchWordBookMark("'" + searchFieldBookMark.getText().toLowerCase().trim() + "%'");
            listBookMark.setItems(list);
        }

    }

    @FXML
    void onMouseClickListView(MouseEvent event) {
        if (listBookMark != null && listBookMark.getSelectionModel().getSelectedItem()!=null) {
            editBookMarkPane.setVisible(false);
            bookmarkContainer.setVisible(true);
            Word selectedWord = listBookMark.getSelectionModel().getSelectedItem();
            DictionaryManagement.dbSetHistory(selectedWord.getId(),DictionaryManagement.dbGetHistory());
            selectedWord.setHistory(1);
            if (selectedWord != null) {
                wordBookMarkExplain.setText(selectedWord.getWordExplain());
                if (selectedWord.getPronounce() != null){
                    wordBookMarkHiragana.setText("【"+selectedWord.getPronounce()+"】");
                }
                targetBookMarkWord.setText(selectedWord.getWordTarget());
                wordBookMarkCV.setText(selectedWord.getWordCV());
                bookmarkListFalse.setVisible(0==selectedWord.getIsClick());
                bookmarkListTrue.setVisible(1==selectedWord.getIsClick());
            }
        }
    }

    public void actionRemove () {
        if (listBookMark!= null && listBookMark.getSelectionModel().getSelectedItem() != null) {
            Word selectedWord = listBookMark.getSelectionModel().getSelectedItem();
            int stt = selectedWord.getId();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("確認");
            alert.setContentText("保存しますか");
            if(alert.showAndWait().get() == ButtonType.OK) {
                DictionaryManagement.dbDeleteWord(stt);
                listBookMark.getItems().remove(selectedWord);
                targetBookMarkWord.setVisible(false);
                wordBookMarkCV.setVisible(false);
                wordBookMarkExplain.setVisible(false);
                wordBookMarkHiragana.setVisible(false);
                bookmarkContainer.setVisible(false);
            }
        }
    }
    @FXML
    void voiceAction(ActionEvent event) {
        if (listBookMark!= null && listBookMark.getSelectionModel().getSelectedItem() != null){
            Word selectedWord = listBookMark.getSelectionModel().getSelectedItem();
            try {
                VoiceRRS.speaker(selectedWord.getWordTarget());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (DictionaryManagement.dbFetchBookMark()!=null) {
            ObservableList<Word> list= DictionaryManagement.dbFetchBookMark();
            listBookMark.setItems(list);
            listBookMark.setFixedCellSize(30);
        }
    }
}

