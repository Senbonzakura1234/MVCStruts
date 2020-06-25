package com.app.entity;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.UUID;

@Entity
@Table(name = "book")
public class Book {
    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "shopId")
    private String shopId;

    @ManyToOne
    @JoinColumn(name = "shopId", updatable = false, insertable = false)
    private Shop shop;

    @Column(name = "categoryId")
    private String categoryId;

    @ManyToOne
    @JoinColumn(name = "categoryId", updatable = false, insertable = false)
    private Category category;

    @Column(name = "authorId")
    private String authorId;

    @ManyToOne
    @JoinColumn(name = "authorId", updatable = false, insertable = false)
    private Author author;

    @NotBlank(message = "please provide a name")
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "createdAt", nullable = false)
    private Long createdAt;

    @Column(name = "updatedAt", nullable = false)
    private Long updatedAt;

    @Column(name = "deletedAt")
    private Long deletedAt;

    @Column(name = "status", nullable = false)
    private StatusEnum status;

    public Book() {
        id = UUID.randomUUID().toString();
        status = StatusEnum.SHOW;
        createdAt = System.currentTimeMillis();
        updatedAt = System.currentTimeMillis();
    }

    public enum StatusEnum {
        SHOW(0),
        DELETED(1);

        private final int value;

        StatusEnum(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
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

    public Long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Long createdAt) {
        this.createdAt = createdAt;
    }

    public Long getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Long updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Long getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(Long deletedAt) {
        this.deletedAt = deletedAt;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }
}
