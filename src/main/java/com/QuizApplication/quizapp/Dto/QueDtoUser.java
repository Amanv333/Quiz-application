package com.QuizApplication.quizapp.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QueDtoUser {
    private long id;
    private String questionText;
    private String optionA;
    private String optionB;
    private String optionC;
    private String optionD;
}
