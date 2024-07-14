package com.epam.controller;

import com.epam.dto.QuizDto;
import com.epam.exception.QuizNotFoundException;
import com.epam.model.Quiz;
import com.epam.service.QuizService;
import com.epam.util.DtoConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class QuizController {

    @Autowired
    QuizService quizService;
    @Autowired
    DtoConverter dtoConverter;

    @GetMapping("/quizzes")
    public List<Quiz> getQuizzes() {
        return quizService.read();
    }

    @GetMapping("/quizzes/{id}")
    public ResponseEntity<Quiz> getQuiz(@PathVariable int id) throws QuizNotFoundException {
        Quiz quiz = quizService.readById(id);
        return new ResponseEntity<>(quiz, HttpStatus.OK);
    }

    @PostMapping("/quizzes")
    public ResponseEntity<Quiz> createQuiz(@Valid @RequestBody QuizDto quizDto) {
        Quiz quizEntity = dtoConverter.convertToQuiz(quizDto);
        Quiz quiz = quizService.create(quizEntity);
        return new ResponseEntity<>(quiz, HttpStatus.CREATED);
    }

    @DeleteMapping("/quizzes/{id}")
    public ResponseEntity<String> deleteQuiz(@PathVariable int id) {
        quizService.delete(id);
        return new ResponseEntity<>("Quiz Deleted Successfully!!", HttpStatus.OK);
    }

    @PutMapping("/quizzes/{id}")
    public ResponseEntity<Quiz> updateQuiz(@PathVariable int id, @Valid @RequestBody QuizDto quizDto) throws QuizNotFoundException {
        Quiz quizEntity = dtoConverter.convertToQuiz(quizDto);
        Quiz quiz = quizService.update(id, quizEntity);
        return new ResponseEntity<>(quiz, HttpStatus.OK);
    }


}
