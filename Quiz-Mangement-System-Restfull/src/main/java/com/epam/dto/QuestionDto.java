package com.epam.dto;


import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

public class QuestionDto {

    private int id;

    @NotBlank(message = "Please fill title field!")
    private String title;

    @Size(max = 4)
    @NotEmpty(message = "Please fill Option field!")
    private List<OptionDto> options;

    private List<QuizDto> quizzes;
    @NotBlank(message = "Please fill difficulty field!")
    private String difficulty;
    @NotBlank(message = "Please fill tag field!")
    private String tag;

    private int answer;

    public QuestionDto() {
    }


    public QuestionDto(int id, String title, List<OptionDto> options, List<QuizDto> quizzes, String difficulty, String tag, int answer) {
        this.id = id;
        this.title = title;
        this.options = options;
        this.quizzes = quizzes;
        this.difficulty = difficulty;
        this.tag = tag;
        this.answer = answer;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<OptionDto> getOptions() {
        return options;
    }

    public void setOptions(List<OptionDto> options) {
        this.options = options;
    }

    public List<QuizDto> getQuizzes() {
        return quizzes;
    }

    public void setQuizzes(List<QuizDto> quizzes) {
        this.quizzes = quizzes;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public int getAnswer() {
        return answer;
    }

    public void setAnswer(int answer) {
        this.answer = answer;
    }
}
