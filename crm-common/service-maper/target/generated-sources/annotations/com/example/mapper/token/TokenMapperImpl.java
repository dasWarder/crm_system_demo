package com.example.mapper.token;

import com.example.mapper.dto.user.token.TokenDto;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-10-06T15:42:06+0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.10 (AdoptOpenJDK)"
)
@Component
public class TokenMapperImpl implements TokenMapper {

    @Override
    public TokenDto fromStringsToToken(String authToken, String refreshToken) {
        if ( authToken == null && refreshToken == null ) {
            return null;
        }

        TokenDto tokenDto = new TokenDto();

        if ( authToken != null ) {
            tokenDto.setAuthToken( authToken );
        }
        if ( refreshToken != null ) {
            tokenDto.setRefreshToken( refreshToken );
        }

        return tokenDto;
    }
}
