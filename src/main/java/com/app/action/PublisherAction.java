package com.app.action;

import com.app.context.PublisherBean;
import com.app.entity.Publisher;
import com.app.model.SelectOption;
import com.app.model.returnResult.DatabaseQueryResult;
import com.opensymphony.xwork2.ActionSupport;

import java.util.ArrayList;
import java.util.List;

public class PublisherAction extends ActionSupport {
    PublisherBean publisherBean;

    public String id;
    public String name;
    public String address;
    public String email;
    public String phone;
    public Publisher.StatusEnum status;
    public List<Publisher> publishers;
    public List<SelectOption> statusSelectList;


    public String index(){
        publisherBean = new PublisherBean();
        publishers = publisherBean.getAllPublisher("");
        if(publishers != null){
            return "success";
        }else {
            return "bad";
        }
    }
    public String createGet(){
        return "success";
    }
    public String createPost(){
        Publisher publisher = new Publisher();
        publisher.setName(name);
        publisher.setAddress(address);
        publisher.setEmail(email);
        publisher.setPhone(phone);
        publisherBean = new PublisherBean();
        DatabaseQueryResult DQR = publisherBean.addPublisher(publisher);
        if(DQR.isSuccess()){
            return "success";
        }else {
            return "bad";
        }
    }
    public String updateGet(){
        publisherBean = new PublisherBean();
        Publisher publisher = publisherBean.getPublisher(id);
        if(publisher != null){
            id = publisher.getId();
            name = publisher.getName();
            address = publisher.getAddress();
            email = publisher.getEmail();
            phone = publisher.getPhone();
            return "success";
        }else {
            return "bad";
        }
    }
    public String updatePost(){
        Publisher publisher = new Publisher();
        publisher.setName(name);
        publisher.setPhone(phone);
        publisher.setEmail(email);
        publisher.setAddress(address);
        publisherBean = new PublisherBean();
        DatabaseQueryResult DQR = publisherBean.updatePublisher(publisher, id);
        if(DQR.isSuccess()){
            return "success";
        }else {
            return "bad";
        }
    }
    public String updateStatusGet(){
        publisherBean = new PublisherBean();
        Publisher publisher = publisherBean.getPublisher(id);
        if(publisher != null){
            statusSelectList = new ArrayList<>();
            for (Publisher.StatusEnum item: Publisher.StatusEnum.values()
            ) {
                statusSelectList.add(new SelectOption(item.name(), item.name(), publisher.getStatus() == item));
            }
            return "success";
        }else {
            return "bad";
        }
    }
    public String updateStatusPost(){
        Publisher publisher = new Publisher();
        publisher.setStatus(status);
        publisherBean = new PublisherBean();
        DatabaseQueryResult DQR = publisherBean.updatePublisherStatus(publisher, id);
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

    public List<Publisher> getPublishers() {
        return publishers;
    }

    public void setPublishers(List<Publisher> publishers) {
        this.publishers = publishers;
    }

    public Publisher.StatusEnum getStatus() {
        return status;
    }

    public void setStatus(Publisher.StatusEnum status) {
        this.status = status;
    }

    public List<SelectOption> getStatusSelectList() {
        return statusSelectList;
    }

    public void setStatusSelectList(List<SelectOption> statusSelectList) {
        this.statusSelectList = statusSelectList;
    }
}
