package com.example.spacecommunityuserbe.controller;

import com.example.spacecommunityuserbe.dto.CommentsDTO;
import com.example.spacecommunityuserbe.dto.DocumentDTO;
import com.example.spacecommunityuserbe.service.CommentsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class CommentsController {
  private final CommentsService commentsService;

  public CommentsController(CommentsService commentsService) {
    this.commentsService = commentsService;
  }

  // Comment Create
  @Operation(
          summary = "댓글 생성",
          description = "댓글을 생성합니다.",
          requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                  description = "댓글 정보",
                  required = true,
                  content = @Content(schema = @Schema(implementation = CommentsDTO.class))
          ),
          responses = {
                  @ApiResponse(responseCode = "201", description = "Successfully Created"),
                  @ApiResponse(responseCode = "400", description = "Invalid input")
          },
          parameters = {
                  @Parameter(name = "docID", description = "댓글을 생성하는 문서의 ID")
          }
  )
  @ResponseStatus(HttpStatus.OK)
  @PostMapping("/community/comments/{docID}")
  public BaseApiResponse create(@PathVariable Long docID, @RequestBody CommentsDTO comment) {
    return commentsService.createComment(comment, docID);
  }

  // Comment Create
  @Operation(
          summary = "댓글 보기",
          description = "댓글을 불러옵니다. (리스트) 만약 댓글이 없다면 빈 리스트를 반환합니다.",
          responses = {
                  @ApiResponse(responseCode = "200", description = "Successfully Read"),
                  @ApiResponse(responseCode = "404", description = "Document Not Found")
          },
          parameters = {
                  @Parameter(name = "docID", description = "문서의 ID")
          }
  )
  @ResponseStatus(HttpStatus.OK)
  @GetMapping("/community/comments/{docID}")
  public BaseApiResponse read(@PathVariable Long docID) {
    return commentsService.readComment(docID);
  }


  // Comment Update
  @Operation(
          summary = "댓글 수정",
          description = "댓글을 수정합니다.",
          responses = {
                  @ApiResponse(responseCode = "200", description = "Successfully updated"),
                  @ApiResponse(responseCode = "404", description = "Document not found"),
                  @ApiResponse(responseCode = "400", description = "Invalid input")
          },
          parameters = {
                  @Parameter(name = "docID", description = "문서의 ID"),
                  @Parameter(name = "commentID", description = "댓글의 ID")
          }
  )
  @ResponseStatus(HttpStatus.OK)
  @PutMapping("/community/comments/{docID}/{commentID}")
  public BaseApiResponse update(@PathVariable Long docID, @PathVariable Long commentID, @RequestBody CommentsDTO comment) {
    return commentsService.updateComment(comment, docID, commentID);
  }


  // Comment Delete
  @Operation(
          summary = "댓글 삭제",
          description = "댓글을 삭제합니다.",
          responses = {
                  @ApiResponse(responseCode = "200", description = "Successfully deleted"),
                  @ApiResponse(responseCode = "404", description = "Document not found"),
                  @ApiResponse(responseCode = "403", description = "Unauthorized access")
          },
          parameters = {
                  @Parameter(name = "docID", description = "문서의 ID"),
                  @Parameter(name = "commentID", description = "댓글의 ID")
          }
  )
  @ResponseStatus(HttpStatus.OK)
  @DeleteMapping("/community/comments/{docID}/{commentID}")
  public BaseApiResponse delete(@PathVariable Long docID, @PathVariable Long commentID) {
    return commentsService.deleteComment(docID, commentID);
  }
}
