package npp.booksocialnetwork.notification.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import npp.booksocialnetwork.notification.dto.ApiResponse;
import npp.booksocialnetwork.notification.dto.request.SendEmailRequest;
import npp.booksocialnetwork.notification.dto.response.EmailResponse;
import npp.booksocialnetwork.notification.service.EmailService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
@Tag(name = "Email Controller", description = "Controller for send email via Brevo")
public class EmailController {
    EmailService emailService;

    @Operation(summary = "Send Email", description = "API for sending email via Brevo")
    @PostMapping("/email/send")
    ApiResponse<EmailResponse> sendEmail(@RequestBody SendEmailRequest request){
        return ApiResponse.<EmailResponse>builder()
                .result(emailService.sendEmail(request))
                .build();
    }
}