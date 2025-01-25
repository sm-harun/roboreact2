package com.nas.robospring.service;

import com.nas.robospring.model.Quiz;
import com.nas.robospring.model.QuizQuestion;
import com.nas.robospring.repository.QuizQuestionRepository;
import com.nas.robospring.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class QuizService {
    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private QuizQuestionRepository quizQuestionRepository;

    public Flux<Quiz> getAllQuizzes() {
        return quizRepository.findAll(); // Retrieve all quizzes
    }

    public Mono<Quiz> createQuiz(Quiz quiz) {
        return quizRepository.save(quiz); // Create a new quiz
    }

    public Mono<Quiz> getQuizById(Long id) {
        return quizRepository.findById(id); // Get a quiz by ID
    }

    public Mono<Void> deleteQuiz(Long id) {
        return quizRepository.deleteById(id); // Delete quiz by ID
    }

    public Mono<QuizQuestion> addQuestionToQuiz(Long quizId, QuizQuestion question) {
        return quizRepository.findById(quizId)
                .flatMap(quiz -> {
                    question.setQuizId(quizId);
                    return quizQuestionRepository.save(question);
                });
    }
}
