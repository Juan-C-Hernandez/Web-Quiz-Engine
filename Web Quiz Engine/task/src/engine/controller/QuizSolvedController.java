package engine.controller;

import engine.MyUserDetails;
import engine.entity.QuizSolved;
import engine.repository.QuizSolvedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QuizSolvedController {
    @Autowired
    QuizSolvedRepository quizSolvedRepository;

    @GetMapping(path = "/api/quizzes/completed")
    public Page<QuizSolved> getCompleted(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(defaultValue = "completed_at") String sortBy
    ) {
        MyUserDetails currentUser = (MyUserDetails) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
        return quizSolvedRepository.getCompleted(PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, sortBy)),
                currentUser.getUser().getId());
    }
}
