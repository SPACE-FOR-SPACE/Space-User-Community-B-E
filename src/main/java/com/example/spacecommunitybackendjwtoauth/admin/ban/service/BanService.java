package com.example.spacecommunitybackendjwtoauth.admin.ban.service;

public interface BanService {
    void addBanList(String username);
    void removeBanList(String username);
}
