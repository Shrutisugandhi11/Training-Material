package com.epam.dao;


import com.epam.model.User;
import com.epam.util.JPAUtil;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao<User, String> {


    EntityManagerFactory entityManagerFactory = JPAUtil.getInstance();

    EntityManager entityManager;

    @Override
    public User create(User user) {
        entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(user);
        entityManager.getTransaction().commit();
        entityManager.close();
        return user;

    }

    @Override
    public User findById(int id) {
        entityManager = entityManagerFactory.createEntityManager();
        User user = entityManager.find(User.class, id);
        entityManager.close();
        return user;
    }

    @Override
    public List<User> findAll() {
        entityManager = entityManagerFactory.createEntityManager();
        return entityManager.createQuery("from User user", User.class).getResultList();

    }

    @Override
    public User delete(String userName) {
        entityManager = entityManagerFactory.createEntityManager();
        User user = entityManager.find(User.class, userName);
       entityManager.getTransaction().begin();
        entityManager.remove(user);
        entityManager.getTransaction().commit();
        entityManager.close();
        return user;

    }


    @Override
    public boolean deleteById(int id) {
        entityManager = entityManagerFactory.createEntityManager();
        boolean isUser = false;
        User user = entityManager.find(User.class, id);
        if (user == null) {
            isUser = false;
        } else {
            entityManager.getTransaction().begin();
            entityManager.remove(user);
            entityManager.getTransaction().commit();
            entityManager.close();
            isUser = true;
        }
        return isUser;
    }

}

