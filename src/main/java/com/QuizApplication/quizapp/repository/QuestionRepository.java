package com.QuizApplication.quizapp.repository;

import com.QuizApplication.quizapp.Dto.QuestionDto;
import com.QuizApplication.quizapp.Entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question,Long> {
    public List<Question> findAllByCategory(String category);

    @Query(value = "Select * from questions q where q.category=:category order by RAND() limit :numQ",nativeQuery = true)
    public List<Question> findRandomQuestionByCategory(String category, int numQ);
}
