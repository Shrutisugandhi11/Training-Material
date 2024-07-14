package com.epam.service;


import com.epam.exception.QuizNotFoundException;
import com.epam.model.Question;
import com.epam.model.Quiz;
import com.epam.repository.QuestionRepository;
import com.epam.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuizService {
    @Autowired
    QuizRepository quizRepository;

    @Autowired
    QuestionRepository questionRepository;

    public Quiz create(Quiz quiz) {
        List<Question> questions = questionRepository.findByTag(quiz.getTag());
        quiz.setQuestions(questions);
        return quizRepository.save(quiz);
    }

    public void delete(int quizId) {
        quizRepository.deleteById(quizId);
    }

    public List<Quiz> readByTag(String tag) {
        return quizRepository.findByTag(tag);
    }

    public List<Quiz> read() {
        return quizRepository.findAll();
    }

    public Quiz readById(int quizId) throws QuizNotFoundException {
        return quizRepository.findById(quizId).orElseThrow(() -> new QuizNotFoundException("Quiz not found!!"));

    }

    public Quiz update(int id, Quiz quiz) throws QuizNotFoundException {
        Quiz existingQuiz = quizRepository.findById(id).orElseThrow(() -> new QuizNotFoundException("Quiz Not Found!"));
        existingQuiz.setTag(quiz.getTag());
        existingQuiz.setCategory(quiz.getCategory());
        quizRepository.save(existingQuiz);
        return existingQuiz;
    }


}
