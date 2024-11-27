package com.example.spacecommunitybackendjwtoauth.community.doc.presentation.controller;

import com.example.spacecommunitybackendjwtoauth.community.doc.presentation.dto.DocumentReadDTO;
import com.example.spacecommunitybackendjwtoauth.community.doc.service.UserDocumentReadService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/community/doc")
public class DocumentReadController {

    private final UserDocumentReadService userDocumentReadService;

    @GetMapping("/{docId}")
    @Operation(summary = "문서 보기", description = "문서 보기")
    public ResponseEntity<DocumentReadDTO> getDocument(HttpServletRequest request, @PathVariable Long docId) {
        if(docId==null) throw new IllegalArgumentException();
        return ResponseEntity.ok(userDocumentReadService.getDocumentById(request, docId));
    }
}
