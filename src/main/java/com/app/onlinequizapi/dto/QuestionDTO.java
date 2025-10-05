package com.app.onlinequizapi.dto;

import java.util.List;
import com.app.onlinequizapi.model.QuestionType;

public class QuestionDTO {
    private Long id;
    private String text;
    private QuestionType type;
    private List<OptionDTO> options;

    // Getters and setters

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public QuestionType getType() {
        return type;
    }

    public void setType(QuestionType type) {
        this.type = type;
    }

    public List<OptionDTO> getOptions() {
        return options;
    }

    public void setOptions(List<OptionDTO> options) {
        this.options = options;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
