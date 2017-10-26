package com.re_coded.example.android.booklisting;

import android.databinding.DataBindingUtil;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.re_coded.example.android.booklisting.databinding.ActivityMainBinding;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b = DataBindingUtil.setContentView(this, R.layout.activity_main);
        b.searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isNetworkAvailable()) {
                    b.searchButton.setEnabled(false);
                    b.loadProgress.setVisibility(View.VISIBLE);
                    b.noResultText.setVisibility(View.GONE);
                    b.booksList.setVisibility(View.VISIBLE);
                    BooksAsync task = new BooksAsync();
                    task.execute(b.searchEditText.getText().toString().replaceAll(" ", "+"));
                } else {
                    Toast.makeText(view.getContext(), "Please Check Your Internet Connection!", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public boolean isNetworkAvailable() {
        ConnectivityManager connectivityMgr = (ConnectivityManager) getSystemService(this.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            return true;
        } else {
            return false;
        }
    }

    public void emptySearchResults(){
        b.booksList.setVisibility(View.GONE);
        b.noResultText.setVisibility(View.VISIBLE);
    }

    public void updateUI(List<Book> books) {
        Log.e("UPDATE UI", "YES");
        b.booksList.setAdapter(new BookArrayAdapter(this, R.layout.books_list_item, books));
    }

    class BooksAsync extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... strings) {
            StringBuilder JSONData = new StringBuilder();
            HttpURLConnection httpURLConnection = null;
            InputStream inputStream = null;
            try {
                URL url = new URL(getString(R.string.api_url) + strings[0] + "&maxResults=" + getString(R.string.number_of_result));
                httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("GET");
                httpURLConnection.setConnectTimeout(10000);
                httpURLConnection.setReadTimeout(15000);
                httpURLConnection.connect();
                if (httpURLConnection.getResponseCode() == 200) {
                    inputStream = httpURLConnection.getInputStream();
                    InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                    String line = bufferedReader.readLine();
                    while (line != null) {
                        JSONData.append(line);
                        line = bufferedReader.readLine();
                    }
                } else {
                    Log.e("Log_Msg", httpURLConnection.getResponseCode() + "");
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (httpURLConnection != null) {
                    httpURLConnection.disconnect();
                }
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            return JSONData.toString();
        }

        @Override
        protected void onProgressUpdate(String... values) {

        }

        @Override
        protected void onPostExecute(String s) {
            try {
                JSONObject jsonObject = new JSONObject(s);
                int count = jsonObject.getInt("totalItems");
                if (count > 0) {
                    List<Book> books = Helper.parseJson(s);
                    updateUI(books);
                }else{
                    emptySearchResults();
                }
                b.searchButton.setEnabled(true);
                b.loadProgress.setVisibility(View.GONE);

            } catch (JSONException e) {
                Log.e("PRINT", e.getMessage());
            }
        }
    }
}
