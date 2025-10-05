package com.app.onlinequizapi.dto;

public class ScoreDTO {
    private int totalQuestions;
    private int correctAnswers;

    public ScoreDTO(int totalQuestions, int correctAnswers) {
        this.totalQuestions = totalQuestions;
        this.correctAnswers = correctAnswers;
    }

    public int getTotalQuestions() {
        return totalQuestions;
    }

    public int getCorrectAnswers() {
        return correctAnswers;
    }
}
