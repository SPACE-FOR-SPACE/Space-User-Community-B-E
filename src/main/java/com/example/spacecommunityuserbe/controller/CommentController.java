package com.example.spacecommunityuserbe.controller;

import com.example.spacecommunityuserbe.dto.CommentDTO;
import com.example.spacecommunityuserbe.service.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
public class CommentController {
  private final CommentService commentService;

  public CommentController(CommentService commentService) {
    this.commentService = commentService;
  }

  // Comment Create
  @Operation(
          summary = "댓글 생성",
          description = "댓글을 생성합니다.",
          requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                  description = "댓글 정보",
                  required = true,
                  content = @Content(schema = @Schema(implementation = CommentDTO.class))
          ),
          responses = {
                  @ApiResponse(responseCode = "200", description = "Successfully created", content = @Content(
                          mediaType = MediaType.APPLICATION_JSON_VALUE,
                          examples = @ExampleObject(value = "{\"status\": 200,\n\"message\": \"Successfully created\",\n\"data\": null\n}")
                  )),
                  @ApiResponse(responseCode = "404", description = "Document not found", content = @Content(
                          mediaType = MediaType.APPLICATION_JSON_VALUE,
                          examples = @ExampleObject(value = "{\"status\": 404,\n\"message\": \"Document not found\",\n\"data\": null\n}")
                  )),
                  @ApiResponse(responseCode = "500", description = "Error Message", content = @Content(
                          mediaType = MediaType.APPLICATION_JSON_VALUE,
                          examples = @ExampleObject(value = "{\"status\": 500,\n\"message\": \"Error Message\",\n\"data\": null\n}")
                  ))
          },
          parameters = {
                  @Parameter(name = "docID", description = "댓글을 생성하는 문서의 ID")
          }
  )
  @ResponseStatus(HttpStatus.OK)
  @PostMapping("/community/comment/{docID}")
  public BaseApiResponse create(@PathVariable Long docID, @RequestBody CommentDTO comment) {
    return commentService.createComment(comment, docID);
  }

  // Comment Read
  @Operation(
          summary = "댓글 보기",
          description = "댓글을 불러옵니다. (리스트) 만약 댓글이 없다면 빈 리스트를 반환합니다.",
          responses = {
                  @ApiResponse(responseCode = "200", description = "Successfully read", content = @Content(
                          mediaType = MediaType.APPLICATION_JSON_VALUE,
                          examples = @ExampleObject(value = "{\"status\": 200,\n\"message\": \"Successfully read\",\n\"data\": null\n}")
                  )),
                  @ApiResponse(responseCode = "404", description = "Document not found", content = @Content(
                          mediaType = MediaType.APPLICATION_JSON_VALUE,
                          examples = @ExampleObject(value = "{\"status\": 404,\n\"message\": \"Document not found\",\n\"data\": null\n}")
                  )),
                  @ApiResponse(responseCode = "500", description = "Error Message", content = @Content(
                          mediaType = MediaType.APPLICATION_JSON_VALUE,
                          examples = @ExampleObject(value = "{\"status\": 500,\n\"message\": \"Error Message\",\n\"data\": null\n}")
                  ))
          },
          parameters = {
                  @Parameter(name = "docID", description = "문서의 ID")
          }
  )
  @ResponseStatus(HttpStatus.OK)
  @GetMapping("/community/comment/{docID}")
  public BaseApiResponse read(@PathVariable Long docID) {
    return commentService.readComment(docID);
  }


  // Comment Update
  @Operation(
          summary = "댓글 수정",
          description = "댓글을 수정합니다.",
          responses = {
                  @ApiResponse(responseCode = "200", description = "Successfully updated", content = @Content(
                          mediaType = MediaType.APPLICATION_JSON_VALUE,
                          examples = @ExampleObject(value = "{\"status\": 200,\n\"message\": \"Successfully updated\",\n\"data\": null\n}")
                  )),
                  @ApiResponse(responseCode = "404", description = "Document/Comment not found", content = @Content(
                          mediaType = MediaType.APPLICATION_JSON_VALUE,
                          examples = @ExampleObject(value = "{\"status\": 404,\n\"message\": \"Document/Comment not found\",\n\"data\": null\n}")
                  )),
                  @ApiResponse(responseCode = "500", description = "Error Message", content = @Content(
                          mediaType = MediaType.APPLICATION_JSON_VALUE,
                          examples = @ExampleObject(value = "{\"status\": 500,\n\"message\": \"Error Message\",\n\"data\": null\n}")
                  ))
          },
          parameters = {
                  @Parameter(name = "docID", description = "문서의 ID"),
                  @Parameter(name = "commentID", description = "댓글의 ID")
          }
  )
  @ResponseStatus(HttpStatus.OK)
  @PutMapping("/community/comment/{docID}/{commentID}")
  public BaseApiResponse update(@PathVariable Long docID, @PathVariable Long commentID, @RequestBody CommentDTO comment) {
    return commentService.updateComment(comment, docID, commentID);
  }


  // Comment Delete
  @Operation(
          summary = "댓글 삭제",
          description = "댓글을 삭제합니다.",
          responses = {
                  @ApiResponse(responseCode = "200", description = "Successfully deleted", content = @Content(
                          mediaType = MediaType.APPLICATION_JSON_VALUE,
                          examples = @ExampleObject(value = "{\"status\": 200,\n\"message\": \"Successfully added/removed\",\n\"data\": null\n}")
                  )),
                  @ApiResponse(responseCode = "500", description = "Error Message", content = @Content(
                          mediaType = MediaType.APPLICATION_JSON_VALUE,
                          examples = @ExampleObject(value = "{\"status\": 500,\n\"message\": \"Error Message\",\n\"data\": null\n}")
                  ))
          },
          parameters = {
                  @Parameter(name = "docID", description = "문서의 ID"),
                  @Parameter(name = "commentID", description = "댓글의 ID")
          }
  )
  @ResponseStatus(HttpStatus.OK)
  @DeleteMapping("/community/comment/{docID}/{commentID}")
  public BaseApiResponse delete(@PathVariable Long docID, @PathVariable Long commentID) {
    return commentService.deleteComment(docID, commentID);
  }
}
