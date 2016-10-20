package com.example.android.minorproject;

import android.graphics.Movie;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Ankit on 19-10-2016.
 */
public class BooksAdapter extends RecyclerView.Adapter<BooksAdapter.MyViewHolder>{
    private List<BookInfo> booksList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, author,publisher,available;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            author = (TextView) view.findViewById(R.id.author);
            publisher = (TextView) view.findViewById(R.id.publisher);
            available = (TextView) view.findViewById(R.id.available);
        }
    }

    public BooksAdapter(List<BookInfo> booksList) {
        this.booksList = booksList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_booklist, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        BookInfo book = booksList.get(position);
        holder.title.setText(book.getTitle());
        holder.author.setText(book.getAuthor());
        holder.publisher.setText(book.getPublisher());
        holder.available.setText(book.getAvailable());
    }

    @Override
    public int getItemCount() {
        return booksList.size();
    }
}
