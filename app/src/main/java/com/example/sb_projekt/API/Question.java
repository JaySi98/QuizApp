package com.example.sb_projekt.API;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

// QUESTION ------------------------------------------------------------------------------------- //
// class for storing the question
public class Question
{
    private String category;

    private String type;

    private String difficulty;

    private String question;

    private String correct_answer;

    private List<String> incorrect_answers;

    public Question(String category, String type, String difficulty, String question,
                    String correct_answer, List<String> incorrect_answers)
    {
        this.category = category;
        this.type = type;
        this.difficulty = difficulty;
        this.question = question;
        this.correct_answer = correct_answer;
        this.incorrect_answers = incorrect_answers;
    }

}
