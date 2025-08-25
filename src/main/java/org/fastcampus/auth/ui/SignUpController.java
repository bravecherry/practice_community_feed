package org.fastcampus.auth.ui;

import lombok.RequiredArgsConstructor;
import org.fastcampus.auth.application.AuthService;
import org.fastcampus.auth.application.EmailService;
import org.fastcampus.auth.application.dto.CreateUserAuthReqDto;
import org.fastcampus.auth.application.dto.SendEmailReqDto;
import org.fastcampus.common.ui.Response;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sign/up")
@RequiredArgsConstructor
public class SignUpController {

    private final EmailService emailService;
    private final AuthService authService;

    @PostMapping("/verification/send/{method}")
    public Response<Void> sendVerification(@PathVariable String method, @RequestBody SendEmailReqDto reqDto) {
        emailService.sendEmail(reqDto);
        return Response.ok(null);
    }

    @GetMapping("/verify/token")
    public Response<Void> verifyToken(String email, String token) {
        emailService.verifyEmail(email, token);
        return Response.ok(null);
    }

    @PostMapping("/register")
    public Response<Long> register(@RequestBody CreateUserAuthReqDto reqDto) {
        authService.register(reqDto);
        return Response.ok(null);
    }

}
