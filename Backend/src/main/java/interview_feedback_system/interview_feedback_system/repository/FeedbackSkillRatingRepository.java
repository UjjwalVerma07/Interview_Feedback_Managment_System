package interview_feedback_system.interview_feedback_system.repository;

import java.util.List;

import interview_feedback_system.interview_feedback_system.controller.FeedbackSkillRating;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeedbackSkillRatingRepository extends JpaRepository<FeedbackSkillRating, Long> {
    List<FeedbackSkillRating> findByFeedbackId(Long feedbackId);
}
