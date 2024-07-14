package com.epam.dto;

import com.epam.model.Question;

public class OptionDto {
    private int optionId;
    private String choice;
    private Question question;

    public OptionDto() {
    }

    public OptionDto(int optionId, String choice, Question question) {
        this.optionId = optionId;
        this.choice = choice;
        this.question = question;
    }

    public int getOptionId() {
        return optionId;
    }

    public void setOptionId(int optionId) {
        this.optionId = optionId;
    }

    public String getChoice() {
        return choice;
    }

    public void setChoice(String choice) {
        this.choice = choice;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }
}
