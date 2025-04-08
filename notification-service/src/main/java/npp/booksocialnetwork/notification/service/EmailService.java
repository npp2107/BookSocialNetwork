package npp.booksocialnetwork.notification.service;

import lombok.experimental.NonFinal;
import npp.booksocialnetwork.notification.dto.request.EmailRequest;
import npp.booksocialnetwork.notification.dto.request.SendEmailRequest;
import npp.booksocialnetwork.notification.dto.request.Sender;
import npp.booksocialnetwork.notification.dto.response.EmailResponse;
import npp.booksocialnetwork.notification.exception.AppException;
import npp.booksocialnetwork.notification.exception.ErrorCode;
import npp.booksocialnetwork.notification.repository.httpclient.EmailClient;
import feign.FeignException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class EmailService {
    EmailClient emailClient;

    @Value("${notification.email.brevo-apikey}")
    @NonFinal
    String apiKey;

    public EmailResponse sendEmail(SendEmailRequest request) {
        EmailRequest emailRequest = EmailRequest.builder()
                .sender(Sender.builder()
                        .name("Npp Book Social Network")
                        .email("npp21072002@gmail.com")
                        .build())
                .to(List.of(request.getTo()))
                .subject(request.getSubject())
                .htmlContent(request.getHtmlContent())
                .build();
        try {
            return emailClient.sendEmail(apiKey, emailRequest);
        } catch (FeignException e){
            throw new AppException(ErrorCode.CANNOT_SEND_EMAIL);
        }
    }
}