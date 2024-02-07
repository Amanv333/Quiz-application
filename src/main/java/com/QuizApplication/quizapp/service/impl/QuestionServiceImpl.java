package com.QuizApplication.quizapp.service.impl;

import com.QuizApplication.quizapp.Dto.QuestionDto;
import com.QuizApplication.quizapp.Entity.Question;
import com.QuizApplication.quizapp.Exception.ResourceNotFoundException;
import com.QuizApplication.quizapp.repository.QuestionRepository;
import com.QuizApplication.quizapp.service.QuestionService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuestionServiceImpl implements QuestionService {
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public void saveAll(List<QuestionDto> questionDto) {
        List<Question> questions = questionDto.stream().map(this::maptoEntity).collect(Collectors.toList());
        questionRepository.saveAll(questions);



        questionRepository.saveAll(questions);
    }

    @Override
    public List<QuestionDto> findAllQuestion() {
        List<Question> questions = questionRepository.findAll();
        List<QuestionDto> dtos = questions.stream().map(this::mapToDto).collect(Collectors.toList());
        return dtos;


    }

    @Override
    public List<QuestionDto> getAllByCategory(String category) {
        List<Question> byCategory = questionRepository.findAllByCategory(category);
        List<QuestionDto> dtos = byCategory.stream().map(this::mapToDto).collect(Collectors.toList());
        return dtos;
    }

    @Override
    public void addQuestion(QuestionDto questionDto) {
        Question question = maptoEntity(questionDto);
        questionRepository.save(question);

    }

    @Override
    public void deleteById(long id) {
        questionRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Question is not Found by id : " + id));
        questionRepository.deleteById(id);

    }


    private Question maptoEntity(QuestionDto dto){
        return modelMapper.map(dto, Question.class);

    }
    private QuestionDto mapToDto(Question question){
        return modelMapper.map(question,QuestionDto.class);
    }
}
