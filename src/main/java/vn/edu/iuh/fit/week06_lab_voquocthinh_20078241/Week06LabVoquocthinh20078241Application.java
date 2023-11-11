package vn.edu.iuh.fit.week06_lab_voquocthinh_20078241;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import vn.edu.iuh.fit.week06_lab_voquocthinh_20078241.backend.models.Post;
import vn.edu.iuh.fit.week06_lab_voquocthinh_20078241.backend.models.PostComment;
import vn.edu.iuh.fit.week06_lab_voquocthinh_20078241.backend.models.User;
import vn.edu.iuh.fit.week06_lab_voquocthinh_20078241.backend.repositories.PostCommentRepository;
import vn.edu.iuh.fit.week06_lab_voquocthinh_20078241.backend.repositories.PostRepository;
import vn.edu.iuh.fit.week06_lab_voquocthinh_20078241.backend.repositories.UserRepository;

import java.time.LocalDateTime;
import java.util.Random;

@SpringBootApplication
public class Week06LabVoquocthinh20078241Application {

    public static void main(String[] args) {
        SpringApplication.run(Week06LabVoquocthinh20078241Application.class, args);
    }


    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private PostCommentRepository postCommentRepository;
//    @Bean
    CommandLineRunner initData() {
        return args -> {
            Random rnd = new Random();
            for (int i = 7; i < 49; i++) {
//                User user = new User("Name #" + i, "Văn", "Nguyễn", rnd.nextLong(1111111111L, 9999999999L) + "",
//                        "email_" + i + "@gmail.com",
//                        "123456",
//                        LocalDateTime.now(),
//                        null,
//                        "intro #" + i,
//                        "profile #" + i,
//                        null,
//                        null
//                        );
//                userRepository.save(user);
//
//                Post post = new Post(user, null, "Title #" + i, "Meta Title #" + i,
//                        "Sumary #"+i, true, LocalDateTime.now(), null, null, "Content #" + i,
//                        null, null);
//
//                postRepository.save(post);
                User user = userRepository.findById(Long.parseLong(String.valueOf(i))).get();
                Post post = postRepository.findById(Long.parseLong(String.valueOf(i))).get();
                PostComment comment = new PostComment(post, null, user, "title #" + i, true, LocalDateTime.now(),
                        null, "Content #" + i, null);
                postCommentRepository.save(comment);
            }
        };
    }
}
