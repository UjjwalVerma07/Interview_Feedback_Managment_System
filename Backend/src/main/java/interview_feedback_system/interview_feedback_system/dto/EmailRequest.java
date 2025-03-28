package interview_feedback_system.interview_feedback_system.dto;

public class EmailRequest {
    private String interviewerEmail;
    private String candidateName;
    private String scheduledAt;
    private Long interview_id;

    // Getters and Setters
    public String getInterviewerEmail() { return interviewerEmail; }
    public void setInterviewerEmail(String interviewerEmail) { this.interviewerEmail = interviewerEmail; }

    public String getCandidateName() { return candidateName; }
    public void setCandidateName(String candidateName) { this.candidateName = candidateName; }

    public String getScheduledAt() { return scheduledAt; }
    public void setScheduledAt(String scheduledAt) { this.scheduledAt = scheduledAt; }

    public Long getInterviewId(){return interview_id;}
    public void setInterviewId(Long Id){this.interview_id=Id;}

}
