package com.example.demo.model.dto;


import lombok.Data;

@Data
public class SavingDto {
    public String saveResult;

    public String error;

    public boolean isValid;
}
