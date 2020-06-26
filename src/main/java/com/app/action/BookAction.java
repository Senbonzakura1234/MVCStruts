package com.app.action;

import com.app.context.AuthorBean;
import com.app.context.BookBean;
import com.app.context.CategoryBean;
import com.app.context.PublisherBean;
import com.app.entity.Author;
import com.app.entity.Book;
import com.app.entity.Category;
import com.app.entity.Publisher;
import com.app.model.SelectOption;
import com.app.model.returnResult.DatabaseQueryResult;
import com.opensymphony.xwork2.ActionSupport;

import java.util.ArrayList;
import java.util.List;

public class BookAction extends ActionSupport {
    AuthorBean authorBean;
    CategoryBean categoryBean;
    PublisherBean publisherBean;
    BookBean bookBean;

    public String query;


    public String id;
    public String publisherId;
    public String authorId;
    public String categoryId;
    public String name;
    public String description;
    public Book.StatusEnum status;
    public List<SelectOption> publisherSelectList;
    public List<SelectOption> authorSelectList;
    public List<SelectOption> categorySelectList;
    public List<SelectOption> statusSelectList;
    public List<Book> books;

    public String index(){
        bookBean = new BookBean();
        books = bookBean.getAllBook(query);
        if(books != null){
            return "success";
        }else {
            return "bad";
        }
    }

    public String createGet(){

        categoryBean = new CategoryBean();
        List<Category> categories = categoryBean.getAllCategory("");
        if(categories == null) return "bad";
        if(categories.isEmpty()) return "requireCategory";

        publisherBean = new PublisherBean();
        List<Publisher> publishers = publisherBean.getAllPublisher("");
        if(publishers == null) return "bad";
        if(publishers.isEmpty()) return "requirePublisher";

        authorBean = new AuthorBean();
        List<Author> authors = authorBean.getAllAuthor("");
        if(authors == null) return "bad";
        if(authors.isEmpty()) return "requireAuthor";


        publisherSelectList = new ArrayList<>();
        for (Publisher item: publishers
        ) {
            publisherSelectList.add(new SelectOption(item.getId(), item.getName(), false));
        }
        authorSelectList = new ArrayList<>();
        for (Author item: authors
        ) {
            authorSelectList.add(new SelectOption(item.getId(), item.getName(), false));
        }
        categorySelectList = new ArrayList<>();
        for (Category item: categories
        ) {
            categorySelectList.add(new SelectOption(item.getId(), item.getName(), false));
        }

        return "success";
    }

    public String createPost(){
        Book book = new Book();
        book.setName(name);
        book.setDescription(description);
        book.setAuthorId(authorId);
        book.setCategoryId(categoryId);
        book.setPublisherId(publisherId);
        bookBean = new BookBean();
        DatabaseQueryResult DQR = bookBean.addBook(book);
        if(DQR.isSuccess()){
            return "success";
        }else {
            return "bad";
        }
    }

    public String updateGet(){
        bookBean = new BookBean();
        Book book = bookBean.getBook(id);
        if(book != null){
            id = book.getId();
            name = book.getName();
            description = book.getDescription();
            categoryId = book.getCategoryId();
            publisherId = book.getPublisherId();
            authorId = book.getAuthorId();

            categoryBean = new CategoryBean();
            List<Category> categories = categoryBean.getAllCategory("");
            if(categories == null) return "bad";
            if(categories.isEmpty()) return "requireCategory";

            publisherBean = new PublisherBean();
            List<Publisher> publishers = publisherBean.getAllPublisher("");
            if(publishers == null) return "bad";
            if(publishers.isEmpty()) return "requirePublisher";

            authorBean = new AuthorBean();
            List<Author> authors = authorBean.getAllAuthor("");
            if(authors == null) return "bad";
            if(authors.isEmpty()) return "requireAuthor";

            publisherSelectList = new ArrayList<>();
            for (Publisher item: publishers
            ) {
                publisherSelectList.add(new SelectOption(item.getId(), item.getName(), item.getId().equals(publisherId)));
            }
            authorSelectList = new ArrayList<>();
            for (Author item: authors
            ) {
                authorSelectList.add(new SelectOption(item.getId(), item.getName(), item.getId().equals(authorId)));
            }
            categorySelectList = new ArrayList<>();
            for (Category item: categories
            ) {
                categorySelectList.add(new SelectOption(item.getId(), item.getName(), item.getId().equals(categoryId)));
            }

            return "success";
        }else {
            return "bad";
        }
    }

    public String updatePost(){
        Book book = new Book();
        book.setName(name);
        book.setDescription(description);
        book.setPublisherId(publisherId);
        book.setCategoryId(categoryId);
        book.setAuthorId(authorId);
        bookBean = new BookBean();
        DatabaseQueryResult DQR = bookBean.updateBook(book, id);
        if(DQR.isSuccess()){
            return "success";
        }else {
            return "bad";
        }
    }

    public String deletePost(){
        bookBean = new BookBean();
        DatabaseQueryResult DQR = bookBean.deleteBook(id);
        if(DQR.isSuccess()){
            return "success";
        }else {
            return "bad";
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(String publisherId) {
        this.publisherId = publisherId;
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Book.StatusEnum getStatus() {
        return status;
    }

    public void setStatus(Book.StatusEnum status) {
        this.status = status;
    }

    public List<SelectOption> getPublisherSelectList() {
        return publisherSelectList;
    }

    public void setPublisherSelectList(List<SelectOption> publisherSelectList) {
        this.publisherSelectList = publisherSelectList;
    }

    public List<SelectOption> getAuthorSelectList() {
        return authorSelectList;
    }

    public void setAuthorSelectList(List<SelectOption> authorSelectList) {
        this.authorSelectList = authorSelectList;
    }

    public List<SelectOption> getCategorySelectList() {
        return categorySelectList;
    }

    public void setCategorySelectList(List<SelectOption> categorySelectList) {
        this.categorySelectList = categorySelectList;
    }

    public List<SelectOption> getStatusSelectList() {
        return statusSelectList;
    }

    public void setStatusSelectList(List<SelectOption> statusSelectList) {
        this.statusSelectList = statusSelectList;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }
}
