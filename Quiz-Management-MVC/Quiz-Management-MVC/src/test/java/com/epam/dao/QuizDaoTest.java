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

import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
 class QuizDaoTest {

 @InjectMocks
 private QuizDao quizDao;
 @Mock
 private EntityManager entityManager;
 @Mock
 private EntityManagerFactory entityManagerFactory;
 @Mock
 private EntityTransaction transaction;
 private List<Quiz> quizes;

 @BeforeEach
 void setup() {
  quizes = new ArrayList<>();
  quizes.add(getMockQuiz());
  when(entityManagerFactory.createEntityManager()).thenReturn(entityManager);
 }

 public Quiz getMockQuiz() {
  Option option1 = new Option("1");
  Option option2 = new Option("2");
  Option option3 = new Option("3");
  Option option4 = new Option("4");
  List<Option> options = Arrays.asList(option1, option2, option3, option4);
  List<Question> questions = Arrays.asList(new Question("5+6?",options,"easy","java",1));
  return new Quiz("java","easy",questions);
 }

 @Test
 void createQuiz() {
  when(entityManager.getTransaction()).thenReturn(transaction);
  quizDao.create(quizes.get(0));
  verify(entityManager).persist(quizes.get(0));
  verify(entityManager).close();
  verify(transaction).begin();
  verify(transaction).commit();

 }

 @Test
 void deleteQuizById() {
  when(entityManager.getTransaction()).thenReturn(transaction);
  when(entityManager.find(any(), anyInt())).thenReturn(quizes.get(0));
  quizDao.delete(quizes.get(0).getId());
  verify(entityManager).remove(quizes.get(0));
  verify(entityManager).close();
  verify(transaction).begin();
  verify(transaction).commit();

 }
 @Test
 void getQuizByTag() {
  TypedQuery query = mock(TypedQuery.class);
  when(query.getResultList()).thenReturn(quizes);
  when(entityManager.createQuery(anyString(), any())).thenReturn(query);
  quizDao.readByTag(quizes.get(0).getTag());
  verify(query).getResultList();

 }

 @Test
 void getQuizById() {
  when(entityManager.find(any(), anyInt())).thenReturn(quizes.get(0));
  int id = quizes.get(0).getId();
  quizDao.readById(id);
  verify(entityManager).find(Quiz.class, id);
  verify(entityManager).close();
 }

 @Test
 void getAllQuizes() {

  TypedQuery query = mock(TypedQuery.class);
  when(query.getResultList()).thenReturn(quizes);
  when(entityManager.createQuery(anyString(), any())).thenReturn(query);
  quizDao.read();
  verify(query).getResultList();

 }
 @Test
 void updateQuiz() {
  when(entityManager.getTransaction()).thenReturn(transaction);
  when(entityManager.find(any(), anyInt())).thenReturn(quizes.get(0));
  quizDao.update(quizes.get(0).getId(), "python");
  verify(transaction).begin();
  verify(transaction).commit();
  verify(entityManager).close();
 }
 @Test
 void getAllQuestion() {
  TypedQuery query = mock(TypedQuery.class);
  when(query.getResultList()).thenReturn(quizes);
  when(entityManager.createQuery(anyString(), any())).thenReturn(query);
  quizDao.getQuestionList(quizes.get(0).getTag());
  verify(query).getResultList();
 }

}







