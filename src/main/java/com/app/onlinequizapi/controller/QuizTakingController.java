package com.app.onlinequizapi.controller;

import com.app.onlinequizapi.dto.QuestionDTO;
import com.app.onlinequizapi.dto.QuizDTO;
import com.app.onlinequizapi.dto.ScoreDTO;
import com.app.onlinequizapi.dto.AnswerDTO;
import com.app.onlinequizapi.exception.ResourceNotFoundException;
import com.app.onlinequizapi.mapper.QuizMapper;
import com.app.onlinequizapi.model.Option;
import com.app.onlinequizapi.model.Question;
import com.app.onlinequizapi.service.QuizService;
import com.app.onlinequizapi.model.Quiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/quiz-take")
public class QuizTakingController {

    private final QuizService quizService;
    private final QuizMapper quizMapper;

    @Autowired
    public QuizTakingController(QuizService quizService, QuizMapper quizMapper) {
        this.quizService = quizService;
        this.quizMapper = quizMapper;
    }


    @GetMapping("/{quizId}")
    public ResponseEntity<QuizDTO> getQuizWithQuestionsAndOptions(@PathVariable Long quizId) {
        Optional<Quiz> quizOptional = quizService.getQuizById(quizId);
        if (quizOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Quiz quiz = quizOptional.get();

        // Map Quiz entity to QuizDTO including questions and options

        QuizDTO quizDTO = quizMapper.toDto(quiz);


        // Optional: Hide correct flags in options before sending to quiz taker
        quizDTO.getQuestions().forEach(question ->
                question.getOptions().forEach(option -> option.setCorrect(false))
        );

        return ResponseEntity.ok(quizDTO);
    }

    @GetMapping("/{quizId}/random-question")
    public ResponseEntity<Question> getRandomQuestion(@PathVariable Long quizId) {
        Optional<Quiz> quizOptional = quizService.getQuizById(quizId);
        if (quizOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Quiz quiz = quizOptional.get();
        List<Question> questions = quiz.getQuestions();
        if (questions.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        Random random = new Random();
        Question randomQuestion = questions.get(random.nextInt(questions.size()));

        // Optional: remove or hide correct flags before returning for quiz-taking
        randomQuestion.getOptions().forEach(option -> option.setCorrect(false));

        return ResponseEntity.ok(randomQuestion);
    }

    @PatchMapping("/{quizId}/add-question")
    public ResponseEntity<Quiz> addQuestionToQuiz(@PathVariable Long quizId, @RequestBody QuestionDTO questionDTO) {
        Quiz quiz = quizService.getQuizById(quizId)
                .orElseThrow(() -> new ResourceNotFoundException("Quiz not found with id " + quizId));

        Question question = new Question();
        question.setText(questionDTO.getText());
        question.setType(questionDTO.getType());
        question.setQuiz(quiz);

        if (questionDTO.getOptions() != null) {
            List<Option> options = questionDTO.getOptions().stream().map(optDto -> {
                Option opt = new Option();
                opt.setText(optDto.getText());
                opt.setCorrect(optDto.isCorrect());
                opt.setQuestion(question);
                return opt;
            }).collect(Collectors.toList());
            question.setOptions(options);
        }

        quiz.getQuestions().add(question);

        Quiz savedQuiz = quizService.saveQuiz(quiz);

        return ResponseEntity.ok(savedQuiz);
    }

    @DeleteMapping("/{quizId}/delete-question/{questionId}")
    public ResponseEntity<Void> deleteQuestionFromQuiz(@PathVariable Long quizId, @PathVariable Long questionId) {
        Optional<Quiz> quizOptional = quizService.getQuizById(quizId);
        if (quizOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Quiz quiz = quizOptional.get();
        quiz.getQuestions().removeIf(q -> q.getId().equals(questionId));
//        quizService.createQuiz(quiz);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{quizId}/submit")
    public ResponseEntity<ScoreDTO> submitAnswers(
            @PathVariable Long quizId,
            @RequestBody List<AnswerDTO> answers) {

        Optional<Quiz> quizOptional = quizService.getQuizById(quizId);
        if (quizOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Quiz quiz = quizOptional.get();
        int totalQuestions = quiz.getQuestions().size();
        int correctCount = 0;

        // Map questions by id for fast lookup
        Map<Long, Question> questionMap = quiz.getQuestions().stream()
                .collect(Collectors.toMap(Question::getId, q -> q));

        for (AnswerDTO answer : answers) {
            Question question = questionMap.get(answer.getQuestionId());
            if (question != null) {
                // Get set of selected option IDs
                Set<Long> selectedIds = new HashSet<>(answer.getSelectedOptionId());

                // Get set of correct option IDs for this question
                Set<Long> correctOptionIds = question.getOptions().stream()
                        .filter(Option::isCorrect)
                        .map(Option::getId)
                        .collect(Collectors.toSet());

                // Check if selected equals correct
                if (selectedIds.equals(correctOptionIds)) {
                    correctCount++;
                }
            }

        }

        ScoreDTO scoreDTO = new ScoreDTO(totalQuestions, correctCount);
        return ResponseEntity.ok(scoreDTO);
    }
}
