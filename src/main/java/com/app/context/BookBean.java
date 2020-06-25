package com.app.context;

import com.app.entity.Author;
import com.app.entity.Book;
import com.app.entity.Category;
import com.app.model.returnResult.DatabaseQueryResult;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class BookBean {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistence");
    EntityManager em = emf.createEntityManager();


    public Book getBook(String id) {
        if(id != null && !id.isEmpty()){
            
            try {
                em.getTransaction().begin();
                Book book = em.find(Book.class, id);
                em.getTransaction().commit();
                em.close();emf.close();
                return book.getStatus() != Book.StatusEnum.DELETED? book : null;
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

    public List<Book> getAllBook(String query) {
        
        try {
            em.getTransaction().begin();
            List<Book> list;
            if (query != null && !query.isEmpty()) {
                list = em.createQuery(
                        "SELECT c from Book c where name like :queryString and status != :queryStatus",
                        Book.class).setParameter("queryString", "%" + query + "%")
                        .setParameter("queryStatus", Book.StatusEnum.DELETED).getResultList();
            } else {
                list =  em.createQuery("SELECT c from Book c where status != :queryStatus",
                        Book.class).setParameter("queryStatus", Book.StatusEnum.DELETED)
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

    public List<Book> getBooksOfShop(String id) {
        if (id == null || id.isEmpty()) return null;
        List<Book> list;
        
        try {
            em.getTransaction().begin();
            list = em.createQuery(
                    "select c from Book  c where shopId = :queryString and status != :queryStatus",
                    Book.class)
                    .setParameter("queryString", id)
                    .setParameter("queryStatus", Book.StatusEnum.DELETED)
                    .getResultList();
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

    public List<Book> getBooksOfCategory(String id) {
        if (id == null || id.isEmpty()) return null;
        List<Book> list;
        
        try {
            em.getTransaction().begin();
            list = em.createQuery(
                    "select c from Book  c where categoryId = :queryString and status != :queryStatus",
                    Book.class)
                    .setParameter("queryString", id)
                    .setParameter("queryStatus", Book.StatusEnum.DELETED)
                    .getResultList();
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

    public List<Book> getBooksOfAuthor(String id) {
        if (id == null || id.isEmpty()) return null;
        List<Book> list;
        
        try {
            em.getTransaction().begin();
            list = em.createQuery(
                    "select c from Book  c where authorId = :queryString and status != :queryStatus",
                    Book.class)
                    .setParameter("queryString", id)
                    .setParameter("queryStatus", Book.StatusEnum.DELETED)
                    .getResultList();
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

    public DatabaseQueryResult addBook(Book book) {
        if(book != null){
            
            try {
                em.getTransaction().begin();
                if (em.find(Author.class, book.getAuthorId()) == null) return new DatabaseQueryResult(
                        false,
                        "addBook fail, Author not found");

                if (em.find(Category.class, book.getCategoryId()) == null) return new DatabaseQueryResult(
                        false,
                        "addBook fail, Category not found");

                em.persist(book);
                em.getTransaction().commit();
                em.close();emf.close();
                return new DatabaseQueryResult(true, "addBook success");
            } catch (Exception e) {
                e.printStackTrace();
                em.getTransaction().rollback();
                em.close();emf.close();
                return new DatabaseQueryResult(false, "addBook fail, " + e.getMessage());
            }
        }else {
            return new DatabaseQueryResult(false, "addBook fail, input is null");
        }
    }

    public DatabaseQueryResult updateBook(Book book, String id) {
        if(book != null && id != null && !id.isEmpty()){
            try {
                em.getTransaction().begin();
                Book u = em.find(Book.class, id);
                if(u != null && u.getStatus() != Book.StatusEnum.DELETED){
                    u.setName(book.getName());
                    if (!(em.find(Author.class, book.getAuthorId()) == null)){
                        u.setAuthorId(book.getAuthorId());
                    }else {
                        em.getTransaction().rollback();
                        em.getTransaction().commit();
                        em.close();emf.close();
                        return new DatabaseQueryResult(false,
                                "updateBook failed");
                    }

                    if (!(em.find(Category.class, book.getCategoryId()) == null)){
                        u.setCategoryId(book.getCategoryId());
                    }else {
                        em.getTransaction().rollback();
                        em.getTransaction().commit();
                        em.close();emf.close();
                        return new DatabaseQueryResult(false,
                                "updateBook failed");
                    }

                    u.setDescription(book.getDescription());

                    if(book.getStatus() != Book.StatusEnum.DELETED){
                        u.setStatus(book.getStatus());
                    }else {
                        em.getTransaction().rollback();
                        em.getTransaction().commit();
                        em.close();emf.close();
                        return new DatabaseQueryResult(false,
                                "updateBook failed");
                    }
                    u.setUpdatedAt(System.currentTimeMillis());
                    em.getTransaction().commit();
                    em.close();emf.close();
                    return new DatabaseQueryResult(true,
                            "updateBook success");
                }else {
                    em.getTransaction().commit();
                    em.close();emf.close();
                    return new DatabaseQueryResult(false,
                            "updateBook failed, Book not found");
                }
            } catch (Exception e) {
                e.printStackTrace();
                em.getTransaction().rollback();
                em.close();emf.close();
                return new DatabaseQueryResult(false,
                        "updateBook failed, " + e.getMessage());
            }
        }else {
            return new DatabaseQueryResult(false,
                    "updateBook failed, bad request");
        }
    }

    public DatabaseQueryResult deleteBook(String id) {
        if(id != null && !id.isEmpty()){
            
            try {
                em.getTransaction().begin();
                Book u = em.find(Book.class, id);
                if(u != null && u.getStatus() != Book.StatusEnum.DELETED){
                    u.setStatus(Book.StatusEnum.DELETED);
                    u.setUpdatedAt(System.currentTimeMillis());
                    u.setDeletedAt(System.currentTimeMillis());

                    em.getTransaction().commit();
                    em.close();emf.close();
                    return new DatabaseQueryResult(true,
                            "deleteBook success");
                }else {
                    em.getTransaction().commit();
                    em.close();emf.close();
                    return new DatabaseQueryResult(false,
                            "deleteBook failed, Book not found");
                }
            } catch (Exception e) {
                e.printStackTrace();
                em.getTransaction().rollback();
                em.close();emf.close();
                return new DatabaseQueryResult(false,
                        "updateBook failed" + e.getMessage());
            }
        }else {
            return new DatabaseQueryResult(false,
                    "deleteBook failed, bad request");
        }
    }
}
