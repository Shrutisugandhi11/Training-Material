package com.epam.service;

import com.epam.exception.QuizNotFoundException;
import com.epam.model.Option;
import com.epam.model.Question;
import com.epam.model.Quiz;
import com.epam.repository.QuestionRepository;
import com.epam.repository.QuizRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class QuizServiceTest {
    @InjectMocks
    QuizService quizService;
    @Mock
    QuizRepository quizRepository;
    @Mock
    QuestionRepository questionRepository;
    Question question;
    List<Question> questions;
    List<Option> options;
    Quiz quiz;
    List<Quiz> quizzes;

    @BeforeEach
    void setup() {
        question = new Question();
        question.setTitle("What is java?");
        options = Stream.of(new Option("1"), new Option("2"), new Option("3"), new Option("4")).collect(Collectors.toList());
        question.setOptions(options);
        question.setDifficulty("easy");
        question.setTag("java");
        question.setAnswer(2);
        questions = Arrays.asList(question);
        quiz = new Quiz("math", "medium");
        quiz.setQuestions(questions);
        quizzes = new ArrayList<>();
        quizzes.add(quiz);
    }

    @Test
    void testCreateQuiz() {
        when(questionRepository.findByTag(quiz.getTag())).thenReturn(questions);
        when(quizRepository.save(quiz)).thenReturn(quiz);
        assertEquals(quiz, quizService.create(quiz));


    }

    @Test
    void testReadQuiz() {
        when(quizRepository.findAll()).thenReturn(quizzes);
        assertEquals(quizzes.size(), quizService.read().size());

    }

    @Test
    void testReadQuizById() throws QuizNotFoundException {
        when(quizRepository.findById(quiz.getId())).thenReturn(Optional.of(quiz));
        assertEquals(quiz, quizService.readById(quiz.getId()));

    }

    @Test
    void testReadQuizByTag() {
        when(quizRepository.findByTag(quiz.getTag())).thenReturn(quizzes);
        assertEquals(quizzes.size(), quizService.readByTag(quiz.getTag()).size());

    }

    @Test
    void testDeleteQuiz() {
        quizService.delete(quiz.getId());
        verify(quizRepository).deleteById(quiz.getId());
    }

    @Test
    void testUpdateQuiz() throws QuizNotFoundException {
        when(quizRepository.findById(anyInt())).thenReturn(Optional.ofNullable(quiz));
        when(quizRepository.save(any())).thenReturn(quiz);
        Quiz quiz1 = quizService.update(quiz.getId(), quiz);
        quiz1.setTag("py");
        assertEquals("py", quiz1.getTag());

    }


}
