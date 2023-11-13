package vn.edu.iuh.fit.week06_lab_voquocthinh_20078241.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import vn.edu.iuh.fit.week06_lab_voquocthinh_20078241.backend.models.Post;
import vn.edu.iuh.fit.week06_lab_voquocthinh_20078241.backend.models.User;

import java.util.List;

public interface PostRepository extends JpaRepository<Post,Long> {
    List<Post> findByUserAndPostIsNull(User user);
    @Query("select p from Post p where p.id not in (select p2.id from Post p2 where p2.user.id != :userID and p2.published = false)")
    List<Post> findByPublishedIsTrue(long userID);

    List<Post> findByPost_Id(long id);
}
