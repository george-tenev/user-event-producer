package georgetenev.usereventproducer.controller;

import georgetenev.usereventproducer.dto.UserLogin;
import georgetenev.usereventproducer.service.UserLoginService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserLoginController {

    private final UserLoginService userService;

    public UserLoginController(UserLoginService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<String> sendMessage(@RequestBody UserLogin userLogin) {
        userService.sendMessage(userLogin);
        return ResponseEntity.ok("Sent message: " + userLogin);
    }
}