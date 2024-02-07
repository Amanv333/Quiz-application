package com.QuizApplication.quizapp.service;

import com.QuizApplication.quizapp.Dto.QuestionDto;
import com.QuizApplication.quizapp.Entity.Question;

import java.util.List;

public interface QuestionService {
    public void saveAll(List<QuestionDto> question);
    public List<QuestionDto> findAllQuestion();

    List<QuestionDto> getAllByCategory(String category);

    void addQuestion(QuestionDto questionDto);


    void deleteById(long id);
}
