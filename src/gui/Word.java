package gui;

import java.util.ArrayList;

public class Word {

    private int selectedWord;

    public Word() {
        this.selectedWord = 0;

    }

    protected int getSelectedWord() {
        return selectedWord;
    }

    protected void setSelectedWord(int selectedWords) {
        this.selectedWord = selectedWords;
    }


    public String firstLetter(ArrayList<String> arrayList) {
        return String.valueOf(arrayList.get(this.getSelectedWord()).toUpperCase().charAt(0));
    }

    public String secondLetter(ArrayList<String> arrayList) {
        return String.valueOf(arrayList.get(this.getSelectedWord()).toUpperCase().charAt(1));
    }

}
