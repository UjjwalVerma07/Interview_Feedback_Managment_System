package interview_feedback_system.interview_feedback_system.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import interview_feedback_system.interview_feedback_system.entity.SkillRating;

@Repository
public interface SkillRatingRepository extends JpaRepository<SkillRating, Long> {
    List<SkillRating> findByFeedbackId(Long feedbackId);
}
