package com.mmm.dic.Classes;

import java.util.List;

public class Test {
    private int id;
    private String name;
    private String description;
    private List<Question> questions;

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }
}
