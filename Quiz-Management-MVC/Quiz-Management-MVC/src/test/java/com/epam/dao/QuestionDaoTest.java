package com.epam.dao;


import com.epam.model.Option;
import com.epam.model.Question;
import com.epam.model.Quiz;
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

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class QuestionDaoTest {
    @InjectMocks
    private QuestionDao questionDao;
    @Mock
    private EntityManager entityManager;
    @Mock
    private EntityManagerFactory entityManagerFactory;
    @Mock
    private EntityTransaction transaction;
    @Mock
    private TypedQuery<Question> typedQuestion;
    private List<Question> questions;
    private Quiz quiz;

    @BeforeEach
    void setup() {
        questions = new ArrayList<>();
        questions.add(getMockQuestion());
        when(entityManagerFactory.createEntityManager()).thenReturn(entityManager);
    }

    public Question getMockQuestion() {
        Option option1 = new Option("1");
        Option option2 = new Option("2");
        Option option3 = new Option("3");
        Option option4 = new Option("4");
        List<Option> options = Arrays.asList(option1, option2, option3, option4);

        Question question = new Question("5+6?", options, "easy", "java", 1);
        return question;
    }

    @Test
    void createQuestion() {
        when(entityManager.getTransaction()).thenReturn(transaction);
        questionDao.create(questions.get(0));
        verify(entityManager).merge(questions.get(0));
        verify(entityManager).close();
        verify(transaction).begin();
        verify(transaction).commit();
    }

    @Test
    void getQuestionById() {
        when(entityManager.find(any(), anyInt())).thenReturn(questions.get(0));
        int id = questions.get(0).getId();
        questionDao.readById(id);
        verify(entityManager).find(Question.class, id);
        verify(entityManager).close();
    }

    @Test
    void getAllQuestions() {
        String queryString = "from Question q";
        TypedQuery query = mock(TypedQuery.class);
        when(query.getResultList()).thenReturn(questions);
        when(entityManager.createQuery(anyString(), any())).thenReturn(query);
        questionDao.read();
        verify(query).getResultList();

    }

    @Test
    void updateQuestion() {
        when(entityManager.getTransaction()).thenReturn(transaction);
        when(entityManager.find(any(), anyInt())).thenReturn(questions.get(0));
        questionDao.update(questions.get(0).getId(), "###");
        verify(transaction).begin();
        verify(transaction).commit();
        verify(entityManager).close();
    }

    @Test
    void updateQuestionTitle() {
        when(entityManager.getTransaction()).thenReturn(transaction);
        when(entityManager.find(any(), anyInt())).thenReturn(questions.get(0));
        questionDao.updateQuestion(questions.get(0).getId(), "###", 1);
        verify(transaction).begin();
        verify(transaction).commit();
        verify(entityManager).close();
    }

    @Test
    void updateQuestiontag() {
        when(entityManager.getTransaction()).thenReturn(transaction);
        when(entityManager.find(any(), anyInt())).thenReturn(questions.get(0));
        questionDao.updateQuestion(questions.get(0).getId(), "###", 2);
        verify(transaction).begin();
        verify(transaction).commit();
        verify(entityManager).close();
    }

    @Test
    void updateQuestionDifficulty() {
        when(entityManager.getTransaction()).thenReturn(transaction);
        when(entityManager.find(any(), anyInt())).thenReturn(questions.get(0));
        questionDao.updateQuestion(questions.get(0).getId(), "###", 3);
        verify(transaction).begin();
        verify(transaction).commit();
        verify(entityManager).close();
    }

    @Test
    void updateQuestionAnswer() {
        when(entityManager.getTransaction()).thenReturn(transaction);
        when(entityManager.find(any(), anyInt())).thenReturn(questions.get(0));
        questionDao.updateQuestion(questions.get(0).getId(), "1", 5);
        verify(transaction).begin();
        verify(transaction).commit();
        verify(entityManager).close();
    }


    @Test
    void updateOption() {
        when(entityManager.getTransaction()).thenReturn(transaction);
        when(entityManager.find(any(), anyInt())).thenReturn(questions.get(0));
        questionDao.updateOptions(questions.get(0).getId(), "###", questions.get(0).getOption().get(0).getOptionId());
        verify(transaction).begin();
        verify(transaction).commit();
        verify(entityManager).close();
    }

    @Test
    void deleteQuestion() {
         List<Quiz> quizzes = new ArrayList<>();
        Quiz quiz1 = new Quiz("tag", "category");
        Option option1 = new Option("1");
        Option option2 = new Option("2");
        Option option3 = new Option("3");
        Option option4 = new Option("4");
        List<Option> options = Arrays.asList(option1, option2, option3, option4);
        Question question = new Question("5+6?", options, "easy", "java", 1);
        question.setQuiz(quizzes);
        List<Question> questionList= Arrays.asList(question);
        quiz1.setQuestions(questions);
        when(entityManager.getTransaction()).thenReturn(transaction);
        when(entityManager.find(any(), anyInt())).thenReturn(questionList.get(0));
         assertNotNull(questionDao.delete(question.getId()));


    }
}

