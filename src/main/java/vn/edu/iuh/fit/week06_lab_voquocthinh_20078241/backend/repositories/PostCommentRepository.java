package vn.edu.iuh.fit.week06_lab_voquocthinh_20078241.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import vn.edu.iuh.fit.week06_lab_voquocthinh_20078241.backend.models.PostComment;

import java.util.List;

public interface PostCommentRepository extends JpaRepository<PostComment, Long> {
//    SELECT * FROM post_comment c WHERE c.post_id =1 AND c.parent_id is NULL AND c.id NOT IN (SELECT pc.id FROM post_comment pc WHERE pc.post_id = 1 AND pc.user_id != 1 AND pc.published = 0)

    @Query("select pc from PostComment pc where pc.post.id = :id and pc.postComment is null and pc.id not in (select pc2.id from PostComment pc2 where pc2.post.id = :id and pc2.user.id != :userID and pc2.published = false)")
    List<PostComment> findByPost_IdAndPostCommentIsNull(long id, long userID);
    @Query("select pc from PostComment pc where pc.postComment.id = :id and pc.id not in (select pc2.id from PostComment pc2 where pc2.postComment.id = :id and pc2.user.id != :userID and pc2.published = false)")
    List<PostComment> findByPostComment_Id(long id, long userID);
}
