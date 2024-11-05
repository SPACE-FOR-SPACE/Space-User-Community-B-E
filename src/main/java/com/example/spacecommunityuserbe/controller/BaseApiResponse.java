package com.example.spacecommunityuserbe.controller;

public class BaseApiResponse<T> {
  public int status;
  public String message;
  public T data;

  public BaseApiResponse(int status, String message, T data) {
    this.status = status;
    this.message = message;
    this.data = data;
  }
  public BaseApiResponse(int status, String message) {
    this(status, message, null);
  }
}
