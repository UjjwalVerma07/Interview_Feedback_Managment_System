package interview_feedback_system.interview_feedback_system.service;

import interview_feedback_system.interview_feedback_system.dto.LoginRequest;
import interview_feedback_system.interview_feedback_system.dto.LoginResponse;
import interview_feedback_system.interview_feedback_system.dto.SignupRequest;
import interview_feedback_system.interview_feedback_system.entity.Role;
import interview_feedback_system.interview_feedback_system.entity.User;
import interview_feedback_system.interview_feedback_system.repository.UserRepository;
import interview_feedback_system.interview_feedback_system.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

//There may exist error in this code:
     public void registerUser(SignupRequest request) {
        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(Role.valueOf(request.getRole()));

        userRepository.save(user);
    }


    public LoginResponse login(LoginRequest loginRequest) {
        try {
            System.out.println("Attempting login for: " + loginRequest.getEmail());
            
            Optional<User> user = userRepository.findByEmail(loginRequest.getEmail());
    
            if (user.isPresent()) {
                System.out.println("User found in database: " + user.get().getEmail());
                System.out.println("Stored Hashed Password: " + user.get().getPassword());
                System.out.println("Input Password: " + loginRequest.getPassword());
                System.out.println("To check if encoding is working or not : "+passwordEncoder.encode(user.get().getPassword()));
                System.out.println("For Krishna: "+passwordEncoder.encode("Krishna@123"));

                System.out.println(passwordEncoder.encode("Ujjwal"));
                System.out.println("Alice"+passwordEncoder.encode("securepassword"));
                if (passwordEncoder.matches(loginRequest.getPassword(), user.get().getPassword())) {
                    String token = jwtUtil.generateToken(loginRequest.getEmail());
                    System.out.println("Login successful! JWT Token generated.");
                    return new LoginResponse(token, "Login successful");
                } else {
                    System.out.println("Incorrect password for: " + loginRequest.getEmail());
                    throw new RuntimeException("Invalid credentials from Ujjwal");
                }
            } else {
                System.out.println("User not found: " + loginRequest.getEmail());
                throw new RuntimeException("User not found");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Login failed: " + e.getMessage());
        }
    }
    
}