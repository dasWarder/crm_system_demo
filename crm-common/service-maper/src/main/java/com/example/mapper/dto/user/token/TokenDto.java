package com.example.mapper.dto.user.token;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TokenDto {

  @NotNull
  private String authToken;

  @NotNull
  private String refreshToken;
}
