package interview_feedback_system.interview_feedback_system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import interview_feedback_system.interview_feedback_system.dto.EmailRequest;
import interview_feedback_system.interview_feedback_system.service.EmailService;

@RestController
@RequestMapping("/email")
public class EmailController {
    @Autowired
    private EmailService emailService;

    @PostMapping("/send")
    public ResponseEntity<String> sendInterviewMail(@RequestBody EmailRequest request) {
        emailService.sendInterviewMail(request.getInterviewerEmail(), request.getCandidateName(), request.getScheduledAt(),request.getInterviewId());
        return ResponseEntity.ok("Email sent successfully.");
    }
}
