package interview_feedback_system.interview_feedback_system.dto;
import lombok.Data;
import java.util.Map;
@Data
public class SkillRatingsRequest {
      private Map<String,String>skillRatings;
}
