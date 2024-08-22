package com.example.sessionpaymentapi.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthRequest {

    @NotBlank
    String username;

    @NotBlank
    String password;

}
