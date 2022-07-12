package base;

public class Word {
    private String wordTarget;
    private String wordExplain;
    private String pronounce;
    private String wordCV;
    private int isClick;
    private int Id;
    private int history;

    public Word() {
        this.wordTarget = "";
        this.wordExplain = "";
        this.pronounce = "";
        this.wordCV = "";
        this.isClick = 0;
        this.Id = - 1;
        this.history = -1;
    }

    public Word(String wordTarget, String wordExplain, String pronounce, String wordCV, int isClick, int id, int history) {
        this.wordTarget = wordTarget;
        this.wordExplain = wordExplain;
        this.pronounce = pronounce;
        this.wordCV = wordCV;
        this.isClick=isClick;
        this.Id = id;
        this.history = history;
    }

    public String getWordTarget() {
        return wordTarget;
    }

    public void setWordTarget(String wordTarget) {
        this.wordTarget = wordTarget;
    }

    public String getWordExplain() {
        return wordExplain;
    }

    public void setWordExplain(String wordExplain) {
        this.wordExplain = wordExplain;
    }

    public String getPronounce() {
        return pronounce;
    }

    public void setPronounce(String pronounce) {
        this.pronounce = pronounce;
    }

    public String getWordCV() {
        return wordCV;
    }

    public void setWordCV(String wordCV) {
        this.wordCV = wordCV;
    }

    public int getIsClick() {
        return isClick;
    }

    public int getId() {
        return Id;
    }

    public void setIsClick(int isClick) {
        this.isClick = isClick;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getHistory() {
        return history;
    }

    public void setHistory(int history) {
        this.history = history;
    }

    @Override
    public String toString() {
        return wordTarget;
    }
}
