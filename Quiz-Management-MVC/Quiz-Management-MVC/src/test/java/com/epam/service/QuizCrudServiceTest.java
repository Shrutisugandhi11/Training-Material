package com.epam.service;


import com.epam.dao.QuizDao;
import com.epam.model.Option;
import com.epam.model.Question;
import com.epam.model.Quiz;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class QuizCrudServiceTest {
    @InjectMocks
    QuizService quizService;

    @Mock
    QuizDao quizDao;

    Quiz quiz;
    List<Quiz> quizzes;

    @BeforeEach
    void setup() {
        quiz = new Quiz("math", "medium", Stream.of(new Question("1+1?", Stream.of(new Option("1"), new Option("2"), new Option("3"), new Option("4")).collect(Collectors.toList()), "medium", "math", 2)).collect(Collectors.toList()));
        quizzes = new ArrayList<>();
        quizzes.add(quiz);
    }

    @Test
    void create() {
        when(quizDao.create(quiz)).thenReturn("quiz added successfully");
        assertEquals("quiz added successfully", quizService.create(quiz));
    }

    @Test
    void delete() {
        quizService.delete(quiz.getId());
        verify(quizDao).delete(quiz.getId());
    }

    @Test
    void readQuizTest() {
        when(quizDao.read()).thenReturn(quizzes);
        assertEquals(quizzes.size(), quizService.read().size());

    }

    @Test
    void readById() {
        quizService.readById(quiz.getId());
        verify(quizDao).readById(quiz.getId());
    }

    @Test
    void readByTag() {
        quizService.readByTag(quiz.getTag());
        verify(quizDao).readByTag(quiz.getTag());
    }
    @Test
    void getByQuestionTag() {
        quizService.getQuestionList(quiz.getTag());
        verify(quizDao).getQuestionList(quiz.getTag());
    }


}

