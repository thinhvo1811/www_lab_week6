package vn.edu.iuh.fit.week06_lab_voquocthinh_20078241.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.edu.iuh.fit.week06_lab_voquocthinh_20078241.backend.models.PostComment;

public interface PostCommentRepository extends JpaRepository<PostComment, Long> {
}
