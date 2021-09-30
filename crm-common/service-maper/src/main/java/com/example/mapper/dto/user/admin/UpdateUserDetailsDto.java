package com.example.mapper.dto.user.admin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserDetailsDto {

    private String email;

    private boolean enabled;
}
