package interview_feedback_system.interview_feedback_system.dto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupRequest {
    private String name;
    private String email;
    private String password;
    private String role;
}

