package com.mmm.dic.DataBase;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import java.util.List;

@Entity(tableName = "test")
    public class TestEntity {

        @PrimaryKey(autoGenerate = true)
        public int uid;
        @ColumnInfo(name = "id")
        public int id;
        @ColumnInfo(name = "name")
        private String name;
        @ColumnInfo(name = "description")
        private String description;
        @ColumnInfo(name = "questions")
        private String questions;

    @Ignore
    public TestEntity(String name, String description, String questions) {
        this.name = name;
        this.description = description;
        this.questions = questions;

    }

    public TestEntity(int id, String name, String description, String questions) {

        this.id = id;
        this.name = name;
        this.description = description;
        this.questions = questions;


    }

    public String getQuestions() {
        return questions;
    }

    public void setQuestions(String questions) {
        this.questions = questions;
    }

    public int getId() {
        return id;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }
    public void setUid(int uid) {
        this.uid = uid;
    }
    public String getName() {
        return name;
    }

    public int getUid() {
        return uid;
    }
}
