package com.example.web.controller.viewExceptionHandling.violation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Violation {

    private String fieldName;

    private String message;
}
