package com.epam.controller;

import com.epam.model.Option;
import com.epam.model.Question;
import com.epam.model.Quiz;
import com.epam.service.QuestionService;
import com.epam.service.QuizService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest
@AutoConfigureMockMvc
 class QuizControllerTest {
    @InjectMocks
    QuizController quizController;
    @MockBean
    QuizService quizService;
    @MockBean
    QuestionService questionService;

    Question question;
    Quiz quiz;
    @Autowired
    private MockMvc mockMvc;
    private List<Question> questions;

    private List<Quiz> quizzes;
    @BeforeEach
    void setUp() {
        Option option1 = new Option("1");
        Option option2 = new Option("2");
        Option option3 = new Option("3");
        Option option4 = new Option("4");
        List<Option> options = Arrays.asList(option1, option2, option3, option4);
        question = new Question("5+6?", options, "easy", "java", 1);
        questions = List.of(question);
        quiz = new Quiz("math", "easy");
        quiz.setQuestions(questions);
    }
    @Test
    void testCreateQuiz() throws Exception {
        when(quizService.create(quiz)).thenReturn("Quiz created succesfully");
        mockMvc.perform(get("/quiz/new")).andExpect(status().isOk()).andExpect(view().name("createQuiz"));

    }
    @Test
    void testSaveQuiz() throws Exception {
        when(quizService.create(quiz)).thenReturn("quiz added successfully");
        mockMvc.perform(post("/quiz")).andExpect(status().is3xxRedirection()).andExpect(view().name("redirect:/quizzes"));

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
        when(quizService.delete(1)).thenReturn("quiz deleted successfully");
        mockMvc.perform(get("/quiz/delete/1")).andExpect(status().is3xxRedirection()).andExpect(view().name("redirect:/quizzes"));
    }

    @Test
    void testAvailableQuestion() throws Exception {
        when(quizService.readById(quiz.getId())).thenReturn(quiz);
        when(quiz.getQuestions()).thenReturn(questions);
        mockMvc.perform(get("/quiz/fetch/question/1")).andExpect(status().isOk()).andExpect(view().name("questionDisplay"));
    }


}