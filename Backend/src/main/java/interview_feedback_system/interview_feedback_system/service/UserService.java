package interview_feedback_system.interview_feedback_system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import interview_feedback_system.interview_feedback_system.entity.Interview;
import interview_feedback_system.interview_feedback_system.entity.User;
import interview_feedback_system.interview_feedback_system.repository.UserRepository;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    //Create User;
    public User createUser(User user){
            return userRepository.save(user);
    }

    //Get All User;
    public List <User>getAllUsers(){
        return userRepository.findAll();
    }

    //Get By User Id;
    public Optional<User>getUserById(Long id){
        return userRepository.findById(id);
    }

    //Delete User;
    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }
    
    public Optional<User>findByEmail(String email){
        return userRepository.findByEmail(email);
    }


}
