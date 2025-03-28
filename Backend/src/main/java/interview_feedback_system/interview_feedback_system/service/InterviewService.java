package interview_feedback_system.interview_feedback_system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import interview_feedback_system.interview_feedback_system.entity.Interview;
import interview_feedback_system.interview_feedback_system.entity.User;
import interview_feedback_system.interview_feedback_system.repository.InterviewRepository;
import interview_feedback_system.interview_feedback_system.repository.UserRepository;

import java.lang.foreign.Linker.Option;
import java.util.List;
import java.util.Optional;

@Service
public class InterviewService {
    @Autowired
    private InterviewRepository interviewRepository;

        @Autowired
    private UserRepository userRepository;

     //Create An Interview;
     public Interview createInterview(Interview interview){
         return interviewRepository.save(interview);
     }

     //Get all scheuler interview;
     public List<Interview> getAllInterviews(){
        return interviewRepository.findAll();
     }

     //Get a specific interview by id;
     public Optional<Interview> getInterviewById(Long id){
        return interviewRepository.findById(id);
     }
     
     //Get the list of interviews according to email passe d
     public List<Interview> getInterviewsByInterviewerEmail(String interviewerEmail) {
        return interviewRepository.findByInterviewerEmail(interviewerEmail);
    }
       
     //Delete interview;
     public void deleteInterview(Long id){
        interviewRepository.deleteById(id);
     }

     //Update an interveiw;
     public Interview updateInterview(Long id, Interview updatedInterview) {
      Interview existingInterview = interviewRepository.findById(id)
          .orElseThrow(() -> new RuntimeException("Interview not found"));
  
      // 🔹 Update all fields
      existingInterview.setCandidateName(updatedInterview.getCandidateName());
      existingInterview.setCandidateEmail(updatedInterview.getCandidateEmail());
      existingInterview.setScheduledAt(updatedInterview.getScheduledAt());
      existingInterview.setStatus(updatedInterview.getStatus());
      existingInterview.setInterviewerEmail(updatedInterview.getInterviewerEmail());
      existingInterview.setInterviewerName(updatedInterview.getInterviewerName());
  
      return interviewRepository.save(existingInterview);
  }  

  public Optional<String> getHrManagerEmailByInterviewId(Long interviewId) {
    Optional<Interview> interviewOptional = interviewRepository.findById(interviewId);
    
    if (interviewOptional.isPresent()) {
        Interview interview = interviewOptional.get();
        User hrManager = interview.getHrManager(); // Get HR Manager object
        
        if (hrManager != null) {
            return Optional.of(hrManager.getEmail()); // Get HR Manager's email
        }
    }
    return Optional.empty(); // Return empty if HR Manager not found
}





}
