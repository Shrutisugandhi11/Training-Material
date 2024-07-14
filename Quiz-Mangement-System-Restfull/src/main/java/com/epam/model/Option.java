package com.epam.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Entity
@Table(name = "options")
@Component
public class Option {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private int optionId;
    @Column
    private String choice;
    @JsonBackReference
    @ManyToOne
    private Question question;

    public Option(String choice) {
        this.choice = choice;
    }

    public Option() {
    }

    public int getOptionId() {
        return optionId;
    }

    public void setOptionId(int optionId) {
        this.optionId = optionId;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public String getChoice() {
        return choice;
    }

    public void setChoice(String choice) {

        this.choice = choice;
    }

    @Override
    public String toString() {
        return "Option :" + choice + "";
    }

}
