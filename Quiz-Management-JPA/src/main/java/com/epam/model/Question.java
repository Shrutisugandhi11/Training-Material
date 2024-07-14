package com.epam.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Question")
@Component
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;
    @Column
    private String title;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "question")
    private List<Option> options;
    @JsonIgnore
    @ManyToMany(mappedBy = "questions", targetEntity = Quiz.class, cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    private List<Quiz> quizzes;

    @Column
    private String difficulty;
    @Column
    private String tag;
    @Column
    private int answer;

    public Question() {

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
        options.forEach(option1 -> option1.setQuestion(this));
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

    @Override
    public String toString() {
        return "Question [id=" + id + ", title=" + title + ", option=" + options + ", difficulty=" + difficulty
                + ", tag=" + tag + ", answer=" + answer + "]";
    }

}
