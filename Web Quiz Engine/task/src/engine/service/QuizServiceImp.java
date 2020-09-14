package engine.service;

import engine.entity.Quiz;
import engine.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class QuizServiceImp implements QuizService{
    @Autowired
    private QuizRepository quizRepository;

    @Override
    public Page<Quiz> getAllQuizzes(Integer page, Integer pageSize, String sortBy) {
        Pageable pages = PageRequest.of(page, pageSize, Sort.by(sortBy));
        Page<Quiz> pageResult = quizRepository.findAll(pages);
        //return pageResult.getContent();
        return pageResult;
    }

    @Override
    public Quiz getQuizById(int id) {
        return quizRepository.findById(id).get();
    }

    @Override
    public Quiz addQuiz(Quiz quiz) {
        return quizRepository.save(quiz);
    }

    @Override
    public long size() {
        return quizRepository.count();
    }

    @Override
    public void deleteQuiz(int id) {
        quizRepository.deleteById(id);
    }
}
