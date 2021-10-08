package com.example.web.config.security;

import com.example.web.config.security.token.JwtFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

  private final UserDetailsService userDetails;

  private final UserJwtAuthEntryPoint authEntryPoint;

  private final PasswordEncoder encoder;

  private final JwtFilter jwtFilter;

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {

    auth.userDetailsService(userDetails).passwordEncoder(encoder);
  }

  @Bean
  @Override
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.csrf()
        .disable()
        .authorizeRequests()
        .antMatchers("/")
        .permitAll()
        .antMatchers("/login/**")
        .permitAll()
        .and()
        .authorizeRequests()
        .antMatchers("/manage/**")
        .authenticated()
        .and()
        .authorizeRequests()
        .antMatchers("/manage/manager/**")
        .hasAuthority("MANAGER")
        .and()
        .authorizeRequests()
        .antMatchers("/manage/admin/**")
        .hasAuthority("ADMIN")
        .and()
        .authorizeRequests()
        .antMatchers("/manage/leader/**")
        .hasAuthority("SUPER_ADMIN")
        .and()
        .exceptionHandling()
        .authenticationEntryPoint(authEntryPoint)
        .and()
        .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
  }
}
