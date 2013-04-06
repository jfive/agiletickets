package br.com.caelum.agiletickets.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 * Created with IntelliJ IDEA.
 * User: rodrigoalmeida
 * Date: 06/04/13
 * Time: 10:19
 * To change this template use File | Settings | File Templates.
 */
public abstract class DaoTest {

    private EntityManager entityManager;
    private EntityTransaction transaction;

    public void before(){
        EntityManagerFactory managerFactory = Persistence.createEntityManagerFactory("test");
        entityManager = managerFactory.createEntityManager();
        transaction = entityManager.getTransaction();
        transaction.begin();
    }



    public void after() {
        transaction.rollback();
    }

}
