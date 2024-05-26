package com.skyline.todo.controller;

import com.skyline.todo.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
    private final AdminService adminService;

    @PatchMapping("/upgrade_user")
    public ResponseEntity<?> upgradeUser(@RequestParam String userEmail) {
        adminService.upgradeUser(userEmail);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/ban_user")
    public ResponseEntity<?> banUser(@RequestParam String userEmail) {
        adminService.banUser(userEmail);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/unban_user")
    public ResponseEntity<?> unbanUser(@RequestParam String userEmail) {
        adminService.unbanUser(userEmail);
        return ResponseEntity.ok().build();
    }
}
