package engine.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import engine.entity.User;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.List;

@Entity
public class Quiz {
    private final static String NOT_EMPTY_MESSAGE = "must not be empty!";

    @NotBlank(message = "Title " + NOT_EMPTY_MESSAGE)
    @Column(nullable = false)
    private String title;

    @NotBlank(message = "Text " + NOT_EMPTY_MESSAGE)
    @Column(nullable = false)
    private String text;

    @NotNull(message = "Options can not be null")
    @Size(min = 2, message = "There must be at least 2 options")
    @Column
    @ElementCollection
    private List<String> options;

    @Column
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private HashSet<Integer> answer;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "quiz_id_generator")
    private int id;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToOne()
    @JoinColumn(name = "user", nullable = false)
    private User user;

    public Quiz(String title, String text, List<String> options, HashSet<Integer> answer) {
        this.title = title;
        this.text = text;
        this.options = options;
        this.answer = answer;
    }

    public Quiz(){
    }

    public String getText() {
        return text;
    }

    public String getTitle() {
        return title;
    }

    public List<String> getOptions() {
        return options;
    }

    public HashSet<Integer> getAnswer() {
        return answer == null ? new HashSet<>() : answer;
    }

    public int getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setAnswer(HashSet<Integer> answer) {
        this.answer = answer;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
