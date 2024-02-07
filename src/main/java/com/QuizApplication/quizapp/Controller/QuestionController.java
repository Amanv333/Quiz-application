package com.QuizApplication.quizapp.Controller;

import com.QuizApplication.quizapp.Dto.QuestionDto;
import com.QuizApplication.quizapp.Entity.Question;
import com.QuizApplication.quizapp.service.impl.QuestionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/question")
public class QuestionController {
    @Autowired
    private QuestionServiceImpl service;
    @PostMapping
    public ResponseEntity<?> saveAllQuestion(@RequestBody List<QuestionDto> dtos){

        service.saveAll(dtos);
        return new ResponseEntity<>("All Question is saved", HttpStatus.CREATED);
    }
    @PostMapping("/addQuestion")
    public ResponseEntity<?> addQuestion(@RequestBody QuestionDto questionDto){
        service.addQuestion(questionDto);
        return new ResponseEntity<>("Question is saved",HttpStatus.CREATED);
    }


    @GetMapping("/getAll")
    public ResponseEntity<?> getAllQuestion(){
        try {
            List<QuestionDto> allQuestion = service.findAllQuestion();
            return new ResponseEntity<>(allQuestion, HttpStatus.OK);
        }catch(Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);

    }
    @GetMapping("/category/{category}")
    public ResponseEntity<?> getQuestionByCategory(@PathVariable String category){
        try {
            List<QuestionDto> questionDtos = service.getAllByCategory(category);
            return new ResponseEntity<>(questionDtos, HttpStatus.OK);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);

    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> DeleteQuestion(@PathVariable long id){
        service.deleteById(id);
        return new ResponseEntity<>("Question is deleted by id : "+id,HttpStatus.OK);
    }
}
