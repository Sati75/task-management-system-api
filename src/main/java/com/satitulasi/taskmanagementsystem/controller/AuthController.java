
package com.satitulasi.taskmanagementsystem.controller;
import com.satitulasi.taskmanagementsystem.dto.RegisterRequest;
import com.satitulasi.taskmanagementsystem.dto.RegisterResponse;
import com.satitulasi.taskmanagementsystem.dto.LoginRequest;
import com.satitulasi.taskmanagementsystem.dto.AuthResponse;
import com.satitulasi.taskmanagementsystem.entity.User;
import com.satitulasi.taskmanagementsystem.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @GetMapping("/test")
    public String test() {
        return "Auth Controller Working";
    }

    @PostMapping("/register")
    public RegisterResponse registerUser(
            @Valid @RequestBody RegisterRequest request) {

        User user = userService.registerUser(request);

        return new RegisterResponse(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getRole()
        );
    }

    @PostMapping("/login")
    public AuthResponse loginUser(@Valid @RequestBody LoginRequest request) {
        return userService.loginUser(request);
    }
}