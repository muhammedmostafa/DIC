package com.mmm.dic.DataBase;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "terms")
    public class TermEntity {

        @PrimaryKey(autoGenerate = true)
        public int uid;
        @ColumnInfo(name = "id")
        public int id;
        @ColumnInfo(name = "name")
        private String name;
        @ColumnInfo(name = "name_in_arabic")
        private String name_in_arabic;
        @ColumnInfo(name = "description")
        private String description;
        @ColumnInfo(name = "details")
        private String details;

    @Ignore
    public TermEntity(String name, String name_in_arabic, String description, String details) {
        this.name = name;
        this.name_in_arabic = name_in_arabic;
        this.description = description;
        this.details = details;

    }

    public TermEntity(int id, String name, String name_in_arabic, String description, String details) {

        this.id = id;
        this.name = name;
        this.name_in_arabic = name_in_arabic;
        this.description = description;
        this.details = details;


    }

    public int getId() {
        return id;
    }

    public void setName_in_arabic(String name_in_arabic) {
        this.name_in_arabic = name_in_arabic;
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

    public void setDetails(String details) {
        this.details = details;
    }

    public String getDescription() {
        return description;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getDetails() {
        return details;
    }

    public String getName_in_arabic() {
        return name_in_arabic;
    }

    public String getName() {
        return name;
    }

    public int getUid() {
        return uid;
    }
}
