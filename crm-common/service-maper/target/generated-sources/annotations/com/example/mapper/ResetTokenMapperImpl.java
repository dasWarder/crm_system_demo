package com.example.mapper;

import com.example.mapper.dto.user.token.ResetTokenDto;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
<<<<<<< HEAD
    date = "2021-09-30T16:40:14+0300",
=======
    date = "2021-09-29T17:39:40+0300",
>>>>>>> 1ee3f8c527335e208121d478ad3785854e7263b7
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 16.0.1 (Oracle Corporation)"
)
@Component
public class ResetTokenMapperImpl implements ResetTokenMapper {

    @Override
    public ResetTokenDto stringToResetTokenDto(String resetToken) {
        if ( resetToken == null ) {
            return null;
        }

        ResetTokenDto resetTokenDto = new ResetTokenDto();

        resetTokenDto.setResetToken( resetToken );

        return resetTokenDto;
    }
}
