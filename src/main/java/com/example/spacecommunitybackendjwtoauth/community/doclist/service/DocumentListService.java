package com.example.spacecommunitybackendjwtoauth.community.doclist.service;

import com.example.spacecommunitybackendjwtoauth.community.doclist.presentation.dto.DocumentListElementDTO;

import java.util.List;

public interface DocumentListService {
    List<DocumentListElementDTO> generalDocumentList(String orderBy);
    List<DocumentListElementDTO> userDoucmentList(String username, String orderBy);
}
