package interview_feedback_system.interview_feedback_system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import interview_feedback_system.interview_feedback_system.dto.SkillRatingsRequest;
import interview_feedback_system.interview_feedback_system.service.FlaskPredictionService;

@RestController
@RequestMapping("/api")
public class FlaskPredictionController {
    
    @Autowired
    private FlaskPredictionService flaskPredictionService;

    @PostMapping("/get-decision")
    public ResponseEntity<String> getDecision(@RequestBody SkillRatingsRequest request) {
        try {
            // This will call service which will send POST request to Flask
            String decision = flaskPredictionService.getPrediction(request.getSkillRatings());
            return ResponseEntity.ok(decision);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error calling Flask server: " + e.getMessage());
        }
    }
}
