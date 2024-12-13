package com.example.demo.obj;


import com.example.demo.annotation.DuplicateMailAddress;
import com.example.demo.annotation.DuplicateUserId;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class RegisterInputsObj {
    @Length(min=2, max = 255, message = "First Name must be between 2 - 255 characters")
    @NotNull(message = "First Name must not be empty")
    private String firstName;

    @Length(min=2, max = 255, message = "Last Name must be between 2- 255 characters")
    @NotNull(message = "Last Name must not be empty")
    private String lastName;

    @Pattern(regexp = "^[A-Za-z][A-Za-z0-9_]{7,30}$",message = "Not a valid Username format")
    @Length(min =7, max = 30, message = "Username must be between 7 - 30 characters")
    @DuplicateUserId
    @NotNull(message = "Username must not be empty")
    private String username;

    @Pattern(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$",message = "Not a valid email")
    @Length(min = 10, max = 30, message = "Email must be between 10 - 30 characters")
    @DuplicateMailAddress
    @NotNull(message = "Email must not be empty")
    private String email;

    @Pattern(regexp = "^[A-Za-z][A-Za-z0-9_]{7,15}$",message = "Not a valid password")
    @Length(min = 7, max = 15, message = "Password must be between 7 - 15 characters")
    @NotNull(message = "Password must not be empty")
    private String password;

    private boolean isUpdate;

}
