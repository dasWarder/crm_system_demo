package com.example.mapper;

import com.example.mapper.dto.user.token.TokenDto;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-09-09T15:39:33+0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.10 (AdoptOpenJDK)"
)
@Component
public class TokenMapperImpl implements TokenMapper {

    @Override
    public TokenDto fromStringToToken(String authToken) {
        if ( authToken == null ) {
            return null;
        }

        TokenDto tokenDto = new TokenDto();

        tokenDto.setAuthToken( authToken );

        return tokenDto;
    }

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