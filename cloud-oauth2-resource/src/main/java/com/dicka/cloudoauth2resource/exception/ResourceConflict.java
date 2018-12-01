package com.dicka.cloudoauth2resource.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class ResourceConflict extends RuntimeException {

    public ResourceConflict(String msg, Throwable cause){
        super(msg, cause);
    }

    public ResourceConflict(String msg){
        super(msg);
    }
}
