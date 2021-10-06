package com.example.mapper;

import com.example.mapper.dto.user.BaseUserDto;
import com.example.mapper.dto.user.DetailsUserDto;
import com.example.mapper.dto.user.SaveUserDto;
import com.example.mapper.dto.user.admin.AdminDetailsUserDto;
import com.example.model.contactManager.Contact;
import com.example.model.user.User;
import com.example.model.user.UserAuthority;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-10-06T14:36:22+0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.10 (AdoptOpenJDK)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Autowired
    private AuthorityMapper authorityMapper;

    @Override
    public BaseUserDto userToBaseUserDto(User user) {
        if ( user == null ) {
            return null;
        }

        BaseUserDto baseUserDto = new BaseUserDto();

        baseUserDto.setEmail( user.getEmail() );

        return baseUserDto;
    }

    @Override
    public DetailsUserDto userToDetailsUserDto(User user) {
        if ( user == null ) {
            return null;
        }

        DetailsUserDto detailsUserDto = new DetailsUserDto();

        detailsUserDto.setEmail( user.getEmail() );
        detailsUserDto.setEnabled( user.isEnabled() );
        detailsUserDto.setRegistrationDate( user.getRegistrationDate() );

        return detailsUserDto;
    }

    @Override
    public SaveUserDto userToSaveUserDto(Contact contact, User user) {
        if ( contact == null && user == null ) {
            return null;
        }

        SaveUserDto saveUserDto = new SaveUserDto();

        if ( contact != null ) {
            saveUserDto.setFirstName( contact.getFirstName() );
            saveUserDto.setLastName( contact.getLastName() );
            saveUserDto.setJobTitle( contact.getJobTitle() );
            saveUserDto.setCompany( contact.getCompany() );
            saveUserDto.setCountry( contact.getCountry() );
            saveUserDto.setMobilePhone( contact.getMobilePhone() );
        }
        if ( user != null ) {
            saveUserDto.setEmail( user.getEmail() );
            saveUserDto.setPassword( user.getPassword() );
            saveUserDto.setEnabled( user.isEnabled() );
            saveUserDto.setRole( authorityMapper.roleNameToRole( user.getRole() ) );
        }

        return saveUserDto;
    }

    @Override
    public AdminDetailsUserDto userToAdminDetailsUserDto(Contact contact, User user) {
        if ( contact == null && user == null ) {
            return null;
        }

        AdminDetailsUserDto adminDetailsUserDto = new AdminDetailsUserDto();

        if ( contact != null ) {
            adminDetailsUserDto.setFirstName( contact.getFirstName() );
            adminDetailsUserDto.setLastName( contact.getLastName() );
            adminDetailsUserDto.setJobTitle( contact.getJobTitle() );
            adminDetailsUserDto.setCompany( contact.getCompany() );
            adminDetailsUserDto.setCountry( contact.getCountry() );
            adminDetailsUserDto.setMobilePhone( contact.getMobilePhone() );
        }
        if ( user != null ) {
            adminDetailsUserDto.setEmail( user.getEmail() );
            adminDetailsUserDto.setEnabled( user.isEnabled() );
            adminDetailsUserDto.setRegistrationDate( user.getRegistrationDate() );
            adminDetailsUserDto.setRole( userRoleAuthority( user ) );
        }

        return adminDetailsUserDto;
    }

    private String userRoleAuthority(User user) {
        if ( user == null ) {
            return null;
        }
        UserAuthority role = user.getRole();
        if ( role == null ) {
            return null;
        }
        String authority = role.getAuthority();
        if ( authority == null ) {
            return null;
        }
        return authority;
    }
}
