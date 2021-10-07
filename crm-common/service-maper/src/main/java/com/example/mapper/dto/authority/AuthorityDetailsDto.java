package com.example.mapper.dto.authority;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthorityDetailsDto {

    private String authority;

    private Long users;
}
