package com.example.mapper.dto.user.token;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TokenRefreshRequest {

  @NotNull(message = "The field is mandatory")
  private String refreshToken;
}
