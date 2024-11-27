package com.example.spacecommunitybackendjwtoauth.community.doc.presentation.controller;

import com.example.spacecommunitybackendjwtoauth.community.doc.presentation.dto.DocumentCreateDTO;
import com.example.spacecommunitybackendjwtoauth.community.doc.service.UserDocumentCreateService;
import com.example.spacecommunitybackendjwtoauth.util.NotNullUtil;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/community")
public class DocumentCreateController {
    private final UserDocumentCreateService userDocumentCreateService;
    @Operation(summary = "문서 생성", description = "문서 생성")
    @PostMapping("/doc")
    public ResponseEntity<?> createDocument(@RequestBody DocumentCreateDTO documentDTO) {
        NotNullUtil.hasNullFields(documentDTO);
        userDocumentCreateService.createDocument(documentDTO);
        return ResponseEntity.ok("Document successfully created");
    }
}
