package com.epam.controller;

import com.epam.model.Option;
import com.epam.model.Question;
import com.epam.model.Quiz;
import com.epam.repository.QuestionRepository;
import com.epam.service.QuestionService;
import com.epam.service.QuizService;
import com.epam.util.DtoConverter;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(QuizController.class)
 class QuizControllerTest {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;
    @MockBean
    QuestionRepository questionRepository;
    @MockBean
    QuizService quizService;
    @MockBean
    DtoConverter dtoConverter;
    Quiz quiz;
    Question question;
    List<Question> questions = new ArrayList<>();
    List<Quiz> quizzes = new ArrayList<>();
    List<Option> options = new ArrayList<>();

    @BeforeEach
    void setUp() {
        options = Arrays.asList(new Option("1"),
                new Option("2"),
                new Option("3"),
                new Option("4"));
        question = new Question();
        question.setTitle("2+2?");
        question.setOptions(options);
        question.setTag("math");
        question.setDifficulty("hard");
        question.setAnswer(1);
        questions.add(question);
        quiz=new Quiz("math","hard");
        quiz.setQuestions(questions);

    }

    @Test
    void testCreateQuiz() throws Exception {

        when(quizService.create(quiz)).thenReturn(quiz);
        mockMvc.perform(post("/quizzes").contentType("application/json")
                .content(objectMapper.writeValueAsString(quiz))).andExpect(status().isCreated());
    }

    @Test
    void testGetQuiz() throws Exception {
        when(quizService.readById(anyInt())).thenReturn(quiz);
        mockMvc.perform(get("/quizzes/1")).andExpect(status().isOk());
    }

    @Test
    void testGetQuizzes() throws Exception {
        when(quizService.read()).thenReturn(quizzes);
        mockMvc.perform(get("/quizzes")).andExpect(status().is2xxSuccessful());
    }

    @Test
    void testUpdateQuiz() throws Exception {
        when(quizService.update(question.getId(), quiz)).thenReturn(quiz);
        mockMvc.perform(put("/quizzes/1").contentType("application/json")
                .content(objectMapper.writeValueAsString(quiz))).andExpect(status().isOk());
    }
    @Test
    void testDeleteQuiz() throws Exception {
        mockMvc.perform(delete("/quizzes/1")).andExpect(status().isOk());
    }
}
