package com.epam.repository;

import com.epam.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuizRepository extends JpaRepository<Quiz,Integer> {

     List<Quiz> findByTag(String tag);
}
