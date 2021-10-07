package com.example.web.controller.authority;

import com.example.exception.AuthorityNotFoundException;
import com.example.mapper.authority.AuthorityMapper;
import com.example.mapper.dto.authority.AuthorityDetailsDto;
import com.example.mapper.dto.authority.AuthorityDto;
import com.example.model.user.UserAuthority;
import com.example.service.user.authority.AuthorityService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.net.URI;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/manage/leader/authorities")
public class SuperAdminAuthorityController {

  @Value("${url.base}")
  private String baseUrl;

  private final AuthorityMapper authorityMapper;

  private final AuthorityService authorityService;

  @PostMapping("/authority")
  public ResponseEntity<AuthorityDto> createAuthority(@RequestBody @NotNull AuthorityDto dto) {

    UserAuthority userAuthority = authorityMapper.authorityDtoToUserAuthority(dto);
    UserAuthority storedAuthority = authorityService.saveUserAuthority(userAuthority);
    AuthorityDto response = authorityMapper.userAuthorityToAuthorityDto(storedAuthority);

    return ResponseEntity.created(URI.create(baseUrl + "/manage/leader/authorities"))
        .body(response);
  }

  @PutMapping("/authority")
  public ResponseEntity<AuthorityDto> updateAuthority(
      @RequestParam("updatedRole") @NotNull String role, @RequestBody @NotNull AuthorityDto dto)
      throws AuthorityNotFoundException {

    UserAuthority userAuthority = authorityMapper.authorityDtoToUserAuthority(dto);
    UserAuthority updateAuthority =
        authorityService.updateUserAuthorityByAuthorityName(role, userAuthority);
    AuthorityDto response = authorityMapper.userAuthorityToAuthorityDto(updateAuthority);

    return ResponseEntity.ok(response);
  }

  @DeleteMapping("/authority")
  public ResponseEntity<Void> deleteAuthority(@RequestParam("role") @NotNull String authority) {

    authorityService.deleteUserAuthorityByAuthorityName(authority);

    return ResponseEntity.noContent().build();
  }

  @GetMapping("/authority/info")
  public ResponseEntity<AuthorityDetailsDto> getAuthorityDetails(
      @RequestParam("role") @NotNull String authority) throws AuthorityNotFoundException {

    Long count = authorityService.getUsersCountByRole(authority);
    UserAuthority dbAuthority = authorityService.getUserAuthorityByAuthorityName(authority);

    AuthorityDetailsDto responseDto =
        authorityMapper.userAuthorityToAuthorityDetailsDto(count, dbAuthority);

    return ResponseEntity.ok(responseDto);
  }

  @GetMapping
  public ResponseEntity<Page<AuthorityDto>> getAuthorities(
      @PageableDefault(
              size = 20,
              sort = {"authority"},
              direction = Sort.Direction.ASC)
          Pageable pageable) {

    Page<AuthorityDto> responseAuthorities =
        authorityService.getAuthorities(pageable).map(authorityMapper::userAuthorityToAuthorityDto);

    return ResponseEntity.ok(responseAuthorities);
  }
}
