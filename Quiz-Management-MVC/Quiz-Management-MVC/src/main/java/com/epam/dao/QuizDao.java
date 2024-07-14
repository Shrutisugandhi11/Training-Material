package com.epam.dao;


import com.epam.model.Question;
import com.epam.model.Quiz;
import com.epam.util.JPAUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class QuizDao implements CrudDao<Quiz> {


    EntityManagerFactory entityManagerFactory = JPAUtil.getInstance();
    EntityManager entityManager;
    @Autowired
    QuestionDao questionDao;

    @Override
    public String create(Quiz quiz) {
        entityManager = entityManagerFactory.createEntityManager();
        List<Question> questions = questionDao.read();
        quiz.setQuestions(questions);
        entityManager.getTransaction().begin();
        entityManager.persist(quiz);
        entityManager.getTransaction().commit();
        entityManager.close();
        return "Quiz created succesfully";
    }

    @Override
    public List<Quiz> read() {
        entityManager = entityManagerFactory.createEntityManager();
        return entityManager.createQuery("from Quiz quiz", Quiz.class).getResultList();
    }

    @Override
    public Quiz readById(int id) {
        entityManager = entityManagerFactory.createEntityManager();
        Quiz quiz = entityManager.find(Quiz.class, id);
        entityManager.close();
        return quiz;
    }

    public List<Quiz> readByTag(String tag) {
        entityManager = entityManagerFactory.createEntityManager();
        TypedQuery<Quiz> query = entityManager.createQuery("from Quiz q where q.tag=?1", Quiz.class);
        query.setParameter(1, tag);
        return query.getResultList();

    }

    @Override
    public Quiz update(int id, String value) {
        entityManager = entityManagerFactory.createEntityManager();
        Quiz quiz = entityManager.find(Quiz.class, id);
        entityManager.getTransaction().begin();
        quiz.setTag(value);
        entityManager.getTransaction().commit();
        entityManager.close();
        return quiz;
    }

    @Override
    public String delete(int id) {
        String returnString = "";
        entityManager = entityManagerFactory.createEntityManager();
        Quiz quiz = entityManager.find(Quiz.class, id);
        if (quiz == null) {
            returnString = "Quiz not found";
        } else {
            entityManager.getTransaction().begin();
            entityManager.remove(quiz);
            entityManager.getTransaction().commit();
            entityManager.close();
            returnString = "Quiz Deleted Successfully";
        }
        return returnString;

    }

    public List<Question> getQuestionList(String tag) {
        entityManager = entityManagerFactory.createEntityManager();
        TypedQuery<Question> query = entityManager.createQuery("from Question q where q.tag=?1", Question.class);
        query.setParameter(1, tag);
        return query.getResultList();
    }


}
