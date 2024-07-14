package com.epam.service;

import com.epam.exception.QuestionNotFoundException;
import com.epam.model.Option;
import com.epam.model.Question;
import com.epam.repository.QuestionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class QuestionServiceTest {
    @InjectMocks
    QuestionService questionService;
    @Mock
    QuestionRepository questionRepository;

    Question question;
    List<Option> options;
    List<Question> questions;

    @BeforeEach
    void setup() {
        question = new Question();
        question.setTitle("What is java?");
        options = Stream.of(new Option("1"), new Option("2"), new Option("3"), new Option("4")).collect(Collectors.toList());
        question.setOptions(options);
        question.setDifficulty("easy");
        question.setTag("java");
        question.setAnswer(2);
        questions = Arrays.asList();
    }

    @Test
    void testCreateQuestion() {
        when(questionRepository.save(question)).thenReturn(question);
        assertEquals(question, questionService.create(question));


    }

    @Test
    void testReadQuestion() {
        when(questionRepository.findAll()).thenReturn(questions);
        assertEquals(questions.size(), questionService.readAllQuestions().size());

    }

    @Test
    void testReadQuestionById() {
        when(questionRepository.findById(question.getId())).thenReturn(Optional.of(question));
        assertEquals(Optional.of(question), questionService.questionRepository.findById(question.getId()));
        assertThrows(QuestionNotFoundException.class, () -> questionService.readById(5));

    }

    @Test
    void testDeleteQuestion() {
        questionService.delete(question.getId());
        verify(questionRepository).deleteById(question.getId());
    }

    @Test
    void update() throws QuestionNotFoundException {
        when(questionRepository.findById(anyInt())).thenReturn(Optional.ofNullable(question));
        when(questionRepository.save(any())).thenReturn(question);
        Question question1 = questionService.update(question.getId(), question);
        question1.setTitle("demo");
        assertEquals("demo", question1.getTitle());

    }


}
