package com.epam.dto;

import com.epam.model.Question;

import javax.validation.constraints.NotBlank;
import java.util.List;

public class QuizDto {
    int id;
    @NotBlank(message = "Tag cannot be Blank")
    String tag;
    @NotBlank(message = "Category cannot be Blank")
    String category;
    List<Question> questions;

    public QuizDto() {
    }

    public QuizDto(int id, String tag, String category, List<Question> questions) {
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

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }
}
