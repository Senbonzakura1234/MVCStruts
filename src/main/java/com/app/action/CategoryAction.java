package com.app.action;

import com.app.context.CategoryBean;
import com.app.entity.Category;
import com.app.model.returnResult.DatabaseQueryResult;
import com.opensymphony.xwork2.ActionSupport;

import java.util.List;

public class CategoryAction extends ActionSupport {
    CategoryBean categoryBean;

    public String id;
    public String name;
    public List<Category> categories;

    public String index(){
        categoryBean = new CategoryBean();
        categories = categoryBean.getAllCategory("");
        if(categories != null){
            return "success";
        }else {
            return "bad";
        }
    }
    public String createGet(){
        return "success";
    }
    public String createPost(){
        Category category = new Category();
        category.setName(name);
        categoryBean = new CategoryBean();
        DatabaseQueryResult DQR = categoryBean.addCategory(category);
        if(DQR.isSuccess()){
            return "success";
        }else {
            return "bad";
        }
    }
    public String updateGet(){
        categoryBean = new CategoryBean();
        Category category = categoryBean.getCategory(id);
        if(category != null){
            id = category.getId();
            name = category.getName();
            return "success";
        }else {
            return "bad";
        }
    }
    public String updatePost(){
        Category category = new Category();
        category.setName(name);
        categoryBean = new CategoryBean();
        DatabaseQueryResult DQR = categoryBean.updateCategory(category, id);
        if(DQR.isSuccess()){
            return "success";
        }else {
            return "bad";
        }
    }
//    public String deletePost(){
//        categoryBean = new CategoryBean();
//        DatabaseQueryResult DQR = categoryBean.deleteCategory(id);
//        if(DQR.isSuccess()){
//            return "success";
//        }else {
//            return "bad";
//        }
//    }

//    @Override
//    public void validate() {
//        if (name.isEmpty()) {
//            addFieldError("name", "name is required");
//        }
//    }



    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
