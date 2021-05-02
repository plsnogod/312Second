package com.plsnogod.jmboot.controllers;

import com.plsnogod.jmboot.model.Role;
import com.plsnogod.jmboot.model.User;
import com.plsnogod.jmboot.service.RoleService;
import com.plsnogod.jmboot.service.RoleServiceImpl;
import com.plsnogod.jmboot.service.UserService;
import com.plsnogod.jmboot.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.security.Principal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Controller
@RequestMapping("/")
public class AdminController {
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

//    @GetMapping
//    public String homePage() {
//        return "redirect:/login";
//    }

    @GetMapping("/admin")
    public String commonAdminPageGet(Model model, Principal principal) {
        List<User> listUsers = userService.showAllUsers();
        List<Role> listRoles = roleService.list_roles();
        model.addAttribute("all_us", listUsers);
        model.addAttribute("autUser", userService.getUserByEmail(principal.getName()));
        model.addAttribute("allRoles", listRoles);
        return "table_users";
    }

    @PostMapping("/delete")
    public String commonPageDeletePost(@ModelAttribute("deleteUser") User user) {
        userService.deleteUser(user.getId());
        return "redirect:/admin";
    }

    @PostMapping("/edit")
    public String commonPageEditAddPost(@ModelAttribute("editUser") @Valid User user,
                                        @RequestParam(value = "newRole") String[] role) {
        user.setRolesSet(getAddRole(role));
        userService.addUser(user);

        return "redirect:/admin";
    }

    private Set<Role> getAddRole(String[] role) {
        Set<Role> roleSet = new HashSet<>();
        for (int i = 0; i < role.length; i++) {
            roleSet.add(roleService.findByRole(role[i]));
        }
        return roleSet;
    }

    @GetMapping("/user")
    public String commonUserPageGet(Model model, Principal principal) {
        model.addAttribute("autUser", userService.getUserByEmail(principal.getName()));
        return "page_user";
    }
}
