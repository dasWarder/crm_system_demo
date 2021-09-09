package com.example.mapper.dto.contact;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SaveContactDto extends ContactDto {

    private String country;

    private String mobilePhone;
}
