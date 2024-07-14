package com.epam.controller;

import com.epam.model.Option;
import com.epam.model.Question;
import com.epam.model.Quiz;
import com.epam.repository.QuestionRepository;
import com.epam.repository.QuizRepository;
import com.epam.service.QuestionService;
import com.epam.service.QuizService;
import com.epam.util.DtoConverter;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest
@AutoConfigureMockMvc
class QuizControllerTest {
    @InjectMocks
    QuizController quizController;
    @MockBean
    QuizService quizService;
    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    DtoConverter dtoConverter;
    @MockBean
    QuestionService questionService;

    Question question;
    Quiz quiz;
    @Autowired
    private MockMvc mockMvc;
    private List<Question> questions;

    private List<Quiz> quizzes;
    private List<Option> options;
    @MockBean
    private QuizRepository quizRepository;
    @MockBean
    private QuestionRepository questionRepository;


    @BeforeEach
    void setUp() {
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
    void testCreateQuiz() throws Exception {
        when(quizService.create(quiz)).thenReturn(quiz);
        mockMvc.perform(get("/quiz/new")).andExpect(status().isOk()).andExpect(view().name("createQuiz"));

    }

    @Test
    void testSaveQuiz() throws Exception {
        when(quizService.create(quiz)).thenReturn(quiz);
        mockMvc.perform(post("/quiz").contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("tag", "java").param("category", "math")).andDo(print()).andExpect(status().is3xxRedirection());
    }
    @Test
    void testSaveUpdatedQuiz() throws Exception {
        when(quizService.create(quiz)).thenReturn(quiz);
        mockMvc.perform(post("/quiz/save/updated/1").contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("tag", "java").param("category", "math")).andDo(print()).andExpect(status().is3xxRedirection());
    }

    @Test
    void testUpdateQuiz() throws Exception {
        when(quizService.readById(anyInt())).thenReturn(quiz);
        when(quizService.update(anyInt(), any())).thenReturn(quiz);
        mockMvc.perform(get("/quiz/update/1")).andExpect(status().isOk()).andExpect(view().name("updateQuiz"));

    }


    @Test
    void testQuizList() throws Exception {
        when(quizService.read()).thenReturn(quizzes);
        mockMvc.perform(get("/quizzes")).andExpect(status().isOk()).andExpect(view().name("quizList"));
    }

    @Test
    void testAvailableQuiz() throws Exception {
        when(quizService.read()).thenReturn(quizzes);
        mockMvc.perform(get("/quiz/get/quiz")).andExpect(status().isOk()).andExpect(view().name("quizDisplay"));
    }

    @Test
    void testQuizPanel() throws Exception {
        mockMvc.perform(get("/quiz/panel")).andExpect(status().isOk()).andExpect(view().name("quizMenu"));
    }

    @Test
    void testDeleteQuiz() throws Exception {
        quizService.delete(question.getId());
        quizRepository.deleteById(question.getId());
        mockMvc.perform(get("/quiz/delete/1")).andExpect(status().is3xxRedirection()).andExpect(view().name("redirect:/quizzes"));
    }

    @Test
    void testAvailableQuestion() throws Exception {
        when(quizService.readById(1)).thenReturn(quizzes.get(0));
        mockMvc.perform(get("/quiz/fetch/question/1")).andExpect(status().isOk());
    }

}
