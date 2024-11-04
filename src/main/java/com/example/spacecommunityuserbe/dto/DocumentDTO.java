package com.example.spacecommunityuserbe.dto;

import com.example.spacecommunityuserbe.entity.DocumentIcon;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DocumentDTO {
  private Long id;
  private Long userId;
  private String title;
  private String content;
  private DocumentIcon icon;
  private String category;
  private Long likes = 0L;
  private LocalDateTime createdAt;
}
