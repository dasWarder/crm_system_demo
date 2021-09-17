package com.example.mapper.dto.user.token;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResetTokenDto {

  @NotBlank(message = "The token value must be not null or empty")
  private String resetToken;
}
