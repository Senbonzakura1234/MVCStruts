package com.app.action;

import com.app.context.AuthorBean;
import com.app.entity.Author;
import com.app.model.returnResult.DatabaseQueryResult;
import com.opensymphony.xwork2.ActionSupport;

import java.util.List;

public class AuthorAction extends ActionSupport {
    AuthorBean authorBean;

    public String id;
    public String name;
    public String address;
    public String email;
    public String phone;
    public List<Author> authors;

    public String index(){
        authorBean = new AuthorBean();
        authors = authorBean.getAllAuthor("");
        if(authors != null){
            return "success";
        }else {
            return "bad";
        }
    }
    public String createGet(){
        return "success";
    }
    public String createPost(){
        Author author = new Author();
        author.setName(name);
        author.setAddress(address);
        author.setEmail(email);
        author.setPhone(phone);
        authorBean = new AuthorBean();
        DatabaseQueryResult DQR = authorBean.addAuthor(author);
        if(DQR.isSuccess()){
            return "success";
        }else {
            return "bad";
        }
    }
    public String updateGet(){
        authorBean = new AuthorBean();
        Author author = authorBean.getAuthor(id);
        if(author != null){
            id = author.getId();
            name = author.getName();
            address = author.getAddress();
            email = author.getEmail();
            phone = author.getPhone();
            return "success";
        }else {
            return "bad";
        }
    }
    public String updatePost(){
        Author author = new Author();
        author.setName(name);
        author.setPhone(phone);
        author.setEmail(email);
        author.setAddress(address);
        authorBean = new AuthorBean();
        DatabaseQueryResult DQR = authorBean.updateAuthor(author, id);
        if(DQR.isSuccess()){
            return "success";
        }else {
            return "bad";
        }
    }
//    public String deletePost(){
//        authorBean = new AuthorBean();
//        DatabaseQueryResult DQR = authorBean.deleteAuthor(id);
//        if(DQR.isSuccess()){
//            return "success";
//        }else {
//            return "bad";
//        }
//    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }
}
