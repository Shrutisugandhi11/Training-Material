package com.epam.controller;

import com.epam.exception.QuestionNotFoundException;
import com.epam.model.Option;
import com.epam.model.Question;
import com.epam.repository.QuestionRepository;
import com.epam.service.QuestionService;
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
class QuestionControllerTest {
    @InjectMocks
    QuestionController questionController;
    @MockBean
    QuestionService questionService;

    Question question;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    DtoConverter dtoConverter;

    List<Option> options;

    @MockBean
    QuestionRepository questionRepository;

    private List<Question> questions;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
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
    void testCreateQuestion() throws Exception {
        when(questionService.create(question)).thenReturn(question);
        mockMvc.perform(get("/question/new")).andExpect(status().isOk()).andExpect(view().name("createQuestion"));

    }

    @Test
    void testSaveQuestion() throws Exception {
        when(questionService.create(question)).thenReturn(question);
        mockMvc.perform(post("/question/save").contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("title", "java")
                .param("option", "ja")
                .param("difficulty", "oo")
                .param("tag", "t")
                .param("answer", "1")).andDo(print()).andExpect(status().is3xxRedirection());
    }

    @Test
    void testSaveUpdatedQuestion() throws Exception {
        when(questionService.update(1, question)).thenReturn(question);
        mockMvc.perform(post("/question/save/updated/1").contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("title", "java")
                .param("option", "ja")
                .param("difficulty", "oo")
                .param("tag", "t")
                .param("answer", "1")).andDo(print()).andExpect(status().is3xxRedirection());

    }

    @Test
    void testUpdateQuestion() throws QuestionNotFoundException, Exception {
        when(questionService.readById(anyInt())).thenReturn(question);
        when(questionService.update(anyInt(), any())).thenReturn(question);
        mockMvc.perform(get("/question/update/1")).andExpect(status().isOk()).andExpect(view().name("updateQuestion"));

    }

    @Test
    void testQuestionList() throws Exception {
        when(questionService.readAllQuestions()).thenReturn(questions);
        mockMvc.perform(get("/questions")).andExpect(status().isOk()).andExpect(view().name("questionList"));
    }

    @Test
    void testDeleteQuestion() throws Exception {
        questionService.delete(question.getId());
        questionRepository.deleteById(question.getId());
        mockMvc.perform(get("/question/delete/1")).andExpect(status().is3xxRedirection()).andExpect(view().name("redirect:/questions"));
    }

    @Test
    void testQuestionPanel() throws Exception {
        mockMvc.perform(get("/question/panel")).andExpect(status().isOk()).andExpect(view().name("questionMenu"));
    }

}


