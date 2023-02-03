package ru.netology.task1.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.netology.task1.service.SystemProfile;

import javax.annotation.security.RolesAllowed;

@RestController
@RequestMapping("/")
public class ProfileController {
    private final SystemProfile profile;

    public ProfileController(SystemProfile profile) {
        this.profile = profile;
    }

    @GetMapping("profile")
    public String getProfile() {
        return profile.getProfile();
    }

    @GetMapping("read")
    @Secured({"ROLE_READ"})
    public String readInfo() {
        return "Only for read role";
    }

    @GetMapping("write")
    @RolesAllowed({"ROLE_WRITE"})
    public String writeInfo() {
        return "Only for write role";
    }

    @GetMapping("delete")
    @PreAuthorize("hasAnyRole('WRITE', 'DELETE')")
    public String deleteInfo() {
        return "For write and delete roles";
    }

    @GetMapping("user_info")
    @PreAuthorize("#username == authentication.principal.username")
    public String userInfo(@RequestParam String username) {
        return "User info for " + username;
    }
}
