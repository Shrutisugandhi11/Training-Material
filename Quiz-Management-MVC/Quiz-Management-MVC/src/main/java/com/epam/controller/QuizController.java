package com.epam.controller;

import com.epam.model.Question;
import com.epam.model.Quiz;
import com.epam.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller
public class QuizController {
    @Autowired
    QuizService quizService;

    @GetMapping("/quizzes")
    public ModelAndView quizList() {
        ModelAndView mv = new ModelAndView();
        mv.addObject("quizzes", quizService.read());
        mv.setViewName("quizList");
        return mv;
    }

    @GetMapping("/quiz/new")
    public String createQuestion(Model model) {
        Quiz quiz = new Quiz();
        model.addAttribute("quiz", quiz);
        return "createQuiz";
    }

    @PostMapping("/quiz")
    public String saveQuestion(@ModelAttribute("quiz") Quiz quiz) {
        quizService.create(quiz);
        return "redirect:/quizzes";
    }

    @GetMapping("/quiz/delete/{id}")
    public String deleteUser(@PathVariable int id) {
        quizService.delete(id);
        return "redirect:/quizzes";
    }

    @GetMapping("/quiz/get/quiz")
    public ModelAndView availableQuiz() {
        ModelAndView mv = new ModelAndView();
        mv.addObject("quizzes", quizService.read());
        mv.setViewName("quizDisplay");
        return mv;
    }

    @GetMapping("/quiz/fetch/question/{id}")
    public ModelAndView availableQuestion(@PathVariable int id) {
        ModelAndView mv = new ModelAndView();
        List<Question> questions = quizService.readById(id).getQuestions();
        mv.addObject("questions", questions);
        mv.setViewName("questionDisplay");
        return mv;
    }
    @GetMapping("/quiz/panel")
    public String quizPanel() {
        return "quizMenu";
    }

    @GetMapping("/quiz/update/{id}")
    public ModelAndView quizUpdate(@PathVariable(name="id") int quizId) {
        Quiz quiz = quizService.readById(quizId);
        ModelAndView model = new ModelAndView();
        model.addObject("quiz", quiz);
        model.setViewName("createQuiz");
        return model;
    }


}
