package com.example.spacecommunitybackendjwtoauth.community.doc.presentation.controller;

import com.example.spacecommunitybackendjwtoauth.community.doc.service.UserDocumentDeleteService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/community/doc")
public class DocumentDeleteController {

    private final UserDocumentDeleteService documentDeleteService;

    @DeleteMapping("/{docId}")
    @Operation(summary = "문서 제거", description = "문서 제거")
    public ResponseEntity<?> deleteDocument(HttpServletRequest request, @PathVariable Long docId) {
        if(docId==null) throw new IllegalArgumentException();
        documentDeleteService.deleteDocument(request, docId);
        return ResponseEntity.ok("Document successfully deleted");
    }
}
