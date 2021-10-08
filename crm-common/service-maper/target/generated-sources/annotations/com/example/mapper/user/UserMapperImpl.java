package com.example.mapper.user;

import com.example.mapper.authority.AuthorityMapper;
import com.example.mapper.dto.user.BaseUserDto;
import com.example.mapper.dto.user.DetailsUserDto;
import com.example.mapper.dto.user.SaveUserDto;
import com.example.mapper.dto.user.admin.AdminDetailsUserDto;
import com.example.mapper.dto.user.admin.CreateUserDto;
import com.example.mapper.dto.user.superadmin.CreateFullUserDto;
import com.example.mapper.dto.user.superadmin.SuperAdminUserDetailsDto;
import com.example.model.contactManager.Contact;
import com.example.model.report.Report;
import com.example.model.user.User;
import com.example.model.user.UserAuthority;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-10-08T16:46:15+0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 16.0.1 (Oracle Corporation)"
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
    public CreateUserDto userToCreateUserDto(Contact contact, User user) {
        if ( contact == null && user == null ) {
            return null;
        }

        CreateUserDto createUserDto = new CreateUserDto();

        if ( contact != null ) {
            createUserDto.setFirstName( contact.getFirstName() );
            createUserDto.setLastName( contact.getLastName() );
            createUserDto.setJobTitle( contact.getJobTitle() );
            createUserDto.setCompany( contact.getCompany() );
            createUserDto.setCountry( contact.getCountry() );
            createUserDto.setMobilePhone( contact.getMobilePhone() );
        }
        if ( user != null ) {
            createUserDto.setEmail( user.getEmail() );
            createUserDto.setRole( userRoleAuthority( user ) );
            createUserDto.setEnabled( user.isEnabled() );
        }

        return createUserDto;
    }

    @Override
    public AdminDetailsUserDto userToAdminDetailsUserDto(User user) {
        if ( user == null ) {
            return null;
        }

        AdminDetailsUserDto adminDetailsUserDto = new AdminDetailsUserDto();

        adminDetailsUserDto.setFirstName( userContactFirstName( user ) );
        adminDetailsUserDto.setLastName( userContactLastName( user ) );
        adminDetailsUserDto.setJobTitle( userContactJobTitle( user ) );
        adminDetailsUserDto.setCompany( userContactCompany( user ) );
        adminDetailsUserDto.setCountry( userContactCountry( user ) );
        adminDetailsUserDto.setMobilePhone( userContactMobilePhone( user ) );
        adminDetailsUserDto.setEnabled( user.isEnabled() );
        adminDetailsUserDto.setRole( userRoleAuthority( user ) );
        adminDetailsUserDto.setEmail( user.getEmail() );
        adminDetailsUserDto.setRegistrationDate( user.getRegistrationDate() );

        return adminDetailsUserDto;
    }

    @Override
    public SuperAdminUserDetailsDto userToSuperAdminUserDetailsDto(User user) {
        if ( user == null ) {
            return null;
        }

        SuperAdminUserDetailsDto superAdminUserDetailsDto = new SuperAdminUserDetailsDto();

        superAdminUserDetailsDto.setFirstName( userContactFirstName( user ) );
        superAdminUserDetailsDto.setLastName( userContactLastName( user ) );
        superAdminUserDetailsDto.setJobTitle( userContactJobTitle( user ) );
        superAdminUserDetailsDto.setCompany( userContactCompany( user ) );
        superAdminUserDetailsDto.setCountry( userContactCountry( user ) );
        superAdminUserDetailsDto.setMobilePhone( userContactMobilePhone( user ) );
        superAdminUserDetailsDto.setEmail( user.getEmail() );
        superAdminUserDetailsDto.setEnabled( user.isEnabled() );
        superAdminUserDetailsDto.setRegistrationDate( user.getRegistrationDate() );
        superAdminUserDetailsDto.setRole( authorityMapper.roleNameToRole( user.getRole() ) );
        superAdminUserDetailsDto.setId( user.getId() );
        List<Report> list = user.getReports();
        if ( list != null ) {
            superAdminUserDetailsDto.setReports( new ArrayList<Report>( list ) );
        }

        return superAdminUserDetailsDto;
    }

    @Override
    public CreateFullUserDto userToCreateFullUserDto(User user) {
        if ( user == null ) {
            return null;
        }

        CreateFullUserDto createFullUserDto = new CreateFullUserDto();

        createFullUserDto.setFirstName( userContactFirstName( user ) );
        createFullUserDto.setLastName( userContactLastName( user ) );
        createFullUserDto.setJobTitle( userContactJobTitle( user ) );
        createFullUserDto.setCompany( userContactCompany( user ) );
        createFullUserDto.setCountry( userContactCountry( user ) );
        createFullUserDto.setMobilePhone( userContactMobilePhone( user ) );
        createFullUserDto.setRole( userRoleAuthority( user ) );
        createFullUserDto.setEmail( user.getEmail() );
        createFullUserDto.setEnabled( user.isEnabled() );
        createFullUserDto.setPassword( user.getPassword() );
        createFullUserDto.setRegistrationDate( user.getRegistrationDate() );

        return createFullUserDto;
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

    private String userContactFirstName(User user) {
        if ( user == null ) {
            return null;
        }
        Contact contact = user.getContact();
        if ( contact == null ) {
            return null;
        }
        String firstName = contact.getFirstName();
        if ( firstName == null ) {
            return null;
        }
        return firstName;
    }

    private String userContactLastName(User user) {
        if ( user == null ) {
            return null;
        }
        Contact contact = user.getContact();
        if ( contact == null ) {
            return null;
        }
        String lastName = contact.getLastName();
        if ( lastName == null ) {
            return null;
        }
        return lastName;
    }

    private String userContactJobTitle(User user) {
        if ( user == null ) {
            return null;
        }
        Contact contact = user.getContact();
        if ( contact == null ) {
            return null;
        }
        String jobTitle = contact.getJobTitle();
        if ( jobTitle == null ) {
            return null;
        }
        return jobTitle;
    }

    private String userContactCompany(User user) {
        if ( user == null ) {
            return null;
        }
        Contact contact = user.getContact();
        if ( contact == null ) {
            return null;
        }
        String company = contact.getCompany();
        if ( company == null ) {
            return null;
        }
        return company;
    }

    private String userContactCountry(User user) {
        if ( user == null ) {
            return null;
        }
        Contact contact = user.getContact();
        if ( contact == null ) {
            return null;
        }
        String country = contact.getCountry();
        if ( country == null ) {
            return null;
        }
        return country;
    }

    private String userContactMobilePhone(User user) {
        if ( user == null ) {
            return null;
        }
        Contact contact = user.getContact();
        if ( contact == null ) {
            return null;
        }
        String mobilePhone = contact.getMobilePhone();
        if ( mobilePhone == null ) {
            return null;
        }
        return mobilePhone;
    }
}
