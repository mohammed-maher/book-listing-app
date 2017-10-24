package com.re_coded.example.android.booklisting;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import java.util.List;

/**
 * Created by Lenovo on 10/24/2017.
 */

public class MyAsyncTaskLoader extends AsyncTaskLoader<List<Book>> {
    public MyAsyncTaskLoader(Context context,String url) {
        super(context);

    }

    @Override
    public List<Book> loadInBackground() {
        return null;
    }
}
