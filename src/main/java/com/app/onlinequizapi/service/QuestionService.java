package com.app.onlinequizapi.service;

import com.app.onlinequizapi.model.Question;
import com.app.onlinequizapi.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {

    private final QuestionRepository questionRepository;

    @Autowired
    public QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    public Question createQuestion(Question question) {
        if (question.getText() == null || question.getText().trim().isEmpty()) {
            throw new IllegalArgumentException("Question text cannot be empty");
        }
        if (question.getType() == null) {
            throw new IllegalArgumentException("Question type must be specified");
        }
        return questionRepository.save(question);
    }

    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }

    public Optional<Question> getQuestionById(Long id) {
        return questionRepository.findById(id);
    }

    public void deleteQuestion(Long id) {
        questionRepository.deleteById(id);
    }
}
