package com.example.mapper.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetailsUserDto {

  private String email;

  private boolean enabled;

  private LocalDate registrationDate;
}
