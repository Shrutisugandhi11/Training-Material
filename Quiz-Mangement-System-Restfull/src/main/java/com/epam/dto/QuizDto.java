package com.epam.dto;


import javax.validation.constraints.NotBlank;
import java.util.List;

public class QuizDto {
    int id;
    @NotBlank(message = "Please fill tag field!")


    String tag;
    @NotBlank(message = "Please fill category field!")

    String category;

    List<QuestionDto> questions;

    public QuizDto() {
    }

    public QuizDto(int id, String tag, String category, List<QuestionDto> questions) {
        this.id = id;
        this.tag = tag;
        this.category = category;
        this.questions = questions;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<QuestionDto> getQuestions() {
        return questions;
    }

    public void setQuestions(List<QuestionDto> questions) {
        this.questions = questions;
    }
}
