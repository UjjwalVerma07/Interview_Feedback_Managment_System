package interview_feedback_system.interview_feedback_system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import interview_feedback_system.interview_feedback_system.entity.Interview;
import interview_feedback_system.interview_feedback_system.service.InterviewService;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/interviews")
public class InterviewController {
    @Autowired
    private InterviewService interviewService;

    @PostMapping
    public ResponseEntity<Interview> createInterview(@RequestBody Interview interview){
        return ResponseEntity.ok(interviewService.createInterview(interview));
    }

    @GetMapping
    public ResponseEntity<List<Interview>> getAllInterviews(){
        return ResponseEntity.ok(interviewService.getAllInterviews());
    }

    @GetMapping("/interviewer/{email}")
public ResponseEntity<List<Interview>> getInterviewsByInterviewerEmail(@PathVariable String email) {
    return ResponseEntity.ok(interviewService.getInterviewsByInterviewerEmail(email));
}


    @GetMapping("/{id}")
    public ResponseEntity<Optional<Interview>> getInterviewById(@PathVariable Long id){
        return ResponseEntity.ok(interviewService.getInterviewById(id));
    }

    @PutMapping("/{id}")
public ResponseEntity<Interview> updateInterview(
        @PathVariable Long id, 
        @RequestBody Interview updatedInterview) {
    
    Interview interview = interviewService.updateInterview(id, updatedInterview);
    return ResponseEntity.ok(interview);
}

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteInterview(@PathVariable Long id){
        interviewService.deleteInterview(id);
        return ResponseEntity.ok("Interview Deleted Successfully");
    }
}
