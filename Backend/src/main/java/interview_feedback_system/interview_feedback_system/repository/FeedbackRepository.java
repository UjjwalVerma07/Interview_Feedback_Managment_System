package interview_feedback_system.interview_feedback_system.repository;
import interview_feedback_system.interview_feedback_system.entity.Feedback;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface FeedbackRepository extends JpaRepository<Feedback,Long> {
    List<Feedback> findByInterviewId(Long interviewId);

   // Fetch feedback along with skill ratings
   @Query("SELECT f FROM Feedback f LEFT JOIN FETCH f.skillRatings WHERE f.interview.id = :interviewId")
   List<Feedback> findByInterviewIdWithSkills(@Param("interviewId") Long interviewId);
}
