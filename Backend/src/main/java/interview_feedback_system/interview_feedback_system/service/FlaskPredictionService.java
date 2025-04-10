package interview_feedback_system.interview_feedback_system.service;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;

import java.util.HashMap;
import java.util.Map;

@Service
public class FlaskPredictionService {
    private final String FLASK_URL = "http://localhost:5000/predict";  // Flask server address

    public String getPrediction(Map<String, String> skillRatings) {
    RestTemplate restTemplate = new RestTemplate();

    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);

    // 👇 wrap skillRatings inside a parent map
    Map<String, Object> payload = new HashMap<>();
    payload.put("skillRatings", skillRatings);

    HttpEntity<Map<String, Object>> request = new HttpEntity<>(payload, headers);

    ResponseEntity<Map> response = restTemplate.postForEntity(FLASK_URL, request, Map.class);

    if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
        Map<String, Object> body = response.getBody();
        return (String) body.get("decision");
    } else {
        throw new RuntimeException("Failed to get decision from Flask server.");
    }
}
}
