package com.app.context;

import com.app.entity.Author;
import com.app.model.returnResult.DatabaseQueryResult;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

public class AuthorBean {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistence");
    EntityManager em = emf.createEntityManager();



    public List<Author> getAllAuthor(String query) {

        try {
            em.getTransaction().begin();
            List<Author> list;
            if (query != null && !query.isEmpty()) {
                list = em.createQuery(
                        "SELECT c from Author c where name like :queryString and status != :queryStatus",
                        Author.class).setParameter("queryString", "%" + query + "%")
                        .setParameter("queryStatus", Author.StatusEnum.DELETED).getResultList();
            } else {
                list =  em.createQuery("SELECT c from Author c where status != :queryStatus",
                        Author.class).setParameter("queryStatus", Author.StatusEnum.DELETED)
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


    public Author getAuthor(String id) {
        if(id != null && !id.isEmpty()){

            try {
                em.getTransaction().begin();
                Author author = em.find(Author.class, id);
                em.getTransaction().commit();
                em.close();emf.close();
                return author.getStatus() != Author.StatusEnum.DELETED? author : null;
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

    public DatabaseQueryResult addAuthor(Author author) {
        if(author != null){

            try {
                em.getTransaction().begin();
                em.persist(author);
                em.getTransaction().commit();
                em.close();emf.close();
                return new DatabaseQueryResult(true, "addAuthor success");
            } catch (Exception e) {
                e.printStackTrace();
                em.getTransaction().rollback();
                em.close();emf.close();
                return new DatabaseQueryResult(false, "addAuthor fail, " + e.getMessage());
            }
        }else {
            return new DatabaseQueryResult(false, "addAuthor fail, input is null");
        }
    }

    public DatabaseQueryResult updateAuthor(Author author, String id) {
        if(author != null && id != null && !id.isEmpty()){

            try {
                em.getTransaction().begin();
                Author u = em.find(Author.class, id);
                if(u != null && u.getStatus() != Author.StatusEnum.DELETED){
                    u.setName(author.getName());
                    u.setAddress(author.getAddress());
                    u.setPhone(author.getPhone());
                    u.setEmail(author.getEmail());
                    if(author.getStatus() != Author.StatusEnum.DELETED){
                        u.setStatus(author.getStatus());
                    }
                    u.setUpdatedAt(System.currentTimeMillis());

                    em.getTransaction().commit();
                    em.close();emf.close();
                    return new DatabaseQueryResult(true,
                            "updateAuthor success");
                }else {
                    em.getTransaction().commit();
                    em.close();emf.close();
                    return new DatabaseQueryResult(false,
                            "updateAuthor failed, Author not found");
                }
            } catch (Exception e) {
                e.printStackTrace();
                em.getTransaction().rollback();
                em.close();emf.close();
                return new DatabaseQueryResult(false,
                        "updateAuthor failed, " + e.getMessage());
            }
        }else {
            return new DatabaseQueryResult(false,
                    "updateAuthor failed, bad request");
        }
    }

    public DatabaseQueryResult deleteAuthor(String id) {
        if(id != null && !id.isEmpty()){

            try {
                em.getTransaction().begin();
                Author u = em.find(Author.class, id);
                if(u != null && u.getStatus() != Author.StatusEnum.DELETED){
                    u.setStatus(Author.StatusEnum.DELETED);
                    u.setUpdatedAt(System.currentTimeMillis());
                    u.setDeletedAt(System.currentTimeMillis());

                    em.getTransaction().commit();
                    em.close();emf.close();
                    return new DatabaseQueryResult(true,
                            "deleteAuthor success");
                }else {
                    em.getTransaction().commit();
                    em.close();emf.close();
                    return new DatabaseQueryResult(false,
                            "deleteAuthor failed, Author not found");
                }
            } catch (Exception e) {
                e.printStackTrace();
                em.getTransaction().rollback();
                em.close();emf.close();
                return new DatabaseQueryResult(false,
                        "deleteAuthor failed, " + e.getMessage());
            }
        }else {
            return new DatabaseQueryResult(false,
                    "deleteAuthor failed, bad request");
        }
    }
}
