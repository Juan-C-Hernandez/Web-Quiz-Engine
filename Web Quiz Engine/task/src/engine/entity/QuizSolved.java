package engine.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class QuizSolved {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "quiz_solved_id_gen")
    @Column(name = "solved_id")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private int solvedId;

    @ManyToOne()
    @JoinColumn(name = "user", nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private User user;

    private LocalDateTime completedAt;

    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public QuizSolved() {}

    public int getSolvedId() {
        return solvedId;
    }

    public void setSolvedId(int solvedId) {
        this.solvedId = solvedId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getCompletedAt() {
        return completedAt;
    }

    public void setCompletedAt(LocalDateTime completedAt) {
        this.completedAt = completedAt;
    }
}
