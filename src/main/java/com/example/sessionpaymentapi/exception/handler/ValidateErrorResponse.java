package com.example.sessionpaymentapi.exception.handler;

import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

import java.util.Map;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ValidateErrorResponse {

    int errorCode;
    String message;
    Map<String, String> fields;

}
