package com.QuizApplication.quizapp.service.impl;

import com.QuizApplication.quizapp.Dto.QueDtoUser;
import com.QuizApplication.quizapp.Entity.Question;
import com.QuizApplication.quizapp.Entity.Quiz;
import com.QuizApplication.quizapp.Entity.Response;
import com.QuizApplication.quizapp.Exception.ResourceNotFoundException;
import com.QuizApplication.quizapp.repository.QuestionRepository;
import com.QuizApplication.quizapp.repository.QuizRepository;
import com.QuizApplication.quizapp.service.QuizService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuizServiceImpl implements QuizService {
    @Autowired
    QuizRepository quizRepository;
    @Autowired
    QuestionRepository questionRepository;
    @Autowired
    ModelMapper modelMapper;

    @Override
    public void createQuiz(String category, int numQ, String title) {
        List<Question> randomQuestionByCategory = questionRepository.findRandomQuestionByCategory(category, numQ);
        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(randomQuestionByCategory);

        quizRepository.save(quiz);


    }

    @Override
    public List<QueDtoUser> getQuizForUser(long id) {
        Quiz quiz = quizRepository.findById(id).orElseThrow(()->
                new ResourceNotFoundException("Quiz not found by id : "+id));
        List<Question> ques = quiz.getQuestions();
        List<QueDtoUser> dtoUsers = ques.stream().map(this::mapToDto).toList();


        return dtoUsers;
    }

    @Override
    public int getResult(long id, List<Response> responses) {
        Quiz quiz = quizRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Quiz is not present with id : " + id));
        List<Question> questions = quiz.getQuestions();
        int right = 0;
        int i = 0;
        for (Response res : responses) {
            String str = String.valueOf(questions.get(i).getCorrectAnswer());

            if (res.getRes().equals(str)) {
                right++;
            }
            i++;

        }

        return right;
    }

    @Override
    public void deleteQuizById(long id) {
        quizRepository.findById(id).orElseThrow(
                ()->new ResourceNotFoundException("Quiz not found by id : "+id));
        quizRepository.deleteById(id);
    }

    private QueDtoUser mapToDto(Question question) {
        return modelMapper.map(question,QueDtoUser.class);
    }

}
