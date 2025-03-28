package interview_feedback_system.interview_feedback_system.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// import com.fasterxml.jackson.core.JsonProcessingException;
// import com.fasterxml.jackson.databind.ObjectMapper;

import interview_feedback_system.interview_feedback_system.controller.FeedbackSkillRating;
import interview_feedback_system.interview_feedback_system.entity.Feedback;
import interview_feedback_system.interview_feedback_system.repository.FeedbackRepository;
import interview_feedback_system.interview_feedback_system.repository.SkillRatingRepository;
import interview_feedback_system.interview_feedback_system.entity.Interview;
import interview_feedback_system.interview_feedback_system.entity.User;
import interview_feedback_system.interview_feedback_system.service.*;
@Service
public class FeedbackService {

    @Autowired
    private FeedbackRepository feedbackRepository;

       @Autowired
    private SkillRatingRepository skillRatingRepository;

    @Autowired
    private InterviewService interviewService;

    @Autowired
    private UserService userService;

    public Feedback submitFeedback(Feedback feedback) {

        //Step fetch the interview details;
        Interview interview=interviewService.getInterviewById(feedback.getInterview().getId()).
                            orElseThrow(()->new RuntimeException("Interview not found"));


        //Step find the interview email using interviewid;
        User interviwer=userService.findByEmail(interview.getInterviewerEmail()).
                        orElseThrow(()->new RuntimeException("Interviewer not found"));
        
        feedback.setInterviewer(interviwer);
        Feedback savedFeedback = feedbackRepository.save(feedback);
        skillRatingRepository.saveAll(feedback.getSkillRatings());
        return savedFeedback;  
    }
    
    //Get All Feedback;
    public List<Feedback> getAllFeedback(){
        return feedbackRepository.findAll();
    }

    //get feedback by interveiw id;
    public List<Feedback> getFeedbackByInterviewId(Long interviewId){
            // return feedbackRepository.findByInterviewId(interviewId);
            return feedbackRepository.findByInterviewId(interviewId);
    }

    public List<Feedback> getFeedbackByInterviewIdWithSkills(Long interviewId) {
        return feedbackRepository.findByInterviewIdWithSkills(interviewId);
    }

     // Convert JSON String to Java Map (for skill ratings)
    // public String convertMapToJson(Object map) throws JsonProcessingException {
    //     return objectMapper.writeValueAsString(map);
    // }
}
