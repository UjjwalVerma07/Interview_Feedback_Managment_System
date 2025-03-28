package interview_feedback_system.interview_feedback_system.controller;
import lombok.Getter;
import lombok.Setter;
import interview_feedback_system.interview_feedback_system.entity.Feedback;
import jakarta.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "feedback_skill_ratings")
public class FeedbackSkillRating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "feedback_id", nullable = false)
    private Feedback feedback;  

    @Column(nullable = false)
    private String skillName;  

    @Column(nullable = false)
    private String rating;  

    @Column(nullable = true)
    private String topicsUsed;

    @Column(columnDefinition = "TEXT")
    private String comments;
}
