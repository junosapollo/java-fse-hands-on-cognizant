package com.cognizant.ormlearn.quiz;

import jakarta.persistence.*;
import java.util.*;

@Entity
public class QuizAttempt {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    private String candidate;
    @OneToMany(mappedBy = "attempt", cascade = CascadeType.ALL, orphanRemoval = true) private List<AttemptQuestion> questions = new ArrayList<>();
    protected QuizAttempt() { }
    public QuizAttempt(String candidate) { this.candidate = candidate; }
    public Long getId() { return id; } public String getCandidate() { return candidate; } public List<AttemptQuestion> getQuestions() { return questions; }
    public void addQuestion(AttemptQuestion question) { questions.add(question); question.setAttempt(this); }
}
