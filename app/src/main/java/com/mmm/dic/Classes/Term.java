package com.mmm.dic.Classes;

public class Term {
    private String id;
    private String name;
    private String nameInArabic;
    private String description;
    private String meaning;

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }

    public String getMeaning() {
        return meaning;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setNameInArabic(String nameInArabic) {
        this.nameInArabic = nameInArabic;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public String getNameInArabic() {
        return nameInArabic;
    }

    public void setName(String name) {
        this.name = name;
    }

}
