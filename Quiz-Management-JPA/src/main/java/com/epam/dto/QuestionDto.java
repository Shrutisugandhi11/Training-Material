package com.epam.dto;


import com.epam.model.Option;
import com.epam.model.Quiz;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

public class QuestionDto {

    private int id;

    @NotBlank(message = "Title cannot be Blank")
    private String title;

    @Size(max = 4, message = "only 4 options are allowed")
    private List<Option> options;
    private List<Quiz> quizzes;

    @NotBlank(message = "Difficulty cannot be Blank")
    private String difficulty;
    @NotBlank(message = "Tag cannot be Blank")
    private String tag;
    @Max(value = 4, message = "Answer cannot exceed 4")
    private int answer;

    public QuestionDto() {
    }

    public QuestionDto(int id, String title, List<Option> options, List<Quiz> quizzes, String difficulty, String tag, int answer) {
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

    public List<Option> getOptions() {
        return options;
    }

    public void setOptions(List<Option> options) {
        this.options = options;
    }

    public List<Quiz> getQuizzes() {
        return quizzes;
    }

    public void setQuizzes(List<Quiz> quizzes) {
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
