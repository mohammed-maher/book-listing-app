package com.re_coded.example.android.booklisting;

/**
 * Created by Lenovo on 10/23/2017.
 */

public class Book {
    private String title;
    private String date;
    private String author;

    public Book(String title, String date, String author) {
        this.title = title;
        this.date = date;
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
