package vn.edu.iuh.fit.week06_lab_voquocthinh_20078241.frontend.controllers;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import vn.edu.iuh.fit.week06_lab_voquocthinh_20078241.backend.models.Post;
import vn.edu.iuh.fit.week06_lab_voquocthinh_20078241.backend.models.PostComment;
import vn.edu.iuh.fit.week06_lab_voquocthinh_20078241.backend.models.User;
import vn.edu.iuh.fit.week06_lab_voquocthinh_20078241.backend.repositories.PostRepository;
import vn.edu.iuh.fit.week06_lab_voquocthinh_20078241.backend.services.PostCommentService;
import vn.edu.iuh.fit.week06_lab_voquocthinh_20078241.backend.services.PostService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class PostController {
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private PostService postService;
    @Autowired
    private PostCommentService postCommentService;

    @GetMapping("/posts")
    public String showPostListPaging(Model model,
                                     @RequestParam("page") Optional<Integer> page,
                                     @RequestParam("size") Optional<Integer> size,
                                     HttpSession session) {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(10);

        User user = (User) session.getAttribute("user-account");
        Page<Post> postPage = postService.findByPublishedIsTrue(currentPage - 1, pageSize, user.getId());

        model.addAttribute("postPage", postPage);
        int totalPage = postPage.getTotalPages();
        if (totalPage > 0){
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPage)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
        return "posts/posts";
    }


    @GetMapping("/posts/detail/{id}")
    public ModelAndView showPostDetail(
            @PathVariable("id") long id,
            @RequestParam("page") Optional<Integer> page,
            @RequestParam("size") Optional<Integer> size,
            HttpSession session){
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(5);

        User user = (User) session.getAttribute("user-account");
        Page<PostComment> commentPage = postCommentService.findByPostId(currentPage - 1, pageSize, id, user.getId());
        ModelAndView modelAndView = new ModelAndView();
        Post post = postRepository.findById(id).get();
        PostComment postComment = new PostComment();
        modelAndView.addObject("post", post);
        modelAndView.addObject("postComment", postComment);
        modelAndView.addObject("commentPage", commentPage);
        modelAndView.addObject("user", user);
        int totalPage = commentPage.getTotalPages();
        if (totalPage > 0){
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPage)
                    .boxed()
                    .collect(Collectors.toList());
            modelAndView.addObject("pageNumbers", pageNumbers);
        }
        modelAndView.setViewName("posts/postDetail");
        return modelAndView;
    }

    @GetMapping("/users/my-posts")
    public ModelAndView showMyPosts(
            @RequestParam("page") Optional<Integer> page,
            @RequestParam("size") Optional<Integer> size,
            HttpSession session){
        ModelAndView modelAndView = new ModelAndView();

        int currentPage = page.orElse(1);
        int pageSize = size.orElse(5);

        User user = (User) session.getAttribute("user-account");
        Page<Post> postPage = postService.findByUser(currentPage - 1, pageSize, user);
        modelAndView.addObject("postPage", postPage);
        int totalPage = postPage.getTotalPages();
        if (totalPage > 0){
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPage)
                    .boxed()
                    .collect(Collectors.toList());
            modelAndView.addObject("pageNumbers", pageNumbers);
        }
        modelAndView.setViewName("posts/myPosts");
        return modelAndView;
    }

    @GetMapping("/users/my-posts/delete")
    public ModelAndView deleteComment(
            @RequestParam("id") long postID
    ){
        ModelAndView modelAndView = new ModelAndView();
        Post parentPost = postRepository.findById(postID).get().getPost();
        if(parentPost==null){
            modelAndView.setViewName("redirect:/users/my-posts");
        }
        else {
            modelAndView.setViewName("redirect:/users/my-posts/related-posts?id="+parentPost.getId());
        }
        postRepository.deleteById(postID);

        return modelAndView;
    }

    @GetMapping("/users/my-posts/related-posts")
    public ModelAndView showRelatedPosts(
            @RequestParam("page") Optional<Integer> page,
            @RequestParam("size") Optional<Integer> size,
            @RequestParam("id") long id){
        ModelAndView modelAndView = new ModelAndView();

        int currentPage = page.orElse(1);
        int pageSize = size.orElse(5);

        Page<Post> postPage = postService.findByPostID(currentPage - 1, pageSize, id);
        modelAndView.addObject("postPage", postPage);
        modelAndView.addObject("postID", id);
        int totalPage = postPage.getTotalPages();
        if (totalPage > 0){
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPage)
                    .boxed()
                    .collect(Collectors.toList());
            modelAndView.addObject("pageNumbers", pageNumbers);
        }
        modelAndView.setViewName("posts/relatedPosts");
        return modelAndView;
    }

    @GetMapping("/users/show-form-add-post")
    public ModelAndView showFormAddPost(
    ){
        ModelAndView modelAndView = new ModelAndView();
        Post post = new Post();
        modelAndView.addObject("post",post);
        modelAndView.setViewName("posts/showFormAddPost");
        return modelAndView;
    }

    @PostMapping("/users/add-post")
    public ModelAndView addPost(
            @ModelAttribute("post") Post post,
            HttpSession session
    ){
        ModelAndView modelAndView = new ModelAndView();
        User user = (User) session.getAttribute("user-account");
        post.setUser(user);
        post.setPublished(true);
        post.setPublishedAt(LocalDateTime.now());
        post.setCreatedAt(LocalDateTime.now());
        postRepository.save(post);
        modelAndView.setViewName("redirect:/users/my-posts");
        return modelAndView;
    }

    @GetMapping("/users/my-posts/show-form-add-related-post")
    public ModelAndView showFormAddPost(
            @RequestParam("id") long postID
    ){
        ModelAndView modelAndView = new ModelAndView();
        Post post = new Post();
        modelAndView.addObject("post",post);
        modelAndView.addObject("postID",postID);
        modelAndView.setViewName("posts/showFormAddRelatedPost");
        return modelAndView;
    }

    @PostMapping("/users/my-posts/add-related-post")
    public ModelAndView addRelatedPost(
            @ModelAttribute("post") Post post,
            @RequestParam("postID") long postID,
            HttpSession session
    ){
        ModelAndView modelAndView = new ModelAndView();
        User user = (User) session.getAttribute("user-account");
        post.setUser(user);
        post.setPost(postRepository.findById(postID).get());
        post.setPublished(true);
        post.setPublishedAt(LocalDateTime.now());
        post.setCreatedAt(LocalDateTime.now());
        postRepository.save(post);
        modelAndView.setViewName("redirect:/users/my-posts/related-posts?id="+postID);
        return modelAndView;
    }
}
