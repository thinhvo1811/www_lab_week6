package vn.edu.iuh.fit.week06_lab_voquocthinh_20078241.frontend.controllers;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import vn.edu.iuh.fit.week06_lab_voquocthinh_20078241.backend.models.Post;
import vn.edu.iuh.fit.week06_lab_voquocthinh_20078241.backend.models.User;
import vn.edu.iuh.fit.week06_lab_voquocthinh_20078241.backend.repositories.UserRepository;
import vn.edu.iuh.fit.week06_lab_voquocthinh_20078241.backend.services.UserService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public ModelAndView login(){
        ModelAndView modelAndView = new ModelAndView();
        User user = new User();
        modelAndView.addObject("user", user);
        modelAndView.setViewName("users/login");
        return modelAndView;
    }

    @PostMapping("/users/check-login")
    public String checkLogin(@ModelAttribute("user") User user,
                             HttpSession session) {
        User user1 = userService.findByEmailAndPassword(user.getEmail(), user.getPasswordHash());
        if (user1!=null){
            session.setAttribute("user-account", user1);
            userService.updateLastLoginTime(user1);
            return "redirect:/users/info";
        }
        else {
            return "redirect:/login";
        }
    }

    @GetMapping("/register")
    public ModelAndView register(){
        ModelAndView modelAndView = new ModelAndView();
        User user = new User();
        modelAndView.addObject("user", user);
        modelAndView.setViewName("users/register");
        return modelAndView;
    }

    @PostMapping("/users/add")
    public String add(
            @ModelAttribute("user") User user) {
        userService.addUser(user);
        return "redirect:/login";
    }

    @GetMapping ("/users/info")
    public ModelAndView info(
            HttpSession session) {
        ModelAndView modelAndView = new ModelAndView();
        User user = (User) session.getAttribute("user-account");
        modelAndView.addObject("user", user);
        modelAndView.setViewName("users/information");
        return modelAndView;
    }

    @PostMapping("/users/update")
    public ModelAndView update(
            @ModelAttribute("user") User user,
            HttpSession session) {
        ModelAndView modelAndView = new ModelAndView();
        Optional<User> user1 = userRepository.findById(user.getId());
        if (user1.isPresent()){
            user1.get().setFirstName(user.getFirstName());
            user1.get().setMiddleName(user.getMiddleName());
            user1.get().setLastName(user.getLastName());
            user1.get().setMobile(user.getMobile());
            user1.get().setEmail(user.getEmail());
            user1.get().setIntro(user.getIntro());
            user1.get().setProfile(user.getProfile());
            userRepository.save(user1.get());
        }
        session.setAttribute("user-account", user1.get());

        modelAndView.setViewName("redirect:/users/info");
        return modelAndView;
    }

    @GetMapping("/logout")
    public ModelAndView logout(HttpSession session){
        ModelAndView modelAndView = new ModelAndView();
        session.removeAttribute("user-account");
        modelAndView.setViewName("redirect:/login");;
        return modelAndView;
    }

    @GetMapping("/changePassword")
    public ModelAndView changePassword(){
        ModelAndView modelAndView = new ModelAndView();
        User user = new User();
        modelAndView.addObject("user", user);
        modelAndView.setViewName("users/changePassword");
        return modelAndView;
    }

    @PostMapping("/users/updatePassword")
    public ModelAndView updatePassword(
            @ModelAttribute("user") User user,
            HttpServletRequest request,
            HttpSession session) {
        ModelAndView modelAndView = new ModelAndView();
        User user1 = (User) session.getAttribute("user-account");
        if(userService.findByEmailAndPassword(user1.getEmail(), user.getPasswordHash())!=null){
            userService.updatePassword(user1, request.getParameter("new"));
            modelAndView.setViewName("redirect:/users/info");
        }
        else{
            modelAndView.setViewName("redirect:/changePassword");
        }
        return modelAndView;
    }
}
