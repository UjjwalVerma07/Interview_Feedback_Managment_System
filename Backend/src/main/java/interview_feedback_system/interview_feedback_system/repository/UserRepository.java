package interview_feedback_system.interview_feedback_system.repository;
import interview_feedback_system.interview_feedback_system.entity.User;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findByEmail(String email);
    
}
