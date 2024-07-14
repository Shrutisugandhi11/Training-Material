package com.epam.controller;

import com.epam.model.Option;
import com.epam.model.Question;
import com.epam.repository.QuestionRepository;
import com.epam.service.QuestionService;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(QuestionController.class)
 class QuestionControllerTest {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;
    @MockBean
    QuestionRepository questionRepository;
    @MockBean
    QuestionService questionService;
    @MockBean
    DtoConverter dtoConverter;
    Question question;
    List<Question> questions = new ArrayList<>();
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

    }

    @Test
    void testCreateQuestion() throws Exception {
        when(questionService.create(question)).thenReturn(question);
        mockMvc.perform(post("/questions").contentType("application/json").content(objectMapper.writeValueAsString(question))).andExpect(status().isCreated());
    }

    @Test
    void testGetQuestion() throws Exception {
        when(questionService.readById(anyInt())).thenReturn(question);
        mockMvc.perform(get("/questions/1")).andExpect(status().isOk());
    }

    @Test
    void testGetQuestions() throws Exception {
        when(questionService.readAllQuestions()).thenReturn(questions);
        mockMvc.perform(get("/questions")).andExpect(status().is2xxSuccessful());
    }

    @Test
    void testUpdateQuestion() throws Exception {
        when(questionService.update(question.getId(), question)).thenReturn(question);
        mockMvc.perform(put("/questions/1").contentType("application/json")
                .content(objectMapper.writeValueAsString(question))).andExpect(status().isOk());
    }
    @Test
    void testDeleteQuestion() throws Exception {
        mockMvc.perform(delete("/questions/1")).andExpect(status().isOk());
    }
}
