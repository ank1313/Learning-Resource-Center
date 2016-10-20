package com.example.android.minorproject;

/**
 * Created by Ankit on 19-10-2016.
 */
public class BookInfo {
    private String title,author,publisher,available;

    public BookInfo(){
    }

    public BookInfo(String title,String author,String publisher,String available){
        this.title=title;
        this.author=author;
        this.publisher=publisher;
        this.available=available;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getAvailable() {
        return available;
    }

    public void setAvailable(String available) {
        this.available = available;
    }
}
