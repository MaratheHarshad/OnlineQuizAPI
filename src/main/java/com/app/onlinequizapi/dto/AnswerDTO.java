package com.app.onlinequizapi.dto;


import jakarta.validation.constraints.NotNull;

import java.util.List;

public class AnswerDTO {

    @NotNull(message="Question ID cannot be null")
    private Long questionId;

    @NotNull(message="Selected option ID's cannot be null")
    private List<Long> selectedOptionId;

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public List<Long> getSelectedOptionId() {
        return selectedOptionId;
    }

    public void setSelectedOptionId(List<Long> selectedOptionId) {
        this.selectedOptionId = selectedOptionId;
    }
}
