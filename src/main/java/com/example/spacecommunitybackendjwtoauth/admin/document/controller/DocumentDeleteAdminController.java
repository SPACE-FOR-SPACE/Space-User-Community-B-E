package com.example.spacecommunitybackendjwtoauth.admin.document.controller;

import com.example.spacecommunitybackendjwtoauth.admin.document.service.DocumentDeleteAdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class DocumentDeleteAdminController {
    private final DocumentDeleteAdminService documentDeleteAdminService;
    @DeleteMapping("/admin/doc/{docID}")
    public void deleteDocument(@PathVariable Long docID) {
        if(docID==null) throw new IllegalArgumentException();
        documentDeleteAdminService.deleteDoc(docID);
    }
}
