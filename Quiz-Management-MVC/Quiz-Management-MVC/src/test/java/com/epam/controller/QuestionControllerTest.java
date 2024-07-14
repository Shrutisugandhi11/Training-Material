package com.epam.controller;

import com.epam.model.Option;
import com.epam.model.Question;
import com.epam.service.QuestionService;
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
class QuestionControllerTest {
    @InjectMocks
    QuestionController questionController;
    @MockBean
    QuestionService questionService;

    Question question;
    private List<Question> questions;
    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        Option option1 = new Option("1");
        Option option2 = new Option("2");
        Option option3 = new Option("3");
        Option option4 = new Option("4");
        List<Option> options = Arrays.asList(option1, option2, option3, option4);
        question = new Question("5+6?", options, "easy", "java", 1);
        questions = List.of(question);

    }

    @Test
    void testCreateQuestion() throws Exception {
        when(questionService.create(question)).thenReturn("question added successfully");
        mockMvc.perform(get("/question/new")).andExpect(status().isOk()).andExpect(view().name("createQuestion"));

    }

    @Test
    void testSaveQuestion() throws Exception {
        when(questionService.create(question)).thenReturn("question added successfully");
        mockMvc.perform(post("/question/save")).andExpect(status().is3xxRedirection()).andExpect(view().name("redirect:/questions"));
    }

    @Test
    void testQuestionList() throws Exception {
        when(questionService.read()).thenReturn(questions);
        mockMvc.perform(get("/questions")).andExpect(status().isOk()).andExpect(view().name("questionList"));
    }

    @Test
    void testDeleteQuestion() throws Exception {
        when(questionService.delete(1)).thenReturn("Question deleted successfully");
        mockMvc.perform(get("/question/delete/1")).andExpect(status().is3xxRedirection()).andExpect(view().name("redirect:/questions"));
    }

    @Test
    void testQuestionPanel() throws Exception {
        mockMvc.perform(get("/question/panel")).andExpect(status().isOk()).andExpect(view().name("questionMenu"));
    }

//    @Test
//    void testUpdateQuestion() throws Exception {
//        when(questionService.read()).thenReturn(questions);
//        mockMvc.perform(get("/question/update/1")).andExpect(status().isOk()).andExpect(view().name("createQuestion"));
//    }


}
