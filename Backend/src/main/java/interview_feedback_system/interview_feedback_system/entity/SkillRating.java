package interview_feedback_system.interview_feedback_system.entity;

import org.hibernate.annotations.ManyToAny;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.annotation.Generated;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Getter
@Setter
@Table(name="skill_ratings")
public class SkillRating {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long Id;
    
    @ManyToOne
    @JoinColumn(name="feedback_id",nullable =false)
    @JsonBackReference  // Prevents circular dependency
    private Feedback feedback;
    
    @Column(nullable=false)
    private String skillName;

    @Column(nullable=false)
    private String rating;

    @Column(nullable = true)
    private String topics;

    @Column(nullable = true, columnDefinition = "TEXT")
    private String comments;
}
