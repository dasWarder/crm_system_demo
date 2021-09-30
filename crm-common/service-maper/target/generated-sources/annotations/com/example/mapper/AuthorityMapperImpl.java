package com.example.mapper;

import com.example.model.user.UserAuthority;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-09-30T16:40:14+0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 16.0.1 (Oracle Corporation)"
)
@Component
public class AuthorityMapperImpl implements AuthorityMapper {

    @Override
    public String roleNameToRole(UserAuthority authority) {
        if ( authority == null ) {
            return null;
        }

        String string = new String();

        return string;
    }
}
