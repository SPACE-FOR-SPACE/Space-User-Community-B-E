package com.example.spacecommunitybackendjwtoauth.auth.service;

import jakarta.mail.MessagingException;

public interface EmailSendService {
    void sendMail(String email) throws MessagingException;
}
