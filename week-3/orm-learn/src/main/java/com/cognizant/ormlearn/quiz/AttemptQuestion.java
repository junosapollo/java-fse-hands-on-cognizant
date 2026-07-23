package com.cognizant.ormlearn.quiz;

import jakarta.persistence.*;

@Entity
public class AttemptQuestion {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    private String prompt;
    private String answer;
    @ManyToOne(fetch = FetchType.LAZY) private QuizAttempt attempt;
    protected AttemptQuestion() { }
    public AttemptQuestion(String prompt, String answer) { this.prompt = prompt; this.answer = answer; }
    void setAttempt(QuizAttempt attempt) { this.attempt = attempt; }
    public String getPrompt() { return prompt; } public String getAnswer() { return answer; }
}
