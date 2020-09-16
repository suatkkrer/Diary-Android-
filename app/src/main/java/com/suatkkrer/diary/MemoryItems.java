package com.suatkkrer.diary;

public class MemoryItems {

    String date,title,content;
    int icon;

    public MemoryItems() {
    }

    public MemoryItems(String date, String title, String content, int icon) {
        this.date = date;
        this.title = title;
        this.content = content;
        this.icon = icon;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }
}
