package com.re_coded.example.android.booklisting;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by Lenovo on 10/23/2017.
 */

public class Helper {
    public static Book[] parseJson(String jsonData) {
        Book[] books = new Book[jsonData.length()];

        String name;
        String author = "Unknown";
        String publishDate;

        if (jsonData.isEmpty())
            return null;

        try {
            JSONObject book_object = new JSONObject(jsonData);
            JSONArray items = book_object.getJSONArray("items");
            for (int i = 0; i < items.length(); i++) {
                JSONObject items_object = items.getJSONObject(i);
                JSONObject volumeInfo = items_object.getJSONObject("volumeInfo");
                name = volumeInfo.getString("title");
                JSONArray authors;
                if (volumeInfo.has("authors")) {
                    authors = volumeInfo.getJSONArray("authors");
                    author = authors.getString(0);
                }
                if (volumeInfo.getString("publishedDate").isEmpty()) {
                    publishDate = "Unknown";
                } else {
                    publishDate = volumeInfo.getString("publishedDate");

                }

                books[i] = new Book(name, publishDate, author);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return books;
    }
}
