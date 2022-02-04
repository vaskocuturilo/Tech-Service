package site.testengineer.techInterview.entity;

import javax.persistence.*;

@Entity
@Table(name = "exercises", schema = "dbo")
public class Exercises {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String description;
    private Boolean completed = Boolean.FALSE;

    public Exercises() {
    }

    public Exercises(Long id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
