package com.zilin.springtest.entity;

import com.zilin.springtest.Validation.Password;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    private String userId;
    @NotBlank(message = "Username can't be null")
    @Pattern(regexp = "[A-Za-z0-9]+", message = "Username format is incorrect")
    @Size(max = 16, message = "Username format is incorrect")
    private String userName;
    @NotBlank(message = "Password can't be null")
    @Password
    private String userPassword;
    private String nickName;
}
