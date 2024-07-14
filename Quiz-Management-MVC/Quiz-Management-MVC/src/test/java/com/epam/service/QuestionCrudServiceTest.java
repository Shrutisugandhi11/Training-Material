package com.epam.service;


import com.epam.dao.QuestionDao;
import com.epam.model.Option;
import com.epam.model.Question;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class QuestionCrudServiceTest {

    @InjectMocks
    QuestionService questionService;
    @Mock
    QuestionDao questionDao;
    Question question;
    List<Question> questions;

    @BeforeEach
    void setup() {
        question = new Question("1+1?", Stream.of(new Option("1"), new Option("2"), new Option("3"), new Option("4")).collect(Collectors.toList()), "medium", "math", 2);
        questions = Arrays.asList(new Question("1+1?", Stream.of(new Option("1"), new Option("2"), new Option("3"), new Option("4")).collect(Collectors.toList()), "medium", "math", 2));
    }

    @Test
    void create() {
        when(questionDao.create(question)).thenReturn("question added successfully");
        assertEquals("question added successfully", questionService.create(question));

    }

    @Test
    void readQuestionTest() {
        when(questionDao.read()).thenReturn(questions);
        assertEquals(questions.size(), questionService.read().size());

    }

    @Test
    void deleteQuestionTest() {
        questionService.delete(question.getId());
        verify(questionDao).delete(question.getId());
    }

    @Test
    void update() {
        questionService.update(question.getId(), "what?");
        verify(questionDao).update(question.getId(), "what?");
    }
    @Test
    void  updateQuestion(){
        questionService.updateQuestion(question.getId(),"what",1);
        verify(questionDao).updateQuestion(question.getId(),"what",1);
    }
    @Test
    void updateOptions(){
        questionService.updateOption(question.getId(), "what?",question.getOption().get(0).getOptionId());
        verify(questionDao).updateOptions( question.getId(), "what?",question.getOption().get(0).getOptionId());
    }


}