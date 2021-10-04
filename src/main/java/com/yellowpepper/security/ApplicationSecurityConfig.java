package com.yellowpepper.security;

import com.yellowpepper.security.auth.service.ApplicationUserService;
import com.yellowpepper.security.jwt.JwtConfig;
import com.yellowpepper.security.jwt.JwtTokenVerifier;
import com.yellowpepper.security.jwt.JwtUsernameAndPasswordAuthenticationFilter;
import javax.crypto.SecretKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

  private final PasswordEncoder passwordEncoder;
  private final ApplicationUserService applicationUserService;
  private final JwtConfig jwtConfig;
  private final SecretKey secretKey;

  @Autowired
  public ApplicationSecurityConfig(PasswordEncoder passwordEncoder,
      ApplicationUserService applicationUserService,
      JwtConfig jwtConfig,
      SecretKey secretKey) {
    this.passwordEncoder = passwordEncoder;
    this.applicationUserService = applicationUserService;
    this.jwtConfig = jwtConfig;
    this.secretKey = secretKey;
  }

  @Override
  public void configure(WebSecurity web) throws Exception {
    web.ignoring()
        .antMatchers("/swagger-ui/**", "/swagger-ui.html", "/v3/**", "/h2-console/**");
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
        .csrf().disable()
        .sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        .addFilter(
            new JwtUsernameAndPasswordAuthenticationFilter(authenticationManager(), jwtConfig,
                secretKey))
        .addFilterAfter(new JwtTokenVerifier(jwtConfig, secretKey),
            JwtUsernameAndPasswordAuthenticationFilter.class)
        .authorizeRequests()
        .anyRequest()
        .authenticated();

  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.authenticationProvider(daoAuthenticationProvider());
  }

  @Bean
  public DaoAuthenticationProvider daoAuthenticationProvider() {
    DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
    provider.setPasswordEncoder(passwordEncoder);
    provider.setUserDetailsService(applicationUserService);
    return provider;
  }
}
