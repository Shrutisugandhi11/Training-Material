package com.epam.controller;

import com.epam.dto.QuizDto;
import com.epam.exception.QuizNotFoundException;
import com.epam.model.Question;
import com.epam.model.Quiz;
import com.epam.service.QuizService;
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
import java.util.List;

@Controller
public class QuizController {
    @Autowired
    DtoConverter dtoConverter ;
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
    public String createQuiz(Model model) {
        Quiz quiz = new Quiz();
        model.addAttribute("quiz", quiz);
        return "createQuiz";
    }

    @PostMapping("/quiz")
    public String saveQuiz(@Valid @ModelAttribute("quiz") QuizDto quizDto) {
       Quiz quiz= dtoConverter.convertToQuiz(quizDto);
        quizService.create(quiz);
        return "redirect:/quizzes";
    }

    @GetMapping("/quiz/delete/{id}")
    public String deleteQuiz(@PathVariable int id) {
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
    public ModelAndView availableQuestion(@PathVariable int id) throws QuizNotFoundException {
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
    public ModelAndView quizUpdate(@PathVariable(name="id") int id) throws QuizNotFoundException {
        Quiz quiz = quizService.readById(id);
        ModelAndView model = new ModelAndView();
        model.addObject("quiz", quiz);
        model.setViewName("updateQuiz");
        return model;
    }
    @PostMapping("/quiz/save/updated/{id}")
    public String saveQuiz(@PathVariable(name="id") int id,@Valid @ModelAttribute("quiz") QuizDto quizDto) throws QuizNotFoundException {
      Quiz quiz= dtoConverter.convertToQuiz(quizDto);
        quizService.update(id,quiz);
        return "redirect:/quizzes";
    }


}
