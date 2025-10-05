package com.app.onlinequizapi.dto;

import lombok.Getter;
import lombok.Setter;

//@Getter
//@Setter
public class OptionDTO {

    private Long id;
    private String text;
    private boolean correct;

    // Getters and setters

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isCorrect() {
        return correct;
    }

    public void setCorrect(boolean correct) {
        this.correct = correct;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
