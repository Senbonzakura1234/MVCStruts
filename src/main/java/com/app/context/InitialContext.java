package com.app.context;

import com.app.entity.Author;
import com.app.model.returnResult.DatabaseQueryResult;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class InitialContext {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistence");
    EntityManager em = emf.createEntityManager();

    public DatabaseQueryResult initDB(){
        try {
            em.getTransaction().begin();
            em.getTransaction().commit();
            em.close();
            emf.close();
            return new DatabaseQueryResult(true, "Success");
        } catch (Exception e) {
            em.getTransaction().rollback();
            em.close();emf.close();
            e.printStackTrace();
            return new DatabaseQueryResult(false, "Fail");
        }
    }
}
