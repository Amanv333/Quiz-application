package com.QuizApplication.quizapp.repository;

import com.QuizApplication.quizapp.Entity.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizRepository extends JpaRepository<Quiz,Long> {
}
