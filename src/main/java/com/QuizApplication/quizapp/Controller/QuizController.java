package com.QuizApplication.quizapp.Controller;

import com.QuizApplication.quizapp.Dto.QueDtoUser;
import com.QuizApplication.quizapp.Entity.Response;
import com.QuizApplication.quizapp.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/quiz")
public class QuizController {
    @Autowired
    QuizService quizService;

    @PostMapping("/create")
    public ResponseEntity<?> createQuiz(@RequestParam String category,@RequestParam int numQ, @RequestParam String title){
        quizService.createQuiz(category,numQ,title);
        return new ResponseEntity<>("Success", HttpStatus.CREATED);

    }
    @PostMapping("/submit/{id}")
    public ResponseEntity<?> getResponces(@PathVariable long id ,@RequestBody List<Response> responses){
        int result = quizService.getResult(id, responses);
        return new ResponseEntity<>("Your score is : "+result,HttpStatus.OK);

    }
    @GetMapping("/get/{id}")
    public ResponseEntity<?> getQuizforUser(@PathVariable long id){
        List<QueDtoUser> quizForUser = quizService.getQuizForUser(id);
        return new ResponseEntity<>(quizForUser,HttpStatus.OK);
    }

    @DeleteMapping("/deletequiz/{id}")
    public ResponseEntity<?> deleteQuiz(@PathVariable long id){
        quizService.deleteQuizById(id);

        return new ResponseEntity<>("Quiz delete By id : "+id,HttpStatus.OK);
    }

}
