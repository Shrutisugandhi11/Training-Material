package com.epam.controller;

import com.epam.dto.QuestionDto;
import com.epam.exception.QuestionNotFoundException;
import com.epam.model.Question;
import com.epam.service.QuestionService;
import com.epam.util.DtoConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class QuestionController {

    @Autowired
    QuestionService questionService;
    @Autowired
    DtoConverter dtoConverter;

    @GetMapping("/questions")
    public ResponseEntity<List<Question>> getQuestions() {
        List<Question> questions = questionService.readAllQuestions();
        return new ResponseEntity<>(questions, HttpStatus.CREATED);
    }

    @GetMapping("/questions/{id}")
    public ResponseEntity<Question> getQuestion(@PathVariable int id) throws QuestionNotFoundException {
        Question questionEntity = questionService.readById(id);
        return new ResponseEntity<>(questionEntity, HttpStatus.OK);
    }

    @PostMapping("/questions")
    public ResponseEntity<Question> createQuestion(@Valid  @RequestBody QuestionDto questionDto) {
        Question questionEntity = dtoConverter.convertToQuestion(questionDto);
        Question question = questionService.create(questionEntity);
        return new ResponseEntity<>(question, HttpStatus.CREATED);
    }

    @PutMapping("/questions/{id}")
    public ResponseEntity<Question> updateQuestion(@PathVariable int id, @Valid @RequestBody QuestionDto questionDto) throws QuestionNotFoundException {
        Question questionEntity =dtoConverter.convertToQuestion(questionDto);
        Question question = questionService.update(id, questionEntity);
        return new ResponseEntity<>(question, HttpStatus.OK);
    }

    @DeleteMapping("/questions/{id}")
    public ResponseEntity<String> deleteQuestion(@PathVariable int id) {
        questionService.deleteById(id);
        return new ResponseEntity<>("Question Deleted Successfully!!", HttpStatus.OK);
    }
}
