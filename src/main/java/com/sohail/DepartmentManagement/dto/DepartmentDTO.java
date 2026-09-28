package com.sohail.DepartmentManagement.dto;

import com.sohail.DepartmentManagement.validation.OnlyUpperCase;
import lombok.*;

import java.time.LocalDateTime;
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DepartmentDTO{

    private Long id;

    @OnlyUpperCase(message = "Please enter the title in only upper case letter!")
    private String title;

    private Boolean isActive;

    private LocalDateTime createdAt;
}
