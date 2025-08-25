package org.fastcampus.auth.ui;

import lombok.RequiredArgsConstructor;
import org.fastcampus.auth.application.EmailService;
import org.fastcampus.auth.application.dto.SendEmailReqDto;
import org.fastcampus.common.ui.Response;
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

    @PostMapping("send-verification/{method}")
    public Response<Void> sendVerification(@PathVariable String method, @RequestBody SendEmailReqDto reqDto) {
        emailService.sendEmail(reqDto);
        return Response.ok(null);
    }

}
