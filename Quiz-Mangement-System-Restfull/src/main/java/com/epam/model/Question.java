package com.epam.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "questions")
@Component
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;
    @Column(unique = true)
    private String title;
    @JsonManagedReference
    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "question", fetch = FetchType.LAZY)
    private List<Option> options;

    @JsonBackReference(value = "quiz_id")
    @ManyToMany(mappedBy = "questions", targetEntity = Quiz.class, cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
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
