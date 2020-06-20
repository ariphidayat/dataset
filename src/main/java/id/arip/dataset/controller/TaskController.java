package id.arip.dataset.controller;

import id.arip.dataset.model.Task;
import id.arip.dataset.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/")
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
        return "redirect:/";
    }

    @GetMapping("revoke")
    public String revokeTask(Principal principal, @RequestParam("taskId") Integer taskId) {
        Task task = taskService.getTask(taskId);
        if (task.getBookedBy().equals(principal.getName())) {
            taskService.revoke(task);
        }
        return "redirect:/";
    }

    @GetMapping("delete")
    public String deleteUser(@RequestParam("taskId") Integer taskId) {
        taskService.delete(taskId);
        return "redirect:/";
    }

    @PostMapping("upload")
    public String upload(@RequestParam("file") MultipartFile file) {
        if (file.getContentType().equals("application/zip")) {
            Task task = new Task();
            String taskName = Objects.requireNonNull(file.getOriginalFilename())
                    .replaceAll("(?i).zip", "");
            task.setTask(taskName);
            taskService.save(task);
        }
        return "redirect:/";
    }

    @GetMapping(value = "download", produces = "application/zip")
    public void download(HttpServletResponse res, @RequestParam("taskId") Integer taskId) {
        Task task = taskService.getTask(taskId);
        if (task != null) {
            res.setStatus(HttpStatus.OK.value());
            res.setHeader("Content-Disposition", "attachment; filename=\"" + task.getTask() + ".zip\"");
        }
    }
}
