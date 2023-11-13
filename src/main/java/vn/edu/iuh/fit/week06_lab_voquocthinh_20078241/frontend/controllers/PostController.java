package vn.edu.iuh.fit.week06_lab_voquocthinh_20078241.frontend.controllers;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import vn.edu.iuh.fit.week06_lab_voquocthinh_20078241.backend.models.Post;
import vn.edu.iuh.fit.week06_lab_voquocthinh_20078241.backend.models.PostComment;
import vn.edu.iuh.fit.week06_lab_voquocthinh_20078241.backend.models.User;
import vn.edu.iuh.fit.week06_lab_voquocthinh_20078241.backend.repositories.PostRepository;
import vn.edu.iuh.fit.week06_lab_voquocthinh_20078241.backend.services.PostCommentService;
import vn.edu.iuh.fit.week06_lab_voquocthinh_20078241.backend.services.PostService;

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
                                     @RequestParam("size") Optional<Integer> size) {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(10);

        Page<Post> postPage = postService.findAll(currentPage - 1, pageSize, "id", "asc");

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

        Page<PostComment> commentPage = postCommentService.findByPostId(currentPage - 1, pageSize, id);
        ModelAndView modelAndView = new ModelAndView();
        Post post = postRepository.findById(id).get();
        PostComment postComment = new PostComment();
        User user = (User) session.getAttribute("user-account");
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
}
