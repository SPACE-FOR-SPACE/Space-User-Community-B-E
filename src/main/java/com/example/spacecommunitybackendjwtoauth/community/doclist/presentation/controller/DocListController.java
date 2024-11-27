package com.example.spacecommunitybackendjwtoauth.community.doclist.presentation.controller;

import com.example.spacecommunitybackendjwtoauth.community.doclist.presentation.dto.DocumentListElementDTO;
import com.example.spacecommunitybackendjwtoauth.community.doclist.service.DocumentListService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/community")
public class DocListController {
    private final DocumentListService documentListService;

    @GetMapping("/doclist")
    @Operation(summary = "문서 리스트", description = "문서 리스트")
    public List<DocumentListElementDTO> getDocList(@RequestParam(required = false) String orderBy) {
        if(orderBy==null) orderBy = "createdAt";
        return documentListService.generalDocumentList(orderBy);
    }

    @GetMapping("/doclist/{username}")
    @Operation(summary = "유저에 관한 문서 리스트", description = "유저에 관한 문서 리스트")
    public List<DocumentListElementDTO> getDocListByUsername(@PathVariable String username, @RequestParam(required = false) String orderBy) {
        if(orderBy==null) orderBy = "createdAt";
        return documentListService.userDoucmentList(username, orderBy);
    }
}
