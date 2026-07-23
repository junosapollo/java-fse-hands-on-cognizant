package com.cognizant.ormlearn.quiz;

import java.util.Optional;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

public interface QuizAttemptRepository extends JpaRepository<QuizAttempt, Long> {
    @Query("select distinct q from QuizAttempt q left join fetch q.questions where q.id=:id")
    Optional<QuizAttempt> findWithQuestions(@Param("id") Long id);
}
