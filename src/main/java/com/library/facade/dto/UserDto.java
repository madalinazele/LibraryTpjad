package com.library.facade.dto;
import com.library.facade.validator.PasswordMatches;
import com.library.facade.validator.ValidEmail;
import com.library.facade.validator.ValidPassword;
import com.library.facade.validator.ValidPhoneNumber;
import lombok.Data;

import javax.validation.constraints.*;

@Data
@PasswordMatches
public class UserDto {

    @NotBlank(message = "Please input name!")
    @Size(min = 2, max = 200)
    private String name;

    @NotBlank(message = "Please input email!")
    @ValidEmail
    private String email;

    @NotBlank(message = "Please input password!")
    @ValidPassword
    private String password;
    private String matchingPassword;

    @NotBlank(message = "Please input address!")
    private String address;

    @NotBlank(message = "Please input phone number!")
    @ValidPhoneNumber
    private String phoneNo;

}
