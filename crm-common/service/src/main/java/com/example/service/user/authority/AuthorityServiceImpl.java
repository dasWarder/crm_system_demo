package com.example.service.user.authority;

import com.example.exception.AuthorityNotFoundException;
import com.example.repository.AuthorityRepository;
import com.example.model.user.UserAuthority;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Slf4j
@Service
@RequiredArgsConstructor
public class AuthorityServiceImpl implements AuthorityService {

    private final AuthorityRepository authorityRepository;

    @Override
    @Transactional
    public UserAuthority saveUserAuthority(UserAuthority authority) {

        log.info("Store a new user authority");
        UserAuthority storedAuthority = authorityRepository.save(authority);

        return storedAuthority;
    }

    @Override
    @Transactional
    public UserAuthority updateUserAuthorityByAuthorityName(String authority, UserAuthority updateAuthority) throws AuthorityNotFoundException {

        log.info("Update user authority with a name = {}", authority);
        UserAuthority dbAuthority = authorityRepository.getUserAuthorityByAuthority(authority)
                                            .orElseThrow(() -> new AuthorityNotFoundException(
                                                    String.format("The authority with a name %s not found", authority)));
        updateAuthority.setId(dbAuthority.getId());
        UserAuthority storedAuthority = authorityRepository.save(updateAuthority);

        return storedAuthority;
    }

    @Override
    @Transactional(readOnly = true)
    public UserAuthority getUserAuthorityByAuthorityName(String authority) throws AuthorityNotFoundException {

        log.info("Get authority by an authority name = {}", authority);
        UserAuthority userAuthorityByAuthorityName = authorityRepository.getUserAuthorityByAuthority(authority)
                                                                .orElseThrow(() -> new AuthorityNotFoundException(
                                                                        String.format("The authority with a name %s not found")));
        return userAuthorityByAuthorityName;
    }

    @Override
    @Transactional
    public void deleteUserAuthorityByAuthorityName(String authority) {

        log.info("Delete user authority by a name = {}", authority);
        authorityRepository.deleteUserAuthorityByAuthority(authority);
    }
}
