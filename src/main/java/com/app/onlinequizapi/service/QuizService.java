package com.app.onlinequizapi.service;

import com.app.onlinequizapi.dto.OptionDTO;
import com.app.onlinequizapi.dto.QuestionDTO;
import com.app.onlinequizapi.dto.QuizDTO;
import com.app.onlinequizapi.model.Option;
import com.app.onlinequizapi.model.Question;
import com.app.onlinequizapi.model.Quiz;
import com.app.onlinequizapi.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

    private final QuizRepository quizRepository;

    @Autowired
    public QuizService(QuizRepository quizRepository) {
        this.quizRepository = quizRepository;
    }

    public Quiz createQuiz(QuizDTO request) {
        Quiz quiz = new Quiz();
        quiz.setTitle(request.getTitle());
        if (quiz.getQuestions() == null) {
            quiz.setQuestions(new ArrayList<>());
        }
        for (QuestionDTO questionDTO : request.getQuestions()) {
            Question q = new Question();
            q.setText(questionDTO.getText());
            q.setType(questionDTO.getType());

            List<Option> options = new ArrayList<>();
            if (questionDTO.getOptions() != null) {
                for (OptionDTO optionDTO : questionDTO.getOptions()) {
                    Option option = new Option();
                    option.setText(optionDTO.getText());
                    option.setCorrect(optionDTO.isCorrect());
                    option.setQuestion(q);
                    options.add(option);
                }
            }
            q.setOptions(options);

            q.setQuiz(quiz);
            quiz.getQuestions().add(q);
        }
        return quizRepository.save(quiz);
    }

    public List<Quiz> getAllQuizzes() {
        return quizRepository.findAll();
    }

    public Optional<Quiz> getQuizById(Long id) {
        return quizRepository.findById(id);
    }

    public void deleteQuiz(Long id) {
        quizRepository.deleteById(id);
    }

    public Quiz saveQuiz(Quiz quiz) {
        return quizRepository.save(quiz);
    }

}
