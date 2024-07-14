package com.epam.service;



import com.epam.dao.QuizDao;
import com.epam.model.Question;
import com.epam.model.Quiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class QuizService {
  @Autowired
  QuizDao quizDao ;

    public String create(Quiz quiz) {
        return quizDao.create(quiz);
    }

    public List<Question> getQuestionList(String tag) {
        return quizDao.getQuestionList(tag);
    }

    public String delete(int removeId) {
        return quizDao.delete(removeId);
    }

    public List<Quiz> readByTag(String tag) {
        return quizDao.readByTag(tag);
    }

    public List<Quiz> read() {
        return quizDao.read();
    }

    public Quiz readById(int quizId) {
        return quizDao.readById(quizId);
    }


}
