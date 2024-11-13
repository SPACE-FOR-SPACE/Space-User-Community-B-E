package com.example.spacecommunityuserbe.controller;

import com.example.spacecommunityuserbe.dto.DocumentDTO;
import com.example.spacecommunityuserbe.dto.LikesDocumentDTO;
import com.example.spacecommunityuserbe.service.DocumentService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@CrossOrigin(origins = "http://10.150.149.140:3000")
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
                  content = @Content(examples = @ExampleObject(value = "{\n\"userId\": 123,\n\"title\": \"Hello World\",\n\"content\": \"Hello Sekai\",\n\"category\": \"Asks\",\n\"icon\":\"ICON1\"\n}"))
          ),
          responses = {
                  @ApiResponse(responseCode = "201", description = "Successfully Created", content = @Content(
                          mediaType = MediaType.APPLICATION_JSON_VALUE,
                          examples = @ExampleObject(value = "{\"status\": 200,\n\"message\": \"Successfully Created\",\n\"data\": null\n}")
                  )),
                  @ApiResponse(responseCode = "500", description = "Error Message", content = @Content(
                          mediaType = MediaType.APPLICATION_JSON_VALUE,
                          examples = @ExampleObject(value = "{\"status\": 500,\n\"message\": \"Error Message\",\n\"data\": null\n}")
                  ))
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
                  @ApiResponse(responseCode = "200", description = "Successfully read", content = @Content(
                          mediaType = MediaType.APPLICATION_JSON_VALUE,
                          examples = @ExampleObject(value = "{\"status\": 500,\n\"message\": \"Successfully read\",\n\"data\": {\n\"id\": 13, \"userId\": 1234,\n" +
                                  "        \"comments\": [],\n" +
                                  "        \"title\": \"title\",\n" +
                                  "        \"content\": \"content\",\n" +
                                  "        \"icon\": \"ICON1\",\n" +
                                  "        \"category\": \"1\",\n" +
                                  "        \"likes\": 0,\n" +
                                  "        \"createdAt\": \"2024-11-11T21:22:36.735233\"" +
                                  "\n}\n}")
                  )),
                  @ApiResponse(responseCode = "404", description = "Document not found", content = @Content(
                          mediaType = MediaType.APPLICATION_JSON_VALUE,
                          examples = @ExampleObject(value = "{\"status\": 404,\n\"message\": \"Document not found\",\n\"data\": null\n}")
                  )),
                  @ApiResponse(responseCode = "500", description = "Error Message", content = @Content(
                          mediaType = MediaType.APPLICATION_JSON_VALUE,
                          examples = @ExampleObject(value = "{\"status\": 500,\n\"message\": \"Error Message\",\n\"data\": null\n}")
                  ))
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
                  content = @Content(
                          examples = @ExampleObject(value = "{    " +
                                  "    \"title\":\"title\",\n" +
                                  "    \"content\":\"content\",\n" +
                                  "    \"category\":\"1\",\n" +
                                  "    \"icon\":\"ICON1\"}")
                  )
          ),
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
                  @ApiResponse(responseCode = "200", description = "Successfully deleted", content = @Content(
                          mediaType = MediaType.APPLICATION_JSON_VALUE,
                          examples = @ExampleObject(value = "{\"status\": 200,\n\"message\": \"Successfully deleted\",\n\"data\": null\n}")
                  )),
                  @ApiResponse(responseCode = "404", description = "Document not found", content = @Content(
                          mediaType = MediaType.APPLICATION_JSON_VALUE,
                          examples = @ExampleObject(value = "{\"status\": 404,\n\"message\": \"Document not found\",\n\"data\": null\n}")
                  )),
                  @ApiResponse(responseCode = "500", description = "Error Message", content = @Content(
                          mediaType = MediaType.APPLICATION_JSON_VALUE,
                          examples = @ExampleObject(value = "{\"status\": 500,\n\"message\": \"Error Message\",\n\"data\": null\n}")
                  ))
          }
  )
  @ResponseStatus(HttpStatus.OK)
  @DeleteMapping("/community/doc/{id}") // UserId는 임시임
  public BaseApiResponse delete(@PathVariable Long id) {
    return documentService.deleteDocument(id);
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
                  @ApiResponse(responseCode = "200", description = "Successfully liked", content = @Content(
                          mediaType = MediaType.APPLICATION_JSON_VALUE,
                          examples = @ExampleObject(value = "{\"status\": 200,\n\"message\": \"Successfully liked\",\n\"data\": null\n}")
                  )),
                  @ApiResponse(responseCode = "404", description = "Document not found", content = @Content(
                          mediaType = MediaType.APPLICATION_JSON_VALUE,
                          examples = @ExampleObject(value = "{\"status\": 404,\n\"message\": \"Document not found\",\n\"data\": null\n}")
                  )),
                  @ApiResponse(responseCode = "500", description = "Error Message", content = @Content(
                          mediaType = MediaType.APPLICATION_JSON_VALUE,
                          examples = @ExampleObject(value = "{\"status\": 500,\n\"message\": \"Error Message\",\n\"data\": null\n}")
                  ))

          }
  )
  @ResponseStatus(HttpStatus.OK)
  @PostMapping("/community/doc/like")
  public BaseApiResponse like(@RequestBody LikesDocumentDTO likesDocumentDTO) {
    return documentService.like(likesDocumentDTO);
  }

  // Document Search
  @Operation(
          summary = "문서 검색",
          description = "문서를 검색합니다.",
          parameters = {
                  @Parameter(name = "title", description = "문서 제목", required = true)
          },
          responses = {
                  @ApiResponse(responseCode = "200", description = "Successfully searched", content = @Content(
                          mediaType = MediaType.APPLICATION_JSON_VALUE,
                          examples = @ExampleObject(value = "{\"status\": 200,\n\"message\": \"Successfully searched\",\n\"data\": null\n}")
                  )),
                  @ApiResponse(responseCode = "500", description = "Error Message", content = @Content(
                          mediaType = MediaType.APPLICATION_JSON_VALUE,
                          examples = @ExampleObject(value = "{\"status\": 500,\n\"message\": \"Error Message\",\n\"data\": null\n}")
                  ))
          }
  )
  @ResponseStatus(HttpStatus.OK)
  @GetMapping("/search/doc/{title}")
  public BaseApiResponse search(@PathVariable String title) {
    return documentService.searchDocument(title);
  }

  // Get Document List
  @Operation(
          summary = "문서 리스트 받아오기",
          description = "문서를 받아옵니다.",
          parameters = {
                  @Parameter(name = "orderBy", description = "createdAt or likes", required = true)
          },
          responses = {
                  @ApiResponse(responseCode = "200", description = "Successfully got", content = @Content(
                          mediaType = MediaType.APPLICATION_JSON_VALUE,
                          examples = @ExampleObject(value = "{\"status\": 200,\n\"message\": \"Successfully got\",\n\"data\": null\n}")
                  )),
                  @ApiResponse(responseCode = "500", description = "Error Message", content = @Content(
                          mediaType = MediaType.APPLICATION_JSON_VALUE,
                          examples = @ExampleObject(value = "{\"status\": 500,\n\"message\": \"Error Message\",\n\"data\": null\n}")
                  ))
          }
  )
  @ResponseStatus(HttpStatus.OK)
  @GetMapping("/community/doclist")
  public BaseApiResponse getDocumentList(String orderby) {
    return documentService.getDocumentList(orderby);
  }

  // Get Document List By User Id
  @Operation(
          summary = "문서 리스트 유저 아이디별로 받아오기",
          description = "문서를 유저 아이디별로 받아옵니다.",
          parameters = {
                  @Parameter(name = "userId", description = "Int", required = true)
          },
          responses = {
                  @ApiResponse(responseCode = "200", description = "Successfully got", content = @Content(
                          mediaType = MediaType.APPLICATION_JSON_VALUE,
                          examples = @ExampleObject(value = "{\"status\": 200,\n\"message\": \"Successfully got\",\n\"data\": null\n}")
                  )),
                  @ApiResponse(responseCode = "500", description = "Error Message", content = @Content(
                          mediaType = MediaType.APPLICATION_JSON_VALUE,
                          examples = @ExampleObject(value = "{\"status\": 500,\n\"message\": \"Error Message\",\n\"data\": null\n}")
                  ))
          }
  )
  @ResponseStatus(HttpStatus.OK)
  @GetMapping("/community/doclist/{id}")
  public BaseApiResponse getDocumentListByUserId(@PathVariable(required = true) Long id) {
    return documentService.getDocumentListByUserId(id);
  }
}
