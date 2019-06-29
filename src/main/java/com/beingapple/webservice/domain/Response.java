package com.beingapple.webservice.domain;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Response {
    private String status;
    private String message;
    private String errorMessage;
    private String errorCode;
}
