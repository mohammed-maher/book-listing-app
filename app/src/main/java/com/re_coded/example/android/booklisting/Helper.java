package com.re_coded.example.android.booklisting;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lenovo on 10/23/2017.
 */

public class Helper {
    public static List<Book> parseJson(String jsonData) {
        String name;
        String author = "Unknown";
        String publishDate;
        List<Book> books = new ArrayList<>();
        if (jsonData.isEmpty())
            return null;

        try {
            JSONObject book_object = new JSONObject(jsonData);
            JSONArray items = book_object.getJSONArray("items");
            for (int i = 0; i < items.length(); i++) {
                JSONObject items_object = items.getJSONObject(i);
                JSONObject volumeInfo = items_object.getJSONObject("volumeInfo");
                try {
                    name = volumeInfo.getString("title");
                }catch (Exception e){
                    name = "Unkown Book Title";
                }
                JSONArray authors;
                try{
                    authors = volumeInfo.getJSONArray("authors");
                    author = authors.getString(0);
                }catch (Exception e){
                    author = "Unknown Author";
                }
                try{
                    publishDate = volumeInfo.getString("publishedDate");

                }catch (Exception e){
                    publishDate = "Unknown Publish Date";
                }
                if(name!=null && publishDate !=null&&author!=null)
                    books.add( new Book(name, publishDate, author));

            }
        } catch (Exception e) {
            e.printStackTrace();

        }
        return books;
    }
}
