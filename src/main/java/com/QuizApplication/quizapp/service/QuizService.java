package com.QuizApplication.quizapp.service;

import com.QuizApplication.quizapp.Dto.QueDtoUser;
import com.QuizApplication.quizapp.Entity.Response;

import java.util.List;

public interface QuizService {
    void createQuiz(String category, int numQ, String title);
    List<QueDtoUser> getQuizForUser (long id);

    int getResult(long id, List<Response> responses);
}
