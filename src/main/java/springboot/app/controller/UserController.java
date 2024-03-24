package springboot.app.controller;

import jakarta.validation.Valid;
import springboot.app.model.User;
import springboot.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class UserController {

    private UserService service;

    @Autowired
    public void setUserService(UserService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String printUsers(ModelMap model) {
        List<?> list = service.getAllUsers();
        model.addAttribute("users", list);
        return "index";
    }

    @GetMapping("/edit")
    public String editPage(@RequestParam int id, ModelMap model) {
        User user = service.getById(id);
        model.addAttribute("user", user);
        return "edit";
    }

    @GetMapping("/add")
    public String addPage(ModelMap model) {
        model.addAttribute("user", new User());
        return "add";
    }

    @PostMapping("/save")
    public String addUser(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            if (user.getId() != null) {
                return "edit";
            } else {
                return "add";
            }
        }
        service.save(user);
        return "redirect:/";
    }

    @GetMapping("/delete")
    public String deleteUser(@RequestParam int id) {
        service.delete(id);
        return "redirect:/";
    }
}
