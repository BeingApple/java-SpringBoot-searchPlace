package com.beingapple.webservice.web;

import com.beingapple.webservice.domain.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class CommonController {
    protected ResponseEntity<Object> returnResponseObjectWithHttpStatus(HttpStatus status, String message, String errorMessage, String errorCode){
        Response response = new Response(
                status.toString(),
                message,
                errorMessage,
                errorCode);

        return new ResponseEntity<>(response, status);
    }
}
