package com.app.context;

import com.app.entity.Publisher;
import com.app.model.returnResult.DatabaseQueryResult;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

public class PublisherBean {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistence");
    EntityManager em = emf.createEntityManager();
    
    public List<Publisher> getAllPublisher(String query) {
        
        try {
            em.getTransaction().begin();
            List<Publisher> list;
            if (query != null && !query.isEmpty()) {
                list = em.createQuery(
                        "SELECT c from Publisher c where name like :queryString",
                        Publisher.class).setParameter("queryString", "%" + query + "%")
                        .getResultList();
            } else {
                list =  em.createQuery("SELECT c from Publisher c",
                        Publisher.class)
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

    public Publisher getPublisher(String id) {
        if(id != null && !id.isEmpty()){
            
            try {
                em.getTransaction().begin();
                Publisher publisher = em.find(Publisher.class, id);
                em.getTransaction().commit();
                em.close();emf.close();
                return publisher;
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

    public DatabaseQueryResult addPublisher(Publisher publisher) {
        if(publisher != null){
            try {
                em.getTransaction().begin();
                em.persist(publisher);
                em.getTransaction().commit();
                em.close();emf.close();
                return new DatabaseQueryResult(true, "addPublisher success");
            } catch (Exception e) {
                e.printStackTrace();
                em.getTransaction().rollback();
                em.close();emf.close();
                return new DatabaseQueryResult(false, "addPublisher fail, " + e.getMessage());
            }
        }else {
            return new DatabaseQueryResult(false, "addPublisher fail, input is null");
        }
    }

    public DatabaseQueryResult updatePublisher(Publisher publisher, String id) {
        if(publisher != null && id != null && !id.isEmpty()){
            try {
                em.getTransaction().begin();
                Publisher u = em.find(Publisher.class, id);
                if(u != null){
                    u.setName(publisher.getName());
                    u.setAddress(publisher.getAddress());
                    u.setPhone(publisher.getPhone());
                    u.setEmail(publisher.getEmail());
                    u.setUpdatedAt(System.currentTimeMillis());
                    em.getTransaction().commit();
                    em.close();emf.close();
                    return new DatabaseQueryResult(true,
                            "updatePublisher success");
                }else {
                    em.getTransaction().commit();
                    em.close();emf.close();
                    return new DatabaseQueryResult(false,
                            "updatePublisher failed, Publisher not found");
                }
            } catch (Exception e) {
                e.printStackTrace();
                em.getTransaction().rollback();
                em.close();emf.close();
                return new DatabaseQueryResult(false,
                        "updatePublisher failed, " + e.getMessage());
            }
        }else {
            return new DatabaseQueryResult(false,
                    "updateAuthor failed, bad request");
        }
    }

    public DatabaseQueryResult updatePublisherStatus(Publisher publisher, String id) {
        if(id != null && !id.isEmpty()){
            
            try {
                em.getTransaction().begin();
                Publisher u = em.find(Publisher.class, id);
                if(u != null){
                    u.setStatus(publisher.getStatus());
                    u.setUpdatedAt(System.currentTimeMillis());
                    u.setDeletedAt(System.currentTimeMillis());

                    em.getTransaction().commit();
                    em.close();emf.close();
                    return new DatabaseQueryResult(true,
                            "updatePublisher status success");
                }else {
                    em.getTransaction().commit();
                    em.close();emf.close();
                    return new DatabaseQueryResult(false,
                            "updatePublisher status failed, Publisher not found");
                }
            } catch (Exception e) {
                e.printStackTrace();
                em.getTransaction().rollback();
                em.close();emf.close();
                return new DatabaseQueryResult(false,
                        "updatePublisher status failed, " + e.getMessage());
            }
        }else {
            return new DatabaseQueryResult(false,
                    "updatePublisher status failed, bad request");
        }
    }
}
