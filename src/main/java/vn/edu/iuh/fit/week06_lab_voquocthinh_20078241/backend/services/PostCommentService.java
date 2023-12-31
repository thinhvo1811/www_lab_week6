package vn.edu.iuh.fit.week06_lab_voquocthinh_20078241.backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.week06_lab_voquocthinh_20078241.backend.models.PostComment;
import vn.edu.iuh.fit.week06_lab_voquocthinh_20078241.backend.repositories.PostCommentRepository;

import java.util.Collections;
import java.util.List;

@Service
public class PostCommentService {
    @Autowired
    private PostCommentRepository postCommentRepository;

    public Page<PostComment> findByPostId(int pageNo, int pageSize, long id, long userID) {
        int startItem = pageNo * pageSize;
        List<PostComment> list;
        List<PostComment> postComments = postCommentRepository.findByPost_IdAndPostCommentIsNull(id, userID);

        if (postComments.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, postComments.size());
            list = postComments.subList(startItem, toIndex);
        }

        Page<PostComment> commentPage
                = new PageImpl<>(list, PageRequest.of(pageNo, pageSize), postComments.size());

        return commentPage;
    }

    public Page<PostComment> findByPostCommentId(int pageNo, int pageSize, long id, long userID) {
        int startItem = pageNo * pageSize;
        List<PostComment> list;
        List<PostComment> postComments = postCommentRepository.findByPostComment_Id(id, userID);

        if (postComments.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, postComments.size());
            list = postComments.subList(startItem, toIndex);
        }

        Page<PostComment> commentPage
                = new PageImpl<>(list, PageRequest.of(pageNo, pageSize), postComments.size());

        return commentPage;
    }
}
