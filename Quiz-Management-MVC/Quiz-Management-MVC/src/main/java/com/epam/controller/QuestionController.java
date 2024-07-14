package com.epam.controller;

import com.epam.model.Question;
import com.epam.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class QuestionController {

    @Autowired
    QuestionService questionService;

    @GetMapping("/questions")
    public String questionList(Model model) {
        model.addAttribute("questions", questionService.read());
        return "questionList";
    }

    @GetMapping("/question/new")
    public String createQuestion(Model model) {
        Question question = new Question();
        model.addAttribute("question", question);
        return "createQuestion";
    }

    @GetMapping("/question/update/{id}")
    public ModelAndView questionUpdate(@PathVariable(name = "id") int questionId) {
        Question question = questionService.read().stream().filter(question1 -> questionId == question1.getId()).findAny().get();
        ModelAndView model = new ModelAndView();

        model.addObject("question", question);


        return model;
    }

    @GetMapping("/question/delete/{id}")
    public String deleteQuestion(@PathVariable int id) {
        questionService.delete(id);
        return "redirect:/questions";
    }

    @PostMapping("/question/save")
    public String saveQuestion(@ModelAttribute("question") Question question) {
        questionService.create(question);
        return "redirect:/questions";
    }

    @GetMapping("/question/panel")
    public String questionPanel() {
        return "questionMenu";
    }


}



