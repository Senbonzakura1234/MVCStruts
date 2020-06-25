package com.app.context;

import com.app.entity.Category;
import com.app.model.returnResult.DatabaseQueryResult;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;


public class CategoryBean {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistence");
    EntityManager em = emf.createEntityManager();

    public List<Category> getAllCategory(String query) {
        try {
            em.getTransaction().begin();
            List<Category> list;
            if (query != null && !query.isEmpty()) {
                list = em.createQuery(
                        "SELECT c from Category c where name like :queryString and status != :queryStatus",
                        Category.class).setParameter("queryString", "%" + query + "%")
                        .setParameter("queryStatus", Category.StatusEnum.DELETED).getResultList();
            } else {
                list =  em.createQuery("SELECT c from Category c where status != :queryStatus",
                        Category.class).setParameter("queryStatus", Category.StatusEnum.DELETED)
                        .getResultList();
            }
            em.getTransaction().commit();
            em.close();emf.close();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
            em.close();emf.close();
            return null;
        }
    }

    public Category getCategory(String id) {
        if(id != null && !id.isEmpty()){
            try {
                em.getTransaction().begin();
                Category category = em.find(Category.class, id);
                em.getTransaction().commit();
                em.close();emf.close();
                return category.getStatus() != Category.StatusEnum.DELETED? category : null;
            } catch (Exception e) {
                e.printStackTrace();
                em.getTransaction().rollback();
                em.close();emf.close();
                return null;
            }
        }else {
            return null;
        }
    }

    public DatabaseQueryResult addCategory(Category category) {
        if(category != null){
            try {
                em.getTransaction().begin();
                em.persist(category);
                em.getTransaction().commit();
                em.close();emf.close();
                return new DatabaseQueryResult(true, "addCategory success");
            } catch (Exception e) {
                e.printStackTrace();
                em.getTransaction().rollback();
                em.close();emf.close();
                return new DatabaseQueryResult(false, "addCategory fail, " + e.getMessage());
            }
        }else {
            return new DatabaseQueryResult(false, "addCategory fail, input is null");
        }
    }

    public DatabaseQueryResult updateCategory(Category category, String id) {
        if(category != null && id != null && !id.isEmpty()){
            try {
                em.getTransaction().begin();
                Category u = em.find(Category.class, id);
                if(u != null && u.getStatus() != Category.StatusEnum.DELETED){
                    u.setName(category.getName());
                    if(category.getStatus() != Category.StatusEnum.DELETED){
                        u.setStatus(category.getStatus());
                    }
                    u.setUpdatedAt(System.currentTimeMillis());

                    em.getTransaction().commit();
                    em.close();emf.close();
                    return new DatabaseQueryResult(true,
                            "updateCategory success");
                }else {
                    em.getTransaction().commit();
                    em.close();emf.close();
                    return new DatabaseQueryResult(false,
                            "updateCategory failed, Category not found");
                }
            } catch (Exception e) {
                e.printStackTrace();
                em.getTransaction().rollback();
                em.close();emf.close();
                return new DatabaseQueryResult(false,
                        "updateCategory failed, " + e.getMessage());
            }
        }else {
            return new DatabaseQueryResult(false,
                    "updateCategory failed, bad request");
        }
    }

    public DatabaseQueryResult deleteCategory(String id) {
        if(id != null && !id.isEmpty()){
            try {
                em.getTransaction().begin();
                Category u = em.find(Category.class, id);
                if(u != null && u.getStatus() != Category.StatusEnum.DELETED){
                    u.setStatus(Category.StatusEnum.DELETED);
                    u.setUpdatedAt(System.currentTimeMillis());
                    u.setDeletedAt(System.currentTimeMillis());

                    em.getTransaction().commit();
                    em.close();emf.close();
                    return new DatabaseQueryResult(true,
                            "deleteCategory success");
                }else {
                    em.getTransaction().commit();
                    em.close();emf.close();
                    return new DatabaseQueryResult(false,
                            "deleteCategory failed, Category not found");
                }
            } catch (Exception e) {
                e.printStackTrace();
                em.getTransaction().rollback();
                em.close();emf.close();
                return new DatabaseQueryResult(false,
                        "deleteCategory failed, bad request");
            }
        }else {
            return new DatabaseQueryResult(false,
                    "deleteCategory failed, bad request");
        }
    }
}
