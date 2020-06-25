package com.app.action;

import com.app.context.ShopBean;
import com.app.entity.Shop;
import com.app.model.SelectOption;
import com.app.model.returnResult.DatabaseQueryResult;
import com.opensymphony.xwork2.ActionSupport;

import java.util.ArrayList;
import java.util.List;

public class ShopAction extends ActionSupport {
    ShopBean shopBean;

    public String id;
    public String name;
    public String address;
    public String email;
    public String phone;
    public Shop.StatusEnum status;
    public List<Shop> shops;
    public List<SelectOption> statusSelectList;


    public String index(){
        shopBean = new ShopBean();
        shops = shopBean.getAllShop("");
        if(shops != null){
            return "success";
        }else {
            return "bad";
        }
    }
    public String createGet(){
        return "success";
    }
    public String createPost(){
        Shop shop = new Shop();
        shop.setName(name);
        shop.setAddress(address);
        shop.setEmail(email);
        shop.setPhone(phone);
        shopBean = new ShopBean();
        DatabaseQueryResult DQR = shopBean.addShop(shop);
        if(DQR.isSuccess()){
            return "success";
        }else {
            return "bad";
        }
    }
    public String updateGet(){
        shopBean = new ShopBean();
        Shop shop = shopBean.getShop(id);
        if(shop != null){
            id = shop.getId();
            name = shop.getName();
            address = shop.getAddress();
            email = shop.getEmail();
            phone = shop.getPhone();
            return "success";
        }else {
            return "bad";
        }
    }
    public String updatePost(){
        Shop shop = new Shop();
        shop.setName(name);
        shop.setPhone(phone);
        shop.setEmail(email);
        shop.setAddress(address);
        shopBean = new ShopBean();
        DatabaseQueryResult DQR = shopBean.updateShop(shop, id);
        if(DQR.isSuccess()){
            return "success";
        }else {
            return "bad";
        }
    }
    public String updateStatusGet(){
        shopBean = new ShopBean();
        Shop shop = shopBean.getShop(id);
        if(shop != null){
            statusSelectList = new ArrayList<>();
            for (Shop.StatusEnum item: Shop.StatusEnum.values()
            ) {
                statusSelectList.add(new SelectOption(item.name(), item.name(), shop.getStatus() == item));
            }
            return "success";
        }else {
            return "bad";
        }
    }
    public String updateStatusPost(){
        Shop shop = new Shop();
        shop.setStatus(status);
        shopBean = new ShopBean();
        DatabaseQueryResult DQR = shopBean.updateShopStatus(shop, id);
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

    public List<Shop> getShops() {
        return shops;
    }

    public void setShops(List<Shop> shops) {
        this.shops = shops;
    }

    public Shop.StatusEnum getStatus() {
        return status;
    }

    public void setStatus(Shop.StatusEnum status) {
        this.status = status;
    }

    public List<SelectOption> getStatusSelectList() {
        return statusSelectList;
    }

    public void setStatusSelectList(List<SelectOption> statusSelectList) {
        this.statusSelectList = statusSelectList;
    }
}
