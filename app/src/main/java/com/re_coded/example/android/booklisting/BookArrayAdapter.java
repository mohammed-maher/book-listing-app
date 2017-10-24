package com.re_coded.example.android.booklisting;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by Lenovo on 10/24/2017.
 */

public class BookArrayAdapter extends ArrayAdapter {
    int resource;
    public BookArrayAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull Book[] objects) {
        super(context, resource, objects);
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(resource,parent,false);
            viewHolder = new ViewHolder();
            viewHolder.author = convertView.findViewById(R.id.author_text_view);
            viewHolder.title = convertView.findViewById(R.id.title_text_view);
            viewHolder.publish = convertView.findViewById( R.id.publish_text_view);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Book book = (Book) getItem(position);

        viewHolder.title.setText(book.getTitle());
        viewHolder.publish.setText(book.getDate());
        viewHolder.author.setText(book.getAutor());

        return convertView;
    }

    static class ViewHolder {
        TextView title;
        TextView publish;
        TextView author;
    }
}


