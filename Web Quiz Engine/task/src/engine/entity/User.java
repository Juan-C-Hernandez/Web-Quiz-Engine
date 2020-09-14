package engine.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.crypto.bcrypt.BCrypt;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "user_id_generator")
    private int id;

    @Email
    @Pattern(regexp = ".+@.+\\..+", message = "Incorrect email format")
    @Column(name = "username", unique = true)
    @NotNull(message = "User can not be null")
    private String email;

    @Length(min = 5, message = "password must be at least 5 chars long")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<Quiz> quizzes;

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<QuizSolved> quizSolved;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private boolean active;

    public User() {
        this.active = true;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password.length() >= 5 ? BCrypt.hashpw(password, BCrypt.gensalt()) : "";
    }

    @Override
    public String toString() {
        return "Username: " + email + "\n"
                + "Id: " + id;
    }
}