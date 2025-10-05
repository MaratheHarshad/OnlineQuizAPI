package com.app.onlinequizapi.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "quiz_option") // "option" is a reserved SQL keyword
public class Option {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String text;

    private boolean correct;

    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;

}
