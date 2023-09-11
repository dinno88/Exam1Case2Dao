package edu.satraining.model;

import java.util.Date;

public class Book extends BaseModel{
    private String title;
    private String isbn;
    private String publisher;
    private float price;
    private int page;

    public Book(){}

    // Constructor with all parameters
    public Book(String title, String isbn, String publisher, float price, int page, String createdBy, Date createdTime) {
        super(createdBy, createdTime);
        this.title = title;
        this.isbn = isbn;
        this.publisher = publisher;
        this.price = price;
        this.page = page;
    }
    public Book(String title, String isbn, String publisher, float price, int page) {
        this.title = title;
        this.isbn = isbn;
        this.publisher = publisher;
        this.price = price;
        this.page = page;
    }

    // Getters and setters for title
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    // Getters and setters for isbn
    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    // Getters and setters for publisher
    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    // Getters and setters for price
    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    // Getters and setters for page
    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }
    
}

