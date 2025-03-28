package interview_feedback_system.interview_feedback_system.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name="interviews")
public class Interview {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String candidateName;
    @Column(nullable = false)
    private String candidateEmail;
    @Column(nullable=false)
    private LocalDateTime scheduledAt;
     
    @Enumerated(EnumType.STRING)
    private InterviewStatus status;

    @Column(nullable=true,unique = false)
    private String interviewerEmail;

    @Column(nullable = true)
    private String interviewerName;
    
    @ManyToOne
    @JoinColumn(name="hr_manager_id")
    private User hrManager;
 
}
