package id.arip.dataset.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @GetMapping()
    public String hello() {
        return "Hello World!";
    }

    @GetMapping("users")
    public String getUsers() {
        return "List of Users";
    }
}
