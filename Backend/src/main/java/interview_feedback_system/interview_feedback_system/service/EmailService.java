package interview_feedback_system.interview_feedback_system.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import interview_feedback_system.interview_feedback_system.entity.Interview;

@Service
public class EmailService {
     @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private InterviewService interviewService;

    public void sendInterviewMail(String interviewerEmail, String candidateName, String scheduledAt,Long Interview_id) {
        SimpleMailMessage message = new SimpleMailMessage();
        Optional<String> hrManagerEmailOptional = interviewService.getHrManagerEmailByInterviewId(Interview_id);
         
    if (hrManagerEmailOptional.isPresent()) {
        String hrManagerEmail = hrManagerEmailOptional.get();
        message.setFrom(hrManagerEmail);
        message.setTo(interviewerEmail);
        message.setSubject("Upcoming Interview Notification");
        message.setText("Dear Interviewer,\n\nYou have an interview scheduled with candidate " 
                + candidateName + " on " + scheduledAt + ".\n\nBest Regards,\nHR Team");

        mailSender.send(message);
}
 else{
    System.out.println(hrManagerEmailOptional);
    System.out.println("Id is not present");
 }
    }
}