package interview_feedback_system.interview_feedback_system.repository;
import interview_feedback_system.interview_feedback_system.entity.Interview;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface InterviewRepository extends JpaRepository<Interview,Long> {
    List<Interview> findByInterviewerEmail(String interviewerEmail);
    
}
