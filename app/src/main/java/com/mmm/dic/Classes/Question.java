package com.mmm.dic.Classes;

public class Question {
    private int id;
    private String question;

    public Question(int id,String question){
        this.id = id;
        this.question = question;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public int getId() {
        return id;
    }

    public String getQuestion() {
        return question;
    }
}
