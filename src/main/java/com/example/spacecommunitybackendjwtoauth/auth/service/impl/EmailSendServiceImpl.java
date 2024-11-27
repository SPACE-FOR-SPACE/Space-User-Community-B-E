package com.example.spacecommunitybackendjwtoauth.auth.service.impl;

import com.example.spacecommunitybackendjwtoauth.auth.domain.EmailAuthenticateEntity;
import com.example.spacecommunitybackendjwtoauth.auth.presentation.repository.EmailAuthenticateRepository;
import com.example.spacecommunitybackendjwtoauth.auth.service.EmailSendService;
import com.example.spacecommunitybackendjwtoauth.user.exception.UserExistedException;
import com.example.spacecommunitybackendjwtoauth.user.presentation.repository.UserRepository;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;

@Service
@RequiredArgsConstructor
@Transactional
public class EmailSendServiceImpl implements EmailSendService {
    private final UserRepository userRepository;
    private final EmailAuthenticateRepository emailAuthenticateRepository;
    private final JavaMailSender javaMailSender;

    @Value("${AdminMail.id}")
    private String adminMail;

    @Override
    public void sendMail(String email) throws MessagingException {
        if(userRepository.existsByEmail(email)) throw new UserExistedException();
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");

        String epw = createKey();

        String message =
                "<table style='width: 100%; border-collapse: collapse; text-align: center;'>" +
                "<tr><td><h2 style='text-align: center;'>인증번호</h2></td></tr>" +
                "<tr><td><p style='font-size: 36px; font-weight: 700;'>" + epw + "</p></td></tr>" +
                "<tr><td><p style='color: #575757;'>" +
                "올바른 이메일인지 확인하기 위하여 인증번호를 사이트에 입력해주세요</p></td></tr>" +
                "</table>";

        helper.setTo(email);
        helper.setFrom(adminMail);
        helper.setSubject("SpaceCommunity 이메일 검증");
        helper.setText(message, true);

        javaMailSender.send(mimeMessage);

        EmailAuthenticateEntity emailAuthenticateEntity = EmailAuthenticateEntity.builder()
                .token(epw)
                .email(email)
                .build();

        emailAuthenticateRepository.save(emailAuthenticateEntity);
    }

    private static String createKey() {
        StringBuffer key = new StringBuffer();
        Random rnd = new Random();
        for (int i = 0; i < 8; i++) {
            int index = rnd.nextInt(3);

            switch (index) {
                case 0:
                    key.append((char) ((rnd.nextInt(26)) + 97));
                    break;
                case 1:
                    key.append((char) ((rnd.nextInt(26)) + 65));
                    break;
                case 2:
                    key.append((rnd.nextInt(10)));
                    break;
            }
        }
        return key.toString();
    }
}
