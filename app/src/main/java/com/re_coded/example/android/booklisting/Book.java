package com.re_coded.example.android.booklisting;

/**
 * Created by Lenovo on 10/23/2017.
 */

public class Book {
    private String Title;
    private String Date;
    private String Autor;

    public Book(String title, String date, String autor) {
        Title = title;
        Date = date;
        Autor = autor;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getAutor() {
        return Autor;
    }

    public void setAutor(String autor) {
        Autor = autor;
    }
}
