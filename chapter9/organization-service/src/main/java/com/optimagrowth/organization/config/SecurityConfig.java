package com.optimagrowth.organization.config;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(jsr250Enabled = true, prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        JwtAuthenticationConverter jwtAuthConverter = new JwtAuthenticationConverter();
        jwtAuthConverter.setJwtGrantedAuthoritiesConverter(keycloakGrantedAuthoritiesConverter());

        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/actuator/health").permitAll()
                .anyRequest().authenticated()
                .and()
                .oauth2ResourceServer()
                .jwt()
                .jwtAuthenticationConverter(jwtAuthConverter);
    }

    private Converter<Jwt, Collection<GrantedAuthority>> keycloakGrantedAuthoritiesConverter() {
        return jwt -> {
            Collection<String> realmRoles = Optional.ofNullable(jwt.getClaimAsMap("realm_access"))
                    .map(m -> (Collection<String>) m.get("roles"))
                    .orElse(Collections.emptyList());

            Map<String, Object> resourceAccess = (Map<String, Object>) jwt.getClaims().get("resource_access");
            Stream<String> clientRoles = Stream.empty();
            if (resourceAccess != null) {
                clientRoles = resourceAccess.values().stream()
                        .filter(Map.class::isInstance)
                        .map(Map.class::cast)
                        .map(m -> (Collection<String>) m.getOrDefault("roles", Collections.emptyList()))
                        .flatMap(Collection::stream);
            }

            return Stream.<String>concat(realmRoles.stream(), clientRoles)
                    .distinct()
                    .map(r -> "ROLE_" + r.toUpperCase(Locale.ROOT))
                    .map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toList());
        };
    }
}
