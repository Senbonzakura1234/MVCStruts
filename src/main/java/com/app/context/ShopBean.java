package com.app.context;

import com.app.entity.Author;
import com.app.entity.Shop;
import com.app.model.returnResult.DatabaseQueryResult;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

public class ShopBean {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistence");
    EntityManager em = emf.createEntityManager();
    
    public List<Shop> getAllShop(String query) {
        
        try {
            em.getTransaction().begin();
            List<Shop> list;
            if (query != null && !query.isEmpty()) {
                list = em.createQuery(
                        "SELECT c from Shop c where name like :queryString",
                        Shop.class).setParameter("queryString", "%" + query + "%")
                        .getResultList();
            } else {
                list =  em.createQuery("SELECT c from Shop c",
                        Shop.class)
                        .getResultList();
            }
            em.getTransaction().commit();
            em.close();emf.close();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
            em.close();emf.close();
            return new ArrayList<>();
        }
    }

    public Shop getShop(String id) {
        if(id != null && !id.isEmpty()){
            
            try {
                em.getTransaction().begin();
                Shop shop = em.find(Shop.class, id);
                em.getTransaction().commit();
                em.close();emf.close();
                return shop;
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

    public DatabaseQueryResult addShop(Shop shop) {
        if(shop != null){
            try {
                em.getTransaction().begin();
                em.persist(shop);
                em.getTransaction().commit();
                em.close();emf.close();
                return new DatabaseQueryResult(true, "addShop success");
            } catch (Exception e) {
                e.printStackTrace();
                em.getTransaction().rollback();
                em.close();emf.close();
                return new DatabaseQueryResult(false, "addShop fail, " + e.getMessage());
            }
        }else {
            return new DatabaseQueryResult(false, "addShop fail, input is null");
        }
    }

    public DatabaseQueryResult updateShop(Shop shop, String id) {
        if(shop != null && id != null && !id.isEmpty()){
            try {
                em.getTransaction().begin();
                Shop u = em.find(Shop.class, id);
                if(u != null){
                    u.setName(shop.getName());
                    u.setAddress(shop.getAddress());
                    u.setPhone(shop.getPhone());
                    u.setEmail(shop.getEmail());
                    u.setUpdatedAt(System.currentTimeMillis());
                    em.getTransaction().commit();
                    em.close();emf.close();
                    return new DatabaseQueryResult(true,
                            "updateShop success");
                }else {
                    em.getTransaction().commit();
                    em.close();emf.close();
                    return new DatabaseQueryResult(false,
                            "updateShop failed, Shop not found");
                }
            } catch (Exception e) {
                e.printStackTrace();
                em.getTransaction().rollback();
                em.close();emf.close();
                return new DatabaseQueryResult(false,
                        "updateShop failed, " + e.getMessage());
            }
        }else {
            return new DatabaseQueryResult(false,
                    "updateAuthor failed, bad request");
        }
    }

    public DatabaseQueryResult updateShopStatus(Shop shop, String id) {
        if(id != null && !id.isEmpty()){
            
            try {
                em.getTransaction().begin();
                Shop u = em.find(Shop.class, id);
                if(u != null){
                    u.setStatus(shop.getStatus());
                    u.setUpdatedAt(System.currentTimeMillis());
                    u.setDeletedAt(System.currentTimeMillis());

                    em.getTransaction().commit();
                    em.close();emf.close();
                    return new DatabaseQueryResult(true,
                            "updateShop status success");
                }else {
                    em.getTransaction().commit();
                    em.close();emf.close();
                    return new DatabaseQueryResult(false,
                            "updateShop status failed, Shop not found");
                }
            } catch (Exception e) {
                e.printStackTrace();
                em.getTransaction().rollback();
                em.close();emf.close();
                return new DatabaseQueryResult(false,
                        "updateShop status failed, " + e.getMessage());
            }
        }else {
            return new DatabaseQueryResult(false,
                    "updateShop status failed, bad request");
        }
    }
}
