package com.app.onlinequizapi.mapper;

import com.app.onlinequizapi.dto.OptionDTO;
import com.app.onlinequizapi.dto.QuestionDTO;
import com.app.onlinequizapi.dto.QuizDTO;
import com.app.onlinequizapi.model.Option;
import com.app.onlinequizapi.model.Question;
import com.app.onlinequizapi.model.Quiz;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuizMapper {

    public QuizDTO toDto(Quiz quiz) {
        QuizDTO dto = new QuizDTO();
        dto.setId(quiz.getId());
        dto.setTitle(quiz.getTitle());

        // Map questions (entity -> dto)
        List<QuestionDTO> questionDTOs = quiz.getQuestions().stream()
            .map(this::toQuestionDto)
            .collect(Collectors.toList());
        dto.setQuestions(questionDTOs);

        return dto;
    }

    public QuestionDTO toQuestionDto(Question question) {
        QuestionDTO questionDTO = new QuestionDTO();
        questionDTO.setId(question.getId());
        questionDTO.setText(question.getText());
        questionDTO.setType(question.getType());

        // Map options (entity -> dto)
        List<OptionDTO> optionDTOs = question.getOptions().stream()
            .map(this::toOptionDto)
            .collect(Collectors.toList());
        questionDTO.setOptions(optionDTOs);

        return questionDTO;
    }

    public OptionDTO toOptionDto(Option option) {
        OptionDTO optionDTO = new OptionDTO();
        optionDTO.setId(option.getId());
        optionDTO.setText(option.getText());

        // Hide correct flag in quiz-taking APIs
        optionDTO.setCorrect(false); 

        return optionDTO;
    }

}
