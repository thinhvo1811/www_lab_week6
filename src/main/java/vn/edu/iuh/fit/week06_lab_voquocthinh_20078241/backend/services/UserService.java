package vn.edu.iuh.fit.week06_lab_voquocthinh_20078241.backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.week06_lab_voquocthinh_20078241.backend.models.User;
import vn.edu.iuh.fit.week06_lab_voquocthinh_20078241.backend.repositories.UserRepository;
import vn.edu.iuh.fit.week06_lab_voquocthinh_20078241.backend.utils.PasswordHashing;

import java.time.LocalDateTime;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User findByEmailAndPassword(String email, String password){
        String passwordHash = PasswordHashing.toSHA1(password);
        return userRepository.findByEmailAndPasswordHash(email, passwordHash);
    }

    public void addUser(User user){
        String passwordHash = PasswordHashing.toSHA1(user.getPasswordHash());
        user.setPasswordHash(passwordHash);
        user.setRegisterAt(LocalDateTime.now());
        userRepository.save(user);
    }

    public void updateLastLoginTime(User user){
        User user1 = userRepository.findById(user.getId()).get();
        user1.setLastLogin(LocalDateTime.now());
        userRepository.save(user1);
    }

    public void updatePassword(User user, String newPassword){
        User user1 = userRepository.findById(user.getId()).get();
        String passwordHash = PasswordHashing.toSHA1(newPassword);
        user1.setPasswordHash(passwordHash);
        userRepository.save(user1);
    }
}
