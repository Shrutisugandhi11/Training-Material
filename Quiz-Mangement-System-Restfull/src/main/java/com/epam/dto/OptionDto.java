package com.epam.dto;

public class OptionDto {


    private int optionId;

    private String choice;


    private QuestionDto question;

    public OptionDto() {
    }

    public OptionDto(int optionId, String choice, QuestionDto question) {
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

    public QuestionDto getQuestion() {
        return question;
    }

    public void setQuestion(QuestionDto question) {
        this.question = question;
    }
}
