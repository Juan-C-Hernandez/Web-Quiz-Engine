package engine.controller;

import engine.*;
import engine.entity.Quiz;
import engine.entity.QuizSolved;
import engine.repository.QuizSolvedRepository;
import engine.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.Objects;


@RestController
public class QuizController {
    public static final String CORRECT_MESSAGE = "Congratulations, you're right!";
    public static final String WRONG_MESSAGE =  "Wrong answer! Please, try again.";

    @Autowired
    private QuizService quizService;

    @Autowired
    private QuizSolvedRepository quizSolvedRepository;

    @PostMapping(value = "/api/quizzes", consumes = "application/json")
    public Quiz addQuiz(@Valid @RequestBody Quiz quiz) {
        MyUserDetails currentUser = (MyUserDetails) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
        quiz.setUser(currentUser.getUser());
        return quizService.addQuiz(quiz);
    }

    @GetMapping(path = "/api/quizzes/{id}")
    public Quiz getQuiz(@PathVariable int id) {
        return quizService.getQuizById(id);
    }

    @GetMapping(path = "/api/quizzes")
    public Page<Quiz> getQuizzes(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy
    ) {
        return quizService.getAllQuizzes(page, pageSize, sortBy);
    }

    @PostMapping(value = "/api/quizzes/{id}/solve", consumes = "application/json")
    public AnswerResponse solveQuiz(@PathVariable int id, @RequestBody Answer answer) {
        Quiz q = quizService.getQuizById(id);
        boolean isCorrect = Objects.equals(answer.getAnswer(), q.getAnswer());
        if (isCorrect) {
            MyUserDetails currentUser = (MyUserDetails) SecurityContextHolder
                    .getContext()
                    .getAuthentication()
                    .getPrincipal();
            QuizSolved quizSolved = new QuizSolved();
            quizSolved.setUser(currentUser.getUser());
            quizSolved.setCompletedAt(LocalDateTime.now());
            quizSolved.setId(id);
            quizSolvedRepository.save(quizSolved);
        }
        return new AnswerResponse(isCorrect, isCorrect ? CORRECT_MESSAGE : WRONG_MESSAGE);
    }

    @DeleteMapping(value = "/api/quizzes/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteQuiz(@PathVariable int id) {
        MyUserDetails currentUser = (MyUserDetails) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
        Quiz quiz = quizService.getQuizById(id);
        if (quiz.getUser().getId() == currentUser.getUser().getId()) {
            quizService.deleteQuiz(id);
        } else {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, currentUser.getUsername() + " is not the author of this quiz");
        }
    }
}
