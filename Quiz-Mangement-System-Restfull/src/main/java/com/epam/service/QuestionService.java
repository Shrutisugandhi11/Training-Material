package com.epam.service;


import com.epam.exception.QuestionNotFoundException;
import com.epam.model.Option;
import com.epam.model.Question;
import com.epam.repository.QuestionRepository;
import com.epam.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {

    @Autowired
    QuestionRepository questionRepository;
    @Autowired
    QuizRepository quizRepository;


    public Question create(Question question) {
        return questionRepository.save(question);
    }

    public Question update(int id, Question question) {
        Question existingQuestion = questionRepository.findById(id).orElseThrow(() -> new QuestionNotFoundException("Question Not Found!", "ID", id));
        existingQuestion.setTitle(question.getTitle());
        existingQuestion.setTag(question.getTag());
        List<Option> existingOptions = existingQuestion.getOptions();
        List<Option> options = question.getOptions();
        for (int i = 0; i < 4; i++) {
            existingOptions.get(i).setChoice(options.get(i).getChoice());
        }
        existingQuestion.setDifficulty(question.getDifficulty());
        existingQuestion.setAnswer(question.getAnswer());
        questionRepository.save(existingQuestion);
        return existingQuestion;
    }

    public void deleteById(int id) {
        Question question = questionRepository.findById(id).orElseThrow(() -> new QuestionNotFoundException("Question not Found!!", "ID", id));
        question.getQuizzes().forEach(quiz -> quiz.getQuestions().remove(question));
        questionRepository.delete(question);
    }

    public List<Question> readAllQuestions() {
        return questionRepository.findAll();
    }

    public Question readById(int questionId) {
        return questionRepository.findById(questionId).orElseThrow(() -> new QuestionNotFoundException("Question not Found!!", "ID", questionId));
    }


}
