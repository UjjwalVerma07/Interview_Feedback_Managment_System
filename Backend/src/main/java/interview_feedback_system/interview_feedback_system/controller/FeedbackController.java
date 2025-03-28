package interview_feedback_system.interview_feedback_system.controller;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import interview_feedback_system.interview_feedback_system.service.FeedbackService;
import interview_feedback_system.interview_feedback_system.entity.Decision;
import interview_feedback_system.interview_feedback_system.entity.Feedback;
import interview_feedback_system.interview_feedback_system.entity.Interview;
import interview_feedback_system.interview_feedback_system.entity.SkillRating;
import interview_feedback_system.interview_feedback_system.service.InterviewService;
import interview_feedback_system.interview_feedback_system.repository.UserRepository;
import interview_feedback_system.interview_feedback_system.entity.User;

@RestController
@RequestMapping("/feedback")
public class FeedbackController {
    @Autowired
    private FeedbackService feedbackService;

    @Autowired
    private InterviewService interviewService;

    @Autowired
    private UserRepository userRepository;     

    @GetMapping
    public ResponseEntity<List<Feedback>> getAllFeedback(){
        return ResponseEntity.ok(feedbackService.getAllFeedback());
    }

     // Get feedback with skill ratings for a specific interview
     @GetMapping("/interview/{interviewId}")
     public ResponseEntity<List<Feedback>> getFeedbackByInterviewId(@PathVariable Long interviewId) {
         List<Feedback> feedbackList = feedbackService.getFeedbackByInterviewIdWithSkills(interviewId);
         return ResponseEntity.ok(feedbackList);
     }

    @PostMapping("/{interviewId}")
public ResponseEntity<?> submitFeedback(@PathVariable Long interviewId, @RequestBody Map<String, Object> requestBody) {
    try {
        System.out.println("Received request: " + requestBody);

        if (!requestBody.containsKey("interviewer_id")) {
            return ResponseEntity.badRequest().body("Error: Interviewer ID is missing.");
        }

        // Fetch interview details
        Interview interview = interviewService.getInterviewById(interviewId)
                .orElseThrow(() -> new RuntimeException("Interview not found"));

        // Fetch interviewer
        Long interviewerId = Long.valueOf(requestBody.get("interviewer_id").toString());
        User interviewer = userRepository.findById(interviewerId)
                .orElseThrow(() -> new RuntimeException("Interviewer not found"));

        // Create Feedback entity
        Feedback feedback = new Feedback();
        feedback.setInterview(interview);
        feedback.setInterviewer(interviewer);
        feedback.setDecision(Decision.valueOf(requestBody.get("decision").toString()));
        feedback.setComments(requestBody.get("comments") != null ? requestBody.get("comments").toString() : "");

        // Convert skill_ratings to SkillRating entities
        @SuppressWarnings("unchecked")
        Map<String, Map<String, String>> skillRatingsMap = (Map<String, Map<String, String>>) requestBody.get("skill_ratings");

        if (skillRatingsMap == null || skillRatingsMap.isEmpty()) {
            return ResponseEntity.badRequest().body("Error: Skill Ratings are missing.");
        }

        List<SkillRating> skillRatings = new ArrayList<>();
        for (Map.Entry<String, Map<String, String>> entry : skillRatingsMap.entrySet()) {
            String skillName = entry.getKey();
            Map<String, String> details = entry.getValue();

            SkillRating skillRating = new SkillRating();
            skillRating.setFeedback(feedback);
            skillRating.setSkillName(skillName);
            skillRating.setRating(details.get("rating"));
            skillRating.setTopics(details.get("topics"));
            skillRating.setComments(details.get("comments"));

            skillRatings.add(skillRating);
        }

        feedback.setSkillRatings(skillRatings);
        Feedback savedFeedback = feedbackService.submitFeedback(feedback);
        return ResponseEntity.ok(savedFeedback);

    } catch (Exception e) {
        return ResponseEntity.badRequest().body("Error: " + e.getMessage());
    }
}

    
}
