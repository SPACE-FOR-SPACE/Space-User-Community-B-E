package com.example.spacecommunityuserbe.controller;

import com.example.spacecommunityuserbe.dto.CommentDTO;
import com.example.spacecommunityuserbe.dto.RecommentDTO;
import com.example.spacecommunityuserbe.service.CommentService;
import com.example.spacecommunityuserbe.service.RecommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class RecommentController {
  private final RecommentService recommentService;

  public RecommentController(RecommentService recommentService) {
    this.recommentService = recommentService;
  }

  // Recomment Create
  @Operation(
          summary = "대댓글 생성",
          description = "대댓글을 생성합니다.",
          requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                  description = "댓글 정보",
                  required = true,
                  content = @Content(schema = @Schema(implementation = CommentDTO.class))
          ),
          responses = {
                  @ApiResponse(responseCode = "201", description = "Successfully Created"),
                  @ApiResponse(responseCode = "400", description = "Invalid input")
          },
          parameters = {
                  @Parameter(name = "docID", description = "대댓글을 생성하는 문서의 ID"),
                  @Parameter(name = "commentId", description = "부모 댓글의 댓글 ID")
          }
  )
  @ResponseStatus(HttpStatus.OK)
  @PostMapping("/community/comment/{docID}/{commentID}")
  public BaseApiResponse create(@PathVariable Long docID, @PathVariable Long commentID, @RequestBody RecommentDTO recomment) {
    return recommentService.create(recomment, docID, commentID);
  }

  // Recomment Read
  @Operation(
          summary = "대댓글 보기",
          description = "대댓글을 불러옵니다. (리스트) 만약 대댓글이 없다면 빈 리스트를 반환합니다.",
          responses = {
                  @ApiResponse(responseCode = "200", description = "Successfully Read"),
                  @ApiResponse(responseCode = "404", description = "Document Not Found")
          },
          parameters = {
                  @Parameter(name = "docID", description = "문서의 ID")
          }
  )
  @ResponseStatus(HttpStatus.OK)
  @GetMapping("/community/comment/{docID}/{commentID}")
  public BaseApiResponse read(@PathVariable Long docID, @PathVariable Long commentID) {
    return recommentService.readRecomment(docID, commentID);
  }


  // Recomment Update
  @Operation(
          summary = "대댓글 수정",
          description = "대댓글을 수정합니다.",
          responses = {
                  @ApiResponse(responseCode = "200", description = "Successfully updated"),
                  @ApiResponse(responseCode = "404", description = "Document not found"),
                  @ApiResponse(responseCode = "400", description = "Invalid input")
          },
          parameters = {
                  @Parameter(name = "docID", description = "문서의 ID"),
                  @Parameter(name = "commentID", description = "댓글의 ID"),
                  @Parameter(name = "recommentID", description = "대댓글의 ID")
          }
  )
  @ResponseStatus(HttpStatus.OK)
  @PutMapping("/community/comment/{docID}/{commentID}/{recommentID}")
  public BaseApiResponse update(@PathVariable Long docID, @PathVariable Long commentID, @PathVariable Long recommentID, @RequestBody RecommentDTO recomment) {
    return recommentService.updateRecomment(recomment, docID, commentID, recommentID);
  }


  // Comment Delete
  @Operation(
          summary = "대댓글 삭제",
          description = "대댓글을 삭제합니다.",
          responses = {
                  @ApiResponse(responseCode = "200", description = "Successfully deleted"),
                  @ApiResponse(responseCode = "404", description = "Document not found"),
                  @ApiResponse(responseCode = "403", description = "Unauthorized access")
          },
          parameters = {
                  @Parameter(name = "docID", description = "문서의 ID"),
                  @Parameter(name = "commentID", description = "댓글의 ID"),
                  @Parameter(name = "recommentID", description = "대댓글의 ID")
          }
  )
  @ResponseStatus(HttpStatus.OK)
  @DeleteMapping("/community/comment/{docID}/{commentID}/{recommentID}")
  public BaseApiResponse delete(@PathVariable Long docID, @PathVariable Long commentID, @PathVariable Long recommentID) {
    return recommentService.deleteRecomment(docID, commentID, recommentID);
  }
}
