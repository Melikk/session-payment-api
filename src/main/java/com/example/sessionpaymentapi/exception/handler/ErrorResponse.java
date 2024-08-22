package com.example.sessionpaymentapi.exception.handler;

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
public class ErrorResponse {

    int errorCode;
    String message;

}
