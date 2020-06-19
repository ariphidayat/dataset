package id.arip.dataset.controller;

import id.arip.dataset.model.Task;
import id.arip.dataset.model.User;
import id.arip.dataset.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping
    public String getTasks(Model model) {
        List<Task> tasks = taskService.getTasks();

        model.addAttribute("tasks", tasks);
        return "task/list";
    }

    @GetMapping("book")
    public String bookTask(Principal principal, @RequestParam("taskId") Integer taskId) {
        Task task = taskService.getTask(taskId);
        task.setBookedBy(principal.getName());
        taskService.book(task);
        return "redirect:/tasks";
    }

    @GetMapping("revoke")
    public String revokeTask(Principal principal, @RequestParam("taskId") Integer taskId) {
        Task task = taskService.getTask(taskId);
        if (task.getBookedBy().equals(principal.getName())) {
            taskService.revoke(task);
        }
        return "redirect:/tasks";
    }

    @GetMapping("delete")
    public String deleteUser(@RequestParam("taskId") Integer taskId) {
        taskService.delete(taskId);
        return "redirect:/tasks";
    }
}
