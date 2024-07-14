package com.epam.model;


import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
@Component
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    String tag;
    String category;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "Quiz_Question",
            joinColumns = @JoinColumn(name = "quiz_id"),
            inverseJoinColumns = @JoinColumn(name = "question_id"))
    List<Question> questions;

    public Quiz() {
        super();
    }

    public Quiz(int id, String tag, String category, List<Question> questions) {
        super();
        this.id = id;
        this.tag = tag;
        this.category = category;
        this.questions = questions;
    }

    public Quiz(String tag, String category, List<Question> questions) {
        this.tag = tag;
        this.category = category;
        this.questions = questions;
    }

    public Quiz(String tag, String category) {
        this.tag = tag;
        this.category = category;

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

    @Override
    public String toString() {
        return "Quiz [tag=" + tag + ",id= " + id + ", category=" + category + ", questions=" + questions + "]";
    }


}
