package engine.repository;

import engine.entity.QuizSolved;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface QuizSolvedRepository extends PagingAndSortingRepository<QuizSolved, Integer> {
    @Query(value = "SELECT * FROM quiz_solved qs WHERE qs.user = :userId",
            countQuery = " SELECT count(*) FROM quiz_solved",
            nativeQuery = true)
    Page<QuizSolved> getCompleted(Pageable pageable, @Param("userId") Integer userId);
}
