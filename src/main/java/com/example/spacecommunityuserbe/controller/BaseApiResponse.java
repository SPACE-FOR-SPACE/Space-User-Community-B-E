package com.example.spacecommunityuserbe.controller;

public class BaseApiResponse {
  public int status;
  public String message;

  public BaseApiResponse(int status, String message) {
    this.status = status;
    this.message = message;
  }
}
