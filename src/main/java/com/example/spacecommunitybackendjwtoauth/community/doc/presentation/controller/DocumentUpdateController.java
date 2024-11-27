package com.example.spacecommunitybackendjwtoauth.community.doc.presentation.controller;

import com.example.spacecommunitybackendjwtoauth.community.doc.presentation.dto.DocumentUpdateDTO;
import com.example.spacecommunitybackendjwtoauth.community.doc.service.UserDocumentUpdateService;
import com.example.spacecommunitybackendjwtoauth.util.NotNullUtil;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/community")
public class DocumentUpdateController {

    private final UserDocumentUpdateService documentUpdateService;

    @PatchMapping("/doc")
    @Operation(summary = "문서 업데이트", description = "문서 업데이트")
    public ResponseEntity<?> updateDocument(@RequestBody DocumentUpdateDTO documentUpdateDTO) {
        NotNullUtil.hasNullFields(documentUpdateDTO);
        documentUpdateService.updateDocument(documentUpdateDTO);
        return ResponseEntity.ok("Document successfully updated");
    }
}
