package vn.edu.iuh.fit.week06_lab_voquocthinh_20078241.frontend.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import vn.edu.iuh.fit.week06_lab_voquocthinh_20078241.backend.models.PostComment;
import vn.edu.iuh.fit.week06_lab_voquocthinh_20078241.backend.models.User;
import vn.edu.iuh.fit.week06_lab_voquocthinh_20078241.backend.repositories.PostCommentRepository;
import vn.edu.iuh.fit.week06_lab_voquocthinh_20078241.backend.repositories.PostRepository;
import vn.edu.iuh.fit.week06_lab_voquocthinh_20078241.backend.services.PostCommentService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class PostCommentController {
    @Autowired
    private PostCommentRepository postCommentRepository;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private PostCommentService postCommentService;

    @GetMapping("/posts/comments/child-comments/{id}")
    public ModelAndView showChildComments(
                            @PathVariable("id") long id,
                            @RequestParam("postID") long postID,
                            @RequestParam("page") Optional<Integer> page,
                            @RequestParam("size") Optional<Integer> size,
                            HttpSession session){
        ModelAndView modelAndView = new ModelAndView();

        int currentPage = page.orElse(1);
        int pageSize = size.orElse(5);

        User user = (User) session.getAttribute("user-account");
        Page<PostComment> commentPage2 = postCommentService.findByPostCommentId(currentPage - 1, pageSize, id, user.getId());
        modelAndView.addObject("commentPage2", commentPage2);
        modelAndView.addObject("commentID", id);
        modelAndView.addObject("postID", postID);
        modelAndView.addObject("user", user);
        int totalPage = commentPage2.getTotalPages();
        if (totalPage > 0){
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPage)
                    .boxed()
                    .collect(Collectors.toList());
            modelAndView.addObject("pageNumbers", pageNumbers);
        }
        modelAndView.setViewName("comments/childComments");
        return modelAndView;
    }

    @PostMapping("/posts/add-comment")
    public ModelAndView addComment(
                            @ModelAttribute("postComment") PostComment postComment,
                            @RequestParam("postID") long postID,
                            HttpSession session
                            ){
        ModelAndView modelAndView = new ModelAndView();
        postComment.setUser((User) session.getAttribute("user-account"));
        postComment.setCreatedAt(LocalDateTime.now());
        postComment.setPublishedAt(LocalDateTime.now());
        postComment.setPublished(true);
        postComment.setPost(postRepository.findById(postID).get());
        postCommentRepository.save(postComment);
        modelAndView.setViewName("redirect:/posts/detail/"+postID);
        return modelAndView;
    }

    @GetMapping("/posts/comments/show-form-add-child-comment")
    public ModelAndView showFormAddChildComment(
            @RequestParam("commendID") long commendID,
            @RequestParam("postID") long postID
    ){
        ModelAndView modelAndView = new ModelAndView();
        PostComment postComment1 = new PostComment();
        modelAndView.addObject("postComment1",postComment1);
        modelAndView.addObject("commendID",commendID);
        modelAndView.addObject("postID",postID);
        modelAndView.setViewName("comments/showFormAddChildComment");
        return modelAndView;
    }

    @PostMapping("/posts/comments/child-comments/add-child-comment")
    public ModelAndView addChildComment(
            @ModelAttribute("postComment1") PostComment postComment,
            @RequestParam("postID") long postID,
            @RequestParam("commentID") long commentID,
            HttpSession session
    ){
        ModelAndView modelAndView = new ModelAndView();
        postComment.setUser((User) session.getAttribute("user-account"));
        postComment.setCreatedAt(LocalDateTime.now());
        postComment.setPublished(true);
        postComment.setPost(postRepository.findById(postID).get());
        postComment.setPostComment(postCommentRepository.findById(commentID).get());
        postCommentRepository.save(postComment);
        modelAndView.setViewName("redirect:/posts/comments/child-comments/"+commentID+"?postID="+postID);
        return modelAndView;
    }

    @GetMapping("/posts/delete-comment")
    public ModelAndView deleteComment(
            @RequestParam("commentID") long commentID,
            @RequestParam("postID") long postID

    ){
        ModelAndView modelAndView = new ModelAndView();
        PostComment parentPostComment = postCommentRepository.findById(commentID).get().getPostComment();
        if(parentPostComment==null){
            modelAndView.setViewName("redirect:/posts/detail/"+postID);
        }
        else {
            modelAndView.setViewName("redirect:/posts/comments/child-comments/"+parentPostComment.getId()+"?postID="+postID);
        }

        postCommentRepository.deleteById(commentID);

        return modelAndView;
    }

    @GetMapping("/posts/show-form-update-comment")
    public ModelAndView showFormUpdateComment(
            @RequestParam("commentID") long commentID,
            @RequestParam("postID") long postID
    ){
        ModelAndView modelAndView = new ModelAndView();
        PostComment postComment = postCommentRepository.findById(commentID).get();
        modelAndView.addObject("postComment",postComment);
        modelAndView.addObject("postID",postID);
        modelAndView.setViewName("comments/showFormUpdateComment");
        return modelAndView;
    }

    @PostMapping("/posts/update-comment")
    public ModelAndView updateComment(
            @ModelAttribute("postComment") PostComment postComment,
            @RequestParam("postID") long postID,
            @RequestParam("commentID") long commentID
    ){
        ModelAndView modelAndView = new ModelAndView();

        PostComment postComment1 = postCommentRepository.findById(commentID).get();
        postComment1.setTitle(postComment.getTitle());
        postComment1.setContent(postComment.getContent());
        postComment1.setPublished(postComment.isPublished());
        postCommentRepository.save(postComment1);

        if(postComment1.getPostComment()==null){
            modelAndView.setViewName("redirect:/posts/detail/"+postID);
        }
        else {
            modelAndView.setViewName("redirect:/posts/comments/child-comments/"+postComment1.getPostComment().getId()+"?postID="+postID);
        }
        return modelAndView;
    }
}
