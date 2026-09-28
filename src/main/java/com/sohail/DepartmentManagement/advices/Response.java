package com.sohail.DepartmentManagement.advices;

import lombok.*;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Response <T> {

    private T data;

    private Error error;

    private LocalDateTime timeStamp;
}
