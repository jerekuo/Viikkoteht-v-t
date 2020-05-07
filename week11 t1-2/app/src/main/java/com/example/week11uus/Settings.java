package com.example.week11uus;

public class Settings {
    private static Settings settings = new Settings();

    String text= "Default user";

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    int fontSize = 11;

    public int getFontSize() {
        return fontSize;
    }

    public void setFontSize(int fontSize) {
        this.fontSize = fontSize;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getnRows() {
        return nRows;
    }

    public void setnRows(int nRows) {
        this.nRows = nRows;
    }

    public Boolean getEditable() {
        return editable;
    }

    public void setEditable(Boolean editable) {
        this.editable = editable;
    }

    int width = 350;
    int height = 350;
    int nRows = 5;
    Boolean editable = true;

    private Settings() {
        //default constructor
    }

    public static Settings getInstance() {
        return settings;
    }

}
