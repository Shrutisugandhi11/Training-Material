package com.epam.service;


import com.epam.exception.QuestionNotFoundException;
import com.epam.model.Question;
import com.epam.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {

    @Autowired
    QuestionRepository questionRepository;


    public Question create(Question question) {
        question.getOptions();
        return questionRepository.save(question);
    }

    public Question update(int id, Question question) throws QuestionNotFoundException {
        Question existingQuestion = questionRepository.findById(id).orElseThrow(() -> new QuestionNotFoundException("Question Not Found!"));
        existingQuestion.setTitle(question.getTitle());
        existingQuestion.setTag(question.getTag());
        existingQuestion.setDifficulty(question.getDifficulty());
        existingQuestion.setAnswer(question.getAnswer());
        questionRepository.save(existingQuestion);
        return existingQuestion;
    }

    public void delete(int questionId) {
        questionRepository.deleteById(questionId);
    }

    public List<Question> readAllQuestions() {
        return questionRepository.findAll();
    }

    public Question readById(int questionId) throws QuestionNotFoundException {
        return questionRepository.findById(questionId).orElseThrow(() -> new QuestionNotFoundException( "Question not Found!!"));

    }


}
