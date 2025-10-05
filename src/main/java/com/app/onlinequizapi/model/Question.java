package com.app.onlinequizapi.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String text;

    @Enumerated(EnumType.STRING)
    private QuestionType type; // SINGLE_CHOICE, MULTIPLE_CHOICE, TEXT

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "quiz_id")
    private Quiz quiz;

    @JsonBackReference
    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Option> options;

}
