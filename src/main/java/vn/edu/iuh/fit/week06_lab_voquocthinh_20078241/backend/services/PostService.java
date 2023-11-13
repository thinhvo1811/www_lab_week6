package vn.edu.iuh.fit.week06_lab_voquocthinh_20078241.backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.week06_lab_voquocthinh_20078241.backend.models.Post;
import vn.edu.iuh.fit.week06_lab_voquocthinh_20078241.backend.models.PostComment;
import vn.edu.iuh.fit.week06_lab_voquocthinh_20078241.backend.models.User;
import vn.edu.iuh.fit.week06_lab_voquocthinh_20078241.backend.repositories.PostRepository;

import java.util.Collections;
import java.util.List;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;

    public Page<Post> findAll(int pageNo, int pageSize, String sortBy, String sortDirection) {
        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), sortBy);
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        return postRepository.findAll(pageable);
    }

    public Page<Post> findByPublishedIsTrue(int pageNo, int pageSize, long userID) {
        int startItem = pageNo * pageSize;
        List<Post> list;
        List<Post> posts = postRepository.findByPublishedIsTrue(userID);

        if (posts.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, posts.size());
            list = posts.subList(startItem, toIndex);
        }

        Page<Post> postPage
                = new PageImpl<>(list, PageRequest.of(pageNo, pageSize), posts.size());

        return postPage;
    }

    public Page<Post> findByUser(int pageNo, int pageSize, User user) {
        int startItem = pageNo * pageSize;
        List<Post> list;
        List<Post> posts = postRepository.findByUserAndPostIsNull(user);

        if (posts.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, posts.size());
            list = posts.subList(startItem, toIndex);
        }

        Page<Post> postPage
                = new PageImpl<>(list, PageRequest.of(pageNo, pageSize), posts.size());

        return postPage;
    }

    public Page<Post> findByPostID(int pageNo, int pageSize, long id) {
        int startItem = pageNo * pageSize;
        List<Post> list;
        List<Post> posts = postRepository.findByPost_Id(id);

        if (posts.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, posts.size());
            list = posts.subList(startItem, toIndex);
        }

        Page<Post> postPage
                = new PageImpl<>(list, PageRequest.of(pageNo, pageSize), posts.size());

        return postPage;
    }
}
