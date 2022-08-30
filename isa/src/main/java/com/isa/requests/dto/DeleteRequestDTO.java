package com.isa.requests.dto;

public class DeleteRequestDTO {

    private String text;
    private Long userId;
    private String email;

    public DeleteRequestDTO(){}

    public DeleteRequestDTO(String text, Long userId, String email) {
        this.text = text;
        this.userId = userId;
        this.email = email;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
