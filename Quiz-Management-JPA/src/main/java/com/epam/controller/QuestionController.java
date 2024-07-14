package com.epam.controller;

import com.epam.dto.QuestionDto;
import com.epam.exception.QuestionNotFoundException;
import com.epam.model.Question;
import com.epam.service.QuestionService;
import com.epam.util.DtoConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;


@Controller
public class QuestionController {
    @Autowired
    DtoConverter dtoConverter;
    @Autowired
    QuestionService questionService;

    @GetMapping("/questions")
    public String questionList(Model model) {
        model.addAttribute("questions", questionService.readAllQuestions());
        return "questionList";
    }

    @GetMapping("/question/new")
    public String createQuestion(Model model) {
        QuestionDto question = new QuestionDto();
        model.addAttribute("question", question);
        return "createQuestion";
    }

    @GetMapping("/question/update/{id}")
    public ModelAndView questionUpdate(@PathVariable(name = "id") int questionId) throws QuestionNotFoundException {
        Question question = questionService.readById(questionId);
        ModelAndView model = new ModelAndView();
        model.addObject("question", question);
        model.setViewName("updateQuestion");
        return model;
    }

    @GetMapping("/question/delete/{id}")
    public String deleteQuestion(@PathVariable int id) {
        questionService.delete(id);
        return "redirect:/questions";
    }

    @PostMapping("/question/save")
    public String saveQuestion(@Valid @ModelAttribute("question") QuestionDto questionDto) {
        Question question = dtoConverter.convertToQuestion(questionDto);
        questionService.create(question);
        return "redirect:/questions";
    }

    @PostMapping("/question/save/updated/{id}")
    public String saveUpdatedQuestion(@Valid @PathVariable int id, @ModelAttribute("question") QuestionDto questionDto) throws QuestionNotFoundException {
        Question question = dtoConverter.convertToQuestion(questionDto);
        questionService.update(id, question);
        return "redirect:/questions";
    }

    @GetMapping("/question/panel")
    public String questionPanel() {
        return "questionMenu";
    }


}



