package com.epam.dao;


import com.epam.model.Option;
import com.epam.model.Question;
import com.epam.model.Quiz;
import com.epam.util.JPAUtil;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;
import java.util.NoSuchElementException;

@Repository
public class QuestionDao implements CrudDao<Question> {

    EntityManagerFactory entityManagerFactory = JPAUtil.getInstance();
    EntityManager entityManager;


    @Override
    public String create(Question question) {
        entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.merge(question);
        entityManager.getTransaction().commit();
        entityManager.close();
        return "question added successfully";
    }

    @Override
    public List<Question> read() {
        entityManager = entityManagerFactory.createEntityManager();
        return entityManager.createQuery("from Question ques", Question.class).getResultList();

    }

    @Override
    public Question readById(int id) throws NoSuchElementException {
        entityManager = entityManagerFactory.createEntityManager();
        Question question = entityManager.find(Question.class, id);
        entityManager.close();
        return question;
    }

    @Override
    public Question update(int id, String updateValue) throws NoSuchElementException {
        entityManager = entityManagerFactory.createEntityManager();
        Question question = entityManager.find(Question.class, id);
        entityManager.getTransaction().begin();
        question.setTitle(updateValue);
        entityManager.getTransaction().commit();
        entityManager.close();
        return question;

    }


    public Question updateQuestion(int id, String updateValue, int choice) throws NoSuchElementException {
        entityManager = entityManagerFactory.createEntityManager();
        Question question = entityManager.find(Question.class, id);
        entityManager.getTransaction().begin();
        if (choice == 1) {
            question.setTitle(updateValue);
        } else if (choice == 2) {
            question.setTag(updateValue);
        } else if (choice == 3) {
            question.setDifficulty(updateValue);
        } else if (choice == 5) {
            question.setAnswer(Integer.parseInt(updateValue));
        }
        entityManager.getTransaction().commit();
        entityManager.close();
        return question;

    }

    @Override
    public String delete(int id) {
        entityManager = entityManagerFactory.createEntityManager();
        String returnString = "";
        Question question = entityManager.find(Question.class, id);
        if (question == null) {
            returnString = "Question not found!!";//throw exception
        } else {
            entityManager.getTransaction().begin();
            entityManager.remove(question);
            for (Quiz quiz : question.getQuiz()) {
                quiz.getQuestions().remove(question);
            }
            question.getOption().forEach(option -> entityManager.remove(option));
            entityManager.getTransaction().commit();
            entityManager.close();
            returnString = "Question deleted successfully";
        }
        return returnString;
    }

    public Question updateOptions(int id, String updateValue, int choice) throws NoSuchElementException {
        entityManager = entityManagerFactory.createEntityManager();
        Question question = entityManager.find(Question.class, id);
        List<Option> option = question.getOption();
        entityManager.getTransaction().begin();
       // option.stream().filter(opt->opt.getOptionId()== choice).
        for (Option opt : option) {//use of java8

            if (choice == opt.getOptionId()) {
                choice = 0;
                opt.setChoice(updateValue);
                entityManager.persist(opt);
                break;
            }
        }
        if (choice != 0) {
            throw new NoSuchElementException("No Such Element Found!!!");
        }
        entityManager.getTransaction().commit();
        entityManager.close();
        return question;
    }

}


