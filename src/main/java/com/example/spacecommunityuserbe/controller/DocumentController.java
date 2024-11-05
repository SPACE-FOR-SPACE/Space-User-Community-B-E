package com.example.spacecommunityuserbe.controller;

import com.example.spacecommunityuserbe.dto.DocumentDTO;
import com.example.spacecommunityuserbe.dto.LikesDocumentDTO;
import com.example.spacecommunityuserbe.service.DocumentService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
public class DocumentController {
  private final DocumentService documentService;

  public DocumentController(DocumentService documentService) {
    this.documentService = documentService;
  }

  // Document Create
  @Operation(
          summary = "문서 생성",
          description = "문서를 생성합니다.",
          requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                  description = "문서 정보",
                  required = true,
                  content = @Content(schema = @Schema(implementation = DocumentDTO.class))
          ),
          responses = {
                  @ApiResponse(responseCode = "201", description = "Successfully Created"),
                  @ApiResponse(responseCode = "400", description = "Invalid input")
          }
  )
  @ResponseStatus(HttpStatus.CREATED)
  @PostMapping("/community/doc")
  public BaseApiResponse create(@RequestBody DocumentDTO document) {
    return documentService.createDocument(document);
  }


  // Document Read
  @Operation(
          summary = "문서 읽기",
          description = "문서를 불러옵니다.",
          parameters = {
                  @Parameter(name = "id", description = "문서 아이디", required = true)
          },
          responses = {
                  @ApiResponse(responseCode = "200", description = "Successfully read"),
                  @ApiResponse(responseCode = "404", description = "Document not found"),
          }
  )
  @ResponseStatus(HttpStatus.OK)
  @GetMapping("/community/doc/{id}")
  public Object read(@PathVariable Long id) {
    return documentService.readDocument(id);
  }


  // Document Update
  @Operation(
          summary = "문서 수정",
          description = "문서를 수정합니다.",
          parameters = {
                  @Parameter(name = "id", description = "문서 아이디", required = true)
          },
          requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                  description = "수정된 문서 정보",
                  required = true,
                  content = @Content(schema = @Schema(implementation = DocumentDTO.class))
          ),
          responses = {
                  @ApiResponse(responseCode = "200", description = "Successfully updated"),
                  @ApiResponse(responseCode = "404", description = "Document not found"),
                  @ApiResponse(responseCode = "400", description = "Invalid input")
          }
  )
  @ResponseStatus(HttpStatus.OK)
  @PutMapping("/community/doc/{id}")
  public BaseApiResponse update(@PathVariable Long id, @RequestBody DocumentDTO documentDTO) {
    return documentService.updateDocument(id, documentDTO);
  }


  // Document Delete
  @Operation(
          summary = "문서 삭제",
          description = "문서를 삭제합니다.",
          parameters = {
                  @Parameter(name = "id", description = "문서 아이디", required = true)
          },
          responses = {
                  @ApiResponse(responseCode = "200", description = "Successfully deleted"),
                  @ApiResponse(responseCode = "404", description = "Document not found"),
                  @ApiResponse(responseCode = "403", description = "Unauthorized access")
          }
  )
  @ResponseStatus(HttpStatus.OK)
  @DeleteMapping("/community/doc/{id}/{userId}") // UserId는 임시임
  public BaseApiResponse delete(@PathVariable Long id, @PathVariable Long userId) {
    return documentService.deleteDocument(id, userId);
  }


  // Document Like
  @Operation(
          summary = "문서 좋아요",
          description = "문서 좋아요 기능입니다. 이미 그 유저가 그 문서에 좋아요를 눌렀다면 그 좋아요를 삭제합니다.",
          requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                  description = "유저 아이디와 문서 아이디를 통해 좋아요를 관리합니다.",
                  required = true,
                  content = @Content(schema = @Schema(implementation = LikesDocumentDTO.class))
          ),
          responses = {
                  @ApiResponse(responseCode = "200", description = "Successfully added/removed likes"),
                  @ApiResponse(responseCode = "404", description = "Document not found")
          }
  )
  @ResponseStatus(HttpStatus.OK)
  @PostMapping("/community/doc/like")
  public BaseApiResponse like(@RequestBody LikesDocumentDTO likesDocumentDTO) {
    return documentService.like(likesDocumentDTO);
  }
}
