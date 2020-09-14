package engine.service;

import engine.entity.Quiz;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public interface QuizService {
    Page<Quiz> getAllQuizzes(Integer page, Integer pageSize, String sortBy);

    Quiz getQuizById(int id);

    Quiz addQuiz(Quiz quiz);

    long size();

    void deleteQuiz(int id);
}
