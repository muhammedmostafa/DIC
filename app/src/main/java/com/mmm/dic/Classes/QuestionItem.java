package com.mmm.dic.Classes;

public class QuestionItem {
    private int id;
    private String question;
    private int selection;

    public QuestionItem(int id, String question, int selection ){
        this.id = id;
        this.question = question;
        this.selection = selection;
    }

    public void setSelection(int selection) {
        this.selection = selection;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSelection() {
        return selection;
    }

    public String getQuestion() {
        return question;
    }

    public int getId() {
        return id;
    }
}
