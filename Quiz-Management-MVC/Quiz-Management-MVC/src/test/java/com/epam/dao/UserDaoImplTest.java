package com.epam.dao;




import com.epam.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserDaoImplTest {

    @InjectMocks
    private UserDaoImpl userDao;

    @Mock
    private EntityManager entityManager;
    @Mock
    private EntityManagerFactory entityManagerFactory;
    @Mock
    private EntityTransaction transaction;
    private List<User> users;


    @BeforeEach
    void setup() {
        User user1 = new User("root", "root", "admin");
        User user2 = new User("user", "user", "user");
        users = new ArrayList<>();
        users.addAll(Arrays.asList(user1, user2));
        when(entityManagerFactory.createEntityManager()).thenReturn(entityManager);
    }

    @Test
    void createUser() {
        when(entityManager.getTransaction()).thenReturn(transaction);
        userDao.create(users.get(0));
        verify(entityManager).persist(users.get(0));
        verify(entityManager).close();
        verify(transaction).begin();
        verify(transaction).commit();
    }

    @Test
    void findAllUsers() {
        TypedQuery query = mock(TypedQuery.class);
        when(query.getResultList()).thenReturn(users);
        when(entityManager.createQuery(anyString(), any())).thenReturn(query);
        userDao.findAll();
        verify(query).getResultList();
    }
    @Test
    void deleteUserById() {
        when(entityManager.getTransaction()).thenReturn(transaction);
        when(entityManager.find(any(), anyInt())).thenReturn(users.get(0));
        userDao.deleteById(users.get(0).getUserId());
        verify(entityManager).remove(users.get(0));
        verify(entityManager).close();
        verify(transaction).begin();
        verify(transaction).commit();

    }
    @Test
    void getUserById() {
        when(entityManager.find(any(), anyInt())).thenReturn(users.get(0));
        userDao.findById( users.get(0).getUserId());
        verify(entityManager).find(User.class, users.get(0).getUserId());
        verify(entityManager).close();
    }
    @Test
    void deleteByName() {
        when(entityManager.getTransaction()).thenReturn(transaction);
        when(entityManager.find(any(), anyString())).thenReturn(users.get(0));
        userDao.delete(users.get(0).getUserName());
        verify(entityManager).find(User.class, users.get(0).getUserName());
        verify(entityManager).remove(users.get(0));
        verify(entityManager).close();
        verify(transaction).begin();
        verify(transaction).commit();



    }
}


