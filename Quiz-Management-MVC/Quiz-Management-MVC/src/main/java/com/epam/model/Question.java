package com.epam.model;


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
    @OneToMany(cascade = CascadeType.MERGE, mappedBy = "question", fetch = FetchType.EAGER)
    private List<Option> option;
    @ManyToMany(mappedBy = "questions", targetEntity = Quiz.class, cascade = {CascadeType.MERGE}, fetch = FetchType.LAZY)
    private List<Quiz> quiz;

    @Column
    private String difficulty;
    @Column
    private String tag;
    @Column
    private int answer;

    public Question() {
        super();
    }


    public Question(String title, List<Option> option, String difficulty, String tag, int answer) {
        this.title = title;
        this.option = option;
        this.difficulty = difficulty;
        this.tag = tag;
        this.answer = answer;
    }

    public Question(String title, List<Option> option, List<Quiz> quiz, String difficulty, String tag, int answer) {
        this.title = title;
        this.option = option;
        this.quiz = quiz;
        this.difficulty = difficulty;
        this.tag = tag;
        this.answer = answer;
    }


    public List<Quiz> getQuiz() {
        return quiz;
    }

    public void setQuiz(List<Quiz> quiz) {
        this.quiz = quiz;
    }

    public List<Option> getOption() {
        return option;
    }

    public void setOption(List<Option> option) {
        option.forEach(option1 -> option1.setQuestion(this));
        this.option = option;
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
        return "Question [id=" + id + ", title=" + title + ", option=" + option + ", difficulty=" + difficulty
                + ", tag=" + tag + ", answer=" + answer + "]";
    }

}
