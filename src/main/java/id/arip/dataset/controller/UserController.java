package id.arip.dataset.controller;

import id.arip.dataset.model.User;
import id.arip.dataset.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping
    public String getUsers(Model model) {
        List<User> users = userService.getUsers();

        model.addAttribute("users", users);
        return "user/list";
    }

    @GetMapping("new")
    public String newUser(Model model) {
        User user = new User();

        model.addAttribute("user", user);
        return "user/form";
    }

    @GetMapping("modify")
    public String modifyUser(Model model, @RequestParam("username") String username) {
        User user = userService.getUser(username);

        model.addAttribute("user", user);
        return "user/form";
    }

    @PostMapping("save")
    public String saveUser(@ModelAttribute User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.save(user);
        return "redirect:/users";
    }

    @GetMapping("delete")
    public String deleteUser(@RequestParam("username") String username) {
        userService.delete(username);
        return "redirect:/users";
    }
}
