package com.sohail.DepartmentManagement.advices;

import lombok.*;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Error {

    private HttpStatus status;

    private LocalDateTime timeStamp;

    List<String> subErrors = new ArrayList<>();
}
