package com.example.demo.model.dto;


import com.example.demo.request.RequestAuthentication;
import com.example.demo.response.ResponseAuthentication;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CombinedResponseDto {
    public RequestAuthentication requestAuthentication;
    public ResponseAuthentication responseAuthentication;

}
