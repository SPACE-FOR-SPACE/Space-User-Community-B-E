package com.example.spacecommunitybackendjwtoauth.community.search.presentation.controller;

import com.example.spacecommunitybackendjwtoauth.community.search.presentation.dto.DocumentDTO;
import com.example.spacecommunitybackendjwtoauth.community.search.service.SearchDocumentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/search")
public class DocumentSearchController {

    private final SearchDocumentService documentSearchService;

    @GetMapping("/doc/{search}")
    public List<DocumentDTO> getDocuments(@PathVariable String search) {
        if(search==null) throw new IllegalArgumentException();
        return documentSearchService.searchDocuments(search);
    }
}
